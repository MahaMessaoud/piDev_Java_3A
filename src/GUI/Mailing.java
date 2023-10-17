/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import  Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Entities.Plat;
import Entities.Menu;
import Entities.Reservation;

/**
 *
 * @author lenovo
 */
public class Mailing {
    
    private Connection con;
    private Statement ste;

    public Mailing() {
        con = MyDB.getInstance().getConx();

    }

public static void sendEmail(String recipientEmail) throws Exception {
    // Vérification de l'adresse email du destinataire
    if (!recipientEmail.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
        throw new IllegalArgumentException("Adresse email invalide");
    }
    
        // Remplacer les informations ci-dessous avec les informations de votre compte email
        String host = "smtp.gmail.com";//stocker le nom du serveur SMTP(Simple Mail Transfer Protocol) de Gmail.
        String senderEmail = "chaima.benhmida@esprit.tn";
        String senderPassword = "iamaghost";

        // configuration de la connexion SMTP utilisée
        //activer l'authentification SMTP, activer TLS, définir le serveur SMTP 
       // (ici,smtp.gmail.com) et définir le port SMTP (ici, 587)
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

    // créer d'une nouvelle session avec l'authentification
    //créer une nouvelle instance de session en utilisant les propriétés props définies précédemment. 
    //Authenticator est passée comme deuxième paramètre pour fournir une méthode qui renvoie les informations 
    //d'authentification pour le serveur de messagerie.
    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderPassword);
        }
        /*
=>une instance d'authentification est créée avec une méthode getPasswordAuthentication() qui
renvoie un objet PasswordAuthentication contenant l'adresse e-mail de l'expéditeur et son mot de passe 
Cette méthode est protégée car elle est appelée par le framework Java Mail.
         */
    });
 
        // création d'un nouveau message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("EnergyBox:Confirmation de réservation");
        message.setText("Bienvenue chez EnergyBox restaurant ! Votre réservation sera prête dans une heure . Veuillez la confirmer par mail s'il vous plaît ! ");

        // envoi du message
        Transport.send(message);
        System.out.println("Le mail a été envoyé avec succès à " + recipientEmail);
    }

 /*   public static void main(String[] args) {
        try {
            sendEmail("destinataire@example.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 */  

//Mailing de notif sur le nombre de plats : 
public void sendEmailNotif(String sujet, String corps) {
 
    String destinataire = "chaymabenhmida13@gmail.com";
    
    String expediteur = "chaima.benhmida@esprit.tn";
    String motDePasse = "iamaghost";
    
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(expediteur, motDePasse);
        }
      });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(expediteur));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
        //message.setSubject("EnergyBox:Alert!!!");
       // message.setText("EnergyBox restaurant");
        message.setSubject(sujet);
        message.setText(corps);

        // Envoi du message
        Transport.send(message);

        System.out.println("Le mail a été envoyé avec succès à " + destinataire);

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
    
}
