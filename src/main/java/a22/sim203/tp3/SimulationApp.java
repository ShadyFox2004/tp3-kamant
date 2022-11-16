package a22.sim203.tp3;

import a22.sim203.tp3.simulation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Basic calculator/simulator app
 */
public class SimulationApp extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        Label root = new Label("tread activate");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //Service
        Map<String, Variable> variables = new HashMap<>();
        Variable variableAvecEquations;
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
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
        List<State> history = new ArrayList<>();
        history.add(new State(variables));
        Simulation simulation = new Simulation("projectile", history);
        SimulationService service = new SimulationService(simulation, 0.1, false);
        service.valueProperty().addListener((observable,oldVal,newVal)->{
            if (newVal != null) {
                root.setText(newVal.toString());
            }
        });
        service.restart();
    }



    public static void main(String[] args) {
        launch();
    }


}
