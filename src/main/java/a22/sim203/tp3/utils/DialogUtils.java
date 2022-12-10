package a22.sim203.tp3.utils;

import a22.sim203.tp3.simulation.Equation;
import a22.sim203.tp3.simulation.Variable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
    public static Equation createEquationDialogue() {
        final Equation[] newEquation = new Equation[1]; //Only used for having a pointer to call upon

        TextInputDialog textInputDialog = new TextInputDialog("x");

        textInputDialog.setHeaderText("Enter the definition for the new function");
        textInputDialog.setTitle("Create a new function");

        Optional<String> text = textInputDialog.showAndWait();

        text.ifPresent(r -> {
            newEquation[0] = new Equation(text.get(), text.get());
            textInputDialog.close();
        });

        return newEquation[0];
    }

    /**
     * Asks the user for a new variable
     * If the variable is invalid, good luck with that
     * @return the new Variable
     */
    public static Variable createVariableDialogue() {
        final Variable[] newVariable = new Variable[1];

        TextInputDialog textInputDialog = new TextInputDialog("x");

        textInputDialog.setHeaderText("Enter the designation for the new variable");
        textInputDialog.setTitle("Create a new variable");

        Optional<String> text = textInputDialog.showAndWait();

        text.ifPresent(r -> {
            newVariable[0] = new Variable(text.get(),0);
            textInputDialog.close();
        });

        return newVariable[0];
    }

    /**
     * Creates a dialogue for asking a question to the user
     * @param question the question
     * @param title the title
     * @param defaultText the default text
     */
    public static String askUserDialogue(String question, String title, String defaultText) {
        final String[] response = new String[1]; //Get a true pointer

        TextInputDialog textInputDialog = new TextInputDialog(defaultText);
        textInputDialog.setHeaderText(question);
        textInputDialog.setTitle(title);

        Optional<String> text = textInputDialog.showAndWait();
        text.ifPresent(r -> {
            response[0] = text.get();
            textInputDialog.close();
        });

        return response[0];
    }
}
