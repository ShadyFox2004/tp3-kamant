package a22.sim203.tp3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import a22.sim203.tp3.simulation.Simulation;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Sample Skeleton for 'SimulationEditor.fxml' Controller Class
 */
public class SimulationEditor {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="equationList"
    private ListView<?> equationList; // Value injected by FXMLLoader

    @FXML // fx:id="simulationList"
    private ListView<?> simulationList; // Value injected by FXMLLoader

    @FXML // fx:id="variableList"
    private ListView<?> variableList; // Value injected by FXMLLoader

    /**
     * Pointer to the history controller to request and set history
     */
    private History history;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert equationList != null : "fx:id=\"equationList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";
        assert simulationList != null : "fx:id=\"simulationList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";
        assert variableList != null : "fx:id=\"variableList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";

    }

    /**
     * Adds a simulation to the editor view
     * @param simulation Simulation to be added
     */
    public void addSimulation(Simulation simulation){

    }

    /**
     * Returns the selected simulation
     */
    public Simulation getSimulation() {
        return null;
    }

    /**
     * Returns the simulation with a specific name
     */
    public Simulation getSimulation(String name) {
        return null;
    }
}
