package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.State;

/**
 * Simple visual representation that shows two variables as x and y positions of circle
 */
public class View2D {

    private String[] variables;

    /**
     * Changes the position of the circle according to the state
     * @param state the state that contains the new information
     */
    void update(State state){

    }

    /**
     * Tells the view what variables it needs to track
     * @param variables Array of the names of the variables to track
     */
    void setTrackedVariables(String... variables) {
        this.variables = variables;
    }



}
