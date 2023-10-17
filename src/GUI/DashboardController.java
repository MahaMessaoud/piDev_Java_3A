/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnMinus;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnGestionPlanning;
    @FXML
    private Button btnAbonnements;
    @FXML
    private Button btnCompetitions;
    @FXML
    private Button btnRestaurants;
    @FXML
    private Button btnMateriaux;
    @FXML
    private Button btnGestionReclamation;
    @FXML
    private AnchorPane viewPages;

    /**
     * Initializes the controller class.
     */
      /*Close App*/
    @FXML
    public void close(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EnergyBox | CrossFit Center");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous quitter ?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                System.exit(0);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /*Minimize App*/
    @FXML
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);   
    }
    
    
    /*Scroll NavBar Buttons*/
    @FXML
    public void switchForm(ActionEvent event) throws IOException{
        if(event.getSource()== btnRestaurants){
            Parent fxml= FXMLLoader.load(getClass().getResource("gestionRestau.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TO DO
    }
    
    
}
