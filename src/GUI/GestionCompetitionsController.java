/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Salima
 */
public class GestionCompetitionsController implements Initializable {

    @FXML
    private AnchorPane GestionCompetition;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void viewBacck(ActionEvent event) throws IOException {
        
        Parent fxml= FXMLLoader.load(getClass().getResource("ViewBack.fxml"));
        GestionCompetition.getChildren().removeAll();
        GestionCompetition.getChildren().setAll(fxml);
    }

    @FXML
    private void opviewFroont(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ViewFront.fxml"));
        GestionCompetition.getChildren().removeAll();
        GestionCompetition.getChildren().setAll(fxml);
    }
    
}
