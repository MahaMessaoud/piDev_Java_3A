/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.Menu;
import Entities.Plat;
import Entities.Reservation;
import java.awt.Dimension;
import java.awt.GridLayout;
//import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pidev3a52.Pidev3a52;
import services.MenuServices;
import services.PlatServices;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_FrontRestaurantController implements Initializable {

    @FXML
    private Label menuN;
    @FXML
    private Button GoToPlats;
    @FXML
    private Button PDF;
    @FXML
    private ComboBox idPlatpdf;
    private Button Darkmode;
    private Button QrCode;

    public Connection conx;
    public Statement stm;
    @FXML
    private AnchorPane MenuPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuServices ser = new MenuServices();
        List<Menu> listeee;
        try {
            listeee = ser.afficherListe();
            ObservableList<String> list = FXCollections.observableArrayList();

            for (Menu menu : listeee) {
                list.addAll(menu.getCategories());
            }
            menuN.setText(list.get(Pidev3a52.i));
        } catch (SQLException ex) {
            Logger.getLogger(FXML_FrontRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
        //************************PDF ****************
        PlatServices serr = new PlatServices();
        List<Plat> lista;

        try {
            lista = serr.afficherListe();

            ObservableList<String> list = FXCollections.observableArrayList();

            for (Plat plat : lista) {
                list.addAll(plat.getNom());
            }

            idPlatpdf.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(FXML_FrontRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PDF.setOnAction((event) -> {
            try {
                exporterPDF((String) idPlatpdf.getValue()); // exporter le plat sélectionné en PDF

                // Créer une alerte pour informer que le PDF a été exporté avec succès
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Le PDF a été exporté avec succès !");
                // alert.setContentText("");
                alert.showAndWait();

            } catch (SQLException | DocumentException | FileNotFoundException ex) {
                Logger.getLogger(FXML_FrontRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXML_FrontRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
    @FXML
    private void GoToPlat(ActionEvent event) throws IOException {
        MenuServices ser = new MenuServices();

        Pidev3a52.id = ser.idmenu(menuN.getText());
        Parent fxml = FXMLLoader.load(getClass().getResource("FXML_platFront.fxml"));
        MenuPane.getChildren().removeAll();
        MenuPane.getChildren().setAll(fxml);
    }
    @FXML
    public void next() throws SQLException {
        Pidev3a52.i++;
        MenuServices ser = new MenuServices();
        List<Menu> listeee;
        listeee = ser.afficherListe();
        if (Pidev3a52.i == listeee.size()) {
            Pidev3a52.i = 0;
        }
        ObservableList<String> list = FXCollections.observableArrayList();

        for (Menu menu : listeee) {
            list.addAll(menu.getCategories());
        }
        menuN.setText(list.get(Pidev3a52.i));
    }

    @FXML
    public void pred() throws SQLException {
        Pidev3a52.i--;
        MenuServices ser = new MenuServices();
        List<Menu> listeee;
        listeee = ser.afficherListe();
        if (Pidev3a52.i == -1) {
            Pidev3a52.i = listeee.size() - 1;
        }
        ObservableList<String> list = FXCollections.observableArrayList();

        for (Menu menu : listeee) {
            list.addAll(menu.getCategories());
        }
        menuN.setText(list.get(Pidev3a52.i));
    }

//    @FXML
//    public void plat() throws SQLException {
//        MenuServices ser = new MenuServices();
//
//        Pidev3a52.id = ser.idmenu(menuN.getText());
//        Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getResource("FXML_platFront.fxml"));
//            Scene c = new Scene(root);
//            Stage stage = (Stage) GoToPlats.getScene().getWindow();
//            stage.setScene(c);
//        } catch (IOException ex) {
//            Logger.getLogger(FXML_FrontRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //****************************************PDF**************************************** 

    private void exporterPDF(String nomPlat) throws SQLException, FileNotFoundException, DocumentException, BadElementException, IOException {

        String req = "SELECT nom, prix, description, calories FROM plat WHERE nom = ?";

        PreparedStatement pstmt = conx.prepareStatement(req);
        pstmt.setString(1, nomPlat);
        // exécution de la requête SQL et récupération des résultats
        ResultSet rs = pstmt.executeQuery();
        rs.next(); // on suppose qu'il n'y a qu'un seul résultat

        // création du document PDF
        Document document = new Document();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss"); // format de date pour générer un nom de fichier unique
        String filePath = "C:\\Users\\maham\\Documents\\NetBeansProjects\\PIDesktop\\src\\pdfs\\Details_plat_" + dateFormat.format(new Date()) + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // ajout du logo
        Image logo = Image.getInstance("C:\\Users\\maham\\Documents\\NetBeansProjects\\PIDesktop\\src\\GUI\\images\\LogoGymBlack.png"); // créer une instance de l'image
        logo.scaleToFit(100, 100); // ajuster la taille de l'image
        logo.setAbsolutePosition(20, 750); // positionner l'image en haut et à gauche
        logo.setAlignment(Element.ALIGN_LEFT); // aligner l'image  à gauche
        document.add(logo); // ajouter l'image au document

        //ajouter un paragraphe vide pour l'espace
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        // ajout des détails du plat au document
        Font fontTitre = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, new BaseColor(216, 24, 50));
        Font fontTitreCouleur = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, new BaseColor(216, 24, 50));
        Font fontNormal = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(0x00, 0x00, 0x00));

        Paragraph titre = new Paragraph("Nom du plat : ", fontTitre);
        Chunk nomPlatChunk = new Chunk(rs.getString("nom"), fontNormal);
        titre.add(nomPlatChunk);
        titre.setAlignment(Paragraph.ALIGN_CENTER);
        titre.setSpacingAfter(10f); // espace après le paragraphe
        document.add(titre);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        Phrase phrasePrixTitre = new Phrase("Prix : ", fontTitreCouleur);
        Phrase phrasePrixValeur = new Phrase(rs.getDouble("prix") + " Dt", fontNormal);
        Phrase phrasePrixComplete = new Phrase();
        phrasePrixComplete.add(phrasePrixTitre);
        phrasePrixComplete.add(phrasePrixValeur);
        document.add(phrasePrixComplete);
        document.add(Chunk.NEWLINE);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        Phrase phraseDescriptionTitre = new Phrase("Description : ", fontTitreCouleur);
        Phrase phraseDescriptionValeur = new Phrase(rs.getString("description"), fontNormal);
        Phrase phraseDescriptionComplete = new Phrase();
        phraseDescriptionComplete.add(phraseDescriptionTitre);
        phraseDescriptionComplete.add(phraseDescriptionValeur);
        document.add(phraseDescriptionComplete);
        document.add(Chunk.NEWLINE);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        Phrase phraseCaloriesTitre = new Phrase("Nombre de calories : ", fontTitreCouleur);
        Phrase phraseCaloriesValeur = new Phrase(rs.getString("calories"), fontNormal);
        Phrase phraseCaloriesComplete = new Phrase();
        phraseCaloriesComplete.add(phraseCaloriesTitre);
        phraseCaloriesComplete.add(phraseCaloriesValeur);
        document.add(phraseCaloriesComplete);

        // fermeture du document PDF
        document.close();

        // fermeture de la connexion à la base de données
        conx.close();

        System.out.println("Le PDF a été généré avec succès !");
    }
//****************************************PDF****************************************  

}
