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
public class AjoutMaterielController implements Initializable {

    private Button homeButton;

    @FXML
    private Button returnListM;

    @FXML
    private Button validatAjoutM;

    @FXML
    private TextField textfieldAjoutNomM;

    @FXML
    private TextField textfieldAjoutRefM;
    @FXML
    private AnchorPane AjouterMatPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void puser(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BackOfficeHome.fxml"));
        Parent root = loader.load();
        homeButton.getScene().setRoot(root);

    }

    @FXML
    private void retourListF(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListMateriel.fxml"));
//        Parent root = loader.load();
//        returnListM.getScene().setRoot(root);
        Parent fxml = FXMLLoader.load(getClass().getResource("ListMateriel.fxml"));
        AjouterMatPane.getChildren().removeAll();
        AjouterMatPane.getChildren().setAll(fxml);

    }

    private boolean verifs() {
        if (textfieldAjoutNomM.getText().isEmpty() || !textfieldAjoutNomM.getText().matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(null, "Le champ nom est obligatoire et doit contenir uniquement des lettres majuscule de l'alphabet.");
            return false;
        }
        if (textfieldAjoutRefM.getText().isEmpty() || !textfieldAjoutRefM.getText().matches("[A-Z0-9]{8}")) {
            JOptionPane.showMessageDialog(null, "Le champ reference est obligatoire et et doit contenir uniquement 8 lettres ou chiffres!");
            return false;
        }

        return true;
    }

    @FXML
    private void ajouterF(ActionEvent event) throws SQLException, IOException {
        MaterielService utilisateurService = new MaterielService();

        if (verifs()) {

            Materiel p = new Materiel(textfieldAjoutNomM.getText(), textfieldAjoutRefM.getText());

            utilisateurService.create(p);
            JOptionPane.showMessageDialog(null, "Matériel ajouté avec succe");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListMateriel.fxml"));
            Parent root = loader.load();
              AjouterMatPane.getChildren().removeAll();
        AjouterMatPane.getChildren().setAll(root);

          //  validatAjoutM.getScene().setRoot(root);
        }

    }
    Button btnClose;

    private void handleQuitter(ActionEvent event) {
        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

}
