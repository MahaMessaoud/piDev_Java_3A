/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Menu;
import Entities.Plat;
import java.sql.Date;
import Entities.Reservation;
import Entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import services.MenuServices;
import services.PlatServices;
import Services.UserService;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.scene.control.TextField;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.BorderLayout;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Random;
import gui.Mailing;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_AjouterReservationController implements Initializable {

    @FXML
    private Button Reserver;
    @FXML
    private Button Retour;
    @FXML
    private Button Annuler;
    @FXML
    private DatePicker fxdate;
    @FXML
    private ComboBox combo;
    @FXML
    private TextField teluser;
    @FXML
    private ImageView fb;
    @FXML
    private ImageView insta;
    @FXML
    private ImageView chat;

    public Connection conx;
    public Statement stm;
    @FXML
    private AnchorPane AjoutPlatPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PlatServices ser = new PlatServices();
        List<Plat> listeee;

        try {
            listeee = ser.afficherListe();

            ObservableList<String> list = FXCollections.observableArrayList();

            for (Plat plat : listeee) {
                list.addAll(plat.getNom());
            }

            combo.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reservation m = new Reservation();

        try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
        Reserver.setOnAction((ActionEvent event) -> {
            LocalDate date = fxdate.getValue();
            if (date != null && date.isAfter(LocalDate.now().minusDays(1))) {
                String dateString = date.toString();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = null;
                try {
                    utilDate = formatter.parse(dateString);
                } catch (ParseException ex) {
                    Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Date sqlDate = java.sql.Date.valueOf(date);
                m.setDate(sqlDate);

                ajouterReservation(m);
                SMS();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("DATE INVALIDE!");
                alert.setContentText("La date doit être supérieure ou égale à la date d'aujourd'hui.");
                alert.showAndWait();
            }
        });
//        Retour.setOnAction((ActionEvent event) -> {
//            redirectToList();
//        });
        Annuler.setOnAction((ActionEvent event) -> {
            fxdate.setValue(null);
            teluser.clear();
        });

    }
    
    
    @FXML
    void back_Front_Plat(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("FXML_FrontRestaurant.fxml"));
        AjoutPlatPane.getChildren().removeAll();
        AjoutPlatPane.getChildren().setAll(fxml);
        
    }

//    private void redirectToList() {
//        Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getResource("FXML_FrontRestaurant.fxml"));
//            Scene c = new Scene(root);
//            Stage stage = (Stage) Retour.getScene().getWindow();
//            stage.setScene(c);
//        } catch (IOException ex) {
//            Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public void ajouterReservation(Reservation p) {
        PlatServices ser = new PlatServices();
        try {
            int idplat = ser.idmenu(combo.getSelectionModel().getSelectedItem().toString());
            int nombrePlatsActuel = ser.getNombrePlats(idplat);
            int nombrePlatsNouveau = nombrePlatsActuel - 1;
            ser.updateNombrePlats(idplat, nombrePlatsNouveau);

            if (nombrePlatsNouveau == 0) {
                // Envoyer une notification à l'administrateur
                System.out.println("Nombre de plats est 0 !");
                Mailing m = new Mailing();
                m.sendEmailNotif("Notification : Nombre de plats est 0", "Le nombre de plats est maintenant 0 pour le plat "
                        + combo.getSelectionModel().getSelectedItem().toString());
            }

            String req = "INSERT INTO `reservation`(`date`,`idplat_id`,`userid`) "
                    + "VALUES (?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setDate(1, p.getDate());
            ps.setInt(2, idplat);
            ps.setInt(3, UserService.idUser);

            ps.executeUpdate();
            System.out.println("Réservation ajoutée avec succèes");

            try {
                int numTable = 0;
                // Générer le QR code pour cette réservation
                generateQRCode(combo.getSelectionModel().getSelectedItem().toString(), p.getDate().toString(), "RANDOM_CODE", numTable);
            } catch (WriterException ex) {
                Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXML_AjouterReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//SMS
    void SMS() {
        String ACCOUNT_SID = "ACa3722b6d1e3b805de90bf61e339f4cc0";
        String AUTH_TOKEN = "4fead10891bc7222f6dc0cf9b65fea9b";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String recipientNumber = "+216" + teluser.getText();
        String message = "Cher(e) Client(e),\n\nVotre réservation a été ajoutée avec succès.\nVotre plat sera prêt dans une heure à partir de la date de la réservation.\n\nMerci pour votre confiance et à bientôt chez nous !\n\nCordialement,\n EnergyBox";

        Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+13462610360"),
                message)
                .create();

        System.out.println("SMS envoyé : " + twilioMessage.getSid());

    }
//SMS    

//QrCode 
    public static void generateQRCode(String platChoisi, String date, String RANDOM_CODE, int numTable) throws WriterException, IOException {
        String codeRandom = UUID.randomUUID().toString();
        Random rand = new Random();
        int tableNumber = rand.nextInt(100); // Générer un nombre aléatoire entre 0 et 99
        String qrCodeData = "Nom du plat : " + platChoisi + "\n" + "Date de réservation : " + date + "\n" + "Numéro de table : " + tableNumber + "\n" + "Code de réservation : " + codeRandom + "\n" + "Merci de montrer le code pour récupérer votre plat";
        int size = 500;
        Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.MARGIN, 1);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, size, size, hintMap);
        int width = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        java.awt.Graphics2D graphics = (java.awt.Graphics2D) image.getGraphics();
        graphics.setColor(java.awt.Color.WHITE);
        graphics.fillRect(0, 0, width, width);
        graphics.setColor(java.awt.Color.BLACK);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIcon qrCodeIcon = new ImageIcon(image);
        JLabel qrCodeLabel = new JLabel(qrCodeIcon);
        JFrame frame = new JFrame();
        frame.getContentPane().add(qrCodeLabel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("QR Code generated successfully!");
    }
//QrCode

//FB INSTA 
    @FXML
    public void openInstagram(MouseEvent event) throws URISyntaxException {
        String instagramUrl = "https://www.instagram.com/wemanage.tn/";
        try {
            Desktop.getDesktop().browse(new URI(instagramUrl));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openFacebook(MouseEvent event) {
        String facebookUrl = "https://www.facebook.com/Berrys.Five";
        try {
            Desktop.getDesktop().browse(new URI(facebookUrl));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
//FB INSTA 

//CHATBOT
    @FXML
    private void openChatBot(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
//ChatBot

}
