/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.CompetitionUser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import Entity.Competition;
import Entity.Ticket;
import Services.CompetitionServices;
import Services.TicketServices;
import Services.UserService;
import Utils.MyDB;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;


/**
 * FXML Controller class
 *
 * @author Salima
 */
public class ViewFrontController implements Initializable {

    @FXML
    private BorderPane bp;
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
    private Button btReserver;
    private CompetitionServices ccrd =new CompetitionServices();
    ObservableList<Competition> data = FXCollections.observableArrayList();
    @FXML
    private AnchorPane paneFront;
        public void setConnection(Connection connection) {
        this.conx = connection;
    }
 Ticket t=new Ticket();
      Connection conx=null;
      MyDB cnx = null;
    Statement st = null;
    TicketServices tckS=new TicketServices();
    CompetitionServices cS = new CompetitionServices();  
  
    @FXML
    private TextField backIndex;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO.
      tableCompetition.refresh();
      ViewFront();
        try {
            searchBox();
        } catch (SQLException ex) {
            Logger.getLogger(ViewFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


    public void ViewFront(){
        
        try{
        String rq= "SELECT *FROM competition";
        Statement st=MyDB.getInstance().getConx().createStatement();
        ResultSet rs = st.executeQuery(rq);
            int nb=0;
            while (rs.next()) {
                nb++;
                Competition competition = new Competition(rs.getInt("id"),rs.getString("nom_competition"),
                                                         (int) rs.getDouble("frais_competition"),
                                                          rs.getDate("date_competition"),
                                                          rs.getInt("nbr_max_inscrit"),
                                                          rs.getString("etat_competition"),
                                                          rs.getInt("nbr_participant"));
                  data.add(competition);
        }
        }
        catch (SQLException ex) {
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

    }


@FXML
private void reserver(ActionEvent event) throws IOException, SQLException {
    LocalDate currentDate = LocalDate.now();
    Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    TicketServices ts= new TicketServices();
    Competition comp = tableCompetition.getSelectionModel().getSelectedItem();
    if (comp == null) {
        // Aucune compétition sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de réservation");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une compétition à réserver.");
        alert.showAndWait();
    } else if (comp.getEtatCompetition().equals("non disponible") || comp.getNbrMaxInscrit() == 0) {
        // Afficher un message indiquant que la réservation n'est pas possible
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de réservation");
        alert.setHeaderText(null);
        alert.setContentText("Erreur de réservation, cette compétition n'est plus disponible.");
        alert.showAndWait();
    } else if (comp.getNbrMaxInscrit() > 0 && comp.getDateCompetition().after(date)) {
        comp.setNbrMaxInscrit(comp.getNbrMaxInscrit() - 1);
        comp.setNbrParticipants(comp.getNbrParticipants() + 1);
        System.out.println(comp.toString());
        cS.modifierCompetition(comp);
        tableCompetition.refresh();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Réservation validée");
        alert.setHeaderText(null);
        alert.setContentText("Validation de la réservation, compétition réservée avec succès. Vous voulez obtenir le ticket de votre compétition ?");
        Optional<ButtonType> result = alert.showAndWait();
        ts.ReserverCompTicket(new CompetitionUser(UserService.idUser,comp.getId()));
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int idd=tckS.existeTicket(comp.getId());
            if (idd >= 0) {
                System.out.println(tckS.afficher(idd));
                t= tckS.afficher(idd);
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
alert1.setTitle("Ticket de " + comp.getNomCompetition());
alert1.setHeaderText(null);
alert1.setContentText("Votre ticket est :  " + t.getCompetition().getNomCompetition() + "\nDescription:  " + t.getDescription() + "\n\nVoulez-vous le télécharger ?");
ButtonType buttonTypeDownload = new ButtonType("Télécharger");
ButtonType buttonTypeCancel = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
alert1.getButtonTypes().setAll(buttonTypeDownload, buttonTypeCancel);
Optional<ButtonType> result1 = alert1.showAndWait();

if (result1.get() == buttonTypeDownload) {  
    pdfBtn(t);
    // Code pour télécharger le ticket
} else {
    // Code pour annuler la demande de téléchargement
}

            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ticket non disponible");
                alert2.setHeaderText(null);
                alert2.setContentText("Votre réservation a été effectuée. Veuillez attendre l'admin pour l'ajout du ticket !");
                alert2.showAndWait();
            
        }
        }
        tableCompetition.refresh();
        searchBox();
    } else {
        // Afficher un message indiquant que la réservation n'est pas possible
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de réservation");
        alert.setHeaderText(null);
        alert.setContentText("La date limite de réservation pour cette compétition est dépassée.");
        alert.showAndWait();
    }

//&& 
        // Ajouter à la session du user
    
    tableCompetition.refresh();
    searchBox();
}


    public void searchBox() throws SQLException {
   //     Competition competition= getTableView().getItems().
       FilteredList<Competition> filteredData = new FilteredList<>(FXCollections.observableArrayList(cS.afficherListe()), p -> true);
        backIndex.textProperty().addListener((observable, oldValue, newValue) -> {
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
    /*
    public void sendMail() {
        // Set the SMTP host and port for sending the email
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "arco.sc0156@gmail.com";
        String password = "hghseksuroiqviag";

        // Set the properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption

        // Create a new email session using the specified properties
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            Message msg = new MimeMessage(session);

            // Set the "From" address for the email
             msg.setFrom(new InternetAddress("salima.jenhani@esprit.tn"));
            // Add the "To" address for the email (including the recipient's name)
            
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mail de confirmation"));
            // Set the subject and body text for the email
            msg.setSubject("Demande d'une charge.");
            msg.setText("Mr j'ai besoin d'une charge le plus tôt possible et merci!");
            // Create an alert to notify the user that the email was sent successfully

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation d'envoie");
            alert.setHeaderText("Voulez-vous envoyez ce mail à  ?");
            alert.setContentText("Cette action est requise.");
            
            // Show the confirmation dialog and wait for the user's response
            Optional<ButtonType> result = alert.showAndWait();

            // Send the email
            if (result.get() == ButtonType.OK) {

               

                Transport.send(msg);
 

            } else {
                // Close the dialog and do nothing
                alert.close();
               
            }

            // Print a message to the console to indicate that the email was sent successfully
        } catch (AddressException e) {
            // Create an alert to notify the user that there was an error with the email address
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
    */
 public void pdfBtn(Ticket t) {
      FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer le ticket en PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers PDF", "*.pdf"));
     try {
        // Création d'un objet Document
        Document document = new Document();

        // Création d'un objet PdfWriter
        PdfWriter.getInstance(document, new FileOutputStream("ticketNum "+t.getId()+".pdf"));

        // Ouverture du document
        document.open();
                Image image = Image.getInstance(System.getProperty("user.dir") + "/src/images/LogoGymBlack_1.png");

                // Positionner l'image en haut à gauche
                image.setAbsolutePosition(5, document.getPageSize().getHeight() - 120);

                // Modifier la taille de l'image
                image.scaleAbsolute(100, 100);

                // Ajouter l'image au document
                document.add(image);

                // Créer une police personnalisée pour la date
                Font fontDate = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                BaseColor color = new BaseColor(114,0,0); // Rouge: 50, Vert: 187, Bleu: 111
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
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD);
                BaseColor titleColor = new BaseColor(114, 0, 0); //
                font.setColor(titleColor);

                // Ajouter le contenu au document
                Paragraph title = new Paragraph("Ticket pour competition:\n "+t.getCompetition().getNomCompetition(), font);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingBefore(50); // Ajouter une marge avant le titre pour l'éloigner de l'image
                title.setSpacingAfter(20);
                document.add(title);
                Font fontText = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLDITALIC);
                BaseColor textColor = new BaseColor(0, 0, 128); //
                fontText.setColor(textColor);
        // Ajout du contenu au document
       Paragraph Text1=new Paragraph("Ticket numéro : " + t.getId(),fontText);
       Text1.setAlignment(Element.ALIGN_CENTER);
       document.add(Text1);
           Font fontText2 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLDITALIC);
                BaseColor text2Color = new BaseColor(0, 0, 0); //
                fontText2.setColor(text2Color);
     Paragraph Text2=new Paragraph("Nom De Competition : " + t.getCompetition().getNomCompetition(),fontText2);
       Text2.setAlignment(Element.ALIGN_CENTER);
       document.add(Text2);
       
       Paragraph Text3=new Paragraph("Description du Ticket : " + t.getDescription(),fontText2);
       Text3.setAlignment(Element.ALIGN_CENTER);
       document.add(Text3);
       
          Paragraph Text4=new Paragraph("De utilisateur: " +UserService.username,fontText2);
       Text4.setAlignment(Element.ALIGN_CENTER);
       document.add(Text4);
                document.close();

                System.out.println("Le fichier PDF a été généré avec succès.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

   }

