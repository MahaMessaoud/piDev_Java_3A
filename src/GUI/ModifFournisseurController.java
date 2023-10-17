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
import javafx.scene.Scene;
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
public class ModifFournisseurController implements Initializable {

    public Button homeButton;

    @FXML
    public Button retourModifF;

    @FXML
    public Button validerModifF;

    @FXML
    public TextField tfNomF;

    @FXML
    public TextField tfContactF;

    @FXML
    public TextField tfEmailF;

    @FXML
    public TextField tfAdrF;

    public static int idF;
    @FXML
    private Button btnMinus;
    @FXML
    private AnchorPane ModifFournisseurPane;

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean verif() {
        if (tfNomF.getText().isEmpty() || !tfNomF.getText().matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Le champ nom est obligatoire et doit contenir uniquement des lettres de l'alphabet.");
            return false;
        }
        if (tfContactF.getText().isEmpty() || !tfContactF.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
            JOptionPane.showMessageDialog(null, "Le champ prenom est obligatoire et et doit contenir uniquement 8 chiffres!");
            return false;
        }
        if (tfEmailF.getText().isEmpty() || !tfEmailF.getText().matches("[a-zA-Z0-9.]+@[a-z]+\\.[a-z]{2,3}")) {
            JOptionPane.showMessageDialog(null, "Le champ Email ne doit pas etre vide et avec un format valide. ");
            return false;
        }
        if (tfAdrF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Le champ adresse ne doit pas etre vide.");
            return false;
        }

        return true;
    }

    @FXML Button btnClose;
      @FXML
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
    
    @FXML
    private void ModifierFF(javafx.event.ActionEvent event) throws IOException, SQLException {
        if (verif()) {
            Fournisseur modifiedFournisseur = new Fournisseur(
                    tfNomF.getText(),
                    tfContactF.getText(),
                    tfEmailF.getText(),
                    tfAdrF.getText());
            FournisseurService fs = new FournisseurService();
            fs.update(modifiedFournisseur, idF);
            modifiedFournisseur.setId(idF);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailFournisseur.fxml"));
                Parent root = loader.load();
                DetailFournisseurController controller = loader.getController();
                controller.setFournisseur(modifiedFournisseur);
                                      ModifFournisseurPane.getChildren().removeAll();
        ModifFournisseurPane.getChildren().setAll(root);

               /* Scene scene = new Scene(root);
                Stage stage = (Stage) validerModifF.getScene().getWindow();
                stage.setScene(scene);
                stage.show();*/

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void puser(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/backOfficeHome.fxml"));
        Parent root = loader.load();
        homeButton.getScene().setRoot(root);

    }

    @FXML
    private void returnbackModif(javafx.event.ActionEvent event) throws IOException, SQLException {
        FournisseurService fs = new FournisseurService();
        Fournisseur f = fs.getOneById(idF);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailFournisseur.fxml"));
            Parent root = loader.load();
            DetailFournisseurController controller = loader.getController();
            controller.setFournisseur(f);
                      ModifFournisseurPane.getChildren().removeAll();
        ModifFournisseurPane.getChildren().setAll(root);

            /*Scene scene = new Scene(root);
            Stage stage = (Stage) retourModifF.getScene().getWindow();
            stage.setScene(scene);
            stage.show();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
