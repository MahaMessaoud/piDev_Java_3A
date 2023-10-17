/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reset;
import Services.imResetPassword;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mail.Sendmail;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class ResetPwdController implements Initializable {
@FXML
    private TextField inputmail;
    Random rnd = new Random();
    int number = rnd.nextInt(999999);
    long start = System.currentTimeMillis();
    String sTime = Long.toString(start);
    String Object="Réinitialiser Votre mot de passe";
    String Subject="Votre Code est :  "+number+"\n S'il te plait ne passe pas 10 min De maintenant";
    @FXML
    private Text erreurT;
    
    
    public static String sEmail;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SendREs(ActionEvent event)  throws   IOException{
        imResetPassword imr = new imResetPassword();
        Sendmail sn = new Sendmail();
        if (inputmail.getText().equals("")) {
            erreurT.setText(" Champs manquants!!!");
            
        }else if (imr.ajout(new Reset(inputmail.getText(),number,sTime))) {
            sEmail=inputmail.getText();
            sn.envoyer(inputmail.getText(), Object, Subject);
            Parent page2 = FXMLLoader.load(getClass().getResource("VerifCode.fxml"));

                Scene scene2 = new Scene(page2);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene2);
                app_stage.show();

        }else{
                 erreurT.setText("Compte N'existe Pas !!");
             }
       
       
    }
   
    
}
