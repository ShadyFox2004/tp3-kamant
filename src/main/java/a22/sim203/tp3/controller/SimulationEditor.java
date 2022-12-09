package a22.sim203.tp3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import a22.sim203.tp3.factory.SimulationCellFactory;
import a22.sim203.tp3.factory.StateCellFactory;
import a22.sim203.tp3.factory.VariableCellFactory;
import a22.sim203.tp3.simulation.Equation;
import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.simulation.Variable;
import a22.sim203.tp3.utils.DialogUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Sample Skeleton for 'SimulationEditor.fxml' Controller Class
 */
public class SimulationEditor extends HBox {
    /**
     * Creates a SimulationEditor object
     */
    public SimulationEditor() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/SimulationEditor.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="equationList"
    private ListView<Equation> equationList; // Value injected by FXMLLoader

    @FXML // fx:id="simulationList"
    private ListView<Simulation> simulationList; // Value injected by FXMLLoader

    @FXML // fx:id="variableList"
    private ListView<Variable> variableList; // Value injected by FXMLLoader

    @FXML // fx:id="stateList"
    private ListView<State> stateList; // Value injected by FXMLLoader

    /**
     * Pointer to the history controller to request and set history
     */
    private State state;

    /**
     * Pointer to the simulation graph display
     */
    private Simulator simulator;
    /**
     * Pointer to the simulation 2d display
     */
    private View2D view2D;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert equationList != null : "fx:id=\"equationList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";
        assert simulationList != null : "fx:id=\"simulationList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";
        assert variableList != null : "fx:id=\"variableList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";

        simulationList.setItems(FXCollections.observableList(new ArrayList<>()));

        Simulation simulation = new Simulation();
        Map<String, Variable> variables = new HashMap<>();
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        simulation.addInHistory(new State(variables));

        variableList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        equationList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        simulationList.getItems().add(simulation);

        simulationList.setCellFactory(new SimulationCellFactory());
        simulationList.setOnMousePressed(event -> {
            onSelectSimulation();
        });

        MenuItem addVariable = new MenuItem("add");
        addVariable.setOnAction(event -> {
            addVariables(DialogUtils.createVariableDialogue());
        });
        MenuItem removeVariable = new MenuItem("remove");
        removeVariable.setOnAction(event -> {
            removeVariables(variableList.getSelectionModel().getSelectedItems());
            variableList.refresh();
        });

        MenuItem addEquation = new MenuItem("add");
        MenuItem removeEquation = new MenuItem("remove");

        addEquation.setOnAction(event -> {
            Equation newEquation = DialogUtils.createEquationDialogue();
            variableList.getSelectionModel().getSelectedItem().addEquation(newEquation);
            update();
        });

        removeEquation.setOnAction(event -> {
            Collection<Equation> equations =  equationList.getSelectionModel().getSelectedItems();
            variableList.getSelectionModel().getSelectedItem().getEquationsList().removeAll(equations);
            update();
        });

        equationList.setContextMenu(new ContextMenu(addEquation, removeEquation));
        variableList.setContextMenu(new ContextMenu(addVariable, removeVariable));
        variableList.setCellFactory(new VariableCellFactory());
        variableList.setOnMousePressed(event -> {
            List<Equation> equations = variableList.getSelectionModel().getSelectedItem().getEquationsList();
            update();
            simulator.setTrackedVariables(getSelectedVariables());
            view2D.setTrackedVariables(getSelectedVariables());
        });
    }

    private void update() {
        equationList.setItems(FXCollections.observableList(variableList.getSelectionModel().getSelectedItem().getEquationsList()));
    }

    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }

    public void setView2D(View2D view2D) {
        this.view2D = view2D;
    }

    /**
     * Adds a simulation to the editor view
     * @param simulation Simulation to be added
     */
    public void addSimulation(Simulation simulation){
        simulationList.getItems().add(simulation);
        simulationList.getSelectionModel().select(simulation);
    }

    /**
     * Returns the selected simulation
     */
    public Simulation getSimulation() {
        return (simulationList.getSelectionModel().getSelectedItem());
    }

    /**
     * Returns the simulation with a specific name
     */
    public Simulation getSimulation(String name) {
        // TODO implement when we have time
        return null;
    }

    /**
     * Returns an array of the selected variables
     * @return an array of the selected variables
     */
    public String[] getSelectedVariables(){
        Object[] rawVariables = variableList.getSelectionModel().getSelectedItems().toArray();
        String[] variables = new String[rawVariables.length];
        for (int i = 0; i < rawVariables.length; i++) {
            variables[i] = ((Variable)rawVariables[i]).getName();
        }
        return variables;
    }

    public void onSelectSimulation() {
        variableList.getItems().clear();
        variableList.getItems().addAll(getState().getVariableMap().values());
        equationList.getItems().clear();
    }

    public State getState() {
        return getSimulation().getLastState();
    }

    /**
     * Adds a new variable in the state
     * @param newVariable
     */
    public void addVariables(Variable...newVariable) {
        if(newVariable == null) return;
        variableList.getItems().addAll(newVariable);
        getState().addVariables(newVariable);
    }

    /**
     * Remove a variable
     * Won't remove a variable that is important
     * @param variables to remove
     */
    public void removeVariables(Collection<Variable> variables) {
        variables = variables.stream().filter(variable -> {
            return !variable.getName().matches("(t)|(dt)|(STOP)");
        }).toList(); //Filters the vars for important ones

        variableList.getItems().removeAll(variables);
        getState().removeVariables(variables);
    }
}