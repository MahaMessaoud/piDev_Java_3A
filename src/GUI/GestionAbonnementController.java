/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;


/**
 * FXML Controller class
 *
 * @author wiem
 */
public class GestionAbonnementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btnPack;

    @FXML
    private Button btnAbonnement;

    @FXML
    private Button btnPromotion;

    @FXML
    private Button btnAbonnements;
    @FXML
    private AnchorPane gestionAbonnement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void back_GestionAbonnemet() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("gestionAbonnement.fxml"));
        gestionAbonnement.getChildren().removeAll();
        gestionAbonnement.getChildren().setAll(fxml);
        
    }

    @FXML
    void open_listPacks() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("IndexPackAdmin.fxml"));
        gestionAbonnement.getChildren().removeAll();
        gestionAbonnement.getChildren().setAll(fxml);
    }

    @FXML
    void open_listPromotions() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("IndexPromotionAdmin.fxml"));
        gestionAbonnement.getChildren().removeAll();
        gestionAbonnement.getChildren().setAll(fxml);
    }

    @FXML
    void open_listAbonnnements() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("IndexAbonnementAdmin.fxml"));
        gestionAbonnement.getChildren().removeAll();
        gestionAbonnement.getChildren().setAll(fxml);
    }

    /*
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
    
    

    @FXML
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);   
    }
     */
}
