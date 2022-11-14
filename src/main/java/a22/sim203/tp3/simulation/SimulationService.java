package a22.sim203.tp3.simulation;


import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.interrupted;

/**
 * Makes the service run on an independent state
 */
public class SimulationService  {

    private Simulation simulation;

    private long absoluteStartTime;

    private double targetDeltaTime;

    private boolean paused;

    /**
     * Creates a service that runs a simulation
     * @param simulation the simulation to run
     * @param targetDeltaTime the target for the time in between simulation steps
     * @param paused states if the simulation is running
     */
    SimulationService(Simulation simulation, double targetDeltaTime, boolean paused){
        setSimulation(simulation);
        setTargetDeltaTime(targetDeltaTime);
        setPaused(paused);
    }

    /**
     * Creates a simulation task
     * @return a simulation task
     */
    @Override
    protected Task<Void> createTask() {
        setAbsoluteStartTime(System.currentTimeMillis());
        return new simulationTask();
    }

    /**
     * Task that creates a new state from another state
     */
    private class simulationTask extends Task<Void> {

        @Override
        protected Void call() throws Exception {
            return null;
        }
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public long getAbsoluteStartTime() {
        return absoluteStartTime;
    }

    /**
     * Sets the absolute time since the start of the simulation
     * @warning This can very easily break things, use only when there is no other way to do things
     * @param absoluteStartTime the time in milliseconds since January 1, 1970, 00:00:00 GTM when the simulation is first run
     *      *                          (standard time stored on the motherboard, can be had by calling System.currentTimeMillis())
     */
    public void setAbsoluteStartTime(long absoluteStartTime) {
        this.absoluteStartTime = absoluteStartTime;
    }

    public double getTargetDeltaTime() {
        return targetDeltaTime;
    }

    public void setTargetDeltaTime(double targetDeltaTime) {
        this.targetDeltaTime = targetDeltaTime;
    }

    public boolean isPaused() {
        return paused;
    }

    /**
     * Pauses or unpauses the simulation
     * @param paused
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

}
