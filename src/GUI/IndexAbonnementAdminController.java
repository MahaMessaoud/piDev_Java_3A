/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Abonnement;
import Entities.Pack;
import Services.AbonnementService;
import Services.PackService;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class IndexAbonnementAdminController implements Initializable {
  @FXML
    private Button btnAbonnements;
    @FXML
    private TableView<Abonnement> AbonnementTable;
    @FXML
    private TableColumn<Abonnement, Integer> id;
    @FXML
    private TableColumn<Abonnement, Integer> pack;

    @FXML
    private TableColumn<Abonnement, Integer> user;

    @FXML
    private TableColumn<Abonnement, Date> dateAchat;

    @FXML
    private TableColumn<Abonnement, Date> dateFin;

    @FXML
    private TableColumn<Abonnement, String> etatAbonnement;

    @FXML
    private TableColumn<Abonnement, String> codePromo;

    @FXML
    private TableColumn<Abonnement, Double> montantAbonnement;
    @FXML
    private TableColumn<Abonnement, Void> action;
    ObservableList<Abonnement> obslistsp = FXCollections.observableArrayList();
    @FXML
    private Button bntAjouterAbonnement;
    @FXML
    private TextField searchA;

    @FXML
    private Button backIndex;

    @FXML
    private Button btnValider;

    @FXML
    private Button bntReturnAbonnement;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnUsers;

    @FXML
    private Button btnGestionPlanning;

    @FXML
    private Button btnCompetitions;

    @FXML
    private Button btnRestaurants;

    @FXML
    private Button btnMateriaux;
    
    @FXML
    private AnchorPane listAbPane;
    
    @FXML
    void open_AjoutAbonnement() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("AjoutAbonnementAdmin.fxml"));
        listAbPane.getChildren().removeAll();
        listAbPane.getChildren().setAll(fxml);
    }

    @FXML
    void back_GestionAbonnemet() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionAbonnement.fxml"));
        Parent root = loader.load();
        btnAbonnements.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Load();
        } catch (SQLException ex) {
            Logger.getLogger(IndexAbonnementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    @FXML
    public void Load() throws SQLException {

        AbonnementService Sps = new AbonnementService();
        try {
            Sps.getAll
        ().stream().forEach((p) -> {
                obslistsp.add(p);
            });
        } catch (SQLException ex) {
            Logger.getLogger(IndexPackAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
         pack.setCellValueFactory(new PropertyValueFactory<>("packId"));
        pack.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        pack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Abonnement, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Abonnement, Integer> event) {
                Abonnement a = event.getRowValue();
                a.setPackId(event.getNewValue());
                AbonnementService as = new AbonnementService();
                as.modifier(a);
            }
        });
        user.setCellValueFactory(new PropertyValueFactory<>("userId"));
        user.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        user.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Abonnement, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Abonnement, Integer> event) {
                Abonnement a = event.getRowValue();
                a.setUserId(event.getNewValue());
                AbonnementService as = new AbonnementService();
                as.modifier(a);
            }
        });
        dateAchat.setCellValueFactory(new PropertyValueFactory<>("dateAchat"));
        dateAchat.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
            private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public String toString(Date object) {
                return dateFormat.format(object);
            }

            @Override
            public Date fromString(String string) {
                try {
                    // Parse the string into a Date object using the defined format
                    return dateFormat.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // If the string can't be parsed, return null
                    return null;
                }
            }
        }));
        dateAchat.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Abonnement, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Abonnement, Date> event) {
                Abonnement a = event.getRowValue();
                a.setDateAchat(event.getNewValue());
                AbonnementService as = new AbonnementService();
                as.modifier(a);
            }
        });
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        dateFin.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
            private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            @Override
            public String toString(Date object) {
                return dateFormat.format(object);
            }

            @Override
            public Date fromString(String string) {
                try {
                    // Parse the string into a Date object using the defined format
                    return dateFormat.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // If the string can't be parsed, return null
                    return null;
                }
            }
        }));
        dateFin.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Abonnement, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Abonnement, Date> event) {
                Abonnement a = event.getRowValue();
                a.setDateFin(event.getNewValue());
                AbonnementService as = new AbonnementService();
                as.modifier(a);
            }
        });
        etatAbonnement.setCellValueFactory(new PropertyValueFactory<>("etatAbonnement"));
        etatAbonnement.setCellFactory(TextFieldTableCell.forTableColumn());
        etatAbonnement.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Abonnement, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Abonnement, String> event) {
                Abonnement a = event.getRowValue();
                a.setEtatAbonnement(event.getNewValue());
                AbonnementService as = new AbonnementService();
                as.modifier(a);
            }
        });
        codePromo.setCellValueFactory(new PropertyValueFactory<>("codePromo"));
        codePromo.setCellFactory(TextFieldTableCell.forTableColumn());
        codePromo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Abonnement, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Abonnement, String> event) {
                Abonnement a = event.getRowValue();
                a.setCodePromo(event.getNewValue());
                AbonnementService as = new AbonnementService();
                as.modifier(a);
            }
        });
        montantAbonnement.setCellValueFactory(new PropertyValueFactory<>("montantAbonnement"));
        montantAbonnement.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        montantAbonnement.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Abonnement, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Abonnement, Double> event) {
                Abonnement a = event.getRowValue();
                a.setMontantAbonnement(event.getNewValue());
                AbonnementService as = new AbonnementService();
                as.modifier(a);
            }
        });
        AbonnementTable.setItems(obslistsp);

        action.setCellFactory(column -> {
            return new TableCell<Abonnement, Void>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        Abonnement abonnement = getTableView().getItems().get(getIndex());
                        obslistsp.remove(abonnement);
                        Sps.Supprimer(abonnement);
                        deleteButton.setStyle("-fx-background-color: #720000;");
                        deleteButton.setStyle("-fx-text-fill: #fff;");
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
        searchAbonnement();
    }
    public AbonnementService se = new AbonnementService();

    public void searchAbonnement() throws SQLException {
        FilteredList<Abonnement> filteredData = new FilteredList<>(FXCollections.observableArrayList(se.getAll()), p -> true);
        searchA.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(abonnement -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String dta = String.valueOf(abonnement.getDateAchat());
                String dtf = String.valueOf(abonnement.getDateFin());
                String mn = String.valueOf(abonnement.getMontantAbonnement());
                //String pl = String.valueOf(pack.getPlacesPack());
                //String dis = String.valueOf(pack.getDisponibilitePack());
                String lowerCaseFilter = newValue.toLowerCase();
                if (abonnement.getEtatAbonnement().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (abonnement.getCodePromo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (dta.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (dtf.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (mn.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Abonnement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(AbonnementTable.comparatorProperty());
        AbonnementTable.setItems(sortedData);
    }
}
