/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.SponsorService;
import Entities.Sponsor;
import Entities.Upload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class AjoutSponsorController implements Initializable {

    private File file;
    String pic;
    private Button closW;
    @FXML
    private TextField nomSps;
    @FXML
    private TextField Dsps;
    @FXML
    private ImageView imgSpsInput;
    @FXML
    private Button confirm;
    @FXML
    private Button btnImportAct;
    private File selectedImageFile;
    //private String imageName = null;
    @FXML
    private Pane Ajoutsps;
    @FXML
    private Button btnReturn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
//    private void ajouterS(ActionEvent event) {
//        SponsorService sps = new SponsorService();
//        if (nomSps.getText().equals("") || Integer.parseInt(Dsps.getText()) == 0 && Integer.parseInt(Dsps.getText()) > 0) {
//            new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoires", ButtonType.CLOSE).show();
//        } else {
//            sps.ajouter(new Sponsor(nomSps.getText(), Double.parseDouble(Dsps.getText()), pic));
//            JOptionPane.showMessageDialog(null, "Sponsors ajoutée !");
//            nomSps.clear();
//            Dsps.clear();
//        }
//    }
    private void ajouterS(ActionEvent event) {
    SponsorService sps = new SponsorService();

    // Vérifier que les champs obligatoires ont été remplis
    if (nomSps.getText().isEmpty() || Dsps.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Tous les champs sont obligatoires");
        alert.showAndWait();
        return;
    }

    // Vérifier que le champ Dsps contient bien un nombre valide
    double prixSponsor;
    try {
        prixSponsor = Double.parseDouble(Dsps.getText());
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Le champ 'Prix' doit contenir un nombre valide");
        alert.showAndWait();
        return;
    }

    // Vérifier que le champ nomSps ne contient que des lettres et des espaces
    if (!nomSps.getText().matches("[a-zA-Z\\s]+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText("Le champ 'Nom' ne peut contenir que des lettres et des espaces");
        alert.showAndWait();
        return;
    }

    // Ajouter le sponsor
    Sponsor nouveauSponsor = new Sponsor(nomSps.getText(), prixSponsor, pic);
    sps.ajouter(nouveauSponsor);

    // Afficher un message de confirmation
    JOptionPane.showMessageDialog(null, "Sponsor ajouté !");
    nomSps.clear();
    Dsps.clear();
}

    @FXML

    void back_List_sps() throws IOException {

       
  

        Parent fxml = FXMLLoader.load(getClass().getResource("listSponsor.fxml"));
        Ajoutsps.getChildren().removeAll();
        Ajoutsps.getChildren().setAll(fxml);
        
    
    }

    public void closeW(ActionEvent event) {
        Stage stage = (Stage) closW.getScene().getWindow();
        stage.close();
    }

//    @FXML
//    private void uplimg(ActionEvent event) throws IOException {
//
//        FileChooser fileChooser = new FileChooser();
//        file = fileChooser.showOpenDialog(null);
//        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
//        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
//
//        //pic=(file.toURI().toString());
//        pic = new Upload().upload(file, "\\uploads");
//        System.out.println(pic);
//
//    }
    @FXML
    void ajouterImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imgSpsInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgSpsInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            pic = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", "uploads", pic);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }
    }


}
