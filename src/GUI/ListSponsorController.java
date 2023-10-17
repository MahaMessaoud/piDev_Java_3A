/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Sponsor;
import Services.SponsorService;
import Services.UserService;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class ListSponsorController implements Initializable {

    @FXML
    private Button AjoutS;
    @FXML
    TableView<Sponsor> TableSponsor;
    @FXML
    private TableColumn<Sponsor, Integer> ids;
    @FXML
    private TableColumn<Sponsor, String> nomSps;
    @FXML
    private TableColumn<Sponsor, Double> Dsps;
    @FXML

    private TableColumn<Sponsor, String> Isps;
    ObservableList<Sponsor> obslistsp = FXCollections.observableArrayList();
    Sponsor sponsor = new Sponsor();
    @FXML
    private Button closW;

    @FXML
    private TableColumn<Sponsor, Void> act = new TableColumn<>("Actions");
    @FXML
    private AnchorPane listSps;
    @FXML
    private TextField txtSearchSponsor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Load();

    }

    @FXML
    void open_Ajout_Sps() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("ajoutSponsor.fxml"));
        listSps.getChildren().removeAll();
        listSps.getChildren().setAll(fxml);
    }

    

    @FXML
    public void closeW(ActionEvent event) {
        Stage stage = (Stage) closW.getScene().getWindow();
        //System.out.println("hi");
        stage.close();
    }

    public void Load() {
        SponsorService Sps = new SponsorService();
        TableSponsor.setEditable(true);

        Sps.afficherListe().stream().forEach((p) -> {
            obslistsp.add(p);
        });
        ids.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomSps.setCellValueFactory(new PropertyValueFactory<>("nom_sponsor"));
        nomSps.setCellFactory(TextFieldTableCell.forTableColumn());
        nomSps.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sponsor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sponsor, String> event) {
                Sponsor sp = event.getRowValue();
                sp.setNom_sponsor(event.getNewValue());
                SponsorService Spss = new SponsorService();
                Spss.modifier(sp);
            }
        });

        Dsps.setCellValueFactory(new PropertyValueFactory<>("donnation"));
        Dsps.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        Dsps.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sponsor, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sponsor, Double> event) {
                Sponsor sp = event.getRowValue();
                sp.setDonnation(event.getNewValue());
                SponsorService Spss = new SponsorService();
                Spss.modifier(sp);
            }
        });

        Isps.setCellValueFactory(new PropertyValueFactory<>("image"));
        Isps.setCellFactory(TextFieldTableCell.forTableColumn());
        Isps.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Sponsor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Sponsor, String> event) {
                Sponsor sp = event.getRowValue();
                sp.setImage(event.getNewValue());
                SponsorService Spss = new SponsorService();
                Spss.modifier(sp);
            }
        });

       // act.setPrefWidth(198.39993286132812);
        act.setCellFactory(column -> {
            return new TableCell<Sponsor, Void>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        Sponsor sponsor = getTableView().getItems().get(getIndex());
                        obslistsp.remove(sponsor);
                        Sps.supprimer(sponsor);
                        deleteButton.setStyle("-fx-background-color: #720000; -fx-text-fill: white;");
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            };
        });

        TableSponsor.setItems(obslistsp);

    }
//    @FXML
//    private void getAddView(MouseEvent event) throws IOException {
//
//        Parent page2 = FXMLLoader.load(getClass().getResource("ajoutSponsor.fxml"));
//
//        Scene scene2 = new Scene(page2);
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app_stage.setScene(scene2);
//        app_stage.show();
//    }

//   @FXML
//    private void AddV(ActionEvent event) throws IOException {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutSponsor.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage dialog = new Stage();
//            dialog.setScene(scene);
//            dialog.initModality(Modality.APPLICATION_MODAL);
//            dialog.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @FXML
//    private void editSponsor(ActionEvent event) throws IOException {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierSponsor.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage dialog = new Stage();
//            dialog.setScene(scene);
//            dialog.initModality(Modality.APPLICATION_MODAL);
//            dialog.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @FXML
//    private void AddV(MouseEvent event) {
//    }
    @FXML
    private void AddV(MouseEvent event) {
    }

}
