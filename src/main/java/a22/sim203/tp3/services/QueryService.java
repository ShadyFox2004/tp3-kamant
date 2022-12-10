package a22.sim203.tp3.services;

import a22.sim203.tp3.simulation.State;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class QueryService extends Service<State> {

    private SimulationService service;

    private a22.sim203.tp3.simulation.State lastState;

    private double targetDeltaTime;

    private boolean paused;


    public QueryService(SimulationService service, double queryTime) {
        setSimulationService(service);
        targetDeltaTime = queryTime;
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setTargetDeltaTime(double targetDeltaTime) {
        this.targetDeltaTime = targetDeltaTime;
    }

    /**
     * Sets the service to be observed
     *
     * @param service the simulation service that is running
     */
    public void setSimulationService(SimulationService service) {
        this.service = service;
        service.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) update(newValue);
        });
    }

    void update(a22.sim203.tp3.simulation.State lastState) {
        this.lastState = lastState;
    }

    @Override
    protected Task<a22.sim203.tp3.simulation.State> createTask() {
        return new queryTask();
    }

    class queryTask extends Task<a22.sim203.tp3.simulation.State> {

        @Override
        protected a22.sim203.tp3.simulation.State call() throws Exception {
            while (!paused && !isCancelled()) {
                if (lastState != null)
                    updateValue(lastState);
                Thread.sleep((long) targetDeltaTime * 1000);
            }
            return null;
        }
    }
}
