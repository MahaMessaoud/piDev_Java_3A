  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class ProfilCoachController implements Initializable {

    @FXML
    private Label usernameC;
    @FXML
    private Label emailC;
    @FXML
    private Label date_nC;
    @FXML
    private Label num_telC;
    UserService userS = new UserService();
    User user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
