/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pack;

import Services.PackService;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class AjoutPackController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField typePack;

    @FXML
    private TextField montantPack;

    @FXML
    private TextField dureePack;

    @FXML
    private TextField descriptionPack;

    @FXML
    private TextField placesPack;

    @FXML
    private TextField disponibilitePack;

    @FXML
    private Button btnValider;
    @FXML
    private Button bntReturnPack;
    
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
    private AnchorPane ajoutPackPane;

    @FXML
    void back_Packs() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("IndexPackAdmin.fxml"));
        ajoutPackPane.getChildren().removeAll();
        ajoutPackPane.getChildren().setAll(fxml);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 @FXML
    void back_GestionAbonnemet() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("gestionAbonnement.fxml"));
        ajoutPackPane.getChildren().removeAll();
        ajoutPackPane.getChildren().setAll(fxml);
    }
    @FXML
    private void ajouterPack(ActionEvent event) throws SQLException, IOException {
        PackService packService = new PackService();
        String type = typePack.getText();
        String montant = montantPack.getText();
        String duree = dureePack.getText();
        String description = descriptionPack.getText();
        String places = placesPack.getText();
        String disponibilite = disponibilitePack.getText();
        if (type.isEmpty() || montant.isEmpty() || duree.isEmpty() || description.isEmpty() || places.isEmpty() || disponibilite.isEmpty()) {
            // Afficher un message d'erreur pour indiquer que les champs sont obligatoires
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }
        try {
            float montantValue = Float.parseFloat(montant);
            int dureeValue = Integer.parseInt(duree);
            int placesValue = Integer.parseInt(places);
            int disponibiliteValue = Integer.parseInt(disponibilite);

            if (montantValue <= 0 || dureeValue <= 0 || disponibiliteValue <= 0) {
                // Afficher un message d'erreur pour indiquer que les valeurs doivent être positives
                JOptionPane.showMessageDialog(null, "Les valeurs doivent être positives.");
                return;
            }
            Pack p = new Pack(typePack.getText(), Float.parseFloat(montantPack.getText()),
                    Integer.parseInt(dureePack.getText()), descriptionPack.getText(),
                    Integer.parseInt(placesPack.getText()), Integer.parseInt(disponibilitePack.getText()));

            packService.create(p);
            JOptionPane.showMessageDialog(null, "Pack ajouté avec succe");
            Parent fxml = FXMLLoader.load(getClass().getResource("IndexPackAdmin.fxml"));
            ajoutPackPane.getChildren().removeAll();
            ajoutPackPane.getChildren().setAll(fxml);
        } catch (NumberFormatException e) {
            // Afficher un message d'erreur pour indiquer que les valeurs numériques doivent être des nombres
            JOptionPane.showMessageDialog(null, "Les valeurs numériques doivent être des nombres.");
        }
    }
}
