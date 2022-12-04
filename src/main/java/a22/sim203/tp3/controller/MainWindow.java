package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.utils.SaveUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main window to control all the tabs
 * @author Kamran Charles Nayebi + Antoine-Matis Boudreau
 */
public class MainWindow {
    /**
     * Pointer to the SimulationEditor
     */
    private SimulationEditor editor;
    private Simulator simulator;
    private View2D view2D;
    private Calculator calculator;
    private History history;

    private List<Stage> stages = new ArrayList<>();

    @FXML
    private TabPane tabs;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        editor = new SimulationEditor();
        simulator = new Simulator();
        calculator = new Calculator();

        tabs.getTabs().add(new Tab("editor" ,editor));
        tabs.getTabs().add(new Tab("simulator", simulator));
        tabs.getTabs().add(new Tab("calculator" ,calculator));
    }
    /**
     * Pop out the tabs into windows
     * // TODO make this cleaner
     */
    @FXML
    private void popout() {
        tabs.getTabs().forEach(tab -> {
            Node content = tab.getContent();
            tab.setContent(null); // Cannot have two view;
            Stage stage = createStageWithParent((Parent) content);
            stages.add(stage);
            stage.show();
        });
        tabs.getTabs().clear();
    }

    /**
     * Create a stage with a root
     */
    private Stage createStageWithParent(Parent parent) {
        if (parent == null) return null; // TODO Clean this atrocity
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        return stage;
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
        history.update(state);
    }
}
