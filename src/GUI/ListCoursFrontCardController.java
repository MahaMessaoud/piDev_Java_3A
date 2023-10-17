/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListCoursFrontCardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label labelAgeCours;

    @FXML
    private Label labelDescriptionCours;

    @FXML
    private Label labelNomCoachCours;

    @FXML
    private Label labelNomCours;

    @FXML
    private Label labelPrixCours;
    
    MyListener myListener;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    Cours cours;
    
    public void setData (Cours cours, MyListener myListener){
        this.cours = cours ;
        this.myListener = myListener;
        
        labelNomCours.setText(cours.getNom_cours());
        labelPrixCours.setText(String.valueOf(cours.getPrix_cours())+" DT  |  ");
        labelAgeCours.setText(String.valueOf(cours.getAge_min_cours())+" ans");
        labelNomCoachCours.setText("Coach "+cours.getNom_coach());
        labelDescriptionCours.setText(cours.getDescription_cours());
        
    }
    
}
