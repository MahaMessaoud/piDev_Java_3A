/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import java.awt.Button;
import Entities.Fournisseur;
import Services.FournisseurService;
import Utils.MyDB;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class ListFournisseurController implements Initializable {

    @FXML
    private AnchorPane ListFourniPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Fournisseur p = null;
        try {

            affichall();
        } catch (IOException ex) {
            Logger.getLogger(ListFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListFournisseurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private Button versAjoutF;

    @FXML
    private Button retourListF2;

    @FXML
    private TableView<Fournisseur> tableF;

    @FXML
    private TableColumn<Fournisseur, Integer> idF;

    @FXML
    private TableColumn<Fournisseur, String> nomF;

    @FXML
    private TableColumn<Fournisseur, String> contactF;

    @FXML
    private TableColumn<Fournisseur, String> emailF;

    @FXML
    private TableColumn<Fournisseur, String> adresseF;

    public ObservableList<Fournisseur> fournisseurs = FXCollections.observableArrayList();

    public void handleHomeButtonAction(ActionEvent event) throws IOException {
        Parent navSideBarParent = FXMLLoader.load(getClass().getResource("blog.GUI/NavSideBar.fxml"));
        Scene navSideBarScene = new Scene(navSideBarParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(navSideBarScene);
        appStage.show();
    }

//    @FXML
//    private void puser(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/backOfficeHome.fxml"));
//        Parent root = loader.load();
//        homeButton.getScene().setRoot(root);
//
//    }
    @FXML
    void back_GF() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("GestionRessource.fxml"));
        ListFourniPane.getChildren().removeAll();
        ListFourniPane.getChildren().setAll(fxml);

    }
//    @FXML
//    private void retourGestion(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/GestionRessource.fxml"));
//        Parent root = loader.load();
//        retourListF2.getScene().setRoot(root);
//
//    }

    @FXML
    private void versAjouterF(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjoutFournisseur.fxml"));
//        Parent root = loader.load();
//        versAjoutF.getScene().setRoot(root);
        Parent fxml = FXMLLoader.load(getClass().getResource("AjoutFournisseur.fxml"));
        ListFourniPane.getChildren().removeAll();
        ListFourniPane.getChildren().setAll(fxml);

    }

    public Connection conx = MyDB.getInstance().getConx();

    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
    private Button btnClose;

    private void affichall() throws SQLException, IOException {
        fournisseurs.clear();
        try {
            String sql = "Select * From fournisseur";
            PreparedStatement pstmt = conx.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Fournisseur p = new Fournisseur();
                p.setId(rs.getInt("id"));//or rst.getInt(1)
                p.setNom_fournisseur(rs.getString("nom_fournisseur"));//or rst.getInt(1)
                p.setContact_fournisseur(rs.getString("contact_fournisseur"));//or rst.getInt(1)
                p.setEmail_fournisseur(rs.getString("email_fournisseur"));//or rst.getInt(1)
                p.setAdresse_fournisseur(rs.getString("adresse_fournisseur"));//or rst.getInt(1)
                fournisseurs.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        idF.setCellValueFactory(new PropertyValueFactory<Fournisseur, Integer>("id"));

        nomF.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("nom_fournisseur"));

        contactF.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("contact_fournisseur"));

        emailF.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("email_fournisseur"));

        adresseF.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("adresse_fournisseur"));

        tableF.setItems(fournisseurs);
        searchFournisseur();
    }

//Selection d'un element du table:
    @FXML
    private void handleTableRowSelection() {
        Fournisseur selectedFournisseur = tableF.getSelectionModel().getSelectedItem();
        if (selectedFournisseur != null) {
            // Call a method to load the detail view with the selected fournisseur
            loadFournisseurDetailView(selectedFournisseur);
        }
    }

    private void loadFournisseurDetailView(Fournisseur fournisseur) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailFournisseur.fxml"));
            Parent root = loader.load();
            DetailFournisseurController controller = loader.getController();
            controller.setFournisseur(fournisseur);
             ListFourniPane.getChildren().removeAll();
        ListFourniPane.getChildren().setAll(root);
           /* Scene scene = new Scene(root);
            Stage stage = (Stage) tableF.getScene().getWindow();
            stage.setScene(scene);
            stage.show();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//fin selection d'un element.
    @FXML
    private TextField searchfield;
    private FournisseurService se = new FournisseurService();

    private void searchFournisseur() throws SQLException {
        FilteredList<Fournisseur> filteredData = new FilteredList<>(FXCollections.observableArrayList(se.getAll()), p -> true);
        searchfield.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(fournisseur -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (fournisseur.getNom_fournisseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fournisseur.getContact_fournisseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fournisseur.getEmail_fournisseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fournisseur.getAdresse_fournisseur().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Fournisseur> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableF.comparatorProperty());
        tableF.setItems(sortedData);
    }

}
