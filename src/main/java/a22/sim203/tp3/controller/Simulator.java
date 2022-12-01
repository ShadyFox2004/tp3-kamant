package a22.sim203.tp3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import javafx.beans.property.ObjectPropertyBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

/**
 * Tab that displays a line chart of the requested variables
 * To use, simply use setTrackedVariables() to tell it what variables to track and use updateGraph(State) when you have a new state
 * Don't forget to set the graph title to the simulation name through setTitle(String)
 * @author Kamran Charles Nayebi
 */
public class Simulator {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="simulationChart"
    private LineChart<Number, Number> simulationChart; // Value injected by FXMLLoader

    private String[] variables;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert simulationChart != null : "fx:id=\"simulationChart\" was not injected: check your FXML file 'Simulator.fxml'.";
    }

    /**
     * Creates all the series at the start of the simulation
     * @param state the first state
     */
    private void initialiseGraph(State state) {
        if (variables.length == 0)
            throw new IllegalStateException("Tracked variables are not set. Use method setTackedVariables()");
        XYChart.Series<Number, Number> data;
        for (String var : variables){
            data = new XYChart.Series<>();
            data.setName(var);
            data.getData().add(new XYChart.Data<>(state.getVariable("t").getValue(), state.getVariable(var).getValue()));
            simulationChart.getData().add(data);
        }
    }

    /**
     * Adds a point to the graph for all variables requested
     * @param state the state that contains the new information to add
     */
    void updateGraph(State state) {
        if (simulationChart.getData().size() == 0)
            initialiseGraph(state);
        else
            for (int i = 0; i < variables.length; i++)
                simulationChart.getData().get(i).getData().add(new XYChart.Data<>(state.getVariable("t").getValue(), state.getVariable(variables[i]).getValue()));
    }

    /**
     * Clears the graph
     */
    void clearGraph() {
        simulationChart.getData().clear();
    }

    /**
     * Tells the graph what variables it needs to track
     * @param variables Array of the names of the variables to track
     */
    void setTrackedVariables(String... variables) {
        if (simulationChart.getData().size() != 0)
            clearGraph();
        this.variables = variables;
    }

    /**
     * Sets the title of the graph
     * @param title
     */
    void setTitle(String title){
        simulationChart.setTitle(title);
    }

}
