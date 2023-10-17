/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Promotion;
import Services.PromotionService;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class AjoutPromotionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField codePromotion;

    @FXML
    private TextField reductionPromotion;

    @FXML
    private DatePicker dateExpiration;

    @FXML
    private Button btnValider;
    @FXML
    private Button bntReturnPromotion;
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
    private AnchorPane addPromoPane;

  

    @FXML
    void back_Promotions() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("IndexPromotionAdmin.fxml"));
        addPromoPane.getChildren().removeAll();
        addPromoPane.getChildren().setAll(fxml);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
 @FXML
    void back_GestionAbonnemet() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("gestionAbonnement.fxml"));
        addPromoPane.getChildren().removeAll();
        addPromoPane.getChildren().setAll(fxml);
    }
    @FXML
    private void ajouterPromtion(ActionEvent event) throws SQLException, IOException {
        PromotionService promotionService = new PromotionService();
        String code = codePromotion.getText();
        String reduction = reductionPromotion.getText();
        LocalDate date = dateExpiration.getValue();
        Promotion p = new Promotion(codePromotion.getText(), Float.parseFloat(reductionPromotion.getText()), Date.valueOf(dateExpiration.getValue()));
        if (code.isEmpty() || reduction.isEmpty() || date == null) {
            // Afficher un message d'erreur pour indiquer que les champs sont obligatoires
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }

        float reductionValue = Float.parseFloat(reduction);
        if (reductionValue <= 0 || reductionValue >= 1) {
            // Afficher un message d'erreur pour indiquer que la réduction doit être entre 0 et 100
            JOptionPane.showMessageDialog(null, "La réduction doit être entre 0 et 1.");
            return;
        }
        LocalDate now = LocalDate.now();
        if (date.isBefore(now)) {

            JOptionPane.showMessageDialog(null, "La date doit être supérieure à la date système.");
            return;
        }
        promotionService.create(p);
        JOptionPane.showMessageDialog(null, "Promotion ajouté avec succe");
        Parent fxml = FXMLLoader.load(getClass().getResource("IndexPromotionAdmin.fxml"));
        addPromoPane.getChildren().removeAll();
        addPromoPane.getChildren().setAll(fxml);
        

    }

}
