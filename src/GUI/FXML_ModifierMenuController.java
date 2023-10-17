/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Menu;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.util.StringConverter;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.MenuServices;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_ModifierMenuController implements Initializable {
     @FXML
    private TextField champCategories;
     @FXML
    private TextArea champDescription;
    @FXML 
    private Button btnModifier;
    @FXML 
    private Button Annuler;
     @FXML 
    private Button Retour;
        
     private Menu menu;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Annuler.setOnAction((ActionEvent event) -> {
            champCategories.clear();
            champDescription.clear();
        });
        
        Retour.setOnAction((ActionEvent event) -> {
            redirectToList();
        });
    }   
   private void redirectToList(){
            Parent root;
            try {
            
            root = FXMLLoader.load(getClass().getResource("FXML_Menu.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage) Retour.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_ModifierMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       
    // méthode pour passer les détails du menu sélectionné au contrôleur
    public void setMenu(Menu m) {
        this.menu = m;
        
        // affichage des détails du menu dans les éléments de l'interface utilisateur
        champCategories.setText(m.getCategories());
        champDescription.setText(m.getDescriptionmenu());
    }

    // méthode pour gérer l'action du bouton Modifier
    @FXML
    public void modifierMenu(ActionEvent event) {
        // récupérer les nouvelles valeurs entrées par l'utilisateur
        String categories = champCategories.getText();
        String description = champDescription.getText();

        // vérifier que toutes les informations requises sont fournies
        if (categories.isEmpty() || description.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Tous les champs doivent être remplis.");
            alert.showAndWait();
        } else {
            // mettre à jour les informations du menu
            menu.setCategories(categories);
            menu.setDescriptionmenu(description);

            // enregistrer les modifications dans la base de données
            try {
                MenuServices u = new MenuServices();
                u.Modifier(menu);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Le menu a été modifié avec succès.");
                alert.showAndWait();
            } catch (SQLException ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Une erreur s'est produite lors de la modification du menu.");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
    }

    
}



