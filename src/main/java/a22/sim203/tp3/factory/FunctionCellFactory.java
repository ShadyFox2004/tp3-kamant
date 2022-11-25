package a22.sim203.tp3.factory;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.mariuszgromada.math.mxparser.Function;

/**
 * Fix for displaying the functions properly
 * @author Antoine-Matis Boudreau
 */
public class FunctionCellFactory implements Callback<ListView<Function>, ListCell<Function>> {
    @Override
    public ListCell<Function> call(ListView<Function> param) {
        return new ListCell<Function>() {
            @Override
            protected void updateItem(Function item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getFunctionExpressionString());
                }
            }
        };
    }
}
