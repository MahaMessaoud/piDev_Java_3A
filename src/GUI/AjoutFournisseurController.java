/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Fournisseur;
import Services.FournisseurService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class AjoutFournisseurController implements Initializable {

    private Button homeButton;

    @FXML
    private Button retourListF;

    @FXML
    private Button ConfirmerAjoutF;

    @FXML
    private TextField ajoutNomF;

    @FXML
    private TextField ajoutContactF;

    @FXML
    private TextField ajoutEmailF;

    @FXML
    private TextField ajoutAdresseF;
    @FXML
    private AnchorPane AjoutFournisseurPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void puser(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BackOfficeHome.fxml"));
        Parent root = loader.load();
        homeButton.getScene().setRoot(root);

    }
    Button btnClose;

    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

    @FXML
    private void retourListF(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListFournisseur.fxml"));
//        Parent root = loader.load();
//        retourListF.getScene().setRoot(root);
        Parent fxml = FXMLLoader.load(getClass().getResource("ListFournisseur.fxml"));
        AjoutFournisseurPane.getChildren().removeAll();
        AjoutFournisseurPane.getChildren().setAll(fxml);

    }

    private boolean verifs() {
        if (ajoutNomF.getText().isEmpty() || !ajoutNomF.getText().matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Le champ nom est obligatoire et doit contenir uniquement des lettres de l'alphabet.");
            return false;
        }
        if (ajoutContactF.getText().isEmpty() || !ajoutContactF.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
            JOptionPane.showMessageDialog(null, "Le champ prenom est obligatoire et et doit contenir uniquement 8 chiffres!");
            return false;
        }
        if (ajoutEmailF.getText().isEmpty() || !ajoutEmailF.getText().matches("[a-zA-Z0-9.]+@[a-z]+\\.[a-z]{2,3}")) {
            JOptionPane.showMessageDialog(null, "Le champ Email ne doit pas etre vide et avec une format valide. ");
            return false;
        }
        if (ajoutAdresseF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le champ adresse ne doit pas etre vide.");
            return false;
        }

        return true;
    }

    @FXML
    private void ajouterF(ActionEvent event) throws SQLException, IOException {
        FournisseurService utilisateurService = new FournisseurService();

        if (verifs()) {

            Fournisseur p = new Fournisseur(ajoutNomF.getText(), ajoutContactF.getText(), ajoutEmailF.getText(), ajoutAdresseF.getText());

            utilisateurService.create(p);
            JOptionPane.showMessageDialog(null, "Fournisseur ajouté avec succe");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListFournisseur.fxml"));
            Parent root = loader.load();
             AjoutFournisseurPane.getChildren().removeAll();
        AjoutFournisseurPane.getChildren().setAll(root);

          //  ConfirmerAjoutF.getScene().setRoot(root);
        }

    }
}
