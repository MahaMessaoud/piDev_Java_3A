/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Entities.Planning;
import Services.CoursService;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import Services.PlanningService;



/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutPlanningController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addPlanningPane;
    
    
    @FXML
    void return_ListPlanning()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listPlanning.fxml"));
        addPlanningPane.getChildren().removeAll();
        addPlanningPane.getChildren().setAll(fxml);
    }
    
    
    CoursService cs = new CoursService();
    List<Cours> cours = cs.Show();
    private int coursId=-1;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(Cours c : cours){
            textCoursPlanning.getItems().add(c.getNom_cours());
            valuesMap.put(c.getNom_cours(),c.getId());
        }
        
        textCoursPlanning.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = textCoursPlanning.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            coursId = SelectedValue;
        });
        
        
        ObservableList<String> optionsHeure = FXCollections.observableArrayList(
        "09","11","13","16","19");
        textHeurePlanning.setItems(optionsHeure);
        
        ObservableList<String> optionsJour = FXCollections.observableArrayList(
        "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche");
        textJourPlanning.setItems(optionsJour);
    }    
    
    
    /*Formulaire AjoutPlanning*/
    @FXML
    private ComboBox<String> textCoursPlanning;
    @FXML
    private DatePicker txtDatePlanning;
    @FXML
    private ComboBox<String> textJourPlanning;
    @FXML
    private ComboBox<String> textHeurePlanning;
    @FXML 
    private TextField txtNumUser;
    
    
    @FXML
    private Button btnClearPlanning;
    @FXML
    private Button btnAddPlanning;
    
    
    
    @FXML
    private void AjoutPlanning(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddPlanning){
            if (coursId==-1 || txtNumUser.getText().isEmpty() || textHeurePlanning.getValue().isEmpty() || textJourPlanning.getValue().isEmpty() ) 
            {    
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre planning.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterPlanning();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre planning a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                send_SMS();
                clearFieldsPlanning();
            }
        }
        if(event.getSource() == btnClearPlanning){
            clearFieldsPlanning();
        }
    }
    
    
    @FXML
    private void clearFieldsPlanning() {
        txtNumUser.clear();
        txtDatePlanning.getEditor().clear();
    }
    
    
    private void ajouterPlanning() {
        
         // From Formulaire
        int coursPlanning = coursId;
        Date datePlanning = null;
        try {
            LocalDate localDate = txtDatePlanning.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                datePlanning = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        String jourPlanning = textJourPlanning.getValue();
        int heurePlanning = Integer.parseInt(textHeurePlanning.getValue());
       
        
        Planning p = new Planning(
                coursPlanning, datePlanning, jourPlanning, heurePlanning);
        PlanningService ps = new PlanningService();
        ps.ajouter(p);
        
    }
    
    
    
    void send_SMS (){
        // Initialisation de la bibliothèque Twilio avec les informations de votre compte
        String ACCOUNT_SID = "AC861631f98dc0930ce29521048a763b12";
        String AUTH_TOKEN = "5916f6fb31f0ce9e6af26481e600d717";
             
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String recipientNumber = "+216" + txtNumUser.getText();
            String message = "Bonjour Mr ,\n"
                    + "Nous sommes ravis de vous informer qu'un planning a été ajouté.\n "
                    + "Veuillez contactez l'administration pour plus de details.\n "
                    + "Merci de votre fidélité et à bientôt chez EnergyBox.\n"
                    + "Cordialement,\n"
                    + "EnergyBox | CrossFit Center";
                
            Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+15075163294"),message).create();
                
            System.out.println("SMS envoyé : " + twilioMessage.getSid());
            /*TrayNotificationAlert.notif("Coupon", "Coupon sent successfully.",
            NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));*/

        
         
     }
    
}
