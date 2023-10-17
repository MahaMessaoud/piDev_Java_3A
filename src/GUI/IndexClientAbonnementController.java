/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonnement;
import Entities.Pack;
import Entities.Promotion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import Services.AbonnementService;
import Services.PackService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import GUI.MyListener;
import GUI.CartPackController;
import Services.PromotionService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class IndexClientAbonnementController implements Initializable {

    @FXML
    private Button btnRserverUnAbonnement;
    @FXML
    private HBox hbox;
    @FXML
    private HBox vbox;
    @FXML
    private GridPane grid;
    PackService ps = new PackService();
    private List<Pack> packs = ps.getAll();
    private MyListener myListener;
    Pack pack;
    @FXML
    private Button Notif;
    @FXML
    private Pagination pag;
    PromotionService prom = new PromotionService();

    @FXML
    private Label codePromo;
    @FXML
    private ImageView background;
    @FXML
    private AnchorPane listAbonnementPane;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Promotion> promo = prom.getAll();
            for (int i = 0; i < promo.size(); i++) {
                String code = promo.get(i).getCodePromotion();
                codePromo.setText("Code Promo : " + code);
            }
            int column = 0;
            int row = 0;
            try {
                pag.setPageCount((int) Math.ceil(packs.size() / 3.0)); // Nombre total de pages nécessaire pour afficher toutes les cartes
                pag.setPageFactory(pageIndex -> {
                    HBox hbox = new HBox();
                    hbox.setSpacing(10);
                    hbox.setAlignment(Pos.CENTER);
                    int itemsPerPage = 3; // Nombre d'articles à afficher par page
                    int page = pageIndex * itemsPerPage;
                    for (int i = page; i < Math.min(page + itemsPerPage, packs.size()); i++) {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("../GUI/CartPack.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();
                            anchorPane.getStyleClass().add("ct");
                            CartPackController itemController = fxmlLoader.getController();
                            itemController.setData(packs.get(i), myListener);
                            hbox.getChildren().add(anchorPane);
                            HBox.setMargin(anchorPane, new Insets(10)); // Marges entre les cartes
                        } catch (IOException ex) {
                            Logger.getLogger(IndexClientAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    return hbox;
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(IndexClientAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void puser(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AjoutAbonnementClient.fxml"));
        listAbonnementPane.getChildren().removeAll();
        listAbonnementPane.getChildren().setAll(fxml);

    }

//    @FXML
//    private void Consulter_Abonnement(ActionEvent event) throws IOException {
//        Parent fxml = FXMLLoader.load(getClass().getResource("ConsulterAbonnement.fxml"));
//        listAbonnementPane.getChildren().removeAll();
//        listAbonnementPane.getChildren().setAll(fxml);
//
//    }

    @FXML
    public void Click(javafx.event.ActionEvent event) throws SQLException, IOException {
        AbonnementService as = new AbonnementService();

        Abonnement ab = as.findOneByUser(Services.UserService.idUser);//  24 19
        String etat = ab.getEtatAbonnement();
        if (etat != null) {
            if (etat.equalsIgnoreCase("actif")) {
                String title = "Vous avez un abonnement en cours \n " + " Prendra fin le " + ab.getDateFin().toString();
                String message = "Vous avez un abonnement en cours!";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
//                Parent page2 = FXMLLoader.load(getClass().getResource("../GUI/ConsulterAbonnement.fxml"));
//                Scene scene2 = new Scene(page2);
//                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                app_stage.setScene(scene2);
//                app_stage.show();
                Parent fxml = FXMLLoader.load(getClass().getResource("ConsulterAbonnement.fxml"));
                listAbonnementPane.getChildren().removeAll();
                listAbonnementPane.getChildren().setAll(fxml);
            } else if (etat.equalsIgnoreCase("non actif")) {
                String title = "Votre abonnement a expiré!";
                String message = "Votre abonnement a expiré!";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(title);
                tray.setNotificationType(NotificationType.NOTICE);
                tray.showAndDismiss(Duration.millis(3000));
            }
        }
    }

}
