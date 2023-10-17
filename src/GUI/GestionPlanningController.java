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
public class GestionPlanningController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane GestionPlanning;
    
    
    @FXML
    void open_listActivite()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listActivite.fxml"));
        GestionPlanning.getChildren().removeAll();
        GestionPlanning.getChildren().setAll(fxml);
    }
    
    
    @FXML
    void open_listCours()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listCours.fxml"));
        GestionPlanning.getChildren().removeAll();
        GestionPlanning.getChildren().setAll(fxml);
    }
    
    
    @FXML
    void open_listPlanning()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listPlanning.fxml"));
        GestionPlanning.getChildren().removeAll();
        GestionPlanning.getChildren().setAll(fxml);
    }
    
    
    @FXML
    private Button btnActivite;
    @FXML
    private Button btnCours;
    @FXML
    private Button btnPlanning;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
