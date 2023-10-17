/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Post;
import Entities.User;
import Services.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ahmed Ben Abid
 */
public class ShowPostsController implements Initializable {

    public static int i = 0;
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane Empty;
    @FXML
    private BorderPane borderPost;
    @FXML
    private Label rateP;
    @FXML
    private Label postNbr;

    @FXML
    private Label commentP;

    @FXML
    private Button ajoutPP;
    private Button exit;

    @FXML
    private Label descP;

    @FXML
    private Label dateP;

    @FXML
    private Button titleP;

    @FXML
    private ImageView imageP;

    @FXML
    private Button newtP;

    @FXML
    private Button PreviousP;
    private static List<Post> posts;
    @FXML
    private AnchorPane ShowPostPane;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            ShowPostsController.posts = new PostService().getAll();
            Collections.reverse(ShowPostsController.posts);
        } catch (SQLException ex) {
            Logger.getLogger(ShowPostsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        showPost();

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    PostService ps = new PostService();
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {

        i++;
        if (ShowPostsController.posts.size() == i) {
            i = 0;

            showPost();

        } else {
            showPost();

        }

    }));

    @FXML
    private void Go_New_Post(ActionEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("AjoutPost.fxml"));
        ShowPostPane.getChildren().removeAll();
        ShowPostPane.getChildren().setAll(fxml);
    }

    @FXML
    private Label userNom;

    public void showPost() {

        try {

            if (ShowPostsController.posts.size() == 0) {
                Empty.getChildren().clear();
                Pane pane = new Pane();
                Label label2 = new Label("Sorry, no Post for today.");
                label2.setAlignment(Pos.CENTER);
                label2.setLayoutY(69.0);
                label2.setPrefHeight(93.0);
                label2.setPrefWidth(458.0);
                label2.setStyle("-fx-border-color: #ffffff; -fx-border-width: 1 0 0 0;");
                label2.setFont(Font.font("Calibri Italic", 30));
                label2.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));
                pane.getChildren().addAll(label2, ajoutPP);
                Empty.getChildren().add(pane);
            } else {

                Post post = ShowPostsController.posts.get(i);
                int NbrComments = ps.showComments(ShowPostsController.posts.get(i).getId()).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                postNbr.setText(i + 1 + "#");
                titleP.setText(post.getTitle());
                dateP.setText(post.getDate_post());
                descP.setText(post.getDetails());
                UserService a = new UserService();
                User c = a.findById(ShowPostsController.posts.get(i).getUserId());
                 Image img = new Image("/Images/uploads/" + UserService.imageUser);
                userImg.setImage(img);
                userNom.setText(c.getUsername());
                Image image = new Image(post.getImage());

                imageP.setImage(image);
                if (post.getNbrRate() == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) post.getNbrRate()) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(post.getNbrRate())) + "/5");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML 
    private ImageView userImg;

    @FXML
    public void nextPost() {
        i++;

        try {

            if (ShowPostsController.posts.size() == i) {
                i = 0;

                showPost();
            } else {
                Post post = ShowPostsController.posts.get(i);
                int NbrComments = ps.showComments(ShowPostsController.posts.get(i).getId()).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                 UserService a = new UserService();
                User c = a.findById(ShowPostsController.posts.get(i).getUserId());
                 Image img = new Image("/Images/uploads/" + UserService.imageUser);
                userImg.setImage(img);
                userNom.setText(c.getUsername());
                postNbr.setText(i + 1 + "#");
                titleP.setText(post.getTitle());
                dateP.setText(post.getDate_post());
                Image image = new Image(post.getImage());

                imageP.setImage(image);
                descP.setText(post.getDetails());
                if (post.getNbrRate() == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) post.getNbrRate()) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(post.getNbrRate())) + "/5");
                }
                System.out.println(post.getDate_post());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void go_details_Posts(ActionEvent event) throws IOException, SQLException {
        timeline.stop();
        List<Post> posts;
        posts = new PostService().getAll();
        Collections.reverse(posts);
        Post fournisseur = posts.get(i);
        System.out.println(fournisseur);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BlogTest.fxml"));
       BlogTestController controller = loader.getController();
           // controller.setFournisseur(fournisseur);
           controller.setPostt(fournisseur);
           System.out.println(controller.getPostt());
        Parent fxml = FXMLLoader.load(getClass().getResource("BlogTest.fxml"));
        ShowPostPane.getChildren().removeAll();
        ShowPostPane.getChildren().setAll(fxml);

    }

    @FXML
    public void PreviousPost() {
        i--;

        try {

            if (i < 0) {

                i = ShowPostsController.posts.size() - 1;

                showPost();

            } else {
                Post post = ShowPostsController.posts.get(i);
                int NbrComments = ps.showComments(ShowPostsController.posts.get(i).getId()).size();
                if (NbrComments == 1) {
                    commentP.setText(String.valueOf(NbrComments) + " Comment");
                } else {
                    commentP.setText(String.valueOf(NbrComments) + " Comments");
                }
                 UserService a = new UserService();
                User c = a.findById(ShowPostsController.posts.get(i).getUserId());
                 Image img = new Image("/Images/uploads/" + UserService.imageUser);
                userImg.setImage(img);
                userNom.setText(c.getUsername());
                postNbr.setText(i + 1 + "#");
                titleP.setText(post.getTitle());
                dateP.setText(post.getDate_post());
                Image image = new Image(post.getImage());

                imageP.setImage(image);
                descP.setText(post.getDetails());
                if (post.getNbrRate() == 0) {
                    rateP.setText("Rate: " + String.valueOf((int) post.getNbrRate()) + "/5");
                } else {
                    DecimalFormat df = new DecimalFormat("#.0");
                    rateP.setText("Rate: " + String.valueOf(df.format(post.getNbrRate())) + "/5");
                }
                System.out.println(post.getDate_post());
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogTestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleQuitter(ActionEvent event) {

        // Obtenez la fenêtre principale
        Stage stage = (Stage) exit.getScene().getWindow();
        // Fermez la fenêtre
        stage.close();
    }

//    @FXML
//    private void AjoutPost(javafx.event.ActionEvent event) throws IOException {
//        timeline.stop();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjoutPost.fxml"));
//        Parent root = loader.load();
//        ajoutPP.getScene().setRoot(root);
//
//    }
//    @FXML
//    private void detailP() throws SQLException {
//        try {
//            timeline.stop();
//            List<Post> posts;
//            posts = new PostService().getAll();
//            Collections.reverse(posts);
//            Post fournisseur = posts.get(i);
//            BlogTestController b = new BlogTestController();
//            b.setIdP(fournisseur.getId());
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/BlogTest.fxml"));
//            Parent root = loader.load();
//            BlogTestController controller = loader.getController();
//            controller.setFournisseur(fournisseur);
//            controller.setPostt(fournisseur);
//
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) titleP.getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
