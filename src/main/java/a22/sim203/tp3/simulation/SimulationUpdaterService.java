package a22.sim203.tp3.simulation;

import a22.sim203.tp3.exception.InvalidSimulationException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.time.DateTimeException;

/**
 * Wrapper class that creates and queries the simulation at a desired frequency
 */
public class SimulationUpdaterService extends Service<State> {

    Simulation observedSimulation;

    double targetSimulationDelta;

    double targetQueryDelta;

    private boolean paused;

    private a22.sim203.tp3.simulation.State lastState;

    public SimulationUpdaterService(Simulation observedSimulation, double targetSimulationDelta, double targetQueryDelta) {
        setObservedSimulation(observedSimulation);
        setTargetSimulationDelta(targetSimulationDelta );
        setTargetQueryDelta(targetQueryDelta);
    }

    public double getTargetQueryDelta() {
        return targetQueryDelta;
    }

    public void setTargetQueryDelta(double targetQueryDelta) {
        if (targetQueryDelta < 0)
            throw new DateTimeException("Cannot have negative time");
        this.targetQueryDelta = targetQueryDelta;
    }

    public double getTargetSimulationDelta() {
        return targetSimulationDelta;
    }

    public void setTargetSimulationDelta(double targetSimulationDelta) {
        if (targetSimulationDelta < 0)
            throw new DateTimeException("Cannot have negative time");
        this.targetSimulationDelta = targetSimulationDelta;
    }

    public void setObservedSimulation(Simulation observedSimulation) {
        if (observedSimulation == null)
            throw new InvalidSimulationException("simulation is null");
        if (observedSimulation.getHistory().size() == 0)
            throw new InvalidSimulationException("please set an initial state at index 0 of history");
        this.observedSimulation = observedSimulation;
    }

    public Simulation getObservedSimulation() {
        return observedSimulation;
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

    public void setLastState(a22.sim203.tp3.simulation.State lastState) {
        this.lastState = lastState;
    }

    /**
     * Task that queries the simulation state
     */
    @Override
    protected Task<a22.sim203.tp3.simulation.State> createTask() {
        return new SimulationUpdaterTask();
    }

    private class SimulationUpdaterTask extends Task<a22.sim203.tp3.simulation.State> {

        /**
         * Creates and queries the simulation service with the given parameters
         * @return the last calculated state
         */
        @Override
        protected a22.sim203.tp3.simulation.State call() throws Exception {
            SimulationService service = new SimulationService(observedSimulation, 0.1, false);
            service.valueProperty().addListener((observable,oldVal,newVal)->{
                if (newVal != null)
                    setLastState(newVal);
            });
            service.restart();
            while (!isPaused() && isRunning()) {
                Thread.sleep((long)(targetQueryDelta * 1000));
                updateValue(lastState);
            }
            if (isPaused())
                service.setPaused(true);
            if (!isRunning())
                service.cancel();
            return null;
        }
    }
}
