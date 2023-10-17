/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import javafx.scene.Node;
import Entity.Competition;
import Services.CompetitionServices;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Salima
 */
public class ViewBackController implements Initializable {

    @FXML
    private TableView<Competition> tableCompetition;
    @FXML
    private TableColumn<Competition, String> cNom;
    @FXML
    private TableColumn<Competition, Integer> cFrais;
    @FXML
    private TableColumn<Competition, Date> cDate;
    @FXML
    private TableColumn<Competition, Integer> cNbMax;
    @FXML
    private TableColumn<Competition, String> cEtat;
    @FXML
    private TableColumn<Competition, Integer> cNbParticipants;
    @FXML
    private TableColumn<Competition, Integer> cId;
    @FXML
    private Button btModifier;
    @FXML
    private Button btSupprimer;
    @FXML
    private Button btNouvelleC;
    @FXML
    private TextField btNom;
    @FXML
    private DatePicker dateChoisi;
    @FXML
    private TextField btFrais;
    @FXML
    private TextField btMaxParticipants;
    @FXML
    private RadioButton btDisponible;
    @FXML
    private RadioButton btNonDisponible;
    @FXML
    private Button Enregistrer;

    ObservableList<Competition> data = FXCollections.observableArrayList();
    private CompetitionServices ccrd = new CompetitionServices();
    List<Competition> competitions = new ArrayList<>();
    @FXML
    private Button VoirTicket;
    @FXML
    private TextField searchField;
    @FXML
    private Button PDF;
    @FXML
    private Button load;
    @FXML
    private AnchorPane paneViewBack;
    @FXML
    private Button StatBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data.clear();
        tableCompetition.refresh();
        try {
            showBack();
            searchBox();

        } catch (SQLException ex) {
            Logger.getLogger(ViewBackController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 @FXML
    private void NouvelleCompetition(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AjoutCompetition.fxml"));
        paneViewBack.getChildren().removeAll();
        paneViewBack.getChildren().setAll(fxml);

    }
     @FXML
    private void ConsulterTicket(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ViewTickets.fxml"));
        paneViewBack.getChildren().removeAll();
        paneViewBack.getChildren().setAll(fxml);

    }
     @FXML
    private void ConsulterStat(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        paneViewBack.getChildren().removeAll();
        paneViewBack.getChildren().setAll(fxml);

    }
//    @FXML
//    private void NouvelleTicket(ActionEvent event) throws IOException {
//        Parent fxml = FXMLLoader.load(getClass().getResource("AjoutTicket.fxml"));
//        paneViewBack.getChildren().removeAll();
//        paneViewBack.getChildren().setAll(fxml);
//
//    }
   
    public void showBack() throws SQLException {
        try {
            String rq = "SELECT *FROM competition";
            Statement st = MyDB.getInstance().getConx().createStatement();
            ResultSet rs = st.executeQuery(rq);

            while (rs.next()) {
                Competition competition = new Competition(rs.getInt("id"), rs.getString("nom_competition"),
                        (int) rs.getDouble("frais_competition"),
                        rs.getDate("date_competition"),
                        rs.getInt("nbr_max_inscrit"),
                        rs.getString("etat_competition"),
                        rs.getInt("nbr_participant"));
                data.add(competition);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        cNom.setCellValueFactory(new PropertyValueFactory("nomCompetition"));
        cFrais.setCellValueFactory(new PropertyValueFactory("fraisCompetition"));
        cDate.setCellValueFactory(new PropertyValueFactory("dateCompetition"));
        cNbMax.setCellValueFactory(new PropertyValueFactory("nbrMaxInscrit"));
        cEtat.setCellValueFactory(new PropertyValueFactory("etatCompetition"));
        cNbParticipants.setCellValueFactory(new PropertyValueFactory("nbrParticipants"));
        cId.setCellValueFactory(new PropertyValueFactory("id"));
        tableCompetition.setItems(data);
        searchBox();
    }

    @FXML
    private void modifierCompetition(ActionEvent event) {
        Competition comp = tableCompetition.getSelectionModel().getSelectedItem();
        if (comp == null) {
            // Aucune compétition sélectionnée, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une compétition à modifier et remplir tous les champs.");
            alert.showAndWait();
        } else {
            btNom.setText(comp.getNomCompetition());
            Date date = comp.getDateCompetition();
            LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            dateChoisi.setValue(localDate);
            btMaxParticipants.setText(Integer.toString(comp.getNbrMaxInscrit()));
            btFrais.setText(Integer.toString(comp.getFraisCompetition()));

            if (comp.getEtatCompetition().equals("disponible")) {
                btDisponible.setSelected(true);
                btNonDisponible.setSelected(false); // cocher le radioButton
            } else if (comp.getEtatCompetition().equals("non disponible")) {
                btNonDisponible.setSelected(true); // cocher le radioButton
                btDisponible.setSelected(false);
            }
        }
    }

    @FXML
    private void supprimerCompetition(ActionEvent event) throws SQLException {
        if (tableCompetition.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Veuillez sélectionner la competition à supprimer");
            alert.showAndWait();
            return;
        }
        // Afficher une boîte de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment supprimer cette compétition ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Récupérer l'ID de la réclamation sélectionnée dans la vue de la table
            int id = tableCompetition.getSelectionModel().getSelectedItem().getId();

            // Supprimer la réclamation de la base de données
            ccrd.supprimerCompetition(id);
            tableCompetition.refresh();
            // Rafraîchir la liste de données
            data.clear();
            showBack();
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("suppression validée");
            alert.setHeaderText("La compétition séléctionée a été supprimée avec succées ");
            alert.showAndWait();

        }
    }

    @FXML
    private void nouvelleCompetition(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCompetition.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        // Cacher la fenêtre actuelle
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.hide();
        tableCompetition.refresh();
    }

    @FXML
    private void enregistrer(ActionEvent event) {
        // Vérifier que tous les champs sont remplis
        if (tableCompetition.getSelectionModel().isEmpty()
                || btNom.getText().isEmpty()
                || dateChoisi.getValue() == null
                || btMaxParticipants.getText().isEmpty()
                || btFrais.getText().isEmpty()
                || (!btDisponible.isSelected() && !btNonDisponible.isSelected())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de modification");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs pour que la modification s'effectue.");
            alert.showAndWait();
            return;
        } else {
            Competition comp = tableCompetition.getSelectionModel().getSelectedItem();
            int fraisCompetition = 0;
            int nbrMaxParticipants = 0;
            try {
                fraisCompetition = Integer.parseInt(btFrais.getText());
                nbrMaxParticipants = Integer.parseInt(btMaxParticipants.getText());
                if ((fraisCompetition < 0) || (nbrMaxParticipants > 50) || (nbrMaxParticipants < 0)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de modification");
                    alert.setHeaderText(null);
                    alert.setContentText("Les valeurs 'Frais' et 'Max. participants' doivent être des nombres positifs et le nombre max de participants doit être <50.");
                    alert.showAndWait();
                    return;
                }
            } catch (NumberFormatException e) {
                // Afficher un message d'erreur si les valeurs ne peuvent pas être converties en entiers
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setHeaderText(null);
                alert.setContentText("Les valeurs 'Frais' et 'Max. participants' doivent être des nombres entiers.");
                alert.showAndWait();
                return;
            }
            String etatCompetition = btDisponible.isSelected() ? "disponible" : "non disponible";
            if (btDisponible.isSelected() && btNonDisponible.isSelected()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez séléctionner une seule etat!!!.");
                alert.showAndWait();
                return;
            }
            comp.setNomCompetition(btNom.getText());
            comp.setDateCompetition(Date.from(dateChoisi.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            comp.setNbrMaxInscrit(nbrMaxParticipants);
            comp.setFraisCompetition(fraisCompetition);
            comp.setEtatCompetition(etatCompetition);
            ccrd.modifierCompetition(comp);

            // Mettre à jour la compétition dans la base de données
            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification réussie");
            alert.setHeaderText(null);
            alert.setContentText("La compétition a été modifiée avec succès.");
            alert.showAndWait();
            tableCompetition.refresh();
            // Rafraîchir
        }
    }

    @FXML
    private void voirTickets(ActionEvent event) throws IOException {
        Parent loader = FXMLLoader.load(getClass().getResource("ViewTickets.fxml"));
        //Parent root = loader.load();

        // Obtenir le contrôleur associé à la vue FXML
        //AjoutBackController controller = loader.getController();
        // Afficher la nouvelle interface utilisateur
        Scene scene = new Scene(loader);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        // Cacher la fenêtre actuelle
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.hide();
        tableCompetition.refresh();
    }

    @FXML
    private void refreshTab(ActionEvent event) {
        tableCompetition.refresh();
    }

    public void searchBox() throws SQLException {
        //     Competition competition= getTableView().getItems().
        FilteredList<Competition> filteredData = new FilteredList<>(FXCollections.observableArrayList(ccrd.afficherListe()), p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(competition -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String fr = String.valueOf(competition.getFraisCompetition());
                String dt = String.valueOf(competition.getDateCompetition());
                String ins = String.valueOf(competition.getNbrMaxInscrit());
                String nbP = String.valueOf(competition.getNbrParticipants());

                String lowerCaseFilter = newValue.toLowerCase();
                if (competition.getEtatCompetition().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (competition.getNomCompetition().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (fr.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (dt.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (ins.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (nbP.toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<Competition> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableCompetition.comparatorProperty());
        tableCompetition.setItems(sortedData);

    }

    @FXML
    private void pdfbtn(ActionEvent event) throws SQLException {
        // Afficher la boîte de dialogue de sélection de fichier
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le fichier PDF");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers PDF", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            // Générer le fichier PDF avec l'emplacement de sauvegarde sélectionné
            // Récupérer la liste des produits
            CompetitionServices CompetitionService = new CompetitionServices();
            List<Competition> CompetitionList = CompetitionService.afficherListe();

            try {
                // Créer le document PDF
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                // Créer une instance de l'image
                Image image = Image.getInstance(System.getProperty("user.dir") + "/src/images/LogoGymBlack_1.png");

                // Positionner l'image en haut à gauche
                image.setAbsolutePosition(5, document.getPageSize().getHeight() - 120);

                // Modifier la taille de l'image
                image.scaleAbsolute(100, 100);

                // Ajouter l'image au document
                document.add(image);

                // Créer une police personnalisée pour la date
                Font fontDate = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                BaseColor color = new BaseColor(114, 0, 0); // Rouge: 50, Vert: 187, Bleu: 111
                fontDate.setColor(color); // Définir la couleur de la police

                // Créer un paragraphe avec le lieu
                Paragraph tunis = new Paragraph("Tunis", fontDate);
                tunis.setIndentationLeft(455); // Définir la position horizontale
                tunis.setSpacingBefore(-30); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(tunis);

                // Obtenir la date d'aujourd'hui
                LocalDate today = LocalDate.now();

                // Créer un paragraphe avec la date
                Paragraph date = new Paragraph(today.toString(), fontDate);

                date.setIndentationLeft(437); // Définir la position horizontale
                date.setSpacingBefore(1); // Définir la position verticale
                // Ajouter le paragraphe au document
                document.add(date);

                // Créer une police personnalisée
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 32, Font.BOLD);
                BaseColor titleColor = new BaseColor(114, 0, 0); //
                font.setColor(titleColor);

                // Ajouter le contenu au document
                Paragraph title = new Paragraph("Liste des competitions", font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50); // Ajouter une marge avant le titre pour l'éloigner de l'image
                title.setSpacingAfter(20);
                document.add(title);

                PdfPTable table = new PdfPTable(7); // 
                table.setWidthPercentage(100);
                table.setSpacingBefore(30f);
                table.setSpacingAfter(30f);

                // Ajouter les en-têtes de colonnes
                Font hrFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
                BaseColor hrColor = new BaseColor(114, 0, 0); //
                hrFont.setColor(hrColor);

                PdfPCell cell1 = new PdfPCell(new Paragraph("id", hrFont));
                BaseColor bgColor = new BaseColor(255, 192, 203);
                cell1.setBackgroundColor(bgColor);
                cell1.setBorderColor(titleColor);
                cell1.setPaddingTop(20);
                cell1.setPaddingBottom(20);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell2 = new PdfPCell(new Paragraph("NomCompetition", hrFont));
                cell2.setBackgroundColor(bgColor);
                cell2.setBorderColor(titleColor);
                cell2.setPaddingTop(20);
                cell2.setPaddingBottom(20);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell3 = new PdfPCell(new Paragraph("frais Competition", hrFont));
                cell3.setBackgroundColor(bgColor);
                cell3.setBorderColor(titleColor);
                cell3.setPaddingTop(20);
                cell3.setPaddingBottom(20);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell4 = new PdfPCell(new Paragraph("date Competition", hrFont));
                cell4.setBackgroundColor(bgColor);
                cell4.setBorderColor(titleColor);
                cell4.setPaddingTop(20);
                cell4.setPaddingBottom(20);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell5 = new PdfPCell(new Paragraph("nombre Max d'Inscrit ", hrFont));
                cell5.setBackgroundColor(bgColor);
                cell5.setBorderColor(titleColor);
                cell5.setPaddingTop(20);
                cell5.setPaddingBottom(20);
                cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell6 = new PdfPCell(new Paragraph("etat Competition", hrFont));
                cell6.setBackgroundColor(bgColor);
                cell6.setBorderColor(titleColor);
                cell6.setPaddingTop(20);
                cell6.setPaddingBottom(20);
                cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell cell7 = new PdfPCell(new Paragraph("nombre de Participants", hrFont));
                cell7.setBackgroundColor(bgColor);
                cell7.setBorderColor(titleColor);
                cell7.setPaddingTop(20);
                cell7.setPaddingBottom(20);
                cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);
                table.addCell(cell5);
                table.addCell(cell6);
                table.addCell(cell7);

                Font hdFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
                BaseColor hdColor = new BaseColor(114, 0, 0); //
                hrFont.setColor(hdColor);
                // Ajouter les données des produits
                for (Competition competition : CompetitionList) {
                    PdfPCell cellR1 = new PdfPCell(new Paragraph(String.valueOf(competition.getId()), hdFont));
                    cellR1.setBorderColor(titleColor);
                    cellR1.setPaddingTop(10);
                    cellR1.setPaddingBottom(10);
                    cellR1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR1);

                    PdfPCell cellR2 = new PdfPCell(new Paragraph(String.valueOf(competition.getNomCompetition()), hdFont));
                    cellR2.setBorderColor(titleColor);
                    cellR2.setPaddingTop(10);
                    cellR2.setPaddingBottom(10);
                    cellR2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR2);

                    PdfPCell cellR3 = new PdfPCell(new Paragraph(String.valueOf(competition.getFraisCompetition()), hdFont));
                    cellR3.setBorderColor(titleColor);
                    cellR3.setPaddingTop(10);
                    cellR3.setPaddingBottom(10);
                    cellR3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR3);

                    PdfPCell cellR4 = new PdfPCell(new Paragraph(String.valueOf(competition.getDateCompetition()), hdFont));
                    cellR4.setBorderColor(titleColor);
                    cellR4.setPaddingTop(10);
                    cellR4.setPaddingBottom(10);
                    cellR4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR4);

                    PdfPCell cellR5 = new PdfPCell(
                            new Paragraph(String.valueOf(competition.getNbrMaxInscrit()), hdFont));
                    cellR5.setBorderColor(titleColor);
                    cellR5.setPaddingTop(10);
                    cellR5.setPaddingBottom(10);
                    cellR5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR5);

                    PdfPCell cellR6 = new PdfPCell(
                            new Paragraph(String.valueOf(competition.getEtatCompetition()), hdFont));
                    cellR5.setBorderColor(titleColor);
                    cellR5.setPaddingTop(10);
                    cellR5.setPaddingBottom(10);
                    cellR5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR6);
                    PdfPCell cellR7 = new PdfPCell(
                            new Paragraph(String.valueOf(competition.getNbrParticipants()), hdFont));
                    cellR5.setBorderColor(titleColor);
                    cellR5.setPaddingTop(10);
                    cellR5.setPaddingBottom(10);
                    cellR5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cellR7);
                }
                table.setSpacingBefore(20);
                document.add(table);
                document.close();

                System.out.println("Le fichier PDF a été généré avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void Statistique(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistique.fxml"));
        Parent root = loader.load();

        // Obtenir le contrôleur associé à la vue FXML
        StatistiqueController controller = loader.getController();

        // Afficher la nouvelle interface utilisateur
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        // Cacher la fenêtre actuelle
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        currentStage.hide();

    }
}
