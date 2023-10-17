/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import Entities.Reset;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author maham
 */
public class VerifCodeController implements Initializable {
public String email ;
    @FXML
    private TextField code;
    @FXML
    private Text mqte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Verif(ActionEvent event) throws IOException {
         UserService uss = new UserService();
        if (code.getText().equals("")) {
            mqte.setText("Champ Manquant");
        } else if (uss.reset(new Reset(Integer.parseInt(code.getText())))) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMdp.fxml"));
            Parent root = loader.load();
            code.getScene().setRoot(root);

            ModifierMdpController apc = loader.getController();
            apc.setTxt1(email);

        } else {
            mqte.setText("Un erreur survenu");

        }
        {
        }
    }
}
