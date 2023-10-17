/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonnement;
import Entities.UserPackAbonnement;
import Services.AbonnementService;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class ConsulterAbonnementController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private Label etat;

    @FXML
    private Label type;

    @FXML
    private Label prix;

    @FXML
    private Button returnIndex;
    AbonnementService as = new AbonnementService();
    @FXML
    private AnchorPane ConsultAbPane;
    @FXML
    private HBox pack;

    @FXML
    void back_Abonnemets(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("IndexClientAbonnement.fxml"));
        ConsultAbPane.getChildren().removeAll();
        ConsultAbPane.getChildren().setAll(fxml);

    }

    public void initialize(URL location, ResourceBundle resources) {
        try {
            UserPackAbonnement ab = as.getUserPackAbonnementByidUser(Services.UserService.idUser);

            type.setText("Pack : " + String.valueOf(ab.getTypePack()));
            prix.setText("Prix : " + String.valueOf(ab.getMontantAbonnement()) + " DT");
            etat.setText("Etat d'abonnement : " + ab.getEtatAbonnement());

            Callback<DatePicker, DateCell> cellFactory = new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(DatePicker datePicker) {
                    return new DateCell() {

                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (empty) {
                                setText(null);
                                setStyle("");
                            } else {
                                try {
                                    Abonnement ab = as.findOneByUser(Services.UserService.idUser);
                                    Date dateAchat = ab.getDateAchat();
                                    Date dateFin = ab.getDateFin();
                                    Timestamp dateAchatTimestamp = new Timestamp(dateAchat.getTime());
                                    Timestamp dateFinTimestamp = new Timestamp(dateFin.getTime());
                                    Timestamp itemTimestamp = Timestamp.from(item.atStartOfDay(ZoneOffset.UTC).toInstant());

                                    setText(Integer.toString(item.getDayOfMonth()));

                                    if (dateAchatTimestamp.before(itemTimestamp) || dateFinTimestamp.after(itemTimestamp)) {
                                        setDisable(true);
                                        datePicker.getStyleClass().add("disabled-day"); // Ajout de la classe CSS
                                    } else {
                                        setDisable(false);
                                        if (itemTimestamp.equals(dateAchatTimestamp) || itemTimestamp.equals(dateFinTimestamp)) {
                                            datePicker.getStyleClass().add("disabled-day"); // Ajout de la classe CSS
                                        } else {
                                            datePicker.getStyleClass().add("active-day");
                                        }
                                    }

                                    if (dateAchatTimestamp.before(itemTimestamp) && dateFinTimestamp.after(itemTimestamp)) {
                                        getStyleClass().add("ma-classe-css"); // Ajout de la classe CSS
                                    }
                                } catch (SQLException ex) {
                                    Logger.getLogger(ConsulterAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    };
                }
            };

            // Ajout de la classe CSS sur le DatePicker
            // datePicker.getStyleClass().add("ma-classe-css");
            datePicker.setDayCellFactory(cellFactory);
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
