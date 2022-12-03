package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.utils.SaveUtils;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Main window to control all the tabs
 * @author Kamran Charles Nayebi
 */
public class MainWindow {
    /**
     * Pointer to the SimulationEditor
     */
    SimulationEditor editor;
    /**
     * Pointer to the Simulator
     */
    Simulator simulator;
    /**
     * Pointer to the View2D
     */
    View2D view2D;

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
        editor.addSimulation(SaveUtils.loadSimulation(new Stage()));
    }

    /**
     * Get triggered when 'save' button is pressed
     * Save the simulation
     */
    @FXML
    void onSave() {
        SaveUtils.saveSimulation(editor.getSimulation(), new Stage());
    }

    /**
     * Pauses/Resumes the simulation and switches the text of the button to the other state
     */
    void onPauseResume(){

    }

    /**
     * Resets the simulation
     */
    @FXML
    void onReset(){

    }

    /**
     * Starts the simulation
     */
    void onStart(){
        // TODO implement onStart
    }

    /**
     * Gets called when a new frame is generated
     * @param state the new state
     */
    void update(State state){
        // TODO implement update
    }
}
