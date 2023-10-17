/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class GestionRestauController implements Initializable {

    @FXML
    private AnchorPane GestionRestaurant;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnPlat;
    @FXML
    private Button btnReservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    void open_listMenu()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("Fxml_Menu.fxml"));
        GestionRestaurant.getChildren().removeAll();
        GestionRestaurant.getChildren().setAll(fxml);
    }
    @FXML
    void open_listPlat()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("Fxml_Plat.fxml"));
        GestionRestaurant.getChildren().removeAll();
        GestionRestaurant.getChildren().setAll(fxml);
    }
    @FXML
    void open_listReservation()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("Fxml_Reservation.fxml"));
        GestionRestaurant.getChildren().removeAll();
        GestionRestaurant.getChildren().setAll(fxml);
    }
    
    
}
