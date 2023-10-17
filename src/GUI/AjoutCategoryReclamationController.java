/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategoryReclamation;
import Services.CategoryReclamationService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutCategoryReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addCategRecPane;
    
    
    @FXML
    void return_ListCategoryReclamation()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listCategoryReclamation.fxml"));
        addCategRecPane.getChildren().removeAll();
        addCategRecPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
        "Faible","Haute","Moyenne");
        textPrioriteCategRec.setItems(options);
    }    
    
    
    /*Formulaire AjoutCategoryReclamation*/
    @FXML
    private TextField textNomCategRec;
    @FXML
    private ComboBox<String> textPrioriteCategRec;
    @FXML
    private TextArea textDescriptionCategRec;
    
    
    @FXML
    private Button btnClearCategRec;
    @FXML
    private Button btnAddCategRec;
    
    
    @FXML
    private void AjoutCategoryReclamation(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddCategRec){
            if (textNomCategRec.getText().isEmpty() || textPrioriteCategRec.getValue().isEmpty() || textDescriptionCategRec.getText().isEmpty()) 
            {    
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre catégorie réclamation.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterCategoryReclamation();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre catégorie réclamation a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsCategoryReclamation();
            }
        }
        if(event.getSource() == btnClearCategRec){
            clearFieldsCategoryReclamation();
        }
    }
    
    
    @FXML
    private void clearFieldsCategoryReclamation() {
        textNomCategRec.clear();
        textDescriptionCategRec.clear();
    }
    
    
    private void ajouterCategoryReclamation() {
        
        String nomCategRec = textNomCategRec.getText();
        String prioriteCategRec = textPrioriteCategRec.getValue();
        String descriptionRec = textDescriptionCategRec.getText();
        
        
        CategoryReclamation cr = new CategoryReclamation(
                nomCategRec, descriptionRec, prioriteCategRec);
        CategoryReclamationService crs = new CategoryReclamationService();
        crs.ajouter(cr);
    }
    
}
