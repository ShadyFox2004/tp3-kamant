package a22.sim203.tp3.simulation;


import a22.sim203.tp3.exception.InvalidSimulationException;
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
     * Simulation object, cannot be displayed in real time by javafx, use the clone constructor instead
     */
    private Simulation simulation;
    /**
     * the time in milliseconds between January 1, 1970, 00:00:00 GTM and when the last frame was generated
     * Standard time stored on the motherboard, can be had by calling System.currentTimeMillis()
     */
    private long absoluteStartTime;

    private double targetDeltaTime;

    private boolean paused;

    /**
     * Creates a service that runs a simulation
     * @param simulation the simulation to run
     * @param targetDeltaTime the target for the time in between simulation steps
     * @param paused states if the simulation is running
     */
    public SimulationService(Simulation simulation, double targetDeltaTime, boolean paused){
        setSimulation(simulation);
        setTargetDeltaTime(targetDeltaTime);
        setPaused(paused);
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
                    a22.sim203.tp3.simulation.State calculatedState = simulation.simulateStep(simulation.getHistory().get(simulation.getHistory().size() - 1).getVariable("t").getValue() + (double) (System.currentTimeMillis() - absoluteStartTime) / 1000, (double) (System.currentTimeMillis() - absoluteStartTime) / 1000, simulation.getHistory().get(simulation.getHistory().size() - 1));
                    updateValue(calculatedState);
                    simulation.addInHistory(calculatedState);
                }
            }
            return null;
        }
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        if (simulation == null)
            throw new InvalidSimulationException("simulation is null");
        if (simulation.getHistory().size() == 0)
            throw new InvalidSimulationException("please set an initial state at index 0 of history");
        this.simulation = simulation;
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
