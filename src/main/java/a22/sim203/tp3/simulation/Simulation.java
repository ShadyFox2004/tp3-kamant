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
    private int simulatedSteps;

    public Simulation(String name, List<State> history) {
        this.name = name;
        this.history = history;
    }

    public Simulation(){
        name = "new simulation";
        history = new ArrayList<>();
    }

    public Simulation(Simulation simulation) {
        name = simulation.getName();
        history = new ArrayList<>();
        for (State state : simulation.getHistory()) {
            history.add(new State(state));
        }
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

    public State getLastState(){
        return history.get(history.size()-1);
    }


    @Override
    public boolean equals(Object o) {
        if (o==null) return false;
        return Objects.equals(name, ((Simulation)o).name) && Objects.equals(history, ((Simulation)o).history);
    }

    public int getSimulatedSteps() {
        return simulatedSteps;
    }

    public void setSimulatedSteps(int simulatedSteps) {
        this.simulatedSteps = simulatedSteps;
    }
}
