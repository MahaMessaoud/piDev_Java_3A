/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import Services.CoursService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListCoursFrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    private MyListener myListener;
    
    @FXML
    private Pagination pag;
    
    @FXML
    private AnchorPane listCoursFront;
    
    
    @FXML
    void go_ListActivite()throws IOException{ 
        Parent fxml= FXMLLoader.load(getClass().getResource("listActiviteFront.fxml"));
        listCoursFront.getChildren().removeAll();
        listCoursFront.getChildren().setAll(fxml);
    }
    
    @FXML
    void go_ListPlanning()throws IOException{
        Parent fxml= FXMLLoader.load(getClass().getResource("listPlanningFront.fxml"));
        listCoursFront.getChildren().removeAll();
        listCoursFront.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CoursService cs = new CoursService();
            List<Cours> cours = cs.Show();
            pag.setPageCount((int) Math.ceil(cours.size() / 3.0)); // Nombre total de pages nécessaire pour afficher toutes les cartes
            pag.setPageFactory(pageIndex -> {
                HBox hbox = new HBox();
                hbox.setSpacing(10);
                hbox.setAlignment(Pos.CENTER);
                int itemsPerPage = 3; // Nombre d'articles à afficher par page
                int page = pageIndex * itemsPerPage;
                for (int i = page; i < Math.min(page + itemsPerPage, cours.size()); i++) {
                    
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("listCoursFrontCard.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        anchorPane.getStyleClass().add("ct");
                        ListCoursFrontCardController itemController = fxmlLoader.getController();
                        itemController.setData(cours.get(i), myListener);
                        hbox.getChildren().add(anchorPane);
                        HBox.setMargin(anchorPane, new Insets(10)); // Marges entre les cartes
                    } catch (IOException ex) {
                        Logger.getLogger(ListCoursFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                return hbox;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
}
