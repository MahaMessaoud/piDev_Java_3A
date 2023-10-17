/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Entities.Reponse;
import Services.ReclamationService;
import Services.ReponseService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutReponseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addReponsePane;
    
    
    @FXML
    void return_ListReponse()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listReponse.fxml"));
        addReponsePane.getChildren().removeAll();
        addReponsePane.getChildren().setAll(fxml);
    }
    
    ReclamationService rs = new ReclamationService();
    List<Reclamation> reclamation = rs.Show();
    private int reclamationId = -1;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(Reclamation rec : reclamation){
            textReclamationReponse.getItems().add(rec.getNom_user_reclamation());
            valuesMap.put(rec.getNom_user_reclamation(),rec.getId());
        }
        
        textReclamationReponse.setOnAction(event ->{
            String SelectedOption = null;
            SelectedOption = textReclamationReponse.getValue();
            int SelectedValue = 0;
            SelectedValue = valuesMap.get(SelectedOption);
            reclamationId = SelectedValue;
        });
    }    
    
    
    /*Formulaire AjoutReponse*/
    @FXML
    private ComboBox <String> textReclamationReponse;
    @FXML
    private TextField textObjetReponse;
    @FXML
    private DatePicker textDateReponse;
    @FXML
    private TextField textPieceJointeReponse;
    @FXML
    private TextArea textContenuReponse;
    
    
    @FXML
    private Button btnAddReponse;
    @FXML
    private Button btnClearReponse;
    
    
    @FXML
    private void AjoutReponse(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddReponse){
            if (textReclamationReponse.getValue().isEmpty() || textObjetReponse.getText().isEmpty() || textPieceJointeReponse.getText().isEmpty() 
                    || textContenuReponse.getText().isEmpty() || reclamationId == -1) 
            {   
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre réponse.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterReponse();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre réponse a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsReponse();
            }
        }
        if(event.getSource() == btnClearReponse){
            clearFieldsReponse();
        }
    }
    
    
    @FXML
    private void clearFieldsReponse() {
        textObjetReponse.clear();
        textPieceJointeReponse.clear();
        textContenuReponse.clear();
        textDateReponse.getEditor().clear();
    }
    
    
    private void ajouterReponse() {
        
         // From Formulaire
        int recRep = reclamationId ;
        String objetRep = textObjetReponse.getText();
        Date dateRep = null;
        try {
            LocalDate localDate = textDateReponse.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                dateRep = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        String pieceJointe = textPieceJointeReponse.getText();
        String contenuRep = textContenuReponse.getText();
        
        
        Reponse re = new Reponse(
                recRep, objetRep, dateRep, pieceJointe, contenuRep);
        ReponseService rep = new ReponseService();
        rep.ajouter(re);
    }
    
}
