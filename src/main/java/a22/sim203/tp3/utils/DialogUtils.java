package a22.sim203.tp3.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.mariuszgromada.math.mxparser.Function;

import java.util.Optional;

/**
 * Dialogues utilities for 
 */
public class DialogUtils {
    /**
     * Creates an alert from the exeception message
     * @param exception
     */
    public static void createAlert(Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
        alert.showAndWait();
    }

    /**
     * Asks the user for defining a new function.
     * If the function is invalid, create a pop-up.
     * @return the new function obtained from the user.
     */
    public static Function createFunctionDialogue() {
        final Function[] newFunction = new Function[1]; //Only used for having a pointer to call upon

        TextInputDialog textInputDialog = new TextInputDialog("x");
        textInputDialog.setContentText("f(x) = ");
        textInputDialog.setHeaderText("Enter the definition for the new function");
        textInputDialog.setTitle("Create a new function");

        Optional<String> text = textInputDialog.showAndWait();

        text.ifPresent(r -> {
            newFunction[0] = new Function("f(x) = " + text.get());
            textInputDialog.close();
        });
        return newFunction[0];
    }
}
