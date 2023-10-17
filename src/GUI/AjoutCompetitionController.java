/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Competition;
import Services.CompetitionServices;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
public class AjoutCompetitionController implements Initializable {

    @FXML
    private TextField btNom;
    @FXML
    private DatePicker dateChoisi;
    @FXML
    private TextField btFrais;
    @FXML
    private TextField btMaxParticipants;
    @FXML
    private Button btAjout;
    @FXML
    private ImageView btRetour;
    @FXML
    private RadioButton btDisponible;
    @FXML
    private RadioButton btNonDisponible;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnClear;
    @FXML
    private AnchorPane AjoutComp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void back_ViewBack_Competition() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("ViewBack.fxml"));
        AjoutComp.getChildren().removeAll();
        AjoutComp.getChildren().setAll(fxml);
    }

    @FXML
    private void ajouterCompetitionn(ActionEvent event) {
        // Nom
        String nomCompetition = btNom.getText();

// Vérification du champ nom
        if (nomCompetition.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie: champ NOM est vide");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire d'entrer le nom de la compétition ! ");
            alert.showAndWait();
            return;
        }

// Date
        Date dateCompetition = null;
        try {
            LocalDate localDate = dateChoisi.getValue();
            if (localDate.isBefore(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie: la date sélectionnée est antérieure à la date actuelle");
                alert.setHeaderText(null);
                alert.setContentText("La date de la compétition ne peut pas être antérieure à la date actuelle !");
                alert.showAndWait();
                return;
            } else if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                dateCompetition = Date.from(instant);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie: champ DATE est vide");
                alert.setHeaderText(null);
                alert.setContentText("Il est obligatoire de mettre la date de la compétition !");
                alert.showAndWait();
                return;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le champ date est invalide");
            alert.showAndWait();
            return;
        }

// Frais
        int fraisCompetition = 0;
        try {
            fraisCompetition = Integer.parseInt(btFrais.getText());

            // Vérification que le frais est positif
            if (fraisCompetition < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le frais doit être un entier positif");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le champ frais doit être un entier");
            alert.showAndWait();
            return;
        }

// Etat
        String etatCompetition = null;
        if (btDisponible.isSelected() && btNonDisponible.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie: deux etats séléctionnés ");
            alert.setHeaderText(null);
            alert.setContentText("Il faut cocher une seule etat !!");
            alert.showAndWait();
            return;
        } else if (btDisponible.isSelected()) {
            etatCompetition = "Disponible";
        } else if (btNonDisponible.isSelected()) {
            etatCompetition = "non disponible";
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le champ état est obligatoire");
            alert.showAndWait();
            return;
        }

// Nbr participants
        int nbrMaxParticipants = 0;
        try {
            nbrMaxParticipants = Integer.parseInt(btMaxParticipants.getText());

            // Vérification que le nombre de participants est positif
            if (nbrMaxParticipants <= 0 || nbrMaxParticipants > 50) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText(null);
                alert.setContentText("Le nombre de participants doit être un entier positif et inférieur à 50");
                alert.showAndWait();
                return;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Le champ nombre de participants doit être un entier");
            alert.showAndWait();
            return;
        }

        MyDB db = MyDB.getInstance();
        Competition cmp = new Competition(nomCompetition, fraisCompetition, dateCompetition, nbrMaxParticipants, etatCompetition, 0);
        CompetitionServices ps = new CompetitionServices();
        ps.ajouterr(cmp);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout effectué");
        alert.setHeaderText(null);
        alert.setContentText("La Compétition est ajoutée aavec succées");
        alert.showAndWait();
        btNom.clear();
        dateChoisi.getEditor().clear(); //Type: datePeaker
        btFrais.clear();
        btMaxParticipants.clear();
        btDisponible.setSelected(false);//Type: RadioButton
        btNonDisponible.setSelected(false);//type: RadioButton 

    }

    private void voirCompetition(ActionEvent event) throws IOException {

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

    private void listCompetitions(MouseEvent event) throws IOException {

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

    @FXML
    private void voirCompetition(MouseEvent event) throws IOException {

    }

    @FXML
    private void effacerTout(ActionEvent event) {
        btNom.clear();
        dateChoisi.getEditor().clear(); //Type: datePeaker
        btFrais.clear();
        btMaxParticipants.clear();
        btDisponible.setSelected(false);//Type: RadioButton
        btNonDisponible.setSelected(false);//type: RadioButton 

    }

    private void backCompetition(ActionEvent event) throws IOException {

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

}
