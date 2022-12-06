package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.simulation.Variable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;

/**
 * Simple visual representation that shows two variables as x and y positions of circle
 * @author Kamran Charles Nayebi
 */
public class View2D extends HBox{
    /**
     * Variables to update
     */
    private String[] variables;

    @FXML
    private Circle ball;

    public View2D() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/View2D.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    /**
     * Changes the position of the circle according to the state
     * @param state the state that contains the new information
     */
    void update(State state){
        ball.setCenterX(state.getVariable(variables[0]).getValue());
        ball.setCenterY(state.getVariable(variables[1]).getValue());

    }

    /**
     * Tells the view what variables it needs to track
     * @param variables Array of the names of the variables to track
     */
    void setTrackedVariables(String... variables) {
        this.variables = variables;
    }



}
