package a22.sim203.tp3.simulation;

import org.mariuszgromada.math.mxparser.Argument;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Represents a picture of a certain state which contains variables
 */
public class State implements Serializable {
    private Map<String, Variable> variableMap;

    public Variable getVariable(String nom) {
        return variableMap.get(nom);
    }

    /**
     * Makes a deep copy of an existing state
     *
     * @param copiedState state to copy
     */
    public State(State copiedState) {
        this.variableMap = new HashMap<>();
        for (Variable var : copiedState.variableMap.values()) {
            variableMap.put(var.getName(), new Variable(var));
        }
    }

    public State(Map<String, Variable> variableMap) {
        this.variableMap = variableMap;
        if (this.variableMap.get("t") == null) addVariables(new Variable("t", 0));
        if (this.variableMap.get("dt") == null) addVariables(new Variable("dt", 0));
        if (this.variableMap.get("STOP") == null) addVariables(new Variable("STOP", 0));
    }


    public Map<String, Variable> getVariableMap() {
        return variableMap;
    }

    public Collection<Variable> getCollectionVariable() {
        return variableMap.values();
    }

    public void addVariables(Variable...newVariables) {
        Arrays.stream(newVariables).forEach(variable -> {
            variableMap.put(variable.getName(), variable);
        });
    }

    public void removeVariables(Collection<Variable> variables) {
        variables.forEach(variable -> {
            variableMap.remove(variable.getName());
        });
    }

    public double getValFor(Argument argFind) {
        return variableMap.get(argFind.getArgumentName()).getValue();
    }

    @Override
    public String toString() {
        return "State{" +
                "variableList=" + variableMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // TODO fix this because its ugly
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        if (variableMap.size() != state.variableMap.size()) return false;
        for (String s : variableMap.keySet())
            if (!variableMap.get(s).equals(state.variableMap.get(s)))
                return false;
        return true;
    }

}
