package a22.sim203.tp3.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import a22.sim203.tp3.factory.SimulationCellFactory;
import a22.sim203.tp3.factory.StateCellFactory;
import a22.sim203.tp3.factory.VariableCellFactory;
import a22.sim203.tp3.simulation.Equation;
import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.simulation.Variable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
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
    //private History history;

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
        variables.put("a", new Variable("a", 5));
        variables.put("b", new Variable("b", Double.MAX_VALUE));
        variables.put("c", new Variable("c", Double.MIN_VALUE));
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        Variable variableWithEquations = new Variable("d", 2);
        variableWithEquations.addEquation(new Equation("double", "f(t)=t*2"));
        variables.put("d", variableWithEquations);
        simulation.addInHistory(new State(variables));
        HashMap<String, Variable> variables2 = new HashMap<>(variables);
        variables2.remove("a");
        simulation.addInHistory(new State(variables2));
        simulation.setName("defaultName");

        simulationList.getItems().add(simulation);

        simulationList.setCellFactory(new SimulationCellFactory());
        simulationList.setOnMousePressed(event -> {
            stateList.setItems(FXCollections.observableList(getSimulation().getHistory()));
        });

        stateList.setCellFactory(new StateCellFactory());
        stateList.setOnMousePressed(event -> {
            List<Variable> variables3 = stateList.getSelectionModel().getSelectedItem().getCollectionVariable().stream().toList();
            variableList.setItems(FXCollections.observableList(variables3));
        });

        variableList.setCellFactory(new VariableCellFactory());
        variableList.setOnMousePressed(event -> {
            List<Equation> equations = variableList.getSelectionModel().getSelectedItem().getEquationsList();
            equationList.setItems(FXCollections.observableList(equations));
            simulator.setTrackedVariables(getSelectedVariables());
            view2D.setTrackedVariables(getSelectedVariables());
        });
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
}
