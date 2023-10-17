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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class AskChatController implements Initializable {
    private WebEngine webengine;

    @FXML
    private WebView chatGpt;
    @FXML
    private Button returnFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        webengine = chatGpt.getEngine();
                url = this.getClass().getResource("webPackages/Chatgpt.html");
        webengine.load(url.toString());

    }    
    @FXML
    private void BackRegistration(ActionEvent event) throws IOException {
         Parent page2 = FXMLLoader.load(getClass().getResource("FrontOff.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
}
