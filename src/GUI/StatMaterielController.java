/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Materiel;
import Services.ChargeService;
import Services.MaterielService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class StatMaterielController implements Initializable {

      @FXML
    private BarChart<String,Number>stat;

    @FXML
    private Button returnStat;

      @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    
    @FXML 
    private AnchorPane StatPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          try {
              statistique();
          } catch (SQLException ex) {
              Logger.getLogger(StatMaterielController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
    
    MaterielService ms= new MaterielService();
    ChargeService cs = new ChargeService();
    
//    public void stat() throws SQLException {
//        List<Materiel> packs = ms.getAll();
//List<Charge> charges = cs.getAll();
//
//        int totalPlaces = 0;
//        int minPlaces = Integer.MAX_VALUE;
//        int maxPlaces = Integer.MIN_VALUE;
//        for (Materiel pack : packs) {
//            totalPlaces += pack.getPlacesPack();
//            minPlaces = Math.min(minPlaces, pack.getPlacesPack());
//            maxPlaces = Math.max(maxPlaces, pack.getPlacesPack());
//        }
//        double averagePlaces = totalPlaces / (double) packs.size();
//
//        System.out.println("Nombre total de places disponibles : " + totalPlaces);
//        System.out.println("Moyenne de places disponibles : " + averagePlaces);
//        System.out.println("Minimum de places disponibles : " + minPlaces);
//        System.out.println("Maximum de places disponibles : " + maxPlaces);
//    }


public void statistique() throws SQLException {
       MaterielService ms = new MaterielService();

        List<Materiel> packs = new MaterielService().getAll();
       

        // Créer les axes pour le graphique
       
     
        xAxis.setLabel("Nom des matériels");
        
        yAxis.setLabel("nombre des charge par matériel");
    yAxis.setTickLabelGap(5);
xAxis.setTickLabelGap(5);
yAxis.setAutoRanging(false);
yAxis.setLowerBound(0);
yAxis.setUpperBound(10);
yAxis.setTickUnit(1);

        // Créer la série de données à afficher
        XYChart.Series series = new XYChart.Series();
        series.setName("nbr charge par matériel");
        
        
        for (Materiel pack : packs) {
            series.getData().add(new XYChart.Data<>(pack.getNom_materiel(), ms.getCharges(pack)));
        }
        

        
        stat.getData().add(series);
        stat.setCategoryGap(5);
       
     
    }

      @FXML private  Button btnClose;
      @FXML
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

  @FXML
        private void GoToM(javafx.event.ActionEvent event) throws IOException 
     {//  soundService ss = new soundService();
//        ss.btn2();
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ListMateriel.fxml"));
                   Parent root= loader.load();
                     StatPane.getChildren().removeAll();
                StatPane.getChildren().setAll(root);
                // returnStat.getScene().setRoot(root);
                   
       }
}
