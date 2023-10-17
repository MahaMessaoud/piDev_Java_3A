/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class HelloController implements Initializable {

    @FXML
    private AnchorPane hello;
    @FXML
    private Button btnRss;
    @FXML
    private Button btnGpt;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private Label resultLabel;
    @FXML
    private Label sportLabel;
    @FXML
    private Label hoursLabel;
    @FXML
    private Label mealLabel;
    @FXML
    private Label proteinLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.err.println("Hello im here");
    }

    @FXML
    private void Rss(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("rss.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    @FXML
    private void chatGPT(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("AskChat.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

//    @FXML
//    public void calculateIMC() {
//        try {
//            double weight = Double.parseDouble(weightField.getText());
//            double height = Double.parseDouble(heightField.getText());
//            double imc = weight / (height * height);
//            resultLabel.setText("Votre IMC est : " + imc);
//        } catch (NumberFormatException e) {
//            resultLabel.setText("Veuillez entrer des valeurs valides !");
//        }
//    }
//    @FXML
//    public void calculateIMC() {
//        try {
//            double weight = Double.parseDouble(weightField.getText());
//            double height = Double.parseDouble(heightField.getText());
//            double imc = weight / (height * height);
//            resultLabel.setText("Votre IMC est : " + imc);
//
//            // Afficher le sport recommandé selon l'IMC
//            if (imc < 18.5) {
//                sportLabel.setText("Sport recommandé : Musculation et prise de masse");
//            } else if (imc >= 18.5 && imc < 25) {
//                sportLabel.setText("Sport recommandé : Cardio (course, vélo, natation)");
//            } else if (imc >= 25 && imc < 30) {
//                sportLabel.setText("Sport recommandé : Cardio et musculation");
//            } else {
//                sportLabel.setText("Sport recommandé : Cardio intensif (course, boxe, kickboxing)");
//            }
//
//        } catch (NumberFormatException e) {
//            resultLabel.setText("Veuillez entrer des valeurs valides !");
//            sportLabel.setText("");
//        }
//    }
    @FXML
    private void calculateIMC(ActionEvent event) {
        double height = Double.parseDouble(heightField.getText());
        double weight = Double.parseDouble(weightField.getText());
        double bmi = weight / Math.pow(height, 2);

        resultLabel.setText(String.format("Votre IMC est de %.2f", bmi));

        if (bmi < 18.5) {
            sportLabel.setText("Vous avez besoin de développer votre masse musculaire");
            hoursLabel.setText("6 heures de sport par semaine");
            mealLabel.setText("Un régime riche en glucides");
            proteinLabel.setText("1,5g de protéines par kg de poids corporel par jour");
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            sportLabel.setText("Vous êtes en bonne santé!");
            hoursLabel.setText("4 heures de sport par semaine");
            mealLabel.setText("Un régime équilibré");
            proteinLabel.setText("1,2g de protéines par kg de poids corporel par jour");
        } else if (bmi >= 25 && bmi <= 29.9) {
            sportLabel.setText("Vous devez brûler les graisses");
            hoursLabel.setText("5 heures de sport par semaine");
            mealLabel.setText("Un régime faible en gras");
            proteinLabel.setText("1,2g de protéines par kg de poids corporel par jour");
        } else {
            sportLabel.setText("Vous devez perdre du poids");
            hoursLabel.setText("7 heures de sport par semaine");
            mealLabel.setText("Un régime faible en calories");
            proteinLabel.setText("1,5g de protéines par kg de poids corporel par jour");
        }
    }

   

}
