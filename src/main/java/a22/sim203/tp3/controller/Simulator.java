package a22.sim203.tp3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ListView;

/**
 * Sample Skeleton for 'Simulator.fxml' Controller Class
 * TODO change it
 */
public class Simulator {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="simulationChart"
    private LineChart<?, ?> simulationChart; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert simulationChart != null : "fx:id=\"simulationChart\" was not injected: check your FXML file 'Simulator.fxml'.";
    }

    /**
     * Adds a point to the graph for all variables requested
     * @param state the state that contains the new information to add
     * @param variables string array of the name of the variables to track
     */
    void updateGraph(State state, String[] variables) {
            
    }

    /**
     * Clears the graph
     */
    void clearGraph() {

    }

}
