/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategoryReclamation;
import Entities.Reclamation;
import Services.CategoryReclamationService;
import Services.ReclamationService;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutReclamationFrontController implements Initializable {

    /**
     * Initialize the controller class.
     */
    
    @FXML
    private AnchorPane addRecFrontPane;
    
    
    @FXML
    void return_HomePageFront()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listReclamation.fxml"));
        addRecFrontPane.getChildren().removeAll();
        addRecFrontPane.getChildren().setAll(fxml);
    }
    
    
    
    CategoryReclamationService crs = new CategoryReclamationService();
    List<CategoryReclamation> category = crs.Show();
    private int categoryId;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Map<String, Integer> valuesMap = new HashMap<>();
        for(CategoryReclamation cr : category){
            Categorie.getItems().add(cr.getNom_category());
            valuesMap.put(cr.getNom_category(),cr.getId());
        }
        
        Categorie.setOnAction(event ->{
            String SelectedOption = Categorie.getValue();
            int SelectedValue = valuesMap.get(SelectedOption);
            categoryId = SelectedValue;
        });

    }    
    
    
    
    @FXML
    private ComboBox<String> Categorie;

    @FXML
    private Button btnEnvoyer;

    @FXML
    private TextField emailUser;

    @FXML
    private TextField nomUser;

    @FXML
    private TextField objet;

    @FXML
    private Button returnDashboard;

    @FXML
    private TextArea text;
    
    
    
    @FXML
    private void AjoutReclamation(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnEnvoyer){
            if (objet.getText().isEmpty() || nomUser.getText().isEmpty() 
                    || emailUser.getText().isEmpty() || Categorie.getValue().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre réclamation.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterReclamation();
                try {
                    Parent fxml= FXMLLoader.load(getClass().getResource("ajoutReclamationFrontEnvoyee.fxml"));
                    addRecFrontPane.getChildren().removeAll();
                    addRecFrontPane.getChildren().setAll(fxml);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutReclamationFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
    }
    
    
    private void ajouterReclamation() {
        
         // From Formulaire
        String nomUserRec = nomUser.getText();
        String emailUserRec = emailUser.getText();
        String objetRec = objet.getText();
        int categRec = categoryId;
        Date dateReclamation = null;
        try {
            LocalDate localDate = LocalDate.now();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                dateReclamation = Date.from(instant);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        String texteRec = text.getText();
        
        
        Reclamation r = new Reclamation(
                categRec, objetRec, texteRec, dateReclamation, nomUserRec, emailUserRec);
        ReclamationService rs = new ReclamationService();
        rs.ajouter(r);
    }
    
}
