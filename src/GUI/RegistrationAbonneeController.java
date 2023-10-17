/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Upload;
import Entities.User;
import static GUI.RegistrationAdminController.generateRandomString;
import static GUI.RegistrationCoachController.generateRandomString;
import Services.UserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
import mail.Sendmail;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class RegistrationAbonneeController implements Initializable {

    private File file;
    String pic;
    @FXML
    private TextField usernameAb;
    @FXML
    private TextField emailAb;
    @FXML
    private TextField num_telAb;
    @FXML
    private TextField pwdAb;
    @FXML
    private Button RegisterAb;
    @FXML
    private DatePicker date_nAb;
    @FXML
    private Canvas captchaCanvas;
    @FXML
    private TextField captchaField;
    private String captchaText;
    @FXML
    private ImageView imgSpsInput;
    @FXML
    private Button btnImportAct;
    private File selectedImageFile;
    @FXML
    private Button btnClose;
    @FXML
    private Button registerAbonnee1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        captchaText = generateCaptchaText(5);
        drawCaptcha(captchaText);
    }

    @FXML
    private void sisncrire(ActionEvent event) throws IOException, WriterException {
        UserService userS = new UserService();
        String Role = "[\"ROLE_ABONNE\"]";
        String userInput = captchaField.getText();
        String email = emailAb.getText();
        String object = "Hello " + email + "\n thank \'s for ur registration , you can find your PRIVATE QrCode to make u authentificate with it , PLEASE KEEP IT PRIVATLY";
        String Sub = "welcome to EnergyBox";

        if (userInput == null || userInput.isEmpty()) {
            // Afficher un message d'erreur si le champ captcha est vide
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le champ captcha est vide.");
            alert.showAndWait();
            return;
        }

        if (userInput.equals(captchaText)) {
            // Vérifier si l'email existe déjà dans la base de données
            if (userS.emailExists(email)) {
                // Afficher un message d'erreur si l'email existe déjà
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cet email existe déjà dans la base de données.");
                alert.showAndWait();
                return;
            }

            // Enregistrer l'utilisateur s'il a entré le captcha correct et l'email n'existe pas encore
            try {
                int numTel = Integer.parseInt(num_telAb.getText());
                Date dateN = Date.valueOf(date_nAb.getValue());
                String dataQr = generateRandomString(4);
                String myQr = generateQRCodeAndSave(dataQr, email);

                userS.registre(new User(usernameAb.getText(), email, numTel, dateN, pic, pwdAb.getText(), Role, dataQr, myQr));
                Sendmail sn = new Sendmail();
                sn.envoyerQr(email, Sub, object, myQr);
                JOptionPane.showMessageDialog(null, "Abonnee ajoutée !");
                Parent page2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();
            } catch (NumberFormatException e) {
                // Afficher un message d'erreur si le champ num_telA n'est pas un entier
                Alert alert = new Alert(Alert.AlertType.ERROR, "Le champ numero telephone doit être un entier.");
                alert.showAndWait();
            }
        } else {
            // Afficher un message d'erreur si le captcha est incorrect
            Alert alert = new Alert(Alert.AlertType.ERROR, "Captcha incorrect.");
            alert.showAndWait();
        }
    }

    private String generateCaptchaText(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    private void drawCaptcha(String captchaText) {
        GraphicsContext gc = captchaCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.setFill(Color.BLACK);
        gc.setFont(gc.getFont().font(20));
        gc.fillText(captchaText, 30, 35);
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public String generateQRCodeAndSave(String text, String fileName) throws WriterException {
        // Generate the QR code
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 250, 250);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Convert the BufferedImage to a JavaFX Image
        Image fxImage = SwingFXUtils.toFXImage(bufferedImage, null);

        // Save the image to the specified directory
        String directoryPath = "C:/Users/maham/Documents/NetBeansProjects/PIDesktop/src/Images/loginqr";
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String filePath = directoryPath + "/" + fileName + ".png";
        File file = new File(filePath);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(fxImage, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
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

    @FXML
    private void closW(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        //System.out.println("hi");
        stage.close();
    }

    @FXML
    private void VR(ActionEvent event) throws IOException {
         Parent page2 = FXMLLoader.load(getClass().getResource("MyGym.fxml"));
        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    @FXML
    private void goRegisterClient(ActionEvent event) throws IOException {
         Parent page2 = FXMLLoader.load(getClass().getResource("Login.fxml"));
         Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();
    }

}
