package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.State;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Controller class for the side menu
 */
public class SideMenu {

    @FXML // fx:id="simulationTime"
    TextField simulationTime;

    @FXML // fx:id="queryTime"
    TextField queryTime;

    /**
     * Pointer to SimulationEditor to request the current variables view
     * I don't know how you will implement it, so I will let you choose the best way do that
     */
    SimulationEditor editor;

    /**
     * Pointer to the main window to be able to call its methods
     */
    MainWindow window;

    /**
     * Get triggered when 'pause/resume' button is pressed
     * Pauses/Resumes the simulation and switches the text of the button to the other state
     */
    @FXML
    void onPauseResume(){
        // Calls the appropriate method in MainWindow
    }

    /**
     * Get triggered when 'reset' button is pressed
     * Resets the simulation
     */
    @FXML
    void onReset(){
        // Calls the appropriate method in MainWindow
    }

    /**
     * Get triggered when 'start' button is pressed
     * Starts the simulation
     */
    void onStart(){
        // Calls the appropriate method in MainWindow
    }
}
