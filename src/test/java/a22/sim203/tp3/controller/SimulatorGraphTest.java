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
        testSetTitle(loader.getController());
    }

    private void testSetTitle(Simulator simulator) {
        simulator.setTitle("Test simulation");
    }

    void testUpdateGraph(Simulator simulator) {
        simulator.setTrackedVariables("a", "b");

        Map<String, Variable> variables = new HashMap<>();
        Variable variableAvecEquations;
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 0));
        variableAvecEquations = new Variable("a", 5);
        variableAvecEquations.addEquation(new Equation("divide in two", "f(a)=a/2"));
        variables.put("a", variableAvecEquations);
        variableAvecEquations = new Variable("b", 5);
        variableAvecEquations.addEquation(new Equation("add a times time", "f(b,a,t)=b+a*t"));
        variableAvecEquations.addEquation(new Equation("test", "f(x)=30"));
        variables.put("b", variableAvecEquations);
        variableAvecEquations = new Variable("v", 5);
        variableAvecEquations.addEquation(new Equation("speed", "f(v,a,t)=v+a*t"));
        variables.put("v", variableAvecEquations);
        variableAvecEquations = new Variable("x", 0);
        variableAvecEquations.addEquation(new Equation("initial pos of the projectile", "f(x,a,t,v)=x+a/2*t^2+v*2"));
        variables.put("x", variableAvecEquations);

        simulator.update(new State(variables));

        variables.get("a").setValue(2.5);
        variables.get("b").setValue(7.5);
        variables.get("t").setValue(4);

        simulator.update(new State(variables));

        variables.get("a").setValue(1.25);
        variables.get("t").setValue(5);

        simulator.update(new State(variables));
    }

    void testInitialiseGraph(Simulator simulator) {
        Map<String, Variable> variables = new HashMap<>();
        variables.put("a", new Variable("a", 5));
        variables.put("b", new Variable("b", Double.MAX_VALUE));
        variables.put("c", new Variable("c", Double.MIN_VALUE));
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 0));
        Variable variableAvecEquations = new Variable("d", 2);
        variableAvecEquations.addEquation(new Equation("double", "f(t)=t*2"));
        variables.put("d", variableAvecEquations);
        //simulator.initialiseGraph(new State(variables), new String[]{"a"});
    }

    void testClearGraph(Simulator simulator) {
        simulator.clear();
    }
}