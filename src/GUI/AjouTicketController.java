/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Ticket;
import Services.TicketServices;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
public class AjouTicketController implements Initializable {

    @FXML
    private ComboBox<String> nomCompetitionTicket;
    @FXML
    private TextArea descriptionTicket;
    @FXML
    private Button btEnregistrer;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnReturn;
    @FXML
    private ImageView btRetour;
    List<String> nomsCompetition = new ArrayList<>();
    @FXML
    private AnchorPane paneAjoutTicket;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String req = "SELECT nom_competition FROM competition WHERE etat_competition = 'disponible' ";
            Statement st = MyDB.getInstance().getConx().createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                String nomCompetition = rs.getString("nom_competition");
                nomsCompetition.add(nomCompetition);
            }
            nomCompetitionTicket.getItems().setAll(nomsCompetition);
            nomCompetitionTicket.getSelectionModel().selectFirst();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    void back_ViewBack_Competition() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("ViewBack.fxml"));
        paneAjoutTicket.getChildren().removeAll();
        paneAjoutTicket.getChildren().setAll(fxml);
    }

    @FXML
    private void ajoutTicket(ActionEvent event) {
        try {
            String rq = "SELECT id from competition WHERE nom_competition=?";
            PreparedStatement ps = MyDB.getInstance().getConx().prepareStatement(rq);
            ps.setString(1, nomCompetitionTicket.getValue());
            ResultSet rs = ps.executeQuery();
            String desc = descriptionTicket.getText();
            if (desc.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'ajout !");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez mettre une description au ticket.");
                alert.showAndWait();
                return;

            }
            int idCmp;
            if (rs.next()) {
                idCmp = rs.getInt("id");
                Ticket tq = new Ticket(desc, idCmp);
                TicketServices tqS = new TicketServices();
                tqS.ajouterr(tq);
                // si l'jout est fait affichage d'une alerte d'information
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ticket ajouté");
                alert.setHeaderText(null);
                alert.setContentText("Le ticket a été ajouté avec succès.");
                alert.showAndWait();
            } else {
                // si la compétition n'a pas été trouvée, affichage d'une alerte d'erreur
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'ajout !");
                alert.setHeaderText(null);
                alert.setContentText("La compétition n'a pas été trouvée.");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void effacerTout(ActionEvent event) {
        nomCompetitionTicket.getItems().setAll(nomsCompetition);
        descriptionTicket.clear();
    }

    /*  @FXML
    private void voirCompetition(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBack.fxml"));
        Parent root = loader.load();

    // Obtenir le contrôleur associé à la vue FXML
    ViewBackController controller = loader.getController();

    // Afficher la nouvelle interface utilisateur
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
        // Cacher la fenêtre actuelle
    Node source = (Node) event.getSource();
    Stage currentStage = (Stage) source.getScene().getWindow();
    currentStage.hide();
        
    }
     */
    @FXML
    private void voirTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewTickets.fxml"));
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

    @FXML
    private void voirCompetition(MouseEvent event) {
    }

}
