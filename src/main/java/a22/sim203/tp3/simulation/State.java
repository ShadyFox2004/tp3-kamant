package a22.sim203.tp3.simulation;

import org.mariuszgromada.math.mxparser.Argument;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a picture of a certain state which contains variables
 */
public class State implements Serializable {
    private Map<String, Variable> variableMap = new HashMap();

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
    }


    public Map<String, Variable> getVariableMap() {
        return variableMap;
    }

    public Collection<Variable> getCollectionVariable() {
        return variableMap.values();
    }

    public void addVariable(Variable variable) {
        variableMap.put(variable.getName(), variable);
    }

    public double getValFor(Argument argCherche) {
        return variableMap.get(argCherche.getArgumentName()).getValue();
    }

    @Override
    public String toString() {
        return "Etat{" +
                "variableList=" + variableMap +
                '}';
    }

    public void setVariableList(List<Variable> variableList) {
        this.variableMap = variableMap;
    }

    @Override
    public boolean equals(Object o) {
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
