package a22.sim203.tp3.simulation;

import org.mariuszgromada.math.mxparser.Argument;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a named variable that changes in the simulation according to the equations that they contain
 */
public class Variable implements Serializable {

    private String name;
    private double value;

    private List<Equation> equations = new ArrayList<>();

    public Variable(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public Variable(Variable other) {
        name = other.name;
        value = other.value;
        for (Equation equation : other.equations) {
            equations.add(new Equation(equation));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Double.compare(variable.value, value) == 0 && Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "(" + name + ", " + value + ")";
    }

    public void addEquation(Equation equation) {
        equations.add(equation);
    }

    public List<Equation> getEquationsList() {
        return equations;
    }

    public void setEquations(List<Equation> equations) {
        this.equations = equations;
    }
}
