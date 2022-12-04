package a22.sim203.tp3.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import a22.sim203.tp3.factory.SimulationCellFactory;
import a22.sim203.tp3.simulation.Simulation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

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
    private ListView<Simulation> simulationList; // Value injected by FXMLLoader

    @FXML // fx:id="variableList"
    private ListView<?> variableList; // Value injected by FXMLLoade



    /**
     * Pointer to the history controller to request and set history
     */
    private History history;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        simulationList.setItems(FXCollections.observableList(new ArrayList<>()));

        Simulation newSimulation = new Simulation();
        newSimulation.setName("2");

        simulationList.getItems().add(newSimulation);

        simulationList.getSelectionModel().select(newSimulation);

        simulationList.setCellFactory(new SimulationCellFactory());
        simulationList.setOnMousePressed(event -> {
            history.setHistory(getSimulation().getHistory());

        });

        assert equationList != null : "fx:id=\"equationList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";
        assert simulationList != null : "fx:id=\"simulationList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";
        assert variableList != null : "fx:id=\"variableList\" was not injected: check your FXML file 'SimulationEditor.fxml'.";

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
}
