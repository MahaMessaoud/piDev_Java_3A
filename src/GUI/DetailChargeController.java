/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Charge;
import Entities.Fournisseur;
import Entities.Materiel;
import Services.ChargeService;
import Services.FournisseurService;
import Services.MaterielService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class DetailChargeController implements Initializable {

    private Button homeButton;

    @FXML
    private Button arrièreDetailCh;

    @FXML
    private Label labidCh;

    @FXML
    private Label labfCh;

    @FXML
    private Label labmatCh;

    @FXML
    private Label labdaCh;

    @FXML
    private Label labqtCh;

    @FXML
    private Button suppCh;

    @FXML
    private Button modifCh;
    @FXML
    private AnchorPane DetailChargePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //l fournisseur li bch nabaathouh lel detail
    public void setFournisseur(Charge fournisseur) {
        labidCh.setText(String.valueOf(fournisseur.getId()));
        labfCh.setText(fournisseur.getFournisseur_id());
        labmatCh.setText(fournisseur.getMateriel_id());
        labdaCh.setText(fournisseur.getDate_arrivage_charge());
        labqtCh.setText(String.valueOf(fournisseur.getQuantite_charge()));
    }

    private void puser(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/backOfficeHome.fxml"));
        Parent root = loader.load();
        homeButton.getScene().setRoot(root);

    }

    @FXML
    private void returnListCh(javafx.event.ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListCharge.fxml"));
//        Parent root = loader.load();
//        arrièreDetailCh.getScene().setRoot(root);
 

        Parent fxml = FXMLLoader.load(getClass().getResource("GestionRessource.fxml"));
        DetailChargePane.getChildren().removeAll();
        DetailChargePane.getChildren().setAll(fxml);
        
    

    }

    @FXML
    private void suppF(javafx.event.ActionEvent event) throws IOException, SQLException {
        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer Charge");
        alert.setHeaderText("Êtes vous sur ,supprimer cette Charge ?");
        alert.setContentText("Cette action est requise.");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        // If the user clicks "OK", delete the fournisseur
        if (result.get() == ButtonType.OK) {
            ChargeService fs = new ChargeService();
            fs.delete(Integer.parseInt(labidCh.getText()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListCharge.fxml"));
            Parent root = loader.load();
             DetailChargePane.getChildren().removeAll();
            DetailChargePane.getChildren().setAll(root);
            //suppCh.getScene().setRoot(root);
        } else {
            // Close the dialog and do nothing
            alert.close();
        }

    }

    @FXML
    private void handleModifierF() throws SQLException {
        // Get the values from the labels in the current scene
        String id = labidCh.getText();
        String nom = labfCh.getText();
        String contact = labmatCh.getText();
        String email = labdaCh.getText();
        String adresse = labqtCh.getText();
        ChargeService cs = new ChargeService();
        FournisseurService fs= new FournisseurService();
        MaterielService ms =new MaterielService();
    int  elementF =  Integer.parseInt(cs.getOneByIdd(Integer.parseInt(id)).getFournisseur_id());
 int  elementM =  Integer.parseInt(cs.getOneByIdd(Integer.parseInt(id)).getMateriel_id());
Fournisseur four =fs.getOneById(elementF);
Materiel mat = ms.getOneById(elementM);
        // Load the "ModifFournisseur.fxml" file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifCharge.fxml"));
        try {
            Parent root = loader.load();
            ModifChargeController controller = loader.getController();
            // Set the text fields in the "ModifFournisseur.fxml" file with the values from the labels
            controller.setIdM(Integer.parseInt(id));
            controller.tfqtChModif.setText(adresse);
            controller.setDateM(email);
            controller.setFournisseurCh(nom);
            controller.setMaterielCh(contact);
            controller.comboF.setValue(four);
            controller.comboM.setValue(mat);
            // Show the "ModifFournisseur.fxml" file in a new stage
            /*Scene scene = modifCh.getScene();
            scene.setRoot(root);*/
            DetailChargePane.getChildren().removeAll();
            DetailChargePane.getChildren().setAll(root);
            System.out.println(controller.getIdM());
            System.out.println(controller.getDateM());
            System.out.println(controller.getFournisseurCh());
            System.out.println(controller.getMaterielCh());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     public static void generatePDF( Charge c, Materiel m) throws Exception {
    Document document = new Document();
    String fileName = "Charge" + c.getId() + ".pdf";

    // Ouvrir une fenêtre de choix de fichier pour sélectionner l'emplacement où enregistrer le fichier PDF
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Pdf pour la charge "+ c.getId());
    fileChooser.setInitialFileName(fileName);
    File selectedFile = fileChooser.showSaveDialog(null);

    if (selectedFile != null) {
        // Enregistrer le fichier PDF à l'emplacement sélectionné
        PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
        document.open();

        // Ajouter les informations du ticket
        Font fontTitre = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
        Paragraph titre = new Paragraph("D'après le fournisseur : " + c.getFournisseur_id(), fontTitre);
        titre.setAlignment(Element.ALIGN_CENTER);
        titre.setSpacingAfter(20f);
        document.add(titre);

//        Image image = Image.getInstance(evenement.getPhoto());
//        image.setAlignment(Element.ALIGN_CENTER);
//        image.scaleAbsolute(400, 200); // ajuster la taille de l'image en points
//        document.add(image);

        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 14); // créer une police avec une taille de 12 points

        Paragraph info = new Paragraph("Dédié pour le matériel: "+m.getNom_materiel(), font);
        info.setAlignment(Element.ALIGN_CENTER);
        info.setSpacingBefore(20f);
        info.setSpacingAfter(10f);
        document.add(info);

        Paragraph ref = new Paragraph("Quantité envoyé: "+ c.getQuantite_charge(), font);
        ref.setSpacingAfter(5f);
        document.add(ref);

        Paragraph date = new Paragraph("la date d'arrivage de la charge: " + c.getDate_arrivage_charge(), font);
        date.setSpacingAfter(5f);
        document.add(date);

        

       
      

        document.close();

        // Ouvrir le fichier PDF une fois qu'il est enregistré
        Desktop.getDesktop().open(selectedFile);
    }
}
     
     @FXML 
     private Button pdf;
     ChargeService cs = new ChargeService();
     MaterielService ms = new MaterielService();
     FournisseurService fs = new FournisseurService();
     
      Button btnClose;
    private void handleQuitter(ActionEvent event) {
        
        // Obtenez la fenêtre principale
        Stage stage = (Stage) btnClose.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }
     
     @FXML 
     public void generateePDF() throws SQLException, Exception{
         Charge C = cs.getOneById(Integer.parseInt(labidCh.getText()));
         generatePDF(C,ms.getOneById(Integer.parseInt(C.getMateriel_id())) );
         System.out.println("pdf générée");
    
     }
}
