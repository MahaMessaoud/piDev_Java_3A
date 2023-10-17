/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author maham
 */
public class PIDesktopGui extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ajoutSponsor.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/listSponsor.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/IndexClientAbonnement.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/rss.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FrontOff.fxml"));
    //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/GestionRessource.fxml")); 
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ShowPosts.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/gestionAbonnement.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ResetPwd.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Login.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/profilAdmin.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/profileAbonne.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ListUsers.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Captcha.fxml"));
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ShowPosts.fxml"));

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/modifierSponsor.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/userCard.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Weath.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SideNavigBack.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/rss.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AskChat.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SideNavigBack.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SideNavigBack.fxml"));
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/FrontOff.fxml"));
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/GUI/RegistrationAbonnee.fxml"));
       // FXMLLoader loader= new FXMLLoader(getClass().getResource("/GUI/RegistrationAdmin.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/RegistrationCoach.fxml"));
         //FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/RegisterChoice.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        /*stage.setHeight(950);
        stage.setWidth(1300);*/
        stage.show();
        stage.setResizable(false);
        //stage.closew();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
}
