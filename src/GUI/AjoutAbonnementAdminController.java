/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonnement;
import Entities.Pack;
import Entities.User;
import Services.AbonnementService;
import Services.PackService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class AjoutAbonnementAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField typePack;

    @FXML
    private TextField codePromo;

    @FXML
    private Button btnValider;
    @FXML
    private Button bntReturnAbonnement;

    @FXML
    private ComboBox<String> textPack;
    @FXML
    private ComboBox<String> textUser;
    @FXML
    private AnchorPane ajoutAbPane;
     //@FXML
   // private Button backIndex;
   



    @FXML
    void back_Abonnemets() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("IndexAbonnementAdmin.fxml"));
        ajoutAbPane.getChildren().removeAll();
        ajoutAbPane.getChildren().setAll(fxml);
        
    }
    PackService cs = new PackService();
    List<Pack> packs = cs.getAll();
    private int packId = -1;

    UserService us = new UserService();
    List<User> users = us.afficherListe();
    private int userId = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Map<String, Integer> valuesMap = new HashMap<>();
        for (Pack c : packs) {
            textPack.getItems().add(c.getTypePack());
            valuesMap.put(c.getTypePack(), c.getId());
        }

        textPack.setOnAction(event -> {
            String SelectedOption = null;
            SelectedOption = textPack.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            packId = SelectedValue;
        });
        //user
        Map<String, Integer> valuesMapUser = new HashMap<>();
        for (User s : users) {
            textUser.getItems().add(s.getEmail());
            valuesMapUser.put(s.getEmail(), s.getId());
        }

        textUser.setOnAction(event -> {
            String SelectedOptionUser = null;
            SelectedOptionUser = textUser.getValue();
            int SelectedValueUser = 0;
            SelectedValueUser = valuesMapUser.get(SelectedOptionUser);
            userId = SelectedValueUser;
        });

    }

    @FXML
    private void ajouterF(ActionEvent event) throws SQLException, IOException {
        AbonnementService abonnementService = new AbonnementService();

        if (userId == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir un utilisateur.");
            return;
        }
        if (packId == -1) {
            JOptionPane.showMessageDialog(null, "Veuillez choisir un pack.");
            return;
        }
        if (!abonnementService.isValid(codePromo.getText())) {
            JOptionPane.showMessageDialog(null, "Le code promo saisi n'existe pas.");
            return;
        }
        if (abonnementService.hasActiveSubscription(userId)) {
            JOptionPane.showMessageDialog(null, "l'abonné a déjà un abonnement en cours !");
            return;
        }

        Date dateAchat = Date.valueOf("2022-02-20");
        Date dateFin = Date.valueOf("2022-03-20");

        Abonnement p = new Abonnement(dateAchat, dateFin, "a", codePromo.getText(), 100.f,
                packId, userId);
        if (abonnementService.disponible(p)) {
            JOptionPane.showMessageDialog(null, "Désolé, les places sont épuisées !");
            return;
        }
        abonnementService.create(p);

        Parent fxml = FXMLLoader.load(getClass().getResource("IndexAbonnementAdmin.fxml"));
        ajoutAbPane.getChildren().removeAll();
        ajoutAbPane.getChildren().setAll(fxml);

    }
  /*  @FXML
    void back_GestionAbonnemet() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionAbonnement.fxml"));
        Parent root = loader.load();
        btnAbonnements.getScene().setRoot(root);
    } */
}
