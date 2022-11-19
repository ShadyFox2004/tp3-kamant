package a22.sim203.tp3.exception;

/**
 * IncorrectSyntaxException is used internally to handle incorrect syntax the user enters.
 * @author Antoine-Matis Boudreau
 */
public class IncorrectSyntaxException extends RuntimeException {
    /**
     * Exception to handle the user syntax errors.
     *
     * @param invalidExpression the erroneous expression
     */
    public IncorrectSyntaxException(String invalidExpression) {
        super(invalidExpression  + " is invalid, please try a different way of putting it");
    }
}
