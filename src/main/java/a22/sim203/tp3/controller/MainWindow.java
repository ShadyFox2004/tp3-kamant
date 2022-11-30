package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.utils.SaveUtils;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Sample Skeleton for 'MainWindow.fxml' Controller Class
 */
public class MainWindow {
    /**
     * The current simulation
     */
    private Simulation simulation = new Simulation();

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }

    /**
     * Get triggered when 'close' button is pressed*
     */
    @FXML
    void onClose() {
        // TODO figure out how?
    }

    /**
     * Get triggered when 'load' button is pressed
     * Load the simulation
     */
    @FXML
    void onLoad() {
        simulation = SaveUtils.loadSimulation(new Stage());
    }

    /**
     * Get triggered when 'save' button is pressed
     * Save the simulation
     */
    @FXML
    void onSave() {
        SaveUtils.saveSimulation(simulation, new Stage());
    }
}
