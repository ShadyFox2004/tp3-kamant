package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Simple visual representation that shows two variables as x and y positions of circle
 * @author Kamran Charles Nayebi
 */
public class View2D extends HBox {
    /**
     * Variables to update
     */
    private String[] variables;

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;

    public View2D() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/View2D.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
        gc = canvas.getGraphicsContext2D();
    }

    /**
     * Changes the position of the circle according to the state
     * @param state the state that contains the new information
     */
    void update(State state){
        gc.clearRect(0,0,1000,1000);
        if (variables.length > 1)
            gc.fillOval(300 + state.getVariable(variables[0]).getValue(), 200 + state.getVariable(variables[1]).getValue(),40,40);
        else
            gc.fillOval(300 + state.getVariable(variables[0]).getValue(),300,40,40);
    }

    /**
     * Tells the view what variables it needs to track
     * @param variables Array of the names of the variables to track
     */
    void setTrackedVariables(String... variables) {
        this.variables = variables;
    }

}
