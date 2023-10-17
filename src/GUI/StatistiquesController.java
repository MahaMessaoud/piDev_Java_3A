/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Services.CoursService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class StatistiquesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private LineChart<String, Integer> lineChartCours;
    
    
    @FXML
    private AnchorPane statPane;
    
    @FXML
    void return_listCours(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("listCours.fxml"));
        statPane.getChildren().removeAll();
        statPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statistique();
    }   
    
    
    public void statistique() {
        CoursService cs = new CoursService();

        List<Cours> cours = null;
        cours = cs.Show();
        
        

        // Créer les axes pour le graphique
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Nom Cours");
        yAxis.setLabel("Prix Cours");

        // Créer la série de données à afficher
        XYChart.Series series = new XYChart.Series();
        series.setName("Statistiques des cours selon leurs prix");
        for (Cours cour : cours) {
            series.getData().add(new XYChart.Data<>(cour.getNom_cours(), cour.getPrix_cours()));
        }

        // Créer le graphique et ajouter la série de données
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Statistiques des cours");
        lineChart.getData().add(series);

        // Afficher le graphique dans votre scène
        lineChartCours.setCreateSymbols(false);
        lineChartCours.getData().add(series);
        
        


    }
    
}
