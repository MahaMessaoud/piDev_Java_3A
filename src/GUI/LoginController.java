/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.EnumMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import static javafx.application.Platform.exit;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button Login;

    public Connection conx;
    @FXML
    private ImageView imageView;
    @FXML
    private Button reset;
    @FXML
    private Button Register;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conx = MyDB.getInstance().getConx();

    }

   @FXML
void Login(ActionEvent event) throws IOException {
    UserService Uservice = new UserService();
    if (email.getText().equals("") || password.getText().equals("")) {
        new Alert(Alert.AlertType.ERROR, "Tous les champs sont oligatoire", ButtonType.CLOSE).show();
    } else {
        if (!email.getText().contains("@")) {
            new Alert(Alert.AlertType.ERROR, "Respecter format email!!", ButtonType.CLOSE).show();
        } else {
            String loginResult = Uservice.login(new User(email.getText(), password.getText()));
            if (loginResult.equals("[\"ROLE_ADMIN\"]")) {
                Stage stage = (Stage) Login.getScene().getWindow();
                stage.close();
                Parent page2 = FXMLLoader.load(getClass().getResource("SideNavigBack.fxml"));
                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();
            } else if (loginResult.equals("[\"ROLE_ABONNE\"]")) {
                Stage stage = (Stage) Login.getScene().getWindow();
                stage.close();
                Parent page2 = FXMLLoader.load(getClass().getResource("FrontOff.fxml"));
                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();
            } else if (loginResult.equals("[\"ROLE_COACH\"]")) {
                Stage stage = (Stage) Login.getScene().getWindow();
                stage.close();
                Parent page2 = FXMLLoader.load(getClass().getResource("ProfilCoach.fxml"));
                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();
            } else {
                new Alert(Alert.AlertType.ERROR, loginResult, ButtonType.CLOSE).show();
            }
        }
    }
}


    private void scanQRCode(ActionEvent event) {
        conx = MyDB.getInstance().getConx();
        UserService Uservice = new UserService();

        // Create a file chooser to allow the user to select an image file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select QR code image");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

        // Show the file chooser and get the selected file
        File selectedFile = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (selectedFile == null) {
            // User cancelled the file chooser
            return;
        }

        // Load the selected image file
        Image image = new Image(selectedFile.toURI().toString());
        imageView.setImage(image);

        // Decode the QR code from the selected image
        Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        MultiFormatReader reader = new MultiFormatReader();
        Result result = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
            result = reader.decode(binaryBitmap, hints);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if the QR code corresponds to a user in the database
        if (result != null) {
            String code = result.getText();
            try {
                PreparedStatement ps = conx.prepareStatement("SELECT * FROM user WHERE qr_code = ?");
                ps.setString(1, code);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println(rs.getString("email"));
                }
                if (Uservice.loginQr(new User(rs.getString("email"), rs.getString("password"))).equals("[\"ROLE_ABONNEE\"]")) {
                    Stage stage = (Stage) Login.getScene().getWindow();
                    stage.close();
                    Parent page2 = FXMLLoader.load(getClass().getResource("FrontOff.fxml"));
                    Scene scene2 = new Scene(page2);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene2);
                    app_stage.show();
                } else if (Uservice.loginQr(new User(rs.getString("email"), rs.getString("password"))).equals("[\"ROLE_ADMIN\"]")) {
                    Stage stage = (Stage) Login.getScene().getWindow();
                    stage.close();
                    Parent page2 = FXMLLoader.load(getClass().getResource("SideNavigBack.fxml"));
                    Scene scene2 = new Scene(page2);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene2);
                    app_stage.show();

                } else if (Uservice.loginQr(new User(rs.getString("email"), rs.getString("password"))).equals("[\"ROLE_COACH\"]")) {
                    Stage stage = (Stage) Login.getScene().getWindow();
                    stage.close();
                    Parent page2 = FXMLLoader.load(getClass().getResource("FrontOff.fxml"));
                    Scene scene2 = new Scene(page2);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.setScene(scene2);
                    app_stage.show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid QR code", ButtonType.CLOSE).show();
                }
            } catch (IOException | SQLException e) {
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Unable to scan QR code", ButtonType.CLOSE).show();
        }
    }

    @FXML
    private void logQr(ActionEvent event) {
        this.scanQRCode(event);
    }

    @FXML
    private void reset(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("ResetPwd.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();

    }

    @FXML
    private void GoRegister(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("RegisterChoice.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();

    }
}
