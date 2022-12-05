package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.utils.SaveUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    private ControlMenu controlMenu;

    private List<Stage> stages = new ArrayList<>();
    @FXML
    private MenuItem popinButton;
    @FXML
    private MenuItem popoutButton;

    @FXML
    private TabPane tabs;

    @FXML
    private SplitPane slidePane;

    @FXML
    private TabPane sideTabs;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        editor = new SimulationEditor();
        simulator = new Simulator();
        calculator = new Calculator();
        view2D = new View2D();

        controlMenu = new ControlMenu();

        tabs.getTabs().add(new Tab("editor" ,editor));
        tabs.getTabs().add(new Tab("simulator", simulator));
        tabs.getTabs().add(new Tab("calculator" ,calculator));
        tabs.getTabs().add(new Tab("2D view" ,view2D));

        sideTabs.getTabs().add(new Tab("ControlMenu", controlMenu));

        tabs.setOnDragExited(event -> {
            Tab tab = (Tab) event.getSource();
            tabToStage(tab);
        });

        tabs.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);

        updatePopItems();
    }


    /**
     * Pop out the tabs into stages
     * // TODO make this cleaner
     */
    @FXML
    private void popout() {
        popoutButton.setDisable(true);
        while (tabs.getTabs().iterator().hasNext()) {
            Tab tab = tabs.getTabs().iterator().next();
            tabToStage(tab);
        }
        updatePopItems();
    }

    /**
     * Pop in the stage into tabs
     */
    @FXML
    private void popin() {
        popinButton.setDisable(true);
        while (stages.iterator().hasNext()) {
            Stage stage = stages.iterator().next();
            stageToTab(stage);
        }
        updatePopItems();
    }

    /**
     * Create a stage with a root
     */
    private Stage tabToStage(Tab tab) {
        if (tab == null) return null; // TODO Clean this atrocity
        Node content = tab.getContent();
        tab.setContent(null); // Cannot have two view;
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) content));
        stage.setTitle(tab.getText());
        stages.add(stage);
        stage.setOnCloseRequest(event -> {
            stageToTab(stage);
            stages.remove(stage);
            updatePopItems();
        });
        tabs.getTabs().remove(tab);
        updatePopItems();
        stage.show();
        return stage;
    }

    /**
     * Creates a tab from a stage
     * @param stage the stage that is closing
     * @return the tab created
     */
    private Tab stageToTab(Stage stage) {
        Tab tab;
        Parent root = stage.getScene().getRoot();
        stage.setScene(null);
        tab = new Tab(stage.getTitle() ,root);
        tabs.getTabs().add(tab);
        stage.close();
        stages.remove(stage);
        return tab;
    }

    /**
     * Update popin and popout to be disabled when they serve nothing
     */
    public void updatePopItems() {
        popoutButton.setDisable(tabs.getTabs().size() == 0); // Nice touch for the gui
        popinButton.setDisable(stages.size() == 0);
        if(tabs.getTabs().size() == 0) {
            slidePane.setDividerPositions(0);
        } else {
            slidePane.setDividerPositions(0.7);
        }
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
