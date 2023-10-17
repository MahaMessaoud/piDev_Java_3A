/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pack;

import Services.PackService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class IndexPackAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Pack> packTable;
    @FXML
    private TableColumn<Pack, Integer> id;
    @FXML
    private TableColumn<Pack, String> type_pack;

    @FXML
    private TableColumn<Pack, Double> montantPack;

    @FXML
    private TableColumn<Pack, Integer> dureePack;

    @FXML
    private TableColumn<Pack, String> descriptionPack;

    @FXML
    private TableColumn<Pack, Integer> placesPack;

    @FXML
    private TableColumn<Pack, Integer> disponibilitePack;
    @FXML
    private TableColumn<Pack, Void> action;
    ObservableList<Pack> obslistsp = FXCollections.observableArrayList();
    @FXML
    private Button bntAjouterPack;
    @FXML
    private Button btnAbonnements;
    @FXML
    private Button goStatistiques;
    @FXML
    private TextField searchP;
    @FXML
    private BarChart<String, Number> stat;

    @FXML
    private Label total;

    @FXML
    private Label min;

    @FXML
    private Label average;

    @FXML
    private Label max;
    @FXML
    private VBox vbox;
    
    @FXML
    private AnchorPane listPackPane;

    @FXML
    void open_AjoutPack() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("ajoutPack.fxml"));
        listPackPane.getChildren().removeAll();
        listPackPane.getChildren().setAll(fxml);
    }

    @FXML
    void back_GestionAbonnemet() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("gestionAbonnement.fxml"));
        listPackPane.getChildren().removeAll();
        listPackPane.getChildren().setAll(fxml);
        
    }

    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Load();
        } catch (SQLException ex) {
            Logger.getLogger(IndexPackAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        statistique();
    }

    @FXML
    public void Load() throws SQLException {
        PackService Sps = new PackService();
        Sps.getAll().stream().forEach((p) -> {
            obslistsp.add(p);
        });
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type_pack.setCellValueFactory(new PropertyValueFactory<>("typePack"));
        type_pack.setCellFactory(TextFieldTableCell.forTableColumn());
        type_pack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, String> event) {
                Pack a = event.getRowValue();
                a.setTypePack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        montantPack.setCellValueFactory(new PropertyValueFactory<>("montantPack"));
        montantPack.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        montantPack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, Double> event) {
                Pack a = event.getRowValue();
                a.setMontantPack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        dureePack.setCellValueFactory(new PropertyValueFactory<>("dureePack"));
        dureePack.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dureePack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, Integer> event) {
                Pack a = event.getRowValue();
                a.setDureePack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        descriptionPack.setCellValueFactory(new PropertyValueFactory<>("descriptionPack"));
        descriptionPack.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionPack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, String> event) {
                Pack a = event.getRowValue();
                a.setDescriptionPack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        placesPack.setCellValueFactory(new PropertyValueFactory<>("placesPack"));
        placesPack.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        placesPack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, Integer> event) {
                Pack a = event.getRowValue();
                a.setPlacesPack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        disponibilitePack.setCellValueFactory(new PropertyValueFactory<>("disponibilitePack"));
        disponibilitePack.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        disponibilitePack.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Pack, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Pack, Integer> event) {
                Pack a = event.getRowValue();
                a.setDisponibilitePack(event.getNewValue());
                PackService as = new PackService();
                as.modifier(a);
            }
        });
        packTable.setItems(obslistsp);

        action.setCellFactory(column -> {
            return new TableCell<Pack, Void>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        Pack pack = getTableView().getItems().get(getIndex());
                        obslistsp.remove(pack);
                        Sps.Supprimer(pack);
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
        searchPack();
    }
    public PackService se = new PackService();

    public void searchPack() throws SQLException {
        FilteredList<Pack> filteredData = new FilteredList<>(FXCollections.observableArrayList(se.getAll()), p -> true);
        searchP.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pack -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String duree = String.valueOf(pack.getDureePack());
                String montant = String.valueOf(pack.getMontantPack());
                String pl = String.valueOf(pack.getPlacesPack());
                String dis = String.valueOf(pack.getDisponibilitePack());
                String lowerCaseFilter = newValue.toLowerCase();
                if (pack.getTypePack().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pack.getDescriptionPack().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (duree.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (montant.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pl.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (dis.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Pack> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(packTable.comparatorProperty());
        packTable.setItems(sortedData);
    }

    public void statistique() {
        PackService Sps = new PackService();

        List<Pack> packs = null;
        packs = Sps.getAll();

        // Créer les axes pour le graphique
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Type Pack");
        yAxis.setLabel("Places disponibles");

        // Créer la série de données à afficher
        XYChart.Series series = new XYChart.Series();
        series.setName("Places disponibles par pack");
        for (Pack pack : packs) {
            series.getData().add(new XYChart.Data<>(pack.getTypePack(), pack.getPlacesPack()));
        }

        // Créer le graphique et ajouter la série de données
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Statistiques de places disponibles");
        lineChart.getData().add(series);

        // Afficher le graphique dans votre scène
        //stat.setCreateSymbols(false);
        stat.getData().add(series);
        int totalPlaces = 0;
        int minPlaces = Integer.MAX_VALUE;
        int maxPlaces = Integer.MIN_VALUE;
        for (Pack pack : packs) {
            totalPlaces += pack.getPlacesPack();
            minPlaces = Math.min(minPlaces, pack.getPlacesPack());
            maxPlaces = Math.max(maxPlaces, pack.getPlacesPack());
        }
        double averagePlaces = totalPlaces / (double) packs.size();

        // Afficher les statistiques dans des labels
        total = new Label("Nombre total de places réservées : " + totalPlaces);
        total.getStyleClass().add("statistiqueText");
        average = new Label("Moyenne de places réservées : " + averagePlaces);
        average.getStyleClass().add("statistiqueText");
        min = new Label("Minimum de places réservées : " + minPlaces);
        min.getStyleClass().add("statistiqueText");
        max = new Label("Maximum de places réservées : " + maxPlaces);
        max.getStyleClass().add("statistiqueText");
        lineChart.getStyleClass().add("my-bar-chart");

        vbox.getChildren().add(total);
        vbox.getChildren().add(average);
        vbox.getChildren().add(min);
        vbox.getChildren().add(max);

    }
}
