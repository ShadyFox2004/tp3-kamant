package a22.sim203.tp3.factory;

import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.Variable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


/**
 * Factory for SimulationCell
 * Serves one purpose: be refactored
 * TODO clean that garbage
 */
public class VariableCellFactory implements Callback<ListView<Variable>, ListCell<Variable>> {
 @Override
        public ListCell<Variable> call(ListView<Variable> param) {
            return new ListCell<Variable>() {
                @Override
                protected void updateItem(Variable item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        }
    }