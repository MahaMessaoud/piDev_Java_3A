/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import Entity.Competition;
import Services.CompetitionServices;
import com.sun.javafx.scene.control.skin.ColorPalette;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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
public class StatistiqueController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button btnReturn;
    @FXML
    private ImageView btRetour;
    @FXML
    private LineChart<String, Integer> competitions;
    @FXML
    private AnchorPane paneStat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            statistique();
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void back_ViewBack_Competition() throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("ViewBack.fxml"));
        paneStat.getChildren().removeAll();
        paneStat.getChildren().setAll(fxml);
    }

    public void statistique() throws SQLException {
        CompetitionServices cs = new CompetitionServices();

        List<Competition> comp = null;
        comp = cs.afficherListe();

        // Créer les axes pour le graphique
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Nom Competition");
        yAxis.setLabel("Nombre des participants");

        // Créer la série de données à afficher
        XYChart.Series series = new XYChart.Series();
        series.setName("Statistiques des Competitions selon le nombre des participants");
        for (Competition compe : comp) {
            series.getData().add(new XYChart.Data<>(compe.getNomCompetition(), compe.getNbrParticipants()));
        }

        // Créer le graphique et ajouter la série de données
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Statistiques des Competitions par rapport aux nombres de participants");
        lineChart.getData().add(series);

        // Afficher le graphique dans votre scène
        competitions.setCreateSymbols(false);
        competitions.getData().add(series);

    }

    @FXML
    private void voirCompetition(MouseEvent event) {
    }

    @FXML
    private void voirCompetition(ActionEvent event) throws IOException {
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

}
