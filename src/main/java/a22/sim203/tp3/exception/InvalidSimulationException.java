package a22.sim203.tp3.exception;

/**
 * Exception to handle an invalid simulation state
 */
public class InvalidSimulationException extends RuntimeException {

    /**
     * Default exception
     */
    public InvalidSimulationException() {
        super("The simulation is in a state that cannot be processed");
    }

    /**
     * Exception with precision as to what is wrong in the simulation
     * @param precision sentence that precises what is wrong
     */
    public InvalidSimulationException(String precision) {
        super("The simulation is in a state that cannot be processed, " + precision);
    }

}
