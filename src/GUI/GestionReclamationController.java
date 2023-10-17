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
public class GestionReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane GestionReclamation;
    
    
    @FXML
    void open_listReclamation()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listReclamation.fxml"));
        GestionReclamation.getChildren().removeAll();
        GestionReclamation.getChildren().setAll(fxml);
    }
    
    
    @FXML
    void open_listCategoryReclamation()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listCategoryReclamation.fxml"));
        GestionReclamation.getChildren().removeAll();
        GestionReclamation.getChildren().setAll(fxml);
    }
    
    
    @FXML
    void open_listReponse()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listReponse.fxml"));
        GestionReclamation.getChildren().removeAll();
        GestionReclamation.getChildren().setAll(fxml);
    }
    
    
    @FXML
    private Button btnReclamation;
    @FXML
    private Button btnCategoryReclamation;
    @FXML
    private Button btnReponse;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
