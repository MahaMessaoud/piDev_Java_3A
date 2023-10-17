/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_STATController implements Initializable {

    @FXML
    private PieChart piechart;
    @FXML
    private Pane mainpane;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        STAT c=new STAT();
       piechart.setData(c.Stats());
       
        retour.setOnAction((ActionEvent event) -> {
            redirectToList();
    });
    }    
    
   private void redirectToList(){
    try {
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Reservation.fxml"));
        mainpane.getChildren().removeAll();
        mainpane.getChildren().setAll(root);
        
    } catch (IOException ex) {
        Logger.getLogger(FXML_STATController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
   
}

 
