/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Materiel;
import Services.MaterielService;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class ListMaterielController implements Initializable {

    private Button homeButton;

    @FXML
    private Button statt;
    @FXML
    private Button retunToGestion;

    @FXML
    private Button ajoutListM;

    @FXML
    private TableView<Materiel> tableM;

    @FXML
    private TableColumn<Materiel, Integer> idM;

    @FXML
    private TableColumn<Materiel, String> nomM;

    @FXML
    private TableColumn<Materiel, String> refM;

    @FXML
    private TableColumn<Materiel, String> maintM;

    @FXML
    private TableColumn<Materiel, Integer> qtM;

    public ObservableList<Materiel> fournisseurs = FXCollections.observableArrayList();
    @FXML
    private AnchorPane ListMatPane;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            affichall();
        } catch (IOException ex) {
            Logger.getLogger(ListFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    Button btnClose;

    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
    public Connection conx = MyDB.getInstance().getConx();
//Selection d'un element du table:

    @FXML
    private void handleTableRowSelectionM() {
        Materiel selectedFournisseur = tableM.getSelectionModel().getSelectedItem();
        if (selectedFournisseur != null) {
            // Call a method to load the detail view with the selected fournisseur
            loadFournisseurDetailView(selectedFournisseur);
        }
    }

    private void loadFournisseurDetailView(Materiel fournisseur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailMateriel.fxml"));
            Parent root = loader.load();
            DetailMaterielController controller = loader.getController();
            controller.setFournisseurr(fournisseur);
                                      ListMatPane.getChildren().removeAll();
        ListMatPane.getChildren().setAll(root);

           /* Scene scene = new Scene(root);
            Stage stage = (Stage) tableM.getScene().getWindow();
            stage.setScene(scene);
            stage.show();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goStat(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/StatMateriel.fxml"));
        Parent root = loader.load();
                                 ListMatPane.getChildren().removeAll();
        ListMatPane.getChildren().setAll(root);

        
// retunToGestion.getScene().setRoot(root);

    }
     @FXML
  void back_GF() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("GestionRessource.fxml"));
        ListMatPane.getChildren().removeAll();
        ListMatPane.getChildren().setAll(fxml);
        
    }
//fin selection d'un element.

    private void affichall() throws SQLException, IOException {
        fournisseurs.clear();
        try {
            String sql = "Select * From materiel";
            PreparedStatement pstmt = conx.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Materiel p = new Materiel();
                p.setId(rs.getInt("id"));//or rst.getInt(1)
                p.setNom_materiel(rs.getString("nom_materiel"));//or rst.getInt(1)
                p.setReference_materiel(rs.getString("reference_materiel"));//or rst.getInt(1)
                p.setDate_maintenance((rs.getDate("date_maintenance_materiel").toString()));//or rst.getInt(1)
                p.setQuantite_materiel(rs.getInt("quantite_materiel"));//or rst.getInt(1)
                fournisseurs.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        idM.setCellValueFactory(new PropertyValueFactory<Materiel, Integer>("id"));

        nomM.setCellValueFactory(new PropertyValueFactory<Materiel, String>("nom_materiel"));

        refM.setCellValueFactory(new PropertyValueFactory<Materiel, String>("reference_materiel"));

        maintM.setCellValueFactory(new PropertyValueFactory<Materiel, String>("date_maintenance"));

        qtM.setCellValueFactory(new PropertyValueFactory<Materiel, Integer>("quantite_materiel"));

        tableM.setItems(fournisseurs);

        searchFournisseur();
    }

//    @FXML
//    private void retourGestion(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionRessource.fxml"));
//        Parent root = loader.load();
//        retunToGestion.getScene().setRoot(root);
//
//    }

    private void exitM(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BackOfficeHome.fxml"));
        Parent root = loader.load();
        homeButton.getScene().setRoot(root);



    }

    @FXML
    private void goToAjoutM(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjoutMateriel.fxml"));
//        Parent root = loader.load();
//        ajoutListM.getScene().setRoot(root);
Parent fxml = FXMLLoader.load(getClass().getResource("AjoutMateriel.fxml"));
        ListMatPane.getChildren().removeAll();
        ListMatPane.getChildren().setAll(fxml);

    }
    @FXML
    private TextField searchM;

    private MaterielService se = new MaterielService();

    private void searchFournisseur() throws SQLException {
        FilteredList<Materiel> filteredData = new FilteredList<>(FXCollections.observableArrayList(se.getAll()), p -> true);
        searchM.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(fournisseur -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String qttM = String.valueOf(fournisseur.getQuantite_materiel());
                String lowerCaseFilter = newValue.toLowerCase();
                if (fournisseur.getNom_materiel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fournisseur.getReference_materiel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fournisseur.getDate_maintenance().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (qttM.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Materiel> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableM.comparatorProperty());
        tableM.setItems(sortedData);
    }

}
