package a22.sim203.tp3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the side menu
 */
public class ControlMenu extends VBox {

    @FXML
    private ToggleButton pauseResumeButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

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

    public ControlMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/ControlMenu.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    /**
     * Get triggered when 'pause/resume' button is pressed
     * Pauses/Resumes the simulation and switches the text of the button to the other state
     */
    @FXML
    void onPauseResume(ActionEvent event){
        window.onPauseResume(event);
    }

    /**
     * Get triggered when 'reset' button is pressed
     * Resets the simulation
     */
    @FXML
    void onReset(ActionEvent event){
        window.onStartReset(event);
    }

    /**
     * Get triggered when 'start' button is pressed
     * Starts the simulation
     */
    @FXML
    void onStart(ActionEvent event) {
        window.onStartReset(event);

    }

    /**
     * Get triggered when 'stop' button is pressed
     * Stop the simulation
     */
    @FXML
    void onStop(ActionEvent event){
        window.onPauseResume(event);
    }


    public void setWindow(MainWindow window) {
        this.window = window;
    }

}
