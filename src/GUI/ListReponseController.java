/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reponse;
import Services.ReponseService;
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
public class ListReponseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane listReponsePane;
    
    @FXML
    void open_addReponse(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("ajoutReponse.fxml"));
        listReponsePane.getChildren().removeAll();
        listReponsePane.getChildren().setAll(fxml);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherReponse();
    }    
    
    
    /*TableView Reponse*/
    @FXML
    private TableColumn<Reponse, Integer> ReclamationReponseCell;
    @FXML
    private TableColumn<Reponse, String> ObjetReponseCell;
    @FXML
    private TableColumn<Reponse, Date> DateReponseCell;
    @FXML
    private TableColumn<Reponse, String> PieceJointeReponseCell;
    @FXML
    private TableColumn<Reponse, String> ContenuReponseCell;
    @FXML
    private TableView<Reponse> tableReponse;
    
    
    @FXML
    private TextField txtSearchReponse;
    @FXML
    private Button btnDeleteReponse;
    @FXML
    private ComboBox<String> comboBoxTriReponse;
    
    
    ObservableList<Reponse> dataReponse = FXCollections.observableArrayList();
    
    
    public void AfficherReponse()
    {
        ReponseService rep = new ReponseService();
        rep.Show().stream().forEach((c) -> {
            dataReponse.add(c);
        });
        
        ReclamationReponseCell.setCellValueFactory(new PropertyValueFactory<>("reclamation_id"));
        ReclamationReponseCell.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ReclamationReponseCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reponse, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reponse, Integer> event) {
                Reponse rep = event.getRowValue();
                rep.setReclamation_id(event.getNewValue());
                ReponseService reps = new ReponseService();
                reps.modifier(rep);
            }
        });
        ObjetReponseCell.setCellValueFactory(new PropertyValueFactory<>("objet_reponse"));
        ObjetReponseCell.setCellFactory(TextFieldTableCell.forTableColumn());
        ObjetReponseCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reponse, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reponse, String> event) {
                Reponse rep = event.getRowValue();
                rep.setObjet_reponse(event.getNewValue());
                ReponseService reps = new ReponseService();
                reps.modifier(rep);
            }
        });
        DateReponseCell.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
        DateReponseCell.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Date>() {
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
        DateReponseCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reponse, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reponse, Date> event) {
                Reponse rep = event.getRowValue();
                rep.setDate_reponse(event.getNewValue());
                ReponseService reps = new ReponseService();
                reps.modifier(rep);
            }
        });
        PieceJointeReponseCell.setCellValueFactory(new PropertyValueFactory<>("piece_jointe"));
        PieceJointeReponseCell.setCellFactory(TextFieldTableCell.forTableColumn());
        PieceJointeReponseCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reponse, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reponse, String> event) {
                Reponse rep = event.getRowValue();
                rep.setPiece_jointe(event.getNewValue());
                ReponseService reps = new ReponseService();
                reps.modifier(rep);
            }
        });
        ContenuReponseCell.setCellValueFactory(new PropertyValueFactory<>("contenu_reponse"));
        ContenuReponseCell.setCellFactory(TextFieldTableCell.forTableColumn());
        ContenuReponseCell.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Reponse, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Reponse, String> event) {
                Reponse rep = event.getRowValue();
                rep.setContenu_reponse(event.getNewValue());
                ReponseService reps = new ReponseService();
                reps.modifier(rep);
            }
        });
        tableReponse.setItems(dataReponse);
        comboBoxTriReponse.getItems().addAll("Trier Selon", "Date", "Objet", "Contenu");
        try {
            searchReponse();
        } catch (SQLException ex) {
            Logger.getLogger(ListActiviteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void supprimerReponse(ActionEvent event) throws SQLException {
        ReponseService rep = new ReponseService();
        
        if (tableReponse.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner la reponse à supprimer");
            alert.showAndWait();
            return;
        }

        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer ce reponse ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la reponse sélectionnée dans la vue de la table
            int id = tableReponse.getSelectionModel().getSelectedItem().getId();

            // Supprimer la reponse de la base de données
            rep.supprimer(id);
            // Rafraîchir la liste de données
            dataReponse.clear();
            AfficherReponse();
            // Rafraîchir la vue de la table
            tableReponse.refresh();
        }
    }
    
    
    private void TriDateReponse() {
        ReponseService rs = new ReponseService();
        List<Reponse> rep = rs.triDateReponse();
        ReclamationReponseCell.setCellValueFactory(new PropertyValueFactory<>("reclamation_id"));
        ObjetReponseCell.setCellValueFactory(new PropertyValueFactory<>("objet_reponse"));
        DateReponseCell.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
        PieceJointeReponseCell.setCellValueFactory(new PropertyValueFactory<>("piece_jointe"));
        ContenuReponseCell.setCellValueFactory(new PropertyValueFactory<>("contenu_reponse"));

        tableReponse.setItems(FXCollections.observableList(rep));
    }
    
    
    private void TriObjetReponse() {
        ReponseService rs = new ReponseService();
        List<Reponse> rep = rs.triObjetReponse();
        ReclamationReponseCell.setCellValueFactory(new PropertyValueFactory<>("reclamation_id"));
        ObjetReponseCell.setCellValueFactory(new PropertyValueFactory<>("objet_reponse"));
        DateReponseCell.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
        PieceJointeReponseCell.setCellValueFactory(new PropertyValueFactory<>("piece_jointe"));
        ContenuReponseCell.setCellValueFactory(new PropertyValueFactory<>("contenu_reponse"));

        tableReponse.setItems(FXCollections.observableList(rep));
    }
    
    
    private void TriContenuReponse() {
        ReponseService rs = new ReponseService();
        List<Reponse> rep = rs.triContenuReponse();
        ReclamationReponseCell.setCellValueFactory(new PropertyValueFactory<>("reclamation_id"));
        ObjetReponseCell.setCellValueFactory(new PropertyValueFactory<>("objet_reponse"));
        DateReponseCell.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
        PieceJointeReponseCell.setCellValueFactory(new PropertyValueFactory<>("piece_jointe"));
        ContenuReponseCell.setCellValueFactory(new PropertyValueFactory<>("contenu_reponse"));

        tableReponse.setItems(FXCollections.observableList(rep));
    }
    
    
    @FXML
    private void TriChoice(ActionEvent event) throws IOException {
        if (comboBoxTriReponse.getValue().equals("Date")) {
            TriDateReponse();
        } else if (comboBoxTriReponse.getValue().equals("Objet")) {
            TriObjetReponse();
        } else if (comboBoxTriReponse.getValue().equals("Contenu")) {
            TriContenuReponse();
        } 

    }
    
    
    public ReponseService rs = new ReponseService();
    
    public void searchReponse() throws SQLException {    
        FilteredList<Reponse> filteredData = new FilteredList<>(FXCollections.observableArrayList(rs.Show()), p -> true);
        txtSearchReponse.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(rep -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String reclamation = String.valueOf(rep.getReclamation_id());
                String objet = String.valueOf(rep.getObjet_reponse());
                String date = String.valueOf(rep.getDate_reponse());
                String pieceJointe = String.valueOf(rep.getPiece_jointe());
                String contenu = String.valueOf(rep.getContenu_reponse());
                String lowerCaseFilter = newValue.toLowerCase();

                if (reclamation.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (objet.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (date.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pieceJointe.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (contenu.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Reponse> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableReponse.comparatorProperty());
        tableReponse.setItems(sortedData);
    }
    
}
