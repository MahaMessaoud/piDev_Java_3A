
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a52;

import Entities.Menu;
import Entities.Plat;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.MenuServices;
import services.PlatServices;
import Utils.MyDB;

/**
 *
 * @author lenovo
 */
public class Pidev3a52 extends Application{
public static int i = 0;
public static int j = 0;
public static int k = 0;
public static int id;
  
     @Override
    public void start(Stage stage) throws Exception {
 //Chargement de l'interface graphique depuis un fichier FXML
 
        // Parent root = FXMLLoader.load(getClass().getResource("/gui/FXML_FrontRestaurant.fxml"));
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Dashboard.fxml"));
      
//Fin_Chargement de l'interface graphique depuis un fichier FXML 

//Jouer un son
       /* Media sound = new Media(new File("welcome.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();*/
//Fin_jouer son 

// Afficher un message de bienvenue
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bienvenue");
        alert.setHeaderText("EnergyBox Restaurant");
        //  alert.setContentText("Nous sommes ravis de vous accueillir !");
        alert.setResizable(true); // permet le redimensionnement de la fenêtre
        alert.getDialogPane().setPrefSize(1200, 800); // définir la taille préférée de la fenêtre
//Fin afficher msg 

        // Ajouter un style CSS personnalisé pour la fenêtre d'alerte
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/gui/alert.css").toExternalForm());
        alert.getDialogPane().getStyleClass().add("custom-alert");
// Créez un objet TextFlow avec le texte souhaité
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().addAll(
                new Text("Bienvenue "),
                new Text("chez "),
                new Text("EnergyBox !")
        );
// Changez la couleur de l'écriture en rouge
        for (Node child : textFlow.getChildren()) {
            ((Text) child).setStyle("-fx-fill: #720000;");
        }
        textFlow.setStyle("-fx-font-weight: bold;-fx-font-size: 50px;");// définit la taille de police à 30px
// Ajoutez une animation pour animer le texte
        TranslateTransition tt = new TranslateTransition(Duration.seconds(2), textFlow);
        tt.setByX(300);
        tt.setCycleCount(Animation.INDEFINITE);
        tt.setAutoReverse(true);
        tt.play();
// Ajoutez le TextFlow à la fenêtre d'alert
        alert.getDialogPane().setContent(textFlow);
        alert.showAndWait();// Afficher la fenêtre d'alerte
        Scene scene = new Scene(root);// Création d'une nouvelle scène avec l'interface graphique chargée depuis le fichier FXML
        stage.setScene(scene);// Afficher la scène dans la fenêtre
        stage.show();// Afficher la scène dans la fenêtre
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
/*
Le code main est l'entrée principale de l'application,
qui est exécutée lorsque le programme est lancé. 
Elle appelle la méthode "launch" pour lancer l'application. 
C'est une méthode standard dans toutes les applications JavaFX, 
et elle permet d'initialiser et de lancer l'interface utilisateur de l'application.
*/  
       }
   
   
}     
        
        
  /*      
 //        MyDB db = new MyDB();
//        MyDB db1 = new MyDB();
     
        MyDB db2 = MyDB.getInstance();
        MyDB db3 = MyDB.getInstance();
        
        Menu p = new Menu("test","java");
        Menu p1 = new Menu("testtt","java");
        MenuServices ps = new MenuServices();
        try {
       //    ps.ajouter(p);
       //    ps.ajouterr(p1);
            System.out.println(ps.afficherListe());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        */

    
        /*   ShwowVIDeo
       String filePath = "file:///C:/Users/lenovo/Desktop/PIDEV_Desc/EnergyBoxRestaurant.mp4";
        Media media = new Media(filePath);

         // Le lecteur de media
         MediaPlayer mediaPlayer = new MediaPlayer(media);

         // L'élément qui affiche le lecteur
         MediaView mediaView = new MediaView();
         // Association du lecteur à cette vue
         mediaView.setMediaPlayer(mediaPlayer);
         // Joue la vidéo
         mediaPlayer.play();

         // L'élément racine centre la vidéo
         VBox roott = new VBox(mediaView);
         roott.setAlignment(Pos.CENTER);

         Scene sceene = new Scene(roott, 1000, 1000);
        
       stage.setScene(sceene);
        stage.show();
         */









