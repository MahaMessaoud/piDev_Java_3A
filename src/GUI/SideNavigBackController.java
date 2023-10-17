/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class SideNavigBackController implements Initializable {

    private BorderPane bp;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnUsers;
    @FXML
    private Button btnAbonnements;
    @FXML
    private Button btnCompetitions;
    @FXML
    private Button btnMateriaux;
    @FXML
    private Button btnRestaurants;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnGestionPlanning;
    @FXML
    private Button btnGestionReclamation;
    /*Containers to View*/
    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane viewPages;
    /*Close+Minus Buttons*/

    @FXML
    private Button btnMinus;

    @FXML
    private Button profile;
    @FXML
    private Button btnSponsors;
    @FXML
    private Button Logout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void switchForm(ActionEvent event) throws IOException {
        if (event.getSource() == btnHome) {
            Parent fxml = FXMLLoader.load(getClass().getResource("Weath.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnUsers) {
            Parent fxml = FXMLLoader.load(getClass().getResource("ListUsers.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnGestionPlanning) {
            Parent fxml = FXMLLoader.load(getClass().getResource("gestionPlanning.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnGestionReclamation) {
            Parent fxml = FXMLLoader.load(getClass().getResource("gestionReclamation.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnSponsors) {
            Parent fxml = FXMLLoader.load(getClass().getResource("ListSponsor.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == profile) {
            Parent fxml = FXMLLoader.load(getClass().getResource("ProfilAdmin.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnAbonnements) {
            Parent fxml = FXMLLoader.load(getClass().getResource("gestionAbonnement.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnMateriaux) {
            Parent fxml = FXMLLoader.load(getClass().getResource("GestionRessource.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnCompetitions) {
            Parent fxml = FXMLLoader.load(getClass().getResource("ViewBack.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnSponsors) {
            Parent fxml = FXMLLoader.load(getClass().getResource("listSponsor.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        } else if (event.getSource() == btnRestaurants) {
            Parent fxml = FXMLLoader.load(getClass().getResource("gestionRestau.fxml"));
            viewPages.getChildren().removeAll();
            viewPages.getChildren().setAll(fxml);
        }
        
    }

    @FXML
    public void close() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EnergyBox | CrossFit Center");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous quitter ?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
//    private void Logout(ActionEvent event)  throws IOException{
//        
//             Parent page2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
//                Scene scene2 = new Scene(page2);
//                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                app_stage.setScene(scene2);
//                app_stage.show();
//               
//    }

    @FXML

    private void Logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de déconnexion");
        alert.setHeaderText("Êtes-vous sûr de vouloir vous déconnecter ?");
        //alert.setContentText("Toutes les modifications non sauvegardées seront perdues.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Parent page2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene2 = new Scene(page2);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene2);
            app_stage.show();
        }
    }

}
