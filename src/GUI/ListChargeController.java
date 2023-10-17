/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Charge;
import Services.ChargeService;
import Services.FournisseurService;
import Services.MaterielService;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class ListChargeController implements Initializable {

    @FXML
    private TableView<Charge> tableCh;

    @FXML
    private TableColumn<Charge, Integer> colIdCh;

    @FXML
    private TableColumn<Charge, String> colFournisseurCh;

    @FXML
    private TableColumn<Charge, String> colMaterielCh;

    @FXML
    private TableColumn<Charge, String> colDateCh;

    @FXML
    private TableColumn<Charge, Integer> colQttCh;

    @FXML
    private TextField searchM;

    private Button homeButton;

    @FXML
    private Button ajouterCh;

    @FXML
    private Button returnGestionCh;
    public ObservableList<Charge> fournisseurs = FXCollections.observableArrayList();
    @FXML
    private AnchorPane ChargePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            affichallCh();
        } catch (SQLException ex) {
            Logger.getLogger(ListChargeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            searchFournisseur();
        } catch (SQLException ex) {
            Logger.getLogger(ListChargeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void puser(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/backOfficeHome.fxml"));
        Parent root = loader.load();
        homeButton.getScene().setRoot(root);

    }

    @FXML
    void back_GF() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("GestionRessource.fxml"));
        ChargePane.getChildren().removeAll();
        ChargePane.getChildren().setAll(fxml);

    }
//    @FXML
//    private void retourGestion(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionRessource.fxml"));
//        Parent root = loader.load();
//        returnGestionCh.getScene().setRoot(root);
//
//    }
    Button btnClose;

    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

    public Connection conx = MyDB.getInstance().getConx();

    private void affichallCh() throws SQLException, IOException {
        fournisseurs.clear();
        try {
            String sql = "Select * From charge";
            PreparedStatement pstmt = conx.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FournisseurService cs = new FournisseurService();
                MaterielService ms = new MaterielService();
                Charge p = new Charge();
                p.setId(rs.getInt("id"));//or rst.getInt(1)
                p.setFournisseur_id(cs.getOneById(rs.getInt("fournisseur_id")).getNom_fournisseur());//or rst.getInt(1)
                p.setMateriel_id(ms.getOneById(rs.getInt("materiel_id")).getNom_materiel());//or rst.getInt(1)
                p.setQuantite_charge(rs.getInt("quantite_charge"));//or rst.getInt(1)
                p.setDate_arrivage_charge(rs.getString("date_arrivage_charge"));//or rst.getInt(1)
                fournisseurs.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        colIdCh.setCellValueFactory(new PropertyValueFactory<Charge, Integer>("id"));

        colFournisseurCh.setCellValueFactory(new PropertyValueFactory<Charge, String>("fournisseur_id"));

        colMaterielCh.setCellValueFactory(new PropertyValueFactory<Charge, String>("materiel_id"));

        colQttCh.setCellValueFactory(new PropertyValueFactory<Charge, Integer>("quantite_charge"));

        colDateCh.setCellValueFactory(new PropertyValueFactory<Charge, String>("date_arrivage_charge"));

        tableCh.setItems(fournisseurs);

    }

    @FXML
    private void versAjouterCh(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjoutCharge.fxml"));
//        Parent root = loader.load();
//        ajouterCh.getScene().setRoot(root);
        Parent fxml = FXMLLoader.load(getClass().getResource("AjoutCharge.fxml"));
        ChargePane.getChildren().removeAll();
        ChargePane.getChildren().setAll(fxml);

    }

    @FXML
    private void handleTableRowSelection() {
        Charge selectedFournisseur = tableCh.getSelectionModel().getSelectedItem();
        if (selectedFournisseur != null) {
            // Call a method to load the detail view with the selected fournisseur
            loadFournisseurDetailView(selectedFournisseur);
        }
    }

    private void loadFournisseurDetailView(Charge fournisseur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailCharge.fxml"));
            Parent root = loader.load();
            DetailChargeController controller = loader.getController();
            controller.setFournisseur(fournisseur);
            ChargePane.getChildren().removeAll();
            ChargePane.getChildren().setAll(root);
            /*Scene scene = new Scene(root);
            Stage stage = (Stage) tableCh.getScene().getWindow();
            stage.setScene(scene);
            stage.show();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ChargeService se = new ChargeService();

    private void searchFournisseur() throws SQLException {
        FilteredList<Charge> filteredData = new FilteredList<>(FXCollections.observableArrayList(se.getAll2()), p -> true);
        searchM.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(fournisseur -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String qttM = String.valueOf(fournisseur.getQuantite_charge());
                String lowerCaseFilter = newValue.toLowerCase();
                if (fournisseur.getMateriel_id().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fournisseur.getFournisseur_id().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fournisseur.getDate_arrivage_charge().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (qttM.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Charge> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCh.comparatorProperty());
        tableCh.setItems(sortedData);
    }

    @FXML
    private AnchorPane rootPane;

    private void showStatistics() {

        // Create the AnchorPane to display the statistics
        AnchorPane statsPane = new AnchorPane();
        statsPane.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px;");
        AnchorPane.setTopAnchor(statsPane, 50.0);
        AnchorPane.setBottomAnchor(statsPane, 50.0);
        AnchorPane.setLeftAnchor(statsPane, 50.0);
        AnchorPane.setRightAnchor(statsPane, 50.0);

        // Calculate the mean and standard deviation of the data
        double[] data = {2.5, 4.7, 3.2, 5.1, 6.4, 7.2};
        double mean = Arrays.stream(data).average().orElse(Double.NaN);
        double stdDev = Math.sqrt(Arrays.stream(data)
                .map(x -> Math.pow(x - mean, 2))
                .average().orElse(Double.NaN));

        // Create the labels to display the mean and standard deviation
        Label meanLabel = new Label("Mean: " + String.format("%.2f", mean));
        Label stdDevLabel = new Label("Standard Deviation: " + String.format("%.2f", stdDev));
        VBox vBox = new VBox(meanLabel, stdDevLabel);
        vBox.setSpacing(10.0);
        vBox.setPadding(new Insets(10.0));

        // Create the button to close the statistics pane
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            //closeStatistics();
        });
        AnchorPane.setBottomAnchor(closeButton, 10.0);
        AnchorPane.setRightAnchor(closeButton, 10.0);

        // Add the labels and button to the statistics pane
        statsPane.getChildren().addAll(vBox, closeButton);

        // Add the statistics pane to the main AnchorPane
        rootPane.getChildren().add(statsPane);
    }

//    private void closeStatistics() {
//
//        // Remove the statistics pane from the main AnchorPane
//        rootPane.getChildren().remove(statsPane);
//    }
}
