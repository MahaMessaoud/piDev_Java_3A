/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


import Entities.Menu;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.MenuServices;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_AjouterMenuController implements Initializable {

    @FXML
    private TextArea fxdesc;
    @FXML
    private Button fxajout;
    @FXML
    private Button retour;
    @FXML
    private TextArea fxnom;
     @FXML
    private Button Annuler;
    @FXML
    private AnchorPane addMenuPane;
    
    public Connection conx;
    public Statement stm;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
          Menu m =new Menu () ;
          
         try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
          fxajout.setOnAction((ActionEvent event) -> {
            m.setCategories(fxnom.getText());
            m.setDescriptionmenu(fxdesc.getText());
            ajouterMenu(m);
    });
         retour.setOnAction((ActionEvent event) -> {
            redirectToList();
    });
         Annuler.setOnAction((ActionEvent event) -> {
                fxnom.clear();
                fxdesc.clear();
   });

  
    } 
    
  /*   public void ajouterMenu(Menu m){
        try {
             String req = "INSERT INTO `menu`(`categories`, `descriptionmenu`) "
                + "VALUES (?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, m.getCategories());
            ps.setString(2, m.getDescriptionmenu());
            ps.executeUpdate();
            System.out.println("Menu ajouté avec succés");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     */
     
    public void ajouterMenu(Menu m) {
    String categories = fxnom.getText();
    String descriptionmenu = fxdesc.getText();
    
     // Check if the fields are empty
    if (categories.isEmpty() || descriptionmenu.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Les champs ne doivent pas être vides!");
        alert.showAndWait();
        return;
    }

    // Validate input fields for categories and descriptionmenu
    if (!categories.matches("^[A-Z][a-zA-Z]{2,}$")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Le champ nom doit commencer par une majuscule et contenir au moins 3 lettres!");
        alert.showAndWait();
        return;
    }
    if (!descriptionmenu.matches("^[A-Z][a-zA-Z]{4,}$")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Le champ description doit commencer par une majuscule et contenir au moins 5 lettres!");
        alert.showAndWait();
        return;
    }

    try {
        String req = "INSERT INTO `menu`(`categories`, `descriptionmenu`) "
                + "VALUES (?,?)";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, categories);
        ps.setString(2, descriptionmenu);
        ps.executeUpdate();
        System.out.println("Menu ajouté avec succés");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

      private void redirectToList(){
        
        try {
            
            Parent fxml= FXMLLoader.load(getClass().getResource("FXML_Menu.fxml"));
            addMenuPane.getChildren().removeAll();
            addMenuPane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(MouseEvent event) {
        this.redirectToList();
    }


    
}
    
    





     
 