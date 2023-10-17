/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Services.CoursService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListCoursController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane listCoursPane;
    
    @FXML
    void open_addCours(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutCours.fxml"));
        listCoursPane.getChildren().removeAll();
        listCoursPane.getChildren().setAll(fxml);
    }
    
    
    @FXML
    void open_Stat(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
        listCoursPane.getChildren().removeAll();
        listCoursPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherCours();
    }  
    
    
    /*TableView Cours*/
    @FXML
    private TableColumn<Cours, String> NomCoursCell;
    @FXML
    private TableColumn<Cours, Float> PrixCoursCell;
    @FXML
    private TableColumn<Cours, String> NomCoachCoursCell;
    @FXML
    private TableColumn<Cours, Integer> AgeMinCoursCell;
    @FXML
    private TableColumn<Cours, String> DescriptionCoursCell;
    @FXML
    private TableView<Cours> tableCours;
    
    
    @FXML
    private Button btnDeleteCours;
    @FXML
    private TextField txtSearchCours;
    @FXML
    private ComboBox<String> comboBoxTriCours;
    
    
    ObservableList<Cours> dataCours = FXCollections.observableArrayList();  
    
    
    public void AfficherCours()
    {
        CoursService cs = new CoursService();
        cs.Show().stream().forEach((c) -> {
            dataCours.add(c);
        });
        
        NomCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        NomCoursCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, String> event) {
                Cours c = event.getRowValue();
                c.setNom_cours(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        PrixCoursCell.setCellValueFactory(new PropertyValueFactory<>("prix_cours"));
        PrixCoursCell.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Float>() {
            @Override
            public String toString(Float object) {
                return object.toString();
            }

            @Override
            public Float fromString(String string) {
                return Float.valueOf(string);
            }
        }));
        PrixCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, Float> event) {
                Cours c = event.getRowValue();
                c.setPrix_cours(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        NomCoachCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
        NomCoachCoursCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomCoachCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, String> event) {
                Cours c = event.getRowValue();
                c.setNom_coach(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        AgeMinCoursCell.setCellValueFactory(new PropertyValueFactory<>("age_min_cours"));
        AgeMinCoursCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        AgeMinCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, Integer> event) {
                Cours c = event.getRowValue();
                c.setAge_min_cours(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        DescriptionCoursCell.setCellValueFactory(new PropertyValueFactory<>("description_cours"));
        DescriptionCoursCell.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionCoursCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Cours, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Cours, String> event) {
                Cours c = event.getRowValue();
                c.setDescription_cours(event.getNewValue());
                CoursService cs = new CoursService();
                cs.modifier(c);
            }
        });
        tableCours.setItems(dataCours);
        comboBoxTriCours.getItems().addAll("Trier Selon",  "Nom Cours", "Nom Coach" , "Description");
        try {
            searchCours();
        } catch (SQLException ex) {
            Logger.getLogger(ListActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supprimerCours(ActionEvent event) throws SQLException {
        CoursService cs = new CoursService();
        
        if (tableCours.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner le cours à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce cours ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID du cours sélectionnée dans la vue de la table
            int id = tableCours.getSelectionModel().getSelectedItem().getId();

            // Supprimer le cours de la base de données
            cs.supprimer(id);
            // Rafraîchir la liste de données
            dataCours.clear();
            AfficherCours();
            // Rafraîchir la vue de la table
            tableCours.refresh();
        }
    }
    
    @FXML
    void excelBtn(MouseEvent event) throws FileNotFoundException, IOException {
        // Créer un nouveau classeur
        Workbook workbook = new XSSFWorkbook();

        // Créer une nouvelle feuille de calcul
        Sheet sheet = workbook.createSheet("Cours");

        // Récupérer la liste des produits
        CoursService cs = new CoursService();
        List<Cours> coursList = cs.Show();
        
        if (coursList.isEmpty()) {
            // Aucun cours à exporter
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Export Excel");
            alert.setHeaderText(null);
            alert.setContentText("Aucun cours à exporter.");
            alert.showAndWait();
            return;
        }

        // Créer la première ligne pour les en-têtes des colonnes
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Nom");
        headerRow.createCell(1).setCellValue("Prix");
        headerRow.createCell(2).setCellValue("NomCoach");
        headerRow.createCell(3).setCellValue("Age");
        headerRow.createCell(4).setCellValue("Description");

        
        // Remplir les données des produits
        int rowNum = 1;
        for (Cours cour : coursList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(cour.getNom_cours());
            row.createCell(1).setCellValue(cour.getPrix_cours());
            row.createCell(2).setCellValue(cour.getNom_coach());
            row.createCell(3).setCellValue(cour.getAge_min_cours());
            row.createCell(4).setCellValue(cour.getDescription_cours());
        }

        

        // Ouvrir une boîte de dialogue de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier Excel");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers Excel", "*.xlsx"));
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            // Enregistrer le fichier dans l'emplacement choisi par l'utilisateur
            try (FileOutputStream outputStream = new FileOutputStream(selectedFile)) {
                workbook.write(outputStream);
            }
        }
    }
    
    
    private void TriNomCours() {
        CoursService cs = new CoursService();
        List<Cours> a = cs.triNomCours();
        NomCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        PrixCoursCell.setCellValueFactory(new PropertyValueFactory<>("prix_cours"));
        NomCoachCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
        AgeMinCoursCell.setCellValueFactory(new PropertyValueFactory<>("age_min_cours"));
        DescriptionCoursCell.setCellValueFactory(new PropertyValueFactory<>("description_cours"));
        

        tableCours.setItems(FXCollections.observableList(a));

    }
    
    
    private void TriNomCoach() {
        CoursService cs = new CoursService();
        List<Cours> a = cs.triNomCoach();
        NomCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        PrixCoursCell.setCellValueFactory(new PropertyValueFactory<>("prix_cours"));
        NomCoachCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
        AgeMinCoursCell.setCellValueFactory(new PropertyValueFactory<>("age_min_cours"));
        DescriptionCoursCell.setCellValueFactory(new PropertyValueFactory<>("description_cours"));
        

        tableCours.setItems(FXCollections.observableList(a));

    }
    
    private void TriDescription() {
        CoursService cs = new CoursService();
        List<Cours> a = cs.triDescriptionCours();
        NomCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        PrixCoursCell.setCellValueFactory(new PropertyValueFactory<>("prix_cours"));
        NomCoachCoursCell.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
        AgeMinCoursCell.setCellValueFactory(new PropertyValueFactory<>("age_min_cours"));
        DescriptionCoursCell.setCellValueFactory(new PropertyValueFactory<>("description_cours"));
        

        tableCours.setItems(FXCollections.observableList(a));

    }
    
    
    @FXML
    private void TriChoice(ActionEvent event) throws IOException {
        if (comboBoxTriCours.getValue().equals("Nom Cours")) {
            TriNomCours();
        } else if (comboBoxTriCours.getValue().equals("Nom Coach")) {
            TriNomCoach();
        } else if (comboBoxTriCours.getValue().equals("Description")) {
            TriDescription();
        }

    }
    
    public CoursService cs = new CoursService();
    
    public void searchCours() throws SQLException {    
        FilteredList<Cours> filteredData = new FilteredList<>(FXCollections.observableArrayList(cs.Show()), p -> true);
        txtSearchCours.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cours -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String nom = String.valueOf(cours.getNom_cours());
                String prix = String.valueOf(cours.getPrix_cours());
                String nomCoach = String.valueOf(cours.getNom_coach());
                String ageMin = String.valueOf(cours.getAge_min_cours());
                String description = String.valueOf(cours.getDescription_cours());
                String lowerCaseFilter = newValue.toLowerCase();

                if (nom.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (prix.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (nomCoach.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ageMin.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (description.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Cours> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCours.comparatorProperty());
        tableCours.setItems(sortedData);
    }
    
    
    
}
