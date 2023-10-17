/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class AjouReclamationFrontEnvoyeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private AnchorPane RecEnvPane;

    @FXML
    private Button returnAddRecFront;
    
    
    @FXML
    void return_AddRecFront()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutReclamationFront.fxml"));
        RecEnvPane.getChildren().removeAll();
        RecEnvPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    
    
}
