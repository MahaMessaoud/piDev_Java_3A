/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;


/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane listReclamationPane;
    
    @FXML
    void open_addReclamation(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutReclamation.fxml"));
        listReclamationPane.getChildren().removeAll();
        listReclamationPane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherReclamation();
    }    
    
    
    /*TableView Réclamation*/
    @FXML
    private TableColumn<Reclamation, String> NomUserRecCell;
    @FXML
    private TableColumn<Reclamation, String> EmailUserRecCell;
    @FXML
    private TableColumn<Reclamation, String> ObjetRecCell;
    @FXML
    private TableColumn<Reclamation, Integer> CategRecCell;
    @FXML
    private TableColumn<Reclamation, Date> DateRecCell;
    @FXML
    private TableColumn<Reclamation, String> TexteRecCell;
    @FXML
    private TableView<Reclamation> tableReclamation;
    
    
    @FXML
    private Button btnDeleteRec;
    @FXML
    private TextField txtSearchRec;
    @FXML
    private ComboBox<String> comboBoxTriReclamation;
    @FXML
    private TableColumn<Reclamation,Void> actCell;
    
    
    
    ObservableList<Reclamation> dataReclamation = FXCollections.observableArrayList();
    
    
    public void AfficherReclamation()
    {
        ReclamationService rs = new ReclamationService();
        rs.Show().stream().forEach((c) -> {
            dataReclamation.add(c);
        });
        
        NomUserRecCell.setCellValueFactory(new PropertyValueFactory<>("nom_user_reclamation"));
        NomUserRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        NomUserRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, String> event) {
                Reclamation r = event.getRowValue();
                r.setNom_user_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        EmailUserRecCell.setCellValueFactory(new PropertyValueFactory<>("email_user_reclamation"));
        EmailUserRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        EmailUserRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, String> event) {
                Reclamation r = event.getRowValue();
                r.setEmail_user_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        ObjetRecCell.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
        ObjetRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        ObjetRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, String> event) {
                Reclamation r = event.getRowValue();
                r.setObjet_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        CategRecCell.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        CategRecCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        CategRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, Integer> event) {
                Reclamation r = event.getRowValue();
                r.setCategory_id(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        DateRecCell.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        DateRecCell.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
            private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            @Override
            public String toString(Date object) {
                return dateFormat.format(object);
            }

            @Override
            public Date fromString(String string) {
                try {
                    // Parse the string into a Date object using the defined format
                    return dateFormat.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // If the string can't be parsed, return null
                    return null;
                }
            }
        }));
        DateRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, Date> event) {
                Reclamation r = event.getRowValue();
                r.setDate_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        TexteRecCell.setCellValueFactory(new PropertyValueFactory<>("texte_reclamation"));
        TexteRecCell.setCellFactory(TextFieldTableCell.forTableColumn());
        TexteRecCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reclamation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reclamation, String> event) {
                Reclamation r = event.getRowValue();
                r.setTexte_reclamation(event.getNewValue());
                ReclamationService rs = new ReclamationService();
                rs.modifier(r);
            }
        });
        actCell.setCellFactory(column->{
            return new TableCell<Reclamation,Void>(){
                private final Button repondreBtn = new Button("Repondre");
                {
                    repondreBtn.setStyle("-fx-background-color: #720000; -fx-text-fill: #fff;");
                    repondreBtn.setOnAction(event->{
                        Reclamation rec = getTableView().getItems().get(getIndex());
                        Parent fxml;
                        try {
                            fxml = FXMLLoader.load(getClass().getResource("ajoutReponse.fxml"));
                            listReclamationPane.getChildren().removeAll();
                            listReclamationPane.getChildren().setAll(fxml);
                        } catch (IOException ex) {
                            Logger.getLogger(ListReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                }
                
                @Override
                protected void updateItem(Void item,boolean empty){
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                    }else{
                        setGraphic(repondreBtn);
                    }
                }
            };
        });
        tableReclamation.setItems(dataReclamation);
        comboBoxTriReclamation.getItems().addAll("Trier Selon",  "Nom User", "Email User", "Date", "Objet", "Texte");
        try {
            searchReclamation();
        } catch (SQLException ex) {
            Logger.getLogger(ListActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void supprimerReclamation(ActionEvent event) throws SQLException {
        ReclamationService rs = new ReclamationService();
        
        if (tableReclamation.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner la réclamation à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette réclamation ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID du planning sélectionnée dans la vue de la table
            int id = tableReclamation.getSelectionModel().getSelectedItem().getId();

            // Supprimer la réclamation de la base de données
            rs.supprimer(id);
            // Rafraîchir la liste de données
            dataReclamation.clear();
            AfficherReclamation();
            // Rafraîchir la vue de la table
            tableReclamation.refresh();
        }
    }
    
    
    private void TriNomUser() {
        ReclamationService cs = new ReclamationService();
        List<Reclamation> a = cs.triNomUserReclamation();
        NomUserRecCell.setCellValueFactory(new PropertyValueFactory<>("nom_user_reclamation"));
        EmailUserRecCell.setCellValueFactory(new PropertyValueFactory<>("email_user_reclamation"));
        ObjetRecCell.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
        CategRecCell.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        DateRecCell.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        TexteRecCell.setCellValueFactory(new PropertyValueFactory<>("texte_reclamation"));

        tableReclamation.setItems(FXCollections.observableList(a));
    }
    
    private void TriEmailUser() {
        ReclamationService cs = new ReclamationService();
        List<Reclamation> a = cs.triEmailUserReclamation();
        NomUserRecCell.setCellValueFactory(new PropertyValueFactory<>("nom_user_reclamation"));
        EmailUserRecCell.setCellValueFactory(new PropertyValueFactory<>("email_user_reclamation"));
        ObjetRecCell.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
        CategRecCell.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        DateRecCell.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        TexteRecCell.setCellValueFactory(new PropertyValueFactory<>("texte_reclamation"));

        tableReclamation.setItems(FXCollections.observableList(a));
    }
    
    
    private void TriDateReclamation() {
        ReclamationService cs = new ReclamationService();
        List<Reclamation> a = cs.triDateReclamation();
        NomUserRecCell.setCellValueFactory(new PropertyValueFactory<>("nom_user_reclamation"));
        EmailUserRecCell.setCellValueFactory(new PropertyValueFactory<>("email_user_reclamation"));
        ObjetRecCell.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
        CategRecCell.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        DateRecCell.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        TexteRecCell.setCellValueFactory(new PropertyValueFactory<>("texte_reclamation"));

        tableReclamation.setItems(FXCollections.observableList(a));
    }
    
    
    private void TriObjetReclamation() {
        ReclamationService cs = new ReclamationService();
        List<Reclamation> a = cs.triObjetReclamation();
        NomUserRecCell.setCellValueFactory(new PropertyValueFactory<>("nom_user_reclamation"));
        EmailUserRecCell.setCellValueFactory(new PropertyValueFactory<>("email_user_reclamation"));
        ObjetRecCell.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
        CategRecCell.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        DateRecCell.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        TexteRecCell.setCellValueFactory(new PropertyValueFactory<>("texte_reclamation"));

        tableReclamation.setItems(FXCollections.observableList(a));
    }
    
    private void TriTexteReclamation() {
        ReclamationService cs = new ReclamationService();
        List<Reclamation> a = cs.triTexteReclamation();
        NomUserRecCell.setCellValueFactory(new PropertyValueFactory<>("nom_user_reclamation"));
        EmailUserRecCell.setCellValueFactory(new PropertyValueFactory<>("email_user_reclamation"));
        ObjetRecCell.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
        CategRecCell.setCellValueFactory(new PropertyValueFactory<>("category_id"));
        DateRecCell.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        TexteRecCell.setCellValueFactory(new PropertyValueFactory<>("texte_reclamation"));

        tableReclamation.setItems(FXCollections.observableList(a));
    }
    
    
    @FXML
    private void TriChoice(ActionEvent event) throws IOException {
        if (comboBoxTriReclamation.getValue().equals("Nom User")) {
            TriNomUser();
        } else if (comboBoxTriReclamation.getValue().equals("Email User")) {
            TriEmailUser();
        } else if (comboBoxTriReclamation.getValue().equals("Date")) {
            TriDateReclamation();
        } else if (comboBoxTriReclamation.getValue().equals("Objet")) {
            TriObjetReclamation();
        } else if (comboBoxTriReclamation.getValue().equals("Texte")) {
            TriTexteReclamation();
        } 

    }
    
    public ReclamationService rs = new ReclamationService();
    
    public void searchReclamation() throws SQLException {    
        FilteredList<Reclamation> filteredData = new FilteredList<>(FXCollections.observableArrayList(rs.Show()), p -> true);
        txtSearchRec.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rec -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String category = String.valueOf(rec.getCategory_id());
                String objet = String.valueOf(rec.getObjet_reclamation());
                String texte = String.valueOf(rec.getTexte_reclamation());
                String date = String.valueOf(rec.getDate_reclamation());
                String nomUser = String.valueOf(rec.getNom_user_reclamation());
                String emailUser = String.valueOf(rec.getEmail_user_reclamation());
                String lowerCaseFilter = newValue.toLowerCase();

                if (category.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (objet.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (texte.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (date.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (nomUser.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (emailUser.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReclamation.comparatorProperty());
        tableReclamation.setItems(sortedData);
    }
    
}
