package a22.sim203.tp3.factory;

import a22.sim203.tp3.simulation.Simulation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


/**
 * Factory for SimulationCell
 * Serves one purpose: be refactored
 * TODO clean that garbage
 */
public class SimulationCellFactory implements Callback<ListView<Simulation>, ListCell<Simulation>> {
    @Override
    public ListCell<Simulation> call(ListView<Simulation> param) {
        return new ListCell<Simulation>() {
            @Override
            protected void updateItem(Simulation item, boolean empty) {
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
