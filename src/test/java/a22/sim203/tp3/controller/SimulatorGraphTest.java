package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.Equation;
import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.simulation.Variable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SimulatorGraphTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public SimulatorGraphTest(){}

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/Simulator.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
        testUpdateGraph(loader.getController());
        Thread.sleep(5000);
        testClearGraph(loader.getController());
    }

    void testUpdateGraph(Simulator simulator) {
        Map<String, Variable> variables = new HashMap<>();
        variables.put("a", new Variable("a", 5));
        variables.put("b", new Variable("b", Double.MAX_VALUE));
        variables.put("c", new Variable("c", Double.MIN_VALUE));
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        Variable variableAvecEquations = new Variable("d", 2);
        variableAvecEquations.addEquation(new Equation("double", "f(t)=t*2"));
        variables.put("d", variableAvecEquations);
        variables.get("a").setValue(10);
        simulator.updateGraph(new State(variables), new String[]{"a"});
        variables.get("a").setValue(15);
        simulator.updateGraph(new State(variables), new String[]{"a"});
    }

    void testClearGraph(Simulator simulator) {
        simulator.clearGraph();
    }
}