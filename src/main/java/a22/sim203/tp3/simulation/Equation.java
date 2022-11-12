package a22.sim203.tp3.simulation;

import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.FunctionExtension;
import org.mariuszgromada.math.mxparser.FunctionExtensionVariadic;
import org.mariuszgromada.math.mxparser.PrimitiveElement;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * Équation nommée
 */
public class Equation implements Serializable {

    private String name;
    private String expression;

    public Equation(String name, String expression) {
        this.name = name;
        this.expression = expression;
    }

    public Equation(Equation equation) {
        name = equation.getName();
        expression = equation.getExpression();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equation equation = (Equation) o;
        return Objects.equals(name, equation.name) && Objects.equals(expression, equation.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expression);
    }

    @Override
    public String toString() {
        return name + "->" + expression;
    }
}
