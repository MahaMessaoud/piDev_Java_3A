/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Sponsor;
import Services.SponsorService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class ModifierSponsorController implements Initializable {

    private Sponsor Sponsor;

    @FXML
    private TextField nomSps;
    @FXML
    private TextField Dsps;
    @FXML
    private TextField Isps;
    @FXML
    private Button confirm;
    @FXML
    private Button closW;
    ObservableList<Sponsor> obsSponsors = FXCollections.observableArrayList();
    private boolean update;
int sponsorId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeW(ActionEvent event) {
        Stage stage = (Stage) closW.getScene().getWindow();
        //System.out.println("hi");
        stage.close();
    }
  

    @FXML
    private void EditSponsors(ActionEvent event) {
        SponsorService sponsorSer = new SponsorService();
        Sponsor.setNom_sponsor(nomSps.getText());
        Sponsor.setDonnation(Double.parseDouble(Dsps.getText()));
        Sponsor.setImage(Isps.getText());

        sponsorSer.modifier(new Sponsor(Sponsor.getId(), nomSps.getText(), Double.parseDouble(Dsps.getText()), Isps.getText()));

        new Alert(Alert.AlertType.INFORMATION, Sponsor.getNom_sponsor() + " Modifier !!", ButtonType.APPLY.CLOSE).show();
        //clearFields();
        //obsSponsors.set(TableSponsor.getSelectionModel().getFocusedIndex(), Sponsor);

    }

    
    
    
    
    
    
    
}
