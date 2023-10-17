/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import Entities.Fournisseur;
import Services.FournisseurService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.*;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class DetailFournisseurController implements Initializable {

    @FXML
    private Button retourlisttF;

    @FXML
    private ImageView waiting;
    @FXML
    private Button sendMail;

    private Button homeButton;

    @FXML
    private Label labIDF;

    @FXML
    private Label labNomF;

    @FXML
    private Label labContactF;

    @FXML
    private Label labEmailF;

    @FXML
    private Label labAdrF;
    @FXML
    private Button ModifierF;

    @FXML
    private Button SupprimerF;
    @FXML
    private AnchorPane DetFournisseurPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailer.setText(null);
        waiting.setVisible(false);
    }
    @FXML
    private Label mailer;
    
    Button btnClose;
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }


    @FXML
    private void handleModifierF() {
        // Get the values from the labels in the current scene
        String id = labIDF.getText();
        String nom = labNomF.getText();
        String contact = labContactF.getText();
        String email = labEmailF.getText();
        String adresse = labAdrF.getText();

        // Load the "ModifFournisseur.fxml" file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifFournisseur.fxml"));
        try {
            Parent root = loader.load();
            ModifFournisseurController controller = loader.getController();

            // Set the text fields in the "ModifFournisseur.fxml" file with the values from the labels
            controller.setIdF(Integer.parseInt(id));
            controller.tfNomF.setText(nom);
            controller.tfContactF.setText(contact);
            controller.tfEmailF.setText(email);
            controller.tfAdrF.setText(adresse);
 DetFournisseurPane.getChildren().removeAll();
        DetFournisseurPane.getChildren().setAll(root);

            // Show the "ModifFournisseur.fxml" file in a new stage
          /*Scene scene = ModifierF.getScene();
            scene.setRoot(root);
            System.out.println(id);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//l fournisseur li bch nabaathouh lel detail
    public void setFournisseur(Fournisseur fournisseur) {
        labIDF.setText(String.valueOf(fournisseur.getId()));
        labNomF.setText(fournisseur.getNom_fournisseur());
        labContactF.setText(fournisseur.getContact_fournisseur());
        labEmailF.setText(fournisseur.getEmail_fournisseur());
        labAdrF.setText(fournisseur.getAdresse_fournisseur());
    }
//////////////////////////////////////////////////////

    private void puser(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/backOfficeHome.fxml"));
        Parent root = loader.load();
        homeButton.getScene().setRoot(root);

    }

    @FXML
    private void returnListF(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListFournisseur.fxml"));
        Parent root = loader.load();
       DetFournisseurPane.getChildren().removeAll();
        DetFournisseurPane.getChildren().setAll(root);

        
        // retourlisttF.getScene().setRoot(root);

    }

    @FXML
    private void sendMail() {
        // Set the SMTP host and port for sending the email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "arco.sc0156@gmail.com";
        String password = "hghseksuroiqviag";

        // Set the properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption

        // Create a new email session using the specified properties
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message msg = new MimeMessage(session);

            // Set the "From" address for the email
            // msg.setFrom(new InternetAddress("ahmed.benabid2503@gmail.com"));
            // Add the "To" address for the email (including the recipient's name)
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(labEmailF.getText()));

            // Set the subject and body text for the email
            msg.setSubject("Demande d'une charge.");
            msg.setText("Mr " + labNomF.getText() + ", j'ai besoin d'une charge le plus tôt possible et merci!");
            // Create an alert to notify the user that the email was sent successfully

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation d'envoie");
            alert.setHeaderText("Voulez-vous envoyez ce mail à " + labNomF.getText() + " ?");
            alert.setContentText("Cette action est requise.");
            waiting.setVisible(true);
            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = alert.showAndWait();

            // Send the email
            if (result.get() == ButtonType.OK) {

                waiting.setVisible(true);

                Transport.send(msg);
 waiting.setVisible(false);
                mailer.setText("Envoyé avec succès !");
                Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(2), event -> mailer.setText(null)));
                timeline1.play();

            } else {
                // Close the dialog and do nothing
                alert.close();
                waiting.setVisible(false);

                mailer.setText("Echec d'envoie!");
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> mailer.setText(null)));
                timeline.play();
            }

            // Print a message to the console to indicate that the email was sent successfully
        } catch (AddressException e) {
            // Create an alert to notify the user that there was an error with the email address
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }

    @FXML
    private void suppF(javafx.event.ActionEvent event) throws IOException, SQLException {
        // Create a confirmation dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Supprimer Fournisseur");
        alert.setHeaderText("Êtes vous sur ,supprimer cet fournisseur ?");
        alert.setContentText("Cette action est requise.");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        // If the user clicks "OK", delete the fournisseur
        if (result.get() == ButtonType.OK) {
            FournisseurService fs = new FournisseurService();
            fs.delete(Integer.parseInt(labIDF.getText()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListFournisseur.fxml"));
            Parent root = loader.load();
          DetFournisseurPane.getChildren().removeAll();
        DetFournisseurPane.getChildren().setAll(root);

            
            
            //  SupprimerF.getScene().setRoot(root);
        } else {
            // Close the dialog and do nothing
            alert.close();
        }

    }
    void send_SMS() {
        // Initialisation de la bibliothèque Twilio avec les informations de votre compte
        String ACCOUNT_SID = "ACde0fe3e27b9f6940e6700cbef936d1a9";
        String AUTH_TOKEN = "7270aba8d8ed493992e56483cf83af46";
             
       // Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

     
            
            String recipientNumber = "+21627444789";
            String message = "Bonjour Cher Abonné\n,Nous sommes ravis de vous informez qu'un planning a été ajouté./n Merci de votre fidélité et à bientôt chez EnergyBox.\nCordialement,\n EnergyBox";
                
           /* Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+15076328189"),
               message)
                .create();*/
                
            System.out.println("SMS envoyé ");
         
     
}
    
   

}
