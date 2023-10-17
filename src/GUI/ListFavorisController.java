/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Favoris;
import Entities.Plat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import services.FavorisServices;
import services.PlatServices;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ListFavorisController implements Initializable {

    @FXML
    private VBox Vboxlist;
    @FXML
    private ComboBox combo;
    @FXML
    private RadioButton afficher;
    @FXML
    private Button ajouter;
    @FXML
    private TextField tfRecherche;
    @FXML
    private Button retour;

    public Connection conx;
    public Statement stm;
    @FXML
    private AnchorPane listFavPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
        PlatServices service = new PlatServices();
        List<Plat> listPlat;
        try {

            listPlat = service.afficherListe();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (Plat plat : listPlat) {
                list.addAll(plat.getNom());
            }
            if (combo != null) {
                combo.setItems(list);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
        }

        afficher.setOnAction((event) -> {
            try {
                loadData();
            } catch (SQLException ex) {
                Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ajouter.setOnAction((event) -> {
            Favoris p = new Favoris();

            PlatServices pp = new PlatServices();

            try {

                p.setIdPlatF(pp.getidPlat(combo.getSelectionModel().getSelectedItem().toString()));
                p.setNomplat(combo.getSelectionModel().getSelectedItem().toString());
                p.setNBR(1);
            } catch (SQLException ex) {
                Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
            }
            FavorisServices ser = new FavorisServices();
            try {
                if (ser.getNBR(p.getIdPlatF()) == 0) {
                    ser.ajouterFavori1(p);
                } else {
                    ser.updateFavoris(p.getIdPlatF(), ser.getNBR(p.getIdPlatF()) + 1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        recherche();
        /* try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
//        retour.setOnAction((ActionEvent event) -> {
//            redirectToList1();
//        });

    }

    private void redirectToList1() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXML_FrontRestaurant.fxml"));
            Scene c = new Scene(root);
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void redirectToList(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("FXML_FrontRestaurant.fxml"));
        listFavPane.getChildren().removeAll();
        listFavPane.getChildren().setAll(fxml);
    }

    private void loadData() throws SQLException, FileNotFoundException {
        List<Favoris> liste;
        FavorisServices FavService = new FavorisServices();
        liste = FavService.getFavorisList();
        Vboxlist.getChildren().clear();
        for (Favoris fav : liste) {
            Node node = createEventNode(fav);
            if (node != null) {
                Vboxlist.getChildren().add(node);
            }
        }
    }

    private Node createEventNode(Favoris event) throws FileNotFoundException {
        // Créer un VBox pour contenir le nom du prix
        if (event == null) {
            return null;
        } else {
            VBox articleBox = new VBox();
            articleBox.setPrefSize(150, 150);
            articleBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 3);");

            // Créer des labels pour le nom du plat 
            //  Label namelabel = new Label(event.getIdPlatF());
            Label namelabel = new Label(String.valueOf("Nom: " + event.getNomplat()));
            namelabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
            namelabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(namelabel);

            Label nbrlabel = new Label(String.valueOf("Nombre: " + event.getNBR()));
            nbrlabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
            nbrlabel.setAlignment(Pos.CENTER);
            articleBox.getChildren().add(nbrlabel);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(articleBox);

            // Ajouter un style CSS au VBox pour qu'il soit bien présenté dans le FlowPane
            articleBox.setStyle("-fx-padding: 10px; -fx-background-color: #00000000; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-border-color: #720000; -fx-border-width: 2px;");

            // Définir les contraintes de taille pour le VBox et l'ImageView
            articleBox.setPrefWidth(150);
            articleBox.setMaxWidth(150);
            Vboxlist.getChildren().add(articleBox);
            Vboxlist.setMargin(articleBox, new Insets(5, 5, 5, 5));

            // Retourner le StackPane contenant l'ImageView et le VBox
            return stackPane;
        }

    }

//Rechrche 
    private void recherche() {
        FavorisServices platService = new FavorisServices();
        // Filtrer les plats en utilisant le nouveau texte de recherche
        tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
            List<Favoris> platsRecherche = null;
            try {
                platsRecherche = platService.recuperer().stream()
                        .filter(plat -> plat.getNomplat().toLowerCase().contains(newValue.toLowerCase()))
                        .collect(Collectors.toList());
            } catch (SQLException ex) {
                Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Vider le VBox actuel pour afficher les plats filtrés
            Vboxlist.getChildren().clear();
            for (Favoris plat : platsRecherche) {
                Node platNode = null;
                try {
                    platNode = createEventNode(plat);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ListFavorisController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (platNode != null) {
                    Vboxlist.getChildren().add(platNode); // ajouter le nouveau noeud dans le VBox
                }
            }
            Vboxlist.layout();
        });
    }

}
