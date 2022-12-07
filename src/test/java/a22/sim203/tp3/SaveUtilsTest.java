package a22.sim203.tp3;

import a22.sim203.tp3.simulation.Equation;
import a22.sim203.tp3.simulation.Simulation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.simulation.Variable;
import a22.sim203.tp3.utils.SaveUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for saving utilities
 * Some tests are not junit tests because they need to run on fx thread
 */
public class SaveUtilsTest extends Application {

    /**
     * Launches all the tests
     */
    public static void main(String[] args) {
        launch();
    }

    public SaveUtilsTest(){}

    void saveSimulation(Stage stage) {
        Simulation simulation = new Simulation();
        Map<String, Variable> variables = new HashMap<>();
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        Variable variableAvecEquations = new Variable("a", 5);
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
        simulation.addInHistory(new State(variables));
        SaveUtils.saveSimulation(simulation, stage);
    }

    void loadSimulation(Stage stage) {
        Simulation simulation = new Simulation();
        Map<String, Variable> variables = new HashMap<>();
        variables.put("a", new Variable("a", 5));
        variables.put("b", new Variable("b", Double.MAX_VALUE));
        variables.put("c", new Variable("c", Double.MIN_VALUE));
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        Variable variableAvecEquations = new Variable("d", 2);
        variableAvecEquations.addEquation(new Equation("double", "f(t)=t*2"));
        variables.put("d", variableAvecEquations);
        simulation.addInHistory(new State(variables));
        HashMap<String, Variable> variables2 = new HashMap<>(variables);
        variables2.remove("a");
        simulation.addInHistory(new State(variables2));
        assertEquals(simulation, SaveUtils.loadSimulation(stage));
    }

    @Test
    void saveSimulation(){
        Simulation simulation = new Simulation();
        Map<String, Variable> variables = new HashMap<>();
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        Variable variableAvecEquations = new Variable("a", 5);
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
        simulation.addInHistory(new State(variables));
        SaveUtils.saveSimulation(simulation, "testfile");
    }

    @Test
    void loadSimulation(){
        Simulation simulation = new Simulation();
        Map<String, Variable> variables = new HashMap<>();
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        Variable variableAvecEquations = new Variable("a", 5);
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
        simulation.addInHistory(new State(variables));
        assertEquals(simulation, SaveUtils.loadSimulation("testfile"));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("fxml/MainWindow.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 300, 300));
        primaryStage.show();
        saveSimulation(primaryStage);
        System.out.println(SaveUtils.loadSimulation(primaryStage));

    }
}