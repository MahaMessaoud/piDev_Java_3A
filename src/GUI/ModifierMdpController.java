/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import mail.Sendmail;
import Entities.User;
import static GUI.ResetPwdController.sEmail;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author maham
 */
public class ModifierMdpController implements Initializable {
  @FXML
    private PasswordField Nvmdp;
    @FXML
    private PasswordField cnfMdp;
    @FXML
    private Text txt1;
@FXML
    private Text txt;

    public Text getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1.setText(txt1);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        txt1.setText(sEmail.toString());
        System.out.println("1:"+txt.getText()+"2:"+sEmail);
    }    
     @FXML
    private void SubmitMdp(ActionEvent event) throws IOException, SQLException {
         Sendmail sn = new Sendmail();
        String Obj="Reset Password";
        String Subject = "Bonjour "+txt1.getText()+" Votre mot passe est modifié avec succes";
         UserService uss = new UserService();
        if((Nvmdp.getText().equals(""))&&(cnfMdp.getText().equals(""))){
                        txt.setText("Champ Manquant");

             }else if (!Nvmdp.getText().equals(cnfMdp.getText())) {
            txt.setText("Password Non Compatible");
        } else {
             uss.modifierMdp(new User(sEmail,cnfMdp.getText()));
                         txt.setText("Password Modifer");

                          sn.envoyer(sEmail, Obj, Subject);
                          
                Parent page2 = FXMLLoader.load(getClass().getResource("Login.fxml"));

                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();

        }
    }
}
