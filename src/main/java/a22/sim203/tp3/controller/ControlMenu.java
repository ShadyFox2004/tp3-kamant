package a22.sim203.tp3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the side menu
 */
public class ControlMenu extends VBox {
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
        addEventFilter(KeyEvent.KEY_TYPED, (event)-> {if (!event.getCharacter().matches("[0-9.]")) event.consume();});
        simulationTime.focusedProperty().addListener((observable, oldValue, newValue) -> window.onSimulationTimeSet(Double.parseDouble(simulationTime.getText())));
        queryTime.focusedProperty().addListener((observable, oldValue, newValue) -> window.onQueryTimeSet(Double.parseDouble(queryTime.getText())));

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
     * Get triggered when 'start/reset' button is pressed
     * Starts/Resets the simulation
     */
    @FXML
    void onStartReset(ActionEvent event) {
        window.onStartReset(event);
    }

    /**
     * Sets a reference to the main window
     * @param window the main window
     */
    public void setWindow(MainWindow window) {
        this.window = window;
    }



}
