package a22.sim203.tp3.factory;

import a22.sim203.tp3.simulation.Equation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Fix for displaying the expressions properly
 * @author Antoine-Matis Boudreau
 */
public class EquationCellFactory implements Callback<ListView<Equation>, ListCell<Equation>> {
    @Override
    public ListCell<Equation> call(ListView<Equation> param) {
        return new ListCell<Equation>() {
            @Override
            protected void updateItem(Equation item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getExpression());
                }
            }
        };
    }
}
