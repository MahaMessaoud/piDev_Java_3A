/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Sponsor;
import Entities.User;
import Services.SponsorService;
import Services.UserService;
import com.mysql.jdbc.MySQLConnection;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author maham
 */
public class ListUsersController implements Initializable {

    private User users;

    @FXML
    private TableView<User> TableUsers;

    @FXML
    private TableColumn<User, String> emailuser;
    @FXML
    private TableColumn<User, String> usernameU;

    @FXML
    private TableColumn<User, Date> date_nuser;

    @FXML
    private TableColumn<User, String> imageU;
    private Image imgLabel;
    ObservableList<User> obslistus = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, Void> act;
    @FXML
    private TextField search;
    ObservableList<User> dataList = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> TriChoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Load();

    }

    private void TrieDate() {
        UserService utiSer = new UserService();
        User user = new User();
        List<User> a = utiSer.triDate();
        emailuser.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernameU.setCellValueFactory(new PropertyValueFactory<>("username"));
        date_nuser.setCellValueFactory(new PropertyValueFactory<>("date_n"));

        TableUsers.getItems().setAll(a);

    }

    public void Load() {
        UserService UserS = new UserService();
        User u = new User();
        UserS.afficherListe().stream().forEach((p) -> {
            obslistus.add(p);
        });
        emailuser.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernameU.setCellValueFactory(new PropertyValueFactory<>("username"));
        date_nuser.setCellValueFactory(new PropertyValueFactory<>("date_n"));
        imageU.setCellValueFactory(new PropertyValueFactory<>("image"));

       // act.setPrefWidth(198.39993286132812);
        act.setCellFactory(column -> {
            return new TableCell<User, Void>() {
                private final Button deleteButton = new Button("Delete");
                private final Button getByIdButton = new Button("Get by ID");
                private final Button bloque = new Button("Bloquer");
                private final Button debloque = new Button("Débloquer");

                {
                    deleteButton.setOnAction(event -> {
                        User user = getTableView().getItems().get(getIndex());
                        obslistus.remove(user);
                        UserS.Supprimer(user);

                    });
                    Image image = new Image(getClass().getResourceAsStream("/Images/icons8-delete-48.png"));
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(24);
                    imageView.setFitWidth(24);
                    deleteButton.setGraphic(imageView);
                    deleteButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white;");

                    getByIdButton.setOnAction(event -> {
                        try {
                            User user = getTableView().getItems().get(getIndex());
                            // Open new FXML window to display user info by ID
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("userCard.fxml"));
                            Parent root = loader.load();
                            UserCardController controller = loader.getController();
                            controller.initData(user.getId());
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ListUsersController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    Image image1 = new Image(getClass().getResourceAsStream("/Images/icons8-eye-64.png"));
                    ImageView imageView1 = new ImageView(image1);
                    imageView1.setFitHeight(24);
                    imageView1.setFitWidth(24);
                    getByIdButton.setGraphic(imageView1);
                    getByIdButton.setStyle("-fx-background-color:transparent; -fx-text-fill: white;");

                    bloque.setOnAction(event -> {
                        User user = getTableView().getItems().get(getIndex()); // get the user from the current row
                        UserService us;
                        us = new UserService();
                        try {
                            us.ban(user); // block the user
                            System.out.println("User " + user.getId() + " has been blocked.");
                        } catch (SQLException e) {
                            System.out.println("Error blocking user: " + e.getMessage());
                        }
                    });
                    Image image2 = new Image(getClass().getResourceAsStream("/Images/icons8-ban-64.png"));
                    ImageView imageView2 = new ImageView(image2);
                    imageView2.setFitHeight(24);
                    imageView2.setFitWidth(24);
                    bloque.setGraphic(imageView2);
                    bloque.setStyle("-fx-background-color:transparent; -fx-text-fill: white;");

                    debloque.setOnAction(event -> {
                        User user = getTableView().getItems().get(getIndex()); // get the user from the current row
                        UserService us = new UserService();
                        try {
                            us.unban(user); // unblock the user
                            System.out.println("User " + user.getId() + " has been unblocked.");
                        } catch (SQLException e) {
                            System.out.println("Error unblocking user: " + e.getMessage());
                        }
                    });
                     Image image3 = new Image(getClass().getResourceAsStream("/Images/icons8-approved-unlock-64.png"));
                    ImageView imageView3 = new ImageView(image3);
                    imageView3.setFitHeight(24);
                    imageView3.setFitWidth(24);
                    debloque.setGraphic(imageView3);
                    debloque.setStyle("-fx-background-color:transparent; -fx-text-fill: white;");

                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox buttonBox = new HBox();
                      //  buttonBox.setPadding(new Insets(4));
                        buttonBox.setSpacing(2);
                        buttonBox.getChildren().addAll(deleteButton, getByIdButton, bloque, debloque);
                        setGraphic(buttonBox);
                    }
                }
            };
        });

        TableUsers.setItems(obslistus);
        search.textProperty().addListener((obs, oldText, newText) -> {
            List<User> ae = UserS.Search(newText);
            TableUsers.getItems().setAll(ae);
        });
        TriChoice.getItems().addAll("Aucun", "Trier Selon Date", "Trier Selon Email");
    }

    private void TrieEmail() {
        UserService utiSer = new UserService();
        User user = new User();
        List<User> a = utiSer.triEmail();
        emailuser.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernameU.setCellValueFactory(new PropertyValueFactory<>("username"));
        date_nuser.setCellValueFactory(new PropertyValueFactory<>("date_n"));

        TableUsers.getItems().setAll(a);

    }

    @FXML
    private void TriChoice(ActionEvent event) {
        if (TriChoice.getValue().equals("Trier Selon Date")) {
            TrieDate();
        } else if (TriChoice.getValue().equals("Trier Selon Email")) {
            TrieEmail();
        }

    }

}
