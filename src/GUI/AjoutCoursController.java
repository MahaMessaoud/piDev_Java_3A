/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Services.CoursService;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;



/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjoutCoursController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane addCoursPane;
    
    
    @FXML
    void return_ListCours()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listCours.fxml"));
        addCoursPane.getChildren().removeAll();
        addCoursPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numberPrixInputError.setText("");
        numberAgeInputError.setText("");
    }  
    
    
    /*Formulaire AjoutCours*/
    @FXML
    private TextField textNomCours;
    @FXML
    private TextField textPrixCours;
    @FXML
    private TextField textNomCoachCours;
    @FXML
    private TextField textAgeMinCours;
    @FXML
    private TextArea textDescriptionCours;
    @FXML
    private Label numberPrixInputError;
    @FXML
    private Label numberAgeInputError;
    
    
    @FXML
    private Button btnAddCours;
    @FXML
    private Button btnClearCours;
    
    int numberAgeTest = 0;
    int numberPrixTest = 0;
    
    
    @FXML
    void numberPrixTypedInput(KeyEvent event) {
        String numberText = ((TextField) event.getSource()).getText();
        if (!numberText.matches("-?\\d+")) {
            numberPrixInputError.setText("Le nombre doit être valide.");
            numberPrixTest = 0;
        } else {
            int number = Integer.parseInt(numberText);
            if (number < 0) {
                numberPrixInputError.setText("Le nombre doit être positive.");
                numberPrixTest = 0;
            } else {
                numberPrixInputError.setText(" ");
                numberPrixTest = 1;
            }
        }
    }
    
    
    @FXML
    void numberAgeTypedInput(KeyEvent event) {
        String numberText = ((TextField) event.getSource()).getText();
        if (!numberText.matches("-?\\d+")) {
            numberAgeInputError.setText("L'age doit être valide.");
            numberAgeTest = 0;
        } else {
            int number = Integer.parseInt(numberText);
            if (number < 0) {
                numberAgeInputError.setText("L'age doit être positive.");
                numberAgeTest = 0;
            } else {
                numberAgeInputError.setText(" ");
                numberAgeTest = 1;
            }
        }
    }
    
    @FXML
    private void AjoutCours(ActionEvent event) {
        //check if not empty
        if(event.getSource() == btnAddCours){
            if (textNomCours.getText().isEmpty() || textPrixCours.getText().isEmpty() || textNomCoachCours.getText().isEmpty() || textAgeMinCours.getText().isEmpty() || 
                textDescriptionCours.getText().isEmpty() || numberPrixTest == 0 || numberAgeTest == 0) 
            {    
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les détails concernant votre cours.");
                Optional<ButtonType> option = alert.showAndWait();
                
            } else {
                ajouterCours();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ajouté avec succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre cours a été ajoutée avec succès.");
                Optional<ButtonType> option = alert.showAndWait();
                
                clearFieldsCours();
            }
        }
        if(event.getSource() == btnClearCours){
            clearFieldsCours();
        }
    }
    
    @FXML
    private void clearFieldsCours() {
        textNomCours.clear();
        textPrixCours.clear();
        textNomCoachCours.clear();
        textAgeMinCours.clear();
        textDescriptionCours.clear();
    }
    
    
    private void ajouterCours() {
        
         // From Formulaire
        String nomCours = textNomCours.getText();
        float prixCours = Float.parseFloat(textPrixCours.getText());
        String nomCoach = textNomCoachCours.getText();
        int ageCours = Integer.parseInt(textAgeMinCours.getText());
        String descpCours = textDescriptionCours.getText();
       
        
        MyDB db = MyDB.getInstance();
        Cours c = new Cours(
                nomCours, prixCours, nomCoach, ageCours, descpCours);
        CoursService cs = new CoursService();
        cs.ajouter(c);
    }  
    
    
    
    
}
