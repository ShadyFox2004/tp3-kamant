package a22.sim203.tp3.factory;

import a22.sim203.tp3.simulation.State;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class StateCellFactory implements Callback<ListView<State>, ListCell<State>> {
    @Override
    public ListCell<State> call(ListView<State> param) {
        return new ListCell<State>() {
            @Override
            protected void updateItem(State item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.toString());
                }
            }
        };
    }
}
