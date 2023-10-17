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
import Services.FournisseurService;
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
import javafx.scene.Scene;
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
public class ModifChargeController implements Initializable {

    private Button homeButton;

    @FXML
    private Button returnDetailCh;

    @FXML
    private Button confModifCh;

    @FXML
    public TextField tfqtChModif;

    @FXML
    public ComboBox<Fournisseur> comboF;

    @FXML
    public ComboBox<Materiel> comboM;
    public static Fournisseur four;
    public static Materiel mat;
    @FXML
    private Button btnMinus;

    public Fournisseur getFour() {
        return four;
    }

    public void setFour(Fournisseur four) {
        this.four = four;
    }

    public Materiel getMat() {
        return mat;
    }

    public void setMat(Materiel mat) {
        this.mat = mat;
    }
    public static int idM;

    public static int getIdM() {
        return idM;
    }

    public static void setIdM(int idM) {
        ModifChargeController.idM = idM;
    }

    public static String getDateM() {
        return dateM;
    }

    public static void setDateM(String dateM) {
        ModifChargeController.dateM = dateM;
    }
    public static String dateM, materielCh, fournisseurCh;

    public static String getMaterielCh() {
        return materielCh;
    }

    public static void setMaterielCh(String materielCh) {
        ModifChargeController.materielCh = materielCh;
    }

    public static String getFournisseurCh() {
        return fournisseurCh;
    }

    public static void setFournisseurCh(String fournisseurCh) {
        ModifChargeController.fournisseurCh = fournisseurCh;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Fournisseur c = new Fournisseur();
        List<Fournisseur> a = c.getIdF();
        ObservableList<Fournisseur> list = FXCollections.observableArrayList();
        for (Fournisseur cat : a) {
            list.add(cat);
        }
        comboF.setItems(list);
       // comboF.setValue(four);
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
        //comboM.setValue(mat);
    }

    private boolean verif() {
        // Add your verification logic here, for example:
        if (tfqtChModif.getText().isEmpty() || !tfqtChModif.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Le champ quantité est obligatoire et doit contenir uniquement des chiffres !");
            return false;
        }
        if (comboM.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Tu dois chosir un matériel pour cette charge!");
            return false;
        }
        if (comboF.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Tu dois chosir un fournisseur pour cette charge!");
            return false;
        }

        return true;
    }

      @FXML 
      private Button btnClose;
      @FXML
      private AnchorPane modifChargePane;
      @FXML
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
    private void puser(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/backOfficeHome.fxml"));
        Parent root = loader.load();
        modifChargePane.getChildren().removeAll();
        modifChargePane.getChildren().setAll(root);

    }

    @FXML
    private void ModifierFF(javafx.event.ActionEvent event) throws IOException, SQLException {
        if (verif()) {
            int idM = comboM.getSelectionModel().getSelectedItem().getId();
            int idF = comboF.getSelectionModel().getSelectedItem().getId();

            String nomM = comboM.getSelectionModel().getSelectedItem().getNom_materiel();
            String nomF = comboF.getSelectionModel().getSelectedItem().getNom_fournisseur();

            Charge modifiedFournisseurr = new Charge(dateM,
                    Integer.parseInt(tfqtChModif.getText()),
                    nomM,
                    nomF);

            Charge modifiedFournisseur = new Charge(dateM,
                    Integer.parseInt(tfqtChModif.getText()),
                    String.valueOf(idM),
                    String.valueOf(idF));
            ChargeService fs = new ChargeService();
            fs.update(modifiedFournisseur, getIdM());

            modifiedFournisseurr.setId(getIdM());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailCharge.fxml"));
                Parent root = loader.load();
                DetailChargeController controller = loader.getController();
                controller.setFournisseur(modifiedFournisseurr);
                modifChargePane.getChildren().removeAll();
                modifChargePane.getChildren().setAll(root);
                /*Scene scene = new Scene(root);
                Stage stage = (Stage) confModifCh.getScene().getWindow();
                stage.setScene(scene);
                stage.show();*/

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void returnbackModif(javafx.event.ActionEvent event) throws IOException, SQLException {
        ChargeService fs = new ChargeService();
        MaterielService ms = new MaterielService();
        FournisseurService fss = new FournisseurService();
        Charge f = fs.getOneById(getIdM());
        String nomM = getMaterielCh();
        String nomF = getFournisseurCh();
        f.setFournisseur_id(nomF);
        f.setMateriel_id(nomM);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailCharge.fxml"));
            Parent root = loader.load();
            DetailChargeController controller = loader.getController();
            controller.setFournisseur(f);
            modifChargePane.getChildren().removeAll();
            modifChargePane.getChildren().setAll(root);
            /*Scene scene = new Scene(root);
            Stage stage = (Stage) returnDetailCh.getScene().getWindow();
            stage.setScene(scene);
            stage.show();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
