/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class MyGymController implements Initializable {
  private Browser browser;
    private BrowserView browserView;

    @FXML
    private StackPane webview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Engine engine = Engine.newInstance(
            EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED).licenseKey("1BNDHFSC1G66FQYUB3DDGLBOECXQ0CPH8WS03T2W6CZFSJM92SDB30OLSB30TJFKM5QZKG")
                    .build()
        );
        browser = engine.newBrowser();
        browserView = BrowserView.newInstance(browser);
        webview.getChildren().add(browserView);
        browser.navigation().loadUrl("https://sketchfab.com/models/7d0351a5fa664ac59038ed57eeb45cd9/embed");
    }    
    
    public void dispose() {
        browser.close();

    }

    @FXML
    private void BackRegistration(ActionEvent event) throws IOException {
         Parent page2 = FXMLLoader.load(getClass().getResource("RegistrationAbonnee.fxml"));

        Scene scene2 = new Scene(page2);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene2);
        app_stage.show();
    }
}
