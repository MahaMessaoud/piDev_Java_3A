/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class RegisterChoiceController implements Initializable {

    @FXML
    private Button registerAbonnee;
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
    }    
     @FXML
    private void goRegisterClient(ActionEvent event) throws IOException {
         Parent page2 = FXMLLoader.load(getClass().getResource("RegistrationAbonnee.fxml"));

                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();
         // view = FXMLLoader.load(getClass().getResource("RegistrationAbonnee.fxml"));

       
    }
    @FXML
    private void goLogin(ActionEvent event) throws IOException {
         Parent page2 = FXMLLoader.load(getClass().getResource("Login.fxml"));

                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();
         // view = FXMLLoader.load(getClass().getResource("RegistrationAbonnee.fxml"));

       
    }
    private void backPrec(ActionEvent event) throws IOException {
        Parent page2 = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
    @FXML
    private void closW(ActionEvent event) {
           Stage stage = (Stage) btnClose.getScene().getWindow();
        //System.out.println("hi");
        stage.close();
    }
}
