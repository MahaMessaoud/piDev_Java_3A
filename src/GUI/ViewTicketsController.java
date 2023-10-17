/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.beans.property.SimpleStringProperty;
import Entity.Competition;
import Entity.Ticket;
import Services.CompetitionServices;
import Services.TicketServices;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Salima
 */
public class ViewTicketsController implements Initializable {


     ObservableList<Ticket> data = FXCollections.observableArrayList();
     private TicketServices ccrd =new TicketServices();
    @FXML
    private TableColumn<Ticket, String> ticketNomComp;
    @FXML
    private TableColumn<Ticket, String> descTicket;
    @FXML
    private Button btSupp;
    @FXML
    private Button btAjout;
    @FXML
    private TableView<Ticket> tableTickets;
    @FXML
    private TableColumn<Ticket, Integer> id;
     public Connection conx;
     
      MyDB cnx = null;
    Statement st = null;
    @FXML
    private AnchorPane paneViewTicket;
    @FXML
    private Button btnReturn;
    @FXML
    private ImageView btRetour;
    @FXML
    private TextField searchField;
    
  
    /**
     * Initializes the controller class.
     */
 @Override
public void initialize(URL url, ResourceBundle rb) {
         try {
             // TODO
             data.clear();
             tableTickets.refresh();
             searchBox();
             viewBackTicket();
         } catch (SQLException ex) {
             Logger.getLogger(ViewTicketsController.class.getName()).log(Level.SEVERE, null, ex);
         }
}
@FXML
    void back_ViewBack_Competition() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("ViewBack.fxml"));
        paneViewTicket.getChildren().removeAll();
       paneViewTicket.getChildren().setAll(fxml);
    }
//     @FXML
//    private void NouvelleTicket(ActionEvent event) throws IOException {
//        Parent fxml = FXMLLoader.load(getClass().getResource("ajoutTicket.fxml"));
//        paneViewTicket.getChildren().removeAll();
//        paneViewTicket.getChildren().setAll(fxml);
//
//    }

public void viewBackTicket() throws SQLException {
    TicketServices ts=new TicketServices();
    ts.afficherListe().stream().forEach((t)->{
        data.add(t);
    });

    ticketNomComp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCompetition().getNomCompetition()));

    descTicket.setCellValueFactory(new PropertyValueFactory<>("description"));
    descTicket.setEditable(true);
    descTicket.setCellFactory(TextFieldTableCell.forTableColumn());
    descTicket.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Ticket, String>>() {
        @Override
        public void handle(TableColumn.CellEditEvent<Ticket, String> event) {
            Ticket ticket = event.getRowValue();
            ticket.setDescription(event.getNewValue());
            TicketServices ts = new TicketServices();
            ts.modifierTicket(ticket);
        }
    });
    
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    tableTickets.setItems(data);
    searchBox();
}



    @FXML
    private void AjouterTicket(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ajouTicket.fxml"));
        paneViewTicket.getChildren().removeAll();
        paneViewTicket.getChildren().setAll(fxml);
        // Cacher la fenêtre actuelle
//    Node source = (Node) event.getSource();
//    Stage currentStage = (Stage) source.getScene().getWindow();
//    currentStage.hide();
    tableTickets.refresh();
    }


    @FXML
    private void deleteTicket(ActionEvent event) throws SQLException {
             if (tableTickets.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner le Ticket à supprimer");
            alert.showAndWait();
            return;
        }
          // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce Ticket ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la réclamation sélectionnée dans la vue de la table
            int id = tableTickets.getSelectionModel().getSelectedItem().getId();

            // Supprimer la réclamation de la base de données
            ccrd.supprimerTicket(id);
            // Rafraîchir la liste de données
            data.clear();
            tableTickets.refresh();
            Alert alert2=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("suppression validée");
            alert.setHeaderText("Le Ticket séléctioné a été supprimé avec succés ");
            alert.showAndWait();
            viewBackTicket();
            searchBox();
        }
    }

    private void refreshTab(ActionEvent event) throws SQLException {
    tableTickets.refresh();
    searchBox();
    }
    



    @FXML
    private void voirCompetition(MouseEvent event) {
    }

    @FXML
    private void backCompetition(ActionEvent event) throws IOException {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBack.fxml"));
        Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
        // Cacher la fenêtre actuelle
    Node source = (Node) event.getSource();
    Stage currentStage = (Stage) source.getScene().getWindow();
    currentStage.hide();
    }

        public void searchBox() throws SQLException {
   //     Competition competition= getTableView().getItems().
       FilteredList<Ticket> filteredData = new FilteredList<>(FXCollections.observableArrayList(ccrd.afficherListe()), p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ticket -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
  


                String lowerCaseFilter = newValue.toLowerCase();
                if (ticket.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ticket.getCompetition().getNomCompetition().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Ticket> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableTickets.comparatorProperty());
        tableTickets.setItems(sortedData);
    
    }

} 

