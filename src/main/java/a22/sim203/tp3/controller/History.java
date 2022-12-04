package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.State;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Class that handles history management for the current simulation
 * Controller for the ListView in the side pane
 * @author Kamran Charles Nayebi
 */
public class History {

    ListView<State> history;

    /**
     * Set the history with the new state
     * and select the last
     */
    protected void setHistory(List<State> newHistory) {
        if (newHistory != null) {

            history.setItems(FXCollections.observableList(newHistory));
            history.getSelectionModel().select(history.getItems().size()-1);
        }
    }

    /**
     * Updates the history with a new state
     * @param newState new state of the simulation
     */
    protected void update(State newState) {
        history.getItems().add(newState);
    }

    /**
     * Returns the state that is selected in the ListView
     * @return the state that is selected in the ListView
     */
    protected State getSelectedState() {
        return history.getSelectionModel().getSelectedItem();
    }

    /**
     * Sets the state of the selected variables in the order of the array
     * If null is passed for a selected state, it will remain unchanged
     */
    protected void setSelectedState(State state){
        history.getSelectionModel().select(state);
    }

    /**
     * Returns the history of the simulation
     */
    protected javafx.scene.control.ListView<State> getHistory(){
        return history;
    }
}
