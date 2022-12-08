package a22.sim203.tp3.services;


import a22.sim203.tp3.exception.InvalidSimulationException;
import a22.sim203.tp3.simulation.Equation;
import a22.sim203.tp3.simulation.Variable;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Function;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.interrupted;

/**
 * Makes the simulation run on an independent service
 */
public class SimulationService extends Service<a22.sim203.tp3.simulation.State> {

    /**
     * The time in milliseconds between January 1, 1970, 00:00:00 GTM and when the last frame was generated
     * Standard time stored on the motherboard, can be had by calling System.currentTimeMillis()
     */
    private long absoluteStartTime;
    /**
     * The time in between frames the simulator is aiming for
     */
    private double targetDeltaTime;

    private boolean paused;

    private a22.sim203.tp3.simulation.State currentState;

    /**
     * Creates a service that runs a simulation
     * @param initialState the first state of the simulation
     * @param targetDeltaTime the target for the time in between simulation steps
     */
    public SimulationService(a22.sim203.tp3.simulation.State initialState, double targetDeltaTime){
        setState(initialState);
        setTargetDeltaTime(targetDeltaTime);
        setPaused(false);
    }

    /**
     * Creates a simulation task
     * @return a simulation task
     */
    @Override
    protected Task<a22.sim203.tp3.simulation.State> createTask() {
        setAbsoluteStartTime(System.currentTimeMillis());
        return new simulationTask();
    }

    /**
     * Task that creates a new state from another state
     */
    private class simulationTask extends Task<a22.sim203.tp3.simulation.State> {

        @Override
        protected a22.sim203.tp3.simulation.State call() throws Exception {
            while (!isCancelled()){
                setAbsoluteStartTime(System.currentTimeMillis());
                Thread.sleep((long) (targetDeltaTime * 1000));

                if (!isPaused()) {
                    currentState = simulateStep(currentState.getVariable("t").getValue() + getDeltaTime(), getDeltaTime(), currentState);
                    if (currentState.getVariable("STOP").getValue() == 1.0)
                        setPaused(true);
                    else
                        updateValue(currentState);
                }
            }
            return null;
        }
        double getDeltaTime() {
            return (double) (System.currentTimeMillis() - absoluteStartTime) / 1000;
        }

        /**
         * Generates a step of the equation
         * @param t time since the start of the simulation
         * @param dt time between the two frames
         * @param initialState initial state
         * @return final state
         */
        private a22.sim203.tp3.simulation.State simulateStep(double t, double dt, a22.sim203.tp3.simulation.State initialState) {
            a22.sim203.tp3.simulation.State newState = new a22.sim203.tp3.simulation.State(initialState);//msd depp copy requise
            newState.getVariable("dt").setValue(dt);
            newState.getVariable("t").setValue(t);
            Map<String, Variable> mapVars = newState.getVariableMap();
            for (Variable var : mapVars.values())
                for (Equation equation : var.getEquationsList()) {
                    Function func = new Function(equation.getExpression());
                    for (int i = 0; i < func.getArgumentsNumber(); i++)
                        func.setArgumentValue(i, newState.getValFor(func.getArgument(i)));
                    var.setValue(func.calculate());
                }
            return newState;
        }
    }
    public void setState(a22.sim203.tp3.simulation.State state) {
        if (state == null)
            throw new InvalidSimulationException("simulation is null");
        currentState = state;
    }

    public long getAbsoluteStartTime() {
        return absoluteStartTime;
    }

    /**
     * Sets the absolute time since the last frame was created
     * @param absoluteStartTime the time in milliseconds between January 1, 1970, 00:00:00 GTM and when the last frame was generated
     *      *                          (standard time stored on the motherboard, can be had by calling System.currentTimeMillis())
     */
    private void setAbsoluteStartTime(long absoluteStartTime) {
        if (absoluteStartTime < 0)
            throw new DateTimeException("Cannot have negative time");
        this.absoluteStartTime = absoluteStartTime;
    }
    public double getTargetDeltaTime() {
        return targetDeltaTime;
    }

    public void setTargetDeltaTime(double targetDeltaTime) {
        if (targetDeltaTime < 0)
            throw new DateTimeException("Cannot have negative delta time");
        this.targetDeltaTime = targetDeltaTime;
    }

    public boolean isPaused() {
        return paused;
    }

    /**
     * Pauses or unpauses the simulation
     * @param paused if you want the simulation to be paused
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
