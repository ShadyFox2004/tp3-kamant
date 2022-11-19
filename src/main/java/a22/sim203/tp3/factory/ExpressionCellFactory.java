package a22.sim203.tp3.factory;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import org.mariuszgromada.math.mxparser.Expression;

/**
 * Fix for displaying the expressions properly
 * @author Antoine-Matis Boudreau
 */
public class ExpressionCellFactory implements Callback<ListView<Expression>, ListCell<Expression>> {
    @Override
    public ListCell<Expression> call(ListView<Expression> param) {
        return new ListCell<Expression>() {
            @Override
            protected void updateItem(Expression item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getDescription() + " = " + item.calculate());
                }
            }
        };
    }
}
