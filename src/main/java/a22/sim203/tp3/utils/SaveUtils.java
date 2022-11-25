package a22.sim203.tp3.utils;

import a22.sim203.tp3.simulation.Simulation;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


/**
 * Static utility for saving and loading simulation files
 */
public class SaveUtils {
    /**
     * Displays a dialog for saving a simulation file
     * @param simulation the simulation object to save
     * @param stage the stage to show the dialog on
     */
    public static void saveSimulation(Simulation simulation, Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose simulation save file location");
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null){
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile));
                oos.writeObject(simulation);
                oos.close();
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }

    /**
     * Displays a dialog for saving a simulation file
     * @param simulation the simulation object to save
     * @param fileLocation file location to save the simulation
     */
    public static void saveSimulation(Simulation simulation, String fileLocation){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose simulation save file location");
        File selectedFile = new File(fileLocation);
        if (selectedFile != null){
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile));
                oos.writeObject(simulation);
                oos.close();
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }

    /**
     * Displays a dialog for loading a simulation file
     * @param stage the stage to show the dialog on
     * @return the simulation in the file or null if there is an invalid file
     */
    public static Simulation loadSimulation(Stage stage) {
        Simulation simulation = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose simulation file to load");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile));
                simulation = (Simulation) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e){
                System.out.println(e);
            }
        }
        return simulation;
    }

    /**
     * Displays a dialog for loading a simulation file
     * @param fileLocation location of the simulation to load
     * @return the simulation in the file or null if there is an invalid file
     */
    public static Simulation loadSimulation(String fileLocation) {
        Simulation simulation = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose simulation file to load");
        File selectedFile = new File(fileLocation);
        if (selectedFile != null){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile));
                simulation = (Simulation) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e){
                System.out.println(e);
            }
        }
        return simulation;
    }
}
