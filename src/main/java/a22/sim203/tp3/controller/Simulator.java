/**
 * Sample Skeleton for 'Simulator.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ListView;

public class Simulator {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="history"
    private ListView<?> history; // Value injected by FXMLLoader

    @FXML // fx:id="simulationChart"
    private LineChart<?, ?> simulationChart; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert history != null : "fx:id=\"history\" was not injected: check your FXML file 'Simulator.fxml'.";
        assert simulationChart != null : "fx:id=\"simulationChart\" was not injected: check your FXML file 'Simulator.fxml'.";

    }

}
