/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Charge;
import Entities.Fournisseur;
import Entities.Materiel;
import Services.ChargeService;
import Services.MaterielService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class AjoutChargeController implements Initializable {

    @FXML
    private ComboBox<Fournisseur> comboF;
    @FXML
    private TextField textFieldQtCh;
 @FXML
    private Button confirmAjoutCh;
 @FXML
    private Button returnCh;
    private Button homeButton;

    @FXML
    private ComboBox<Materiel> comboM;
    @FXML
    private AnchorPane AjouterChargePane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Fournisseur c = new Fournisseur();
        List<Fournisseur> a = c.getIdF();
        ObservableList<Fournisseur> list = FXCollections.observableArrayList();
        for (Fournisseur cat : a) {
            list.add(cat);
        }
        comboF.setItems(list);
        try {


            MaterielService m = new MaterielService();
            List<Materiel> l = m.getAll();
            ObservableList<Materiel> list1 = FXCollections.observableArrayList();
            for (Materiel cat : l) {
                list1.add(cat);
            }
            comboM.setItems(list1);
        } catch (SQLException ex) {
            Logger.getLogger(AjoutChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    // Ajout: (comb.getSelectionModel().getSelectedItem().toString())
    private boolean verif() {
    // Add your verification logic here, for example:
    
     if (comboM.getSelectionModel().getSelectedItem()==null) {
        JOptionPane.showMessageDialog(null, "Tu dois chosir un matériel pour cette charge!");
        return false;
    }  if (comboF.getSelectionModel().getSelectedItem()==null ) {
        JOptionPane.showMessageDialog(null, "Tu dois chosir un fournisseur pour cette charge!");
        return false;
    }if (textFieldQtCh.getText().isEmpty() || !textFieldQtCh.getText().matches("[0-9]+")) {
        JOptionPane.showMessageDialog(null, "Le champ quantité est obligatoire et doit contenir uniquement des chiffres !");
        return false;
    }
    
    
  return true;
}
    
    
      private Button btnClose;
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
    
    @FXML
    void Ajouter(ActionEvent event) throws SQLException, IOException {
         ChargeService s= new ChargeService();
               

         if (verif()) {
        Charge p = new Charge(Integer.parseInt( textFieldQtCh.getText()));
int idM = comboM.getSelectionModel().getSelectedItem().getId();
int idF = comboF.getSelectionModel().getSelectedItem().getId();
        s.createe(p,idF,idM);
        JOptionPane.showMessageDialog(null, "Charge ajoutée avec succèe");
         FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ListCharge.fxml"));
                   Parent root= loader.load();
                   AjouterChargePane.getChildren().removeAll();
        AjouterChargePane.getChildren().setAll(root);
                // confirmAjoutCh.getScene().setRoot(root);
        }
         

    }
        private void puser(javafx.event.ActionEvent event) throws IOException 
       {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/BackOfficeHome.fxml"));
                   Parent root= loader.load();
                 homeButton.getScene().setRoot(root);
                   
       }
         @FXML
        private void retourListCh(javafx.event.ActionEvent event) throws IOException 
       {
//             FXMLLoader loader= new FXMLLoader(getClass().getResource("../GUI/ListCharge.fxml"));
//                   Parent root= loader.load();
//                 returnCh.getScene().setRoot(root);
           Parent fxml = FXMLLoader.load(getClass().getResource("ListCharge.fxml"));
        AjouterChargePane.getChildren().removeAll();
        AjouterChargePane.getChildren().setAll(fxml);
                   
       }
}
