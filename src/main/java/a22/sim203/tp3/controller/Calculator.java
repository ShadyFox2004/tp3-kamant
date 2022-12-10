package a22.sim203.tp3.controller;
import a22.sim203.tp3.SimulationApp;
import a22.sim203.tp3.exception.IncorrectSyntaxException;
import a22.sim203.tp3.factory.FunctionCellFactory;
import a22.sim203.tp3.utils.DialogUtils;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static a22.sim203.tp3.utils.DialogUtils.createAlert;
import static a22.sim203.tp3.utils.DialogUtils.createEquationDialogue;

/**
 * Calculator tab to (maybe) edit a specific simulation state (not required)
 * @author Antoine-Matis Boudreau
 */
public class Calculator extends HBox {
    /**
     * Creates a calculator object
     */
    public Calculator() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/Calculator.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }
    public final Function[] DEFAULT_FUNCTIONS = {
            new Function("f(x)=x"),
            new Function("f(x)=sqrt(x)"),
            new Function("f(x)=sin(x)"),
            new Function("f(x)=cos(x)"),
            new Function("f(x)=x^2")};

    public final String KEY_TO_BIND = "0123456789+-*/=()\n";

    /**
     * Maps each key to its button
     */
    private final Map<String, Button> keyMap = new HashMap<>();

    @FXML
    private TextField display;

    @FXML
    private ListView<Function> functions;

    @FXML
    private CheckMenuItem editMode;

    @FXML
    private GridPane gridPane;

    /**
     * the memory itself
     */
    private double memory = 0;

    /**
     * Pointer to the history controller to request and set history
     */
    private History history;

    /**
     * Event to trigger for animation upon button
     * creates an animation and play it.
     */
    private final EventHandler<ActionEvent> pressAnimationHandler = event -> {
        Node node = (Node) event.getSource();
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(200));
        scaleTransition.setNode(node);
        scaleTransition.setFromX(0.8);
        scaleTransition.setFromY(0.8);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    };


    @FXML
    void initialize() {
        assert display != null : "fx:id=\"display\" was not injected: check your FXML file 'Calculator.fxml'.";
        assert functions != null : "fx:id=\"functionView\" was not injected: check your FXML file 'Calculator.fxml'.";
        assert history != null : "fx:id=\"history\" was not injected: check your FXML file 'Calculator.fxml'.";

        doConfigureFunctions();
        doBindKeyToButton();
        gridPane.lookupAll(".button").forEach(button -> {
            button.addEventFilter(ActionEvent.ANY, pressAnimationHandler);
        });
    }

    /**
     * Upon a character button is pressed, insert the element in the display.
     *
     * @param event the event of the action, is used internally only.
     * @author François Marchand
     */
    @FXML
    private void addElementInDisplay(ActionEvent event) {
        String textInDisplay = display.getText();
        textInDisplay += ((Button) event.getSource()).getText();
        display.setText(textInDisplay);
    }

    /**
     * Removes the right most element character from the display value when the button is pressed
     * Set the display to the new value only if it is higher or equal to 0.
     *
     * @param event the event of the action, is used internally only.
     * @author Antoine-Matis Boudreau
     */
    @FXML
    private void removeElementInDisplay(ActionEvent event) {
        String displayText = display.getText();
        int newLength = displayText.length() - 1;
        if (newLength >= 0) {
            displayText = displayText.substring(0, newLength);
        }
        display.setText(displayText);
    }

    /**
     * Removes all text from the display
     *
     * @param event the event of the action, is used internally only.
     * @author Antoine-Matis Boudreau
     */
    @FXML
    private void clearElementInDisplay(ActionEvent event) {
        display.clear();
    }

    /**
     * When the "=" Button is pressed,
     * assemble the expression from state,
     * if there is a syntax error, display it to the user.
     * else add it to the history
     *
     * @param event the event of the action, is used internally only.
     * @author Antoine-Matis Boudreau
     */
    @FXML
    private void parseExpressionInDisplay(ActionEvent event) {
        Expression expression = assembleExpressionFromState();

        if (!expression.checkSyntax())
            createAlert(new IncorrectSyntaxException(expression.getDescription()));
    }

    /**
     * Add the value of the current expression to memory
     *
     * @param event the event of the action, is used internally only.
     * @author Antoine-Matis Boudreau
     */
    @FXML
    private void memoryAdd(ActionEvent event) {
        Expression expression = assembleExpressionFromState();

        if (expression.checkSyntax()) {
            memory = expression.calculate();
        } else {
            createAlert(new IncorrectSyntaxException(expression.getDescription()));
        }
    }

    /**
     * If(edit is toggled) then create the behaviour
     *
     * @param event the event that it listened
     * @author Antoine-Matis Boudreau
     */
    @FXML
    private void functionButtonAction(MouseEvent event) {
        if (editMode.isSelected()) {

            Button functionButton = (Button) event.getSource();
            final Function currentFunction = functions.getSelectionModel().getSelectedItem();

            functionButton.setText(currentFunction.getFunctionExpressionString());
            functionButton.setOnAction(x -> functions.getSelectionModel().select(currentFunction));

            event.consume();
        }
    }

    /**
     * Get the value in memory and add it to display
     *
     * @param event the event of the action, is used internally only.
     * @author Antoine-Matis Boudreau
     */
    @FXML
    private void recallMemory(ActionEvent event) {
        display.setText(display.getText() + memory);
    }

    /**
     * Get the state of all expression making parameters
     * Create a new expression from it and set the description to the full expression definition.
     *
     * @return the new expression
     * @author Antoine-Matis Boudreau
     */
    private Expression assembleExpressionFromState() {
        Function selectedFunction;
        Expression expression;

        selectedFunction = functions.getSelectionModel().getSelectedItem();
        expression = new Expression("f(x)".replace("x", display.getText()), selectedFunction);

        String fancyLog = selectedFunction.getFunctionExpressionString().replace("x", display.getText());
        expression.setDescription(fancyLog);

        return expression;
    }


    /**
     * Push a button upon a key pressed if the key is the button
     */
    @FXML
    private void fireButtonWithKey(KeyEvent event) {
        Button buttonToFire = keyMap.get(event.getCharacter());
        if (buttonToFire != null) {
            buttonToFire.fire();
        }
    }

    // MAKERS

    /**
     * Configure the history and set the menus and behaviours
     *
     * @author Antoine-Matis Boudreau
     */
    private void doConfigureFunctions() {
        MenuItem addItem = new MenuItem("add");
        MenuItem removeItem = new MenuItem("remove");
        MenuItem editItem = new MenuItem("edit");

        removeItem.setOnAction((event ->
                functions.getItems().remove(functions.getSelectionModel().getSelectedItem())));

//        addItem.setOnAction(event -> {
//            Function function = DialogUtils.createFunctionDialogue();
//            if (function.checkSyntax()) {
//                functions.getItems().add(function);
//            } else {
//                createAlert(new IncorrectSyntaxException(function.getFunctionExpressionString()));
//            }
//        });

        functions.setItems(FXCollections.observableList(new ArrayList<>()));
        functions.getItems().addAll(DEFAULT_FUNCTIONS);

        functions.setContextMenu(new ContextMenu(addItem, editItem, removeItem));
        functions.setCellFactory(new FunctionCellFactory()); //Fixes the display for the function
        functions.getSelectionModel().selectFirst();
    }

    /**
     * Configure each key to the button with the same text
     *
     * @author Antoine-Matis Boudreau
     */
    private void doBindKeyToButton() {
        gridPane.getChildren().stream()
                .filter(p -> p.getClass() == Button.class)
                .map(b -> (Button) b)
                .filter(p -> KEY_TO_BIND.contains(p.getText()))
                .forEach(button -> keyMap.put(button.getText(), button));
    }
}
