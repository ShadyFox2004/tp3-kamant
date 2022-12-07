package a22.sim203.tp3.simulation;

import org.mariuszgromada.math.mxparser.Function;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a situation to simulate
 * It's the object passed into the simulation loop
 */
public class Simulation implements Serializable {

    private String name;
    private List<State> history;// state 0 is the first state of the history

    public Simulation(String name, List<State> history) {
        this.name = name;
        this.history = history;
    }

    public Simulation(){
        name = "Simulation par défault";
        history = new ArrayList<>();
    }

    public Simulation(Simulation simulation) {
        name = simulation.getName();
        history = new ArrayList<>();
        for (State state : simulation.getHistory()) {
            history.add(new State(state));
        }
    }

    /**
     * Generates a step of the equation
     * @param t time since the start of the simulation
     * @param dt time between the two frames
     * @param initialState initial state
     * @return final state
     */
    public State simulateStep(double t, double dt, State initialState) {
        State newState = new State(initialState);//msd depp copy requise
        newState.getVariable("dt").setValue(dt);
        newState.getVariable("t").setValue(t);
        Map<String, Variable> mapVars = newState.getVariableMap();
        for (Variable var : mapVars.values()) {
            for (Equation equation : var.getEquationsList()) {
                Function func = convertEquationToFunction(equation);
                for (int i = 0; i < func.getArgumentsNumber(); i++) {
                    func.setArgumentValue(i, newState.getValFor(func.getArgument(i)));
                }
                var.setValue(func.calculate());
            }

        }


        return newState;
    }


    public List<Function> convertEquationsToFunctions(Collection<Equation> equations) {
        List<Function> retList = new ArrayList<>();
        for (Equation equation : equations) {
            retList.add(convertEquationToFunction(equation));
        }
        return retList;
    }

    public Function convertEquationToFunction(Equation equation) {
        return new Function(equation.getExpression());
    }

    public State getHistory(int step) {
        return history.get(step);
    }

    public void addInHistory(State newState) {
        history.add(newState);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "name='" + name + '\'' +
                ", history=" + history +
                '}';
    }

    public List<State> getHistory() {
        return history;
    }

    public void setHistory(List<State> history) {
        this.history = history;
    }

    @Override
    public boolean equals(Object o) {
        if (o==null) return false;
        return Objects.equals(name, ((Simulation)o).name) && Objects.equals(history, ((Simulation)o).history);
    }

}
