package a22.sim203.tp3.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariuszgromada.math.mxparser.Function;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    Simulation simulation, simulation2;

    @BeforeEach
    void setUp() {
        simulation = new Simulation();
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
        simulation.setName("defaultName");
        simulation2 = new Simulation();
    }

    @Test
    void simulateStep() {
        Map<String, Variable> variables = new HashMap<>();
        variables.put("a", new Variable("a", 5));
        variables.put("b", new Variable("b", Double.MAX_VALUE));
        variables.put("c", new Variable("c", Double.MIN_VALUE));
        variables.put("dt", new Variable("dt", 0.1));
        variables.put("t", new Variable("t", 0.1));
        Variable variableAvecEquations = new Variable("d", 0.2);
        variableAvecEquations.addEquation(new Equation("double", "f(t)=t*2"));
        variables.put("d", variableAvecEquations);
        assertEquals(new State(variables), simulation.simulateStep(0.1,0.1, simulation.getHistory(0)));

        variables = new HashMap<>();
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
        simulation.addInHistory(new State(variables));
        variables = new HashMap<>();
        variables.put("dt", new Variable("dt", 0.1));
        variables.put("t", new Variable("t", 0.1));
        variableAvecEquations = new Variable("a", 2.5);
        variableAvecEquations.addEquation(new Equation("divide in two", "f(a)=a/2"));
        variables.put(variableAvecEquations.getName(), variableAvecEquations);
        variableAvecEquations = new Variable("b", 30);
        variableAvecEquations.addEquation(new Equation("add a times time", "f(b,a,t)=b+a*t"));
        variableAvecEquations.addEquation(new Equation("test", "f(x)=30"));
        variables.put(variableAvecEquations.getName(), variableAvecEquations);
        variableAvecEquations = new Variable("v", 5.25);
        variableAvecEquations.addEquation(new Equation("speed", "f(v,a,t)=v+a*t"));
        variables.put(variableAvecEquations.getName(), variableAvecEquations);
        variableAvecEquations = new Variable("x", 10.5125);
        variableAvecEquations.addEquation(new Equation("initial pos of the projectile", "f(x,a,t,v)=x+a/2*t^2+v*t"));
        variables.put(variableAvecEquations.getName(), variableAvecEquations);
        assertEquals(new State(variables), simulation.simulateStep(0.1,0.1, simulation.getHistory(2)));
    }

    @Test
    void convertEquationsToFunctions() {
        Collection<Equation> equations = new ArrayList<>();
        equations.add(new Equation("a", "f(t) = t^2"));
        equations.add(new Equation("b", "f(t,s) = t^s"));
        equations.add(new Equation("c", "f(t) = t^t"));
        equations.add(new Equation("d", "f(t,a) = t*2 + a"));
        List<Function> fonctions = simulation.convertEquationsToFunctions(equations);
        assertEquals(new Function("f(t) = t^2").getFunctionExpressionString(),(fonctions.get(0).getFunctionExpressionString()));
        assertEquals(new Function("f(t,s) = t^s").getFunctionExpressionString(),(fonctions.get(1).getFunctionExpressionString()));
        assertEquals(new Function("f(t) = t^t").getFunctionExpressionString(),(fonctions.get(2).getFunctionExpressionString()));
        assertEquals(new Function("f(t,a) = t*2 + a").getFunctionExpressionString(),(fonctions.get(3).getFunctionExpressionString()));
    }

    @Test
    void convertEquationToFunction() {
        Collection<Equation> equations = new ArrayList<>();
        equations.add(new Equation("a", "f(t) = t^2"));
        List<Function> fonctions = simulation.convertEquationsToFunctions(equations);
        assertEquals(new Function("f(t) = t^2").getFunctionExpressionString(),(fonctions.get(0).getFunctionExpressionString()));
    }

    @Test
    void getHistory() {
        Simulation expected = new Simulation();
        Map<String, Variable> variables = new HashMap<>();
        variables.put("a", new Variable("a", 5));
        variables.put("b", new Variable("b", Double.MAX_VALUE));
        variables.put("c", new Variable("c", Double.MIN_VALUE));
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        Variable variableAvecEquations = new Variable("d", 2);
        variableAvecEquations.addEquation(new Equation("double", "f(t)=t*2"));
        variables.put("d", variableAvecEquations);
        expected.addInHistory(new State(variables));
        HashMap<String, Variable> variables2 = new HashMap<>(variables);
        variables2.remove("a");
        expected.addInHistory(new State(variables2));
        assertEquals(expected.getHistory().get(0), simulation.getHistory().get(0));
        assertEquals(expected.getHistory().get(1), simulation.getHistory().get(1));
    }

    @Test
    void addInHistory() {
        Simulation expected = new Simulation();
        Map<String, Variable> variables = new HashMap<>();
        variables.put("a", new Variable("a", 5));
        variables.put("b", new Variable("b", Double.MAX_VALUE));
        variables.put("c", new Variable("c", Double.MIN_VALUE));
        variables.put("dt", new Variable("dt", 3));
        variables.put("t", new Variable("t", 3));
        Variable variableAvecEquations = new Variable("d", 2);
        variableAvecEquations.addEquation(new Equation("double", "f(t)=t*2"));
        variables.put("d", variableAvecEquations);
        expected.addInHistory(new State(variables));
        HashMap<String, Variable> variables2 = new HashMap<>(variables);
        variables2.remove("a");
        expected.addInHistory(new State(variables2));
        assertEquals(expected.getHistory().get(0), simulation.getHistory().get(0));
        assertEquals(expected.getHistory().get(1), simulation.getHistory().get(1));

    }

    @Test
    void setName() {
        simulation.setName("testName");
        assertEquals("testName", simulation.getName());
    }

    @Test
    void getName() {
        assertEquals("defaultName", simulation.getName());
        assertEquals("Simulation par défault", simulation2.getName());
    }

    @Test
    void testToString() {
        assertEquals("Simulation{name='defaultName', history=[Etat{variableList={dt=(dt, 3.0), a=(a, 5.0), b=(b, 1.7976931348623157E308), c=(c, 4.9E-324), t=(t, 3.0), d=(d, 2.0)}}, Etat{variableList={dt=(dt, 3.0), b=(b, 1.7976931348623157E308), c=(c, 4.9E-324), t=(t, 3.0), d=(d, 2.0)}}]}", simulation.toString());
        assertEquals("Simulation{name='Simulation par défault', history=[]}", simulation2.toString());
    }

    @Test
    void setHistory() {
    }
}