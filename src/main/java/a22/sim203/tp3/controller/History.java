package a22.sim203.tp3.controller;

import a22.sim203.tp3.factory.VariableCellFactory;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.simulation.Variable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Class that handles history management for the current simulation
 * Controller for the ListView in the side pane
 * @author Kamran Charles Nayebi
 * @author Antoine-Matis Boudreau
 */
public class History extends HBox {

    /**
     * Creates a history object
     * @throws IOException
     */
    public History() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/History.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    @FXML
    private TableView<State> historyTable;

    /**
     * Set the history with the new state
     * and select the last
     * @author Antoine-Matis Boudreau
     */
    protected void setHistory(State...newHistory) {
        if (newHistory != null) {
            // Sets the history items
            historyTable.getItems().clear();
            historyTable.setItems(FXCollections.observableList(Arrays.stream(newHistory).toList()));
            historyTable.getColumns().clear();
            historyTable.getItems().get(historyTable.getItems().size()-1).getVariableMap().forEach(new BiConsumer<String, Variable>() {
                @Override
                public void accept(String s, Variable variable) {
                    TableColumn<State,Double> column = new TableColumn<>(s);
                    column.setMaxWidth(Integer.MAX_VALUE);
                    column.setCellValueFactory(cellData -> {
                        return new ReadOnlyObjectWrapper<Double>(cellData.getValue().getVariable(s).getValue());
                    });
                    historyTable.getColumns().add(column);
                }
            });
        }
    }

    /**
     * Updates the history with a new state
     * @param newState new state of the simulation
     */
    protected void update(State newState) {
        historyTable.getItems().add(newState);
    }

    /**
     * Returns the state that is selected in the ListView
     * @return the state that is selected in the ListView
     */
    protected State getSelectedState() {
        return historyTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Sets the state of the selected variables in the order of the array
     * If null is passed for a selected state, it will remain unchanged
     */
    protected void setSelectedState(State state){
        historyTable.getSelectionModel().select(state);
    }

    /**
     * Returns the history of the simulation
     */
    protected TableView<State> getHistory(){
        return historyTable;
    }
}
