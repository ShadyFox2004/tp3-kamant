package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles history management for the current simulation
 * Controller for the ListView in the side pane
 * @author Kamran Charles Nayebi
 */
public class History {

    List<State> history = new ArrayList<>(1000);

    /**
     * Updates the history with a new state
     * @param state new state of the simulation
     */
    public void update(State state){

    }

    /**
     * Returns the states that are selected in the ListView
     * @return the states that are selected in the ListView
     */
    public List<State> getSelectedStates() {
        return null;
    }

    /**
     * Sets the state of the selected variables in the order of the array
     * If null is passed for a selected state, it will remain unchanged
     */
    public void setSelectedStates(List<State> states){

    }

    /**
     * Returns the history of the simulation
     */
    public List<State> getHistory(){
        return history;
    }

}
