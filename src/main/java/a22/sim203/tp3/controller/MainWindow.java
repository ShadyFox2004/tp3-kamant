package a22.sim203.tp3.controller;

import a22.sim203.tp3.services.QueryService;
import a22.sim203.tp3.services.SimulationService;
import a22.sim203.tp3.services.WindowAnimationService;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.utils.SaveUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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

    private Stage stage;
    private SimulationEditor editor;
    private Simulator simulator;
    private View2D view2D;
    private Calculator calculator;
    private History history;
    private SimulationService simulationService;
    private QueryService queryService;
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
    
    /**
     * Know if the simulation is running
     */
    private Boolean started = false;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        editor = new SimulationEditor();
        simulator = new Simulator();
        calculator = new Calculator();
        view2D = new View2D();

        controlMenu = new ControlMenu();
        history = new History();

        controlMenu.setWindow(this);
        editor.setSimulator(simulator);
        editor.setView2D(view2D);

        tabs.getTabs().add(new Tab("editor" ,editor));
        tabs.getTabs().add(new Tab("simulator", simulator));
        tabs.getTabs().add(new Tab("calculator" ,calculator));
        tabs.getTabs().add(new Tab("2D view" ,view2D));

        sideTabs.getTabs().add(new Tab("ControlMenu", controlMenu));
        sideTabs.getTabs().add(new Tab("History", history));

        tabs.setOnDragExited(event -> {
            Tab tab = (Tab) event.getSource();
            tabToStage(tab);
        });

        tabs.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);

        updatePopItems();
    }


    /**
     * Pop out the tabs into stages and start the window positioning animation
     * // TODO make this cleaner
     */
    @FXML
    private void popout() {
        popoutButton.setDisable(true);
        Stage[] stages = new Stage[tabs.getTabs().size()+1];
        WindowAnimationService simulationService = new WindowAnimationService();
        simulationService.setOnFailed((event -> {System.out.println(event.getSource().getException());}));;
        simulationService.valueProperty().addListener(((a, o, n) -> {
            if (n != null) {
                for (int i = 0; i < stages.length; i++) {
                    stages[i].setX(n[i].getX());
                    stages[i].setY(n[i].getY());
                    stages[i].setWidth(n[i].getWidth());
                    stages[i].setHeight(n[i].getHeight());
                }
            }
        }));
        stages[0] = stage;
        simulationService.addActual(new WindowAnimationService.LocationSize(stages[0].getX(), stages[0].getY(), stages[0].getWidth(), stages[0].getHeight()));
        for (int i = 1; i < stages.length; i++) {
            stages[i] = tabToStage(tabs.getTabs().get(0));
            simulationService.addActual(new WindowAnimationService.LocationSize(stages[i].getX(), stages[i].getY(), stages[i].getWidth(), stages[i].getHeight()));
        }
        updatePopItems();
        simulationService.restart();
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
        Platform.exit();
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
    void onPauseResume(ActionEvent e){
        Button button = ((Button)e.getSource());
        if (button.getText().equals("Pause")){
            button.setText("Resume");
            simulationService.setPaused(true);
            queryService.setPaused(true);
        } else {
            button.setText("Pause");
            simulationService.setPaused(false);
            queryService.setPaused(false);
        }
    }

    /**
     * Starts/Resets the simulation
     */
    void onStartReset(ActionEvent e){
        Button button = ((Button)e.getSource());
        if (button.getText().equals("Start")){
            button.setText("Reset");
            simulationService = new SimulationService(new State(editor.getState()), Double.parseDouble(controlMenu.simulationTime.getText()));
            queryService = new QueryService(simulationService, Double.parseDouble(controlMenu.queryTime.getText()));
            history.setHistory(new State(editor.getState()));
            simulationService.valueProperty().addListener((observable, oldValue, newValue) -> {if (newValue != null) update(newValue);});
            queryService.valueProperty().addListener((observable, oldValue, newValue) -> {if (newValue != null) updateUI(newValue);});
            simulationService.restart();
            queryService.restart();
        } else {
            button.setText("Start");
            simulationService.cancel();
            queryService.cancel();
            simulator.clear();
        }
    }

    /**
     * Gets called when a new frame is generated
     * @param state the new state
     */
    void update(State state){
        history.update(state);
    }

    /**
     * Gets called when a new frame is queried
     * @param state the new state
     */

    void updateUI(State state){
        simulator.update(state);
        view2D.update(state);
        editor.update(history.getHistory().getItems().size());
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    /**
     * Sets the target time step for the simulation
     * @param simulationTime the time to set
     */
    void onSimulationTimeSet(double simulationTime) {
        if (simulationService != null)
            simulationService.setTargetDeltaTime(simulationTime);
    }

    /**
     * Sets the target time step for the query
     * @param queryTime the time to set
     */
    void onQueryTimeSet(double queryTime) {
        if (simulationService != null)
            queryService.setTargetDeltaTime(queryTime);
    }
}
