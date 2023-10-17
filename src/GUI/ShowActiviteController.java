/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activite;
import Services.ActiviteService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ShowActiviteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private int idCu;
    
    @FXML
    private Label descriptionLabel;

    @FXML
    private Label diffLabel;

    @FXML
    private Label dureeLabel;

    @FXML
    private ImageView imgLabel;

    @FXML
    private Label nomLabel;

    @FXML
    private Label tenueLabel;
    
    
    public void LoadData() {
        ActiviteService as = new ActiviteService();
        try {
            
            Activite a = as.findById(this.idCu);
            if (a != null) {
                if (a.getNom_activite() != null) {
                    nomLabel.setText(a.getNom_activite());
                }
                if (a.getDuree_activite()!= null) {
                    dureeLabel.setText(a.getDuree_activite());
                }
                if (a.getTenue_activite()!= null) {
                    tenueLabel.setText(a.getTenue_activite());
                }
                if (a.getDifficulte_activite()!= null) {
                    diffLabel.setText(a.getDifficulte_activite());
                }
                if (a.getDescription_activite()!= null) {
                    descriptionLabel.setText(a.getDescription_activite());
                }
                if (a.getImage_activite()!= null) {
                    //imgLabel.getScene().getWindow();
                    Image image = new Image("Images/uploads/"+a.getImage_activite());
                    imgLabel.setFitWidth(207);
                    imgLabel.setFitHeight(208);
                    imgLabel.setImage(image);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int initData(int id) {
        this.idCu = id;
        this.LoadData();
        System.err.println("ena aaaaaa" + this.idCu);
        return this.idCu;

    }
    
    
    
    
    
}
