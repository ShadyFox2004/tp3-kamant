package a22.sim203.tp3.controller;
import java.io.IOException;
import java.util.*;

import a22.sim203.tp3.factory.SimulationCellFactory;
import a22.sim203.tp3.factory.VariableCellFactory;
import a22.sim203.tp3.simulation.Equation;
import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.simulation.Variable;
import a22.sim203.tp3.utils.DialogUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import static a22.sim203.tp3.utils.DialogUtils.askUserDialogue;

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

    @FXML // fx:id="equationList"
    private ListView<Equation> equationList; // Value injected by FXMLLoader

    @FXML // fx:id="simulationList"
    private ListView<Simulation> simulationList; // Value injected by FXMLLoader

    @FXML // fx:id="variableList"
    private ListView<Variable> variableList; // Value injected by FXMLLoader

    /**
     * Pointer to the simulation graph display
     */
    private Simulator simulator;
    /**
     * Pointer to the simulation 2d display
     */
    private View2D view2D;

    /**
     * Adds a new simulation
     */
    public void editSimulationName() {
        simulationList.getSelectionModel().getSelectedItem();
        simulationList.getSelectionModel().getSelectedItem().setName(
                askUserDialogue("new name ",
                        "Name change for simulation",
                        simulationList.getSelectionModel().getSelectedItem().getName()));
    }



    @FXML
    public void addSimulation() {
        Simulation simulation = newSimulationTemplate();
        simulationList.getItems().add(simulation);
        simulationList.getSelectionModel().select(simulation);
    }

    /**
     * Removes the selected simulation
     * And select another one
     */

    @FXML
    public void removeSimulation() {
        Simulation simulationToRemove = simulationList.getSelectionModel().getSelectedItem();
        if (simulationToRemove != null) {
            int index = simulationList.getItems().indexOf(simulationToRemove);
            if (index+1 < simulationList.getItems().size()) {
                // select another one
                simulationList.getSelectionModel().selectNext();
            } else if (index-1 >= 0) {
                // this is the last item so select the previous
                simulationList.getSelectionModel().selectPrevious();
            } else {
                // nothing to be able to edit
                variableList.getItems().clear();
                equationList.getItems().clear();
            }

            simulationList.getItems().remove(simulationToRemove);
        }
        updateVariable();
    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        doConfigureSimulationList();
        doConfigureVariableList();
        doConfigureEquationList();
    }

    private void doConfigureSimulationList() {
        simulationList.setItems(FXCollections.observableList(new ArrayList<>()));
        simulationList.setCellFactory(new SimulationCellFactory());
        simulationList.setOnMousePressed(event -> updateVariable());
    }

    /**
     * Create a simulation with the configured template
     * @return the new simulation
     */
    private static Simulation newSimulationTemplate() {
        Simulation simulation = new Simulation();
        Map<String, Variable> variables = new HashMap<>();
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        simulation.addInHistory(new State(variables));
        return simulation;
    }

    /**
     * sets up variable list
     */
    private void doConfigureEquationList() {
        equationList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        MenuItem addEquation = new MenuItem("add");
        MenuItem removeEquation = new MenuItem("remove");
        MenuItem editEquation = new MenuItem("edit");

        addEquation.setOnAction(event -> {
            Equation newEquation = DialogUtils.createEquationDialogue();
            variableList.getSelectionModel().getSelectedItem().addEquation(newEquation);
            updateEquation();
        });

        removeEquation.setOnAction(event -> {
            Collection<Equation> equations =  equationList.getSelectionModel().getSelectedItems();
            variableList.getSelectionModel().getSelectedItem().getEquationsList().removeAll(equations);
            updateEquation();
        });

        editEquation.setOnAction(event -> {
            String name = equationList.getSelectionModel().getSelectedItem().getExpression();
            String newName = askUserDialogue("new expression", "Equation expression editing", name);
            if(newName != null) {
                equationList.getSelectionModel().getSelectedItem().setName(newName);
            }
        });

        equationList.setContextMenu(new ContextMenu(addEquation, editEquation, removeEquation));
        equationList.setDisable(true);
    }

    /**
     * sets up equation list
     */
    private void doConfigureVariableList() {
        variableList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        MenuItem addVariable = new MenuItem("add");
        addVariable.setOnAction(event -> addVariables(DialogUtils.createVariableDialogue()));
        MenuItem removeVariable = new MenuItem("remove");
        removeVariable.setOnAction(event -> {
            removeVariables(variableList.getSelectionModel().getSelectedItems());
            variableList.refresh();
        });
        MenuItem editVariable =  new MenuItem("edit");

        editVariable.setOnAction(event -> {
            filterCritical(Collections.singleton(variableList.getSelectionModel().getSelectedItem())).forEach(variable -> {
                String name = variable.getName();
                String newName = askUserDialogue("Editing the variable name", "Variable name editing", name);
                if(newName != null) {
                    variable.setName(newName);
                }
            });
        });

        variableList.setContextMenu(new ContextMenu(addVariable, editVariable, removeVariable));
        variableList.setCellFactory(new VariableCellFactory());
        variableList.setDisable(true);

        //EVENTS
        variableList.setOnMousePressed(event -> {
            updateEquation();
            simulator.setTrackedVariables(getSelectedVariables());
            view2D.setTrackedVariables(getSelectedVariables());
        });
    }

    /**
     * Updates the graphics for the equationsList
     * Gets disabled when no variable are selected
     */
    private void updateEquation() {
        equationList.getItems().clear();
        Variable selectedVariable =  variableList.getSelectionModel().getSelectedItem();

        if (selectedVariable != null) {
            List<Equation> newEquations = selectedVariable.getEquationsList();
            equationList.getItems().addAll(newEquations);
        }

        equationList.setDisable(selectedVariable == null); // Sets the equations to be disabled when nothing is selected
    }

    /**
     * Updates the graphics for the variableList
     * Gets disabled when no simulation is selected
     */
    private void updateVariable() {
        variableList.getItems().clear();
        Simulation selectedSimulation = simulationList.getSelectionModel().getSelectedItem();

        if (selectedSimulation != null) {
            variableList.getItems().addAll(getState().getVariableMap().values());
        }

        variableList.setDisable(selectedSimulation == null);
        updateEquation();
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

    public State getState() {
        return getSimulation().getLastState();
    }

    /**
     * Adds a new variable in the state
     * @param newVariable
     */
    public void addVariables(Variable...newVariable) {
        if(newVariable != null) {
            variableList.getItems().addAll(newVariable);
            getState().addVariables(newVariable);
        }
    }

    /**
     * Remove a variable
     * Won't remove a variable that is important
     * @param variables to remove
     */
    public void removeVariables(Collection<Variable> variables) {
        if (variables != null) {
            variables = filterCritical(variables);

            variableList.getItems().removeAll(variables);
            getState().removeVariables(variables);
        }
    }

    /**
     * Updates the editor when a new frame is generated
     * @param steps the number of steps simulated
     */
    public void update(int steps){
        simulationList.refresh();
        getSimulation().setSimulatedSteps(steps);
    }

    /**
     * Remove the critical variable from the selection
     * @param variables collection to filter
     * @return filtered collection
     */
    public Collection<Variable> filterCritical(Collection<Variable> variables) {
        variables = variables.stream().filter(variable -> {
            return !variable.getName().matches("(t)|(dt)|(STOP)");
        }).toList(); //Filters the vars for important ones
        return(variables);
    }
}