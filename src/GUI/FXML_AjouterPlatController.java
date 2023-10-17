/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Menu;
import Entities.Plat;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.MenuServices;

public class FXML_AjouterPlatController implements Initializable {

    @FXML
    private Button fxajout;
    @FXML
    private Button retour;
    @FXML
    private Button fxannuler;
    @FXML
    private TextArea fxnom;
    @FXML
    private TextArea fxdesc;
    @FXML
    private TextArea fxcategId;
    @FXML
    private TextArea fxprix;
    @FXML
    private TextArea fxcalories;
    @FXML
    private TextArea fxetat;
    @FXML
    private TextArea fximage;
    @FXML
    private TextArea fxnbp;

    public Connection conx;
    public Statement stm;
    @FXML
    private ImageView imagepro;
    @FXML
    private ComboBox combo;
    @FXML
    private AnchorPane addPlatPane;

    private File file;
    private String lien = "";

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

            combo.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(FXML_AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Plat p = new Plat();

        try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }

        fxajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (validateInputs() && controleDeSaisie()) {
                    p.setNom(fxnom.getText());
                    p.setDescription(fxdesc.getText());
                    p.setEtat(fxetat.getText());
                    p.setPrix(Double.parseDouble(fxprix.getText()));
                    p.setCalories(fxcalories.getText());
                    //   p.setImage(fximage.getText());
                    p.setImage(lien);
                    p.setNbp(Integer.parseInt(fxnbp.getText()));
                    p.setCategories_id(ser.idmenu(combo.getSelectionModel().getSelectedItem().toString()));
                    ajouterPlat(p);
                }
            }
        });

        fxannuler.setOnAction((ActionEvent event) -> {
            fxnom.clear();
            fxdesc.clear();
            fxprix.clear();
            fxcalories.clear();
            fxetat.clear();
            fximage.clear();
            fxnbp.clear();
            fxcategId.clear();
        });

        retour.setOnAction((ActionEvent event) -> {
            redirectToList();
        });

    }
     

    public void ajouterPlat(Plat p) {

        try {
            String req = "INSERT INTO `plat`(`categories_id`, `nom` ,`prix`,`description`,`calories`,`etat` ,`image`,`nbp`) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, p.getCategories_id());
            ps.setString(2, p.getNom());
            ps.setDouble(3, p.getPrix());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getCalories());
            ps.setString(6, p.getEtat());
            ps.setString(7, p.getImage());
            ps.setInt(8, p.getNbp());
            ps.executeUpdate();
            System.out.println("Plat ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean validateInputs() {
        String nom = fxnom.getText();
        String desc = fxdesc.getText();
        String prix = fxprix.getText();
        String calories = fxcalories.getText();
        String etat = fxetat.getText();
        // String image = fximage.getText();
        String image = imagepro.toString();
        String nbp = fxnbp.getText();

        if (nom.trim().isEmpty() || desc.trim().isEmpty() || prix.trim().isEmpty()
                || calories.trim().isEmpty() || etat.trim().isEmpty() || image.trim().isEmpty()
                || nbp.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Un ou plusieurs champs sont vides");
            alert.showAndWait();
            return false;
        }
        try {
            Double.parseDouble(prix);
            Integer.parseInt(nbp);
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le format de certains champs est incorrect");
            alert.showAndWait();
            return false;
        }
    }

    private void redirectToList() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML_Plat.fxml"));
            addPlatPane.getChildren().removeAll();
            addPlatPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean controleDeSaisie() {
        // Récupérer les valeurs saisies par l'utilisateur
        String nom = fxnom.getText().trim();
        String desc = fxdesc.getText().trim();
        String prixStr = fxprix.getText().trim();
        String caloriesStr = fxcalories.getText().trim();
        String etatStr = fxetat.getText().trim();
        // String image = fximage.getText().trim();
        String image = imagepro.toString();
        String nbpStr = fxnbp.getText().trim();

        // Vérifier la validité des saisies
        boolean isValid = true;

        // Vérifier le nom
        if (nom.length() < 3 || !Character.isUpperCase(nom.charAt(0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le nom doit commencer par une majuscule et contenir au moins 3 lettres.");
            alert.showAndWait();
            isValid = false;
        }

        // Vérifier la description
        if (desc.length() < 10 || !Character.isUpperCase(desc.charAt(0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("La description doit commencer par une majuscule et contenir au moins 10 lettres.");
            alert.showAndWait();
            isValid = false;
        }

        // Vérifier le prix
        double prix = 0;
        try {
            prix = Double.parseDouble(prixStr);
            if (prix <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Le prix doit être un nombre positif.");
                alert.showAndWait();
                isValid = false;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le prix doit être un nombre positif.");
            alert.showAndWait();
            isValid = false;
        }

        // Vérifier les calories
        if (caloriesStr.length() < 1 || caloriesStr.length() > 4 || !caloriesStr.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Les calories doivent contenir entre 1 et 4 chiffres.");
            alert.showAndWait();
            isValid = false;
        }

        // Vérifier le nbp
        int nbp = 0;
        try {
            nbp = Integer.parseInt(nbpStr);
            if (nbp <= 0 || nbp > 50) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Le nombre de personnes doit être un entier positif et ne doit pas dépasser 50.");
                alert.showAndWait();
                isValid = false;
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le nombre de personnes doit être un entier positif et ne doit pas dépasser 50.");
            alert.showAndWait();
            isValid = false;
        }

        // Vérifier l'état
        if (!etatStr.equals("0") && !etatStr.equals("1")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("L'état doit être 0 ou 1.");
            alert.showAndWait();
            isValid = false;
        }

        // Si toutes les saisies sont valides, ajouter le plat
        if (isValid) {
            Plat p = new Plat();
            p.setNom(nom);
            p.setDescription(desc);
            p.setEtat(etatStr);
            p.setPrix(prix);
            p.setCalories(caloriesStr);
            p.setImage(image);
            p.setNbp(nbp);
            // ajouterPlat(p);
        }

        return isValid;
    }

    @FXML
    private void handleChooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        ExtensionFilter extFilterJPG = new ExtensionFilter("JPG files (.JPG)", "*.JPG");
        ExtensionFilter extFilterjpg = new ExtensionFilter("jpg files (.jpg)", "*.jpg");
        ExtensionFilter extFilterPNG = new ExtensionFilter("PNG files (.PNG)", "*.PNG");
        ExtensionFilter extFilterpng = new ExtensionFilter("png files (.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        // Show open file dialog
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                BufferedImage image = ImageIO.read(file);
                WritableImage imagee = SwingFXUtils.toFXImage(image, null);
                imagepro.setImage(imagee);
                imagepro.setFitWidth(200);
                imagepro.setFitHeight(200);
                imagepro.setSmooth(true);
                imagepro.setCache(true);

                try {
                    // Save image to PNG file
                    this.lien = UUID.randomUUID().toString();
                    File f = new File("src/uploads/" + this.lien + ".png");
                    System.out.println(f.toURI().toString());
                    ImageIO.write(image, "PNG", f);

                } catch (IOException ex) {
                    Logger.getLogger(FXML_AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(FXML_AjouterPlatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
