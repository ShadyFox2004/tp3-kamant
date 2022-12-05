package a22.sim203.tp3.controller;

import a22.sim203.tp3.simulation.Equation;
import a22.sim203.tp3.simulation.State;
import a22.sim203.tp3.simulation.Variable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class View2DTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public View2DTest(){}

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxml/View2D.fxml"));
        HBox hBox = new HBox();
        Button bouton = new Button("BougerBalle");
        bouton.setOnAction((e)->testUpdateView2D(loader.getController()));
        hBox.getChildren().addAll(bouton, loader.load());
        primaryStage.setScene(new Scene(hBox));
        primaryStage.show();
    }


    void testUpdateView2D(View2D view2D) {
        view2D.setTrackedVariables("a", "b");

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

        view2D.update(new State(variables));

        variables.get("a").setValue(2.5);
        variables.get("b").setValue(7.5);
        variables.get("t").setValue(4);

        view2D.update(new State(variables));

        variables.get("a").setValue(1.25);
        variables.get("t").setValue(5);

        view2D.update(new State(variables));
        variables.get("a").setValue(200);
        view2D.update(new State(variables));
    }
}