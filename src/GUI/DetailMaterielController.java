/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Materiel;
import Services.MaterielService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class DetailMaterielController implements Initializable {

    @FXML
    private Button retourListM;

    @FXML
    private Button suppM;

    @FXML
    private Button modifM;
    private Button homeButton;

    @FXML
    private Label labidM;

    @FXML
    private Label labnomM;

    @FXML
    private Label labrefM;

    @FXML
    private Label labdmM;

    @FXML
    private Label labqtM;
    @FXML
    private AnchorPane DetMateriel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //l fournisseur li bch nabaathouh lel detail
    public void setFournisseurr(Materiel fournisseur) {
        labidM.setText(String.valueOf(fournisseur.getId()));
        labnomM.setText(fournisseur.getNom_materiel());
        labrefM.setText(fournisseur.getReference_materiel());
        labdmM.setText(fournisseur.getDate_maintenance());
        labqtM.setText(String.valueOf(fournisseur.getQuantite_materiel()));
    }

    @FXML
    private void handleModifierF() {
        // Get the values from the labels in the current scene
        String id = labidM.getText();
        String nom = labnomM.getText();
        String ref = labrefM.getText();
        String dateM = labdmM.getText();
        String qtM = labqtM.getText();

        // Load the "ModifFournisseur.fxml" file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifMateriel.fxml"));
        try {
            Parent root = loader.load();
            ModifMaterielController controller = loader.getController();

            // Set the text fields in the "ModifFournisseur.fxml" file with the values from the labels
            controller.setIdM(Integer.parseInt(id));
            controller.modifnomM.setText(nom);
            controller.modifrefM.setText(ref);
            controller.setDateM(dateM);
            controller.setQttM(Integer.parseInt(qtM));

            // Show the "ModifFournisseur.fxml" file in a new stage
            Scene scene = modifM.getScene();
                                      DetMateriel.getChildren().removeAll();
        DetMateriel.getChildren().setAll(root);

           /* scene.setRoot(root);
            System.out.println(controller.getIdM());
            System.out.println(controller.getDateM());
            System.out.println(controller.getQttM());*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exitM(javafx.event.ActionEvent event) throws IOException {
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
    private void returnListF(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListMateriel.fxml"));
        Parent root = loader.load();
                               DetMateriel.getChildren().removeAll();
        DetMateriel.getChildren().setAll(root);

        
        // retourListM.getScene().setRoot(root);

    }

    @FXML
    private void suppF(javafx.event.ActionEvent event) throws IOException, SQLException {
        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer Matériel " + labnomM.getText());
        alert.setHeaderText("Êtes vous sur ,supprimer ce Matériel ?");
        alert.setContentText("Cette action est requise.");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        // If the user clicks "OK", delete the fournisseur
        if (result.get() == ButtonType.OK) {
            MaterielService fs = new MaterielService();
            fs.delete(Integer.parseInt(labidM.getText()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListMateriel.fxml"));
            Parent root = loader.load();
                                      DetMateriel.getChildren().removeAll();
        DetMateriel.getChildren().setAll(root);

//suppM.getScene().setRoot(root);
        } else {
            // Close the dialog and do nothing
            alert.close();
        }

    }
}
