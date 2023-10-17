/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reservation;
import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.PlatServices;
import Services.UserService;
import services.ReservationServices;
import Utils.MyDB;
import gui.Mailing;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_ReservationController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPlats;
    @FXML
    private Button btnReservations;
    @FXML
    private Pane panePlats;
    @FXML
    private VBox Items;
    @FXML
    private Pane paneMenus;
    @FXML
    private Pane paneReservations;
    @FXML
    private Pane paneHome;
    @FXML
    private Button refresh;
    @FXML
    private Button Stat;
    @FXML
    private TableColumn<Reservation, Integer> idreservation;

    @FXML
    private TableColumn<Reservation, Integer> idplat;
    @FXML
    private TableColumn<Reservation, Date> date;

    @FXML
    private TableColumn<Reservation, String> userid;

    @FXML
    private TableView tvReservation;
    @FXML
    private Button Mailing;
    @FXML
    private TextField saisieEmail;
    @FXML
    private Button captureEcran;
    @FXML
    private AnchorPane monAnchorPane;

    public Connection conx;
    public Statement stm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
        /*   btnHome.setOnAction((ActionEvent event) -> {
            GoToRestau();
        });
        btnPlats.setOnAction((ActionEvent event) -> {
            GoToPlat();
        });
        btnMenus.setOnAction((ActionEvent event) -> {
            GoToMenu();
        });*/
        refresh.setOnAction((ActionEvent event) -> {
            try {
                showActions();
            } catch (SQLException ex) {
                Logger.getLogger(FXML_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Mailing.setOnAction((ActionEvent event) -> {
            Mailing m = new Mailing();
            String destinataire = saisieEmail.getText();
            try {
                m.sendEmail(destinataire);

            } catch (Exception ex) {
                ex.printStackTrace();
                // Afficher un message d'erreur à l'utilisateur, par exemple :
                Alert alert = new Alert(AlertType.ERROR, "Erreur lors de l'envoi du mail : " + ex.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        });

        captureEcran.setOnAction(event -> {
            CaptureEcran cap = new CaptureEcran();
            try {
                cap.capturer(monAnchorPane);
                System.out.println("La capture d'écran a été générée avec succès !");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Capture d'écran");
                alert.setHeaderText("Capture d'écran générée avec succès !");
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println("Erreur lors de la capture d'écran : " + ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la capture d'écran !");
                alert.setContentText("Une erreur est survenue lors de la capture d'écran : " + ex.getMessage());
                alert.showAndWait();
            }
        });

        Stat.setOnAction((ActionEvent event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXML_STAT.fxml"));
                monAnchorPane.getChildren().removeAll();
                monAnchorPane.getChildren().setAll(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /*  private void GoToRestau(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Restaurant.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnHome.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void GoToPlat(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Plat.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnPlats.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      private void GoToMenu(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Menu.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnMenus.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     */
    @FXML
    public ObservableList<Reservation> getReservationList() {
        conx = MyDB.getInstance().getConx();
        ObservableList<Reservation> ReservationList = FXCollections.observableArrayList();
        try {
            String query2 = "SELECT * from reservation ";
            PreparedStatement smt = conx.prepareStatement(query2);
            Reservation R;
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                R = new Reservation(rs.getInt("id"), rs.getInt("idplat_id"), rs.getDate("date"), rs.getString("userid"));
                ReservationList.add(R);
            }
            System.out.println(ReservationList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ReservationList;

    }

    @FXML
    public void ShowListe() throws SQLException {
        UserService us = new UserService();
        User u = new User();
        ObservableList<Reservation> list = getReservationList();
        List<String> list1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            u = us.findById(Integer.parseInt(list.get(i).getUser_id()));
            list1.add(u.getEmail());
        }
        idreservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        idplat.setCellValueFactory(new PropertyValueFactory<>("idplat_id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        userid.setCellValueFactory(features -> {
            Reservation reservation = features.getValue();
            int index = list.indexOf(reservation);
            String email = list1.get(index);
            return new SimpleStringProperty(email);
        });

        tvReservation.setItems(list);
    }

    @FXML
    public void showActions() throws SQLException {
        UserService us = new UserService();
        User u = new User();
        ObservableList<Reservation> list = getReservationList();
        List<String> list1 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            u = us.findById(Integer.parseInt(list.get(i).getUser_id()));
            list1.add(u.getEmail());
        }
        idreservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        idplat.setCellValueFactory(new PropertyValueFactory<>("idplat_id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        userid.setCellValueFactory(features -> {
            Reservation reservation = features.getValue();
            int index = list.indexOf(reservation);
            String email = list1.get(index);
            return new SimpleStringProperty(email);
        });

        TableColumn<Reservation, Void> colBtn = new TableColumn("Actions");

        Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>> cellFactory = new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
            @Override
            public TableCell<Reservation, Void> call(final TableColumn<Reservation, Void> param) {
                final TableCell<Reservation, Void> cell = new TableCell<Reservation, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Reservation data = getTableView().getItems().get(getIndex());
                            try {
                                SupprimerReservation(data);
                            } catch (SQLException ex) {
                                Logger.getLogger(FXML_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tvReservation.setItems(list);
        tvReservation.getColumns().addAll(colBtn);
    }

    private void SupprimerReservation(Reservation r) throws SQLException {
        ReservationServices u = new ReservationServices();
        try {
            u.Supprimer(r);

        } catch (SQLException ex) {
            Logger.getLogger(FXML_ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShowListe();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("EnergyBox :: Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Reservation supprimé");
        alert.showAndWait();
    }

}
