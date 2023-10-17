/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class UserCardController implements Initializable {

    private int idCu;

    @FXML
    private Label usernameU;
    @FXML
    private Label emailU;
    @FXML
    private Label num_telU;
    @FXML
    private Label date_nU;
    @FXML
    private ImageView img;
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LoadData();
    }

    public void LoadData() {

        UserService us = new UserService();
        try {
            User u = us.findById(this.idCu);
            if (u != null) {
                if (u.getUsername() != null) {
                    usernameU.setText(u.getUsername());
                }
                if (u.getEmail() != null) {
                    emailU.setText(u.getEmail());
                }
                if (u.getNumtel() != null) {
                    num_telU.setText(Integer.toString(u.getNumtel()));
                }
                if (u.getDate_n() != null) {
                    date_nU.setText(u.getDate_n().toString());
                }
                if (u.getImage() != null) {
//                    im = new Image("/Images/uploads/" + UserService.imageUser);
//                    System.out.println("image prog : " + im);
//                    img.setImage(im);
                    //imgLabel.getScene().getWindow();
                    Image images = new Image("/Images/uploads/" + u.getImage());
                    img.setFitWidth(107);
                    img.setFitHeight(108);
                    img.setImage(images);

                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int initData(int id) {
        this.idCu = id;
        this.LoadData();
        System.err.println("ena aaaaaa" + this.idCu);
        return this.idCu;

    }

    @FXML
    private void closW(ActionEvent event) {
          Stage stage = (Stage) btnClose.getScene().getWindow();
        //System.out.println("hi");
        stage.close();
    }

}
