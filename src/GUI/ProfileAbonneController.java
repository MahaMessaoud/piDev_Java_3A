/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class ProfileAbonneController implements Initializable {

    private int id = 0;
    @FXML
    private TextField usernameC;
    @FXML
    private TextField emailC;
    @FXML
    private DatePicker date_nC;
    @FXML
    private TextField num_telC;
    @FXML
    private Label usernameC1;
    @FXML
    private Label emailC1;
    @FXML
    private Button save;
    ObservableList<User> obslistus = FXCollections.observableArrayList();
    private Button btnClose;
    @FXML
    private ImageView profImg;
    private Image image1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.LoadId();
    }

    private void LoadId() {
        this.id = UserService.idUser;
        System.err.println("ena aaaaaa" + this.id);
        UserService us = new UserService();
        try {
            User u = us.findById(id);
            usernameC.setText(u.getUsername());
            usernameC1.setText(u.getUsername());
            System.out.println(UserService.imageUser);
            image1 = new Image("/Images/uploads/" + UserService.imageUser);
            System.out.println("image prog : " + image1);
            profImg.setImage(image1);
            emailC.setText(u.getEmail());
            emailC1.setText(u.getEmail());
            num_telC.setText(Integer.toString(u.getNumtel()));
            Date date = u.getDate_n(); // assuming this returns a java.util.Date object
            Instant instant = Instant.ofEpochMilli(date.getTime());
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate localDate = zdt.toLocalDate();
            date_nC.setValue(localDate);
            //imgAb.set
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Edituser(ActionEvent event) throws SQLException {
        UserService userSer = new UserService();
        User u = new User();
        // u.setId();
        u = userSer.findById(UserService.idUser);
        u.setId(UserService.idUser);
        u.setUsername(usernameC.getText());
        u.setEmail(emailC.getText());
        u.setNumtel(Integer.parseInt(num_telC.getText()));
        LocalDate localDate = date_nC.getValue();
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        u.setDate_n(date);
        System.out.println("Data user :" + u);
        userSer.modifier(u);
        new Alert(Alert.AlertType.INFORMATION, u.getUsername() + " modifié !!", ButtonType.CLOSE).show();
        this.LoadId();
    }

    private void backPrec(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("FrontOff.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    private void logout(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }

    private void closW(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        //System.out.println("hi");
        stage.close();
    }

}
