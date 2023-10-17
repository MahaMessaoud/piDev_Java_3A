/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Entities.Menu;
import Entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.MenuServices;
import services.PlatServices;
import Utils.MyDB;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class FXML_PlatController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPlats;
    @FXML
    private Button btnReservations;
    @FXML
    private Pane panePlats;
    @FXML
    private VBox Items;
    @FXML
    private Pane paneMenus;
    @FXML
    private Pane paneReservations;
    @FXML
    private Pane paneHome;
    @FXML
    private Button retour;
     @FXML
    private Button Ajout;
 
     @FXML
    private  TableColumn nom;
     @FXML
    private  TableColumn prix;
      @FXML
    private  TableColumn description;
      @FXML
    private  TableColumn calories;
     @FXML
    private  TableColumn etat;
     @FXML
    private  TableColumn nbp;
       @FXML
    private  TableColumn image; 
      @FXML
    private  TableColumn categories;
     @FXML
    private  TableView tvPlat;
     @FXML
    private Button test;
     @FXML
    private Button Refresh;
      @FXML
    private Button Afficher;
    @FXML
    private Button TriPrix;
    @FXML
    private Button TriNom;
    
    public Connection conx;
    public Statement stm;
     @FXML
    private TextField fxrecherche ;
     
     int index = -1;
    @FXML
    private  ImageView ShowImg;
    @FXML
    private TableColumn<Plat, String> imageplat;
    
    @FXML
    private Button captureEcran;
    @FXML
    private AnchorPane monAnchorPane;
    @FXML
    private Button exportButton;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            // Initialize your database connection here
            conx = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database: " + ex.getMessage());
        }
 /*       btnHome.setOnAction((ActionEvent event) -> {
            GoToRestau();
        });
        btnMenus.setOnAction((ActionEvent event) -> {
            GoToMenu();
        });
        btnReservations.setOnAction((ActionEvent event) -> {
            GoToReservation();
        });*/
        Afficher.setOnAction((ActionEvent event) -> {
            showActions();
        });

        Refresh.setOnAction((ActionEvent event) -> {
            ShowListe();
        });
        Ajout.setOnAction((ActionEvent event) -> {
            GoToAjout();
        });
        TriPrix.setOnAction((ActionEvent event) -> {
            triParPrix();
        });
        TriNom.setOnAction((ActionEvent event) -> {
            triParNom();
        });
        captureEcran.setOnAction(event -> {
            CaptureEcran cap = new CaptureEcran();
            try {
                cap.capturer(monAnchorPane);
                System.out.println("La capture d'écran a été générée avec succès !");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Capture d'écran");
                alert.setHeaderText("Capture d'écran générée avec succès !");
                alert.showAndWait();
            } catch (Exception ex) {
                System.out.println("Erreur lors de la capture d'écran : " + ex.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur lors de la capture d'écran !");
                alert.setContentText("Une erreur est survenue lors de la capture d'écran : " + ex.getMessage());
                alert.showAndWait();
            }
        });
        exportButton.setOnAction(event -> {
            try {
               export(getPlatList(), "C:\\Users\\maham\\Documents\\NetBeansProjects\\PIDesktop\\src\\Excel\\plats.xlsx");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }  
    
  /*  private void GoToRestau(){
            Parent root;
            try {
            
            root = FXMLLoader.load(getClass().getResource("FXML_Restaurant.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnHome.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       private void GoToMenu(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Menu.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnMenus.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        private void GoToReservation(){
            Parent root;
            try {
            root = FXMLLoader.load(getClass().getResource("FXML_Reservation.fxml"));
            Scene c=new Scene(root);
             Stage stage=(Stage)btnReservations.getScene().getWindow();
            stage.setScene(c);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       */
    private void GoToAjout(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML_AjouterPlat.fxml"));
            monAnchorPane.getChildren().removeAll();
            monAnchorPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(FXML_AjouterMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
  @FXML
  public  ObservableList<Plat> getPlatList() {
         conx = MyDB.getInstance().getConx();
        ObservableList<Plat> PlatList = FXCollections.observableArrayList();
        try {
                String query2="SELECT * from plat ";
                PreparedStatement smt = conx.prepareStatement(query2);
                Plat p;
                ResultSet rs= smt.executeQuery();
            while(rs.next()){
         p = new Plat(rs.getInt("id"),rs.getInt("categories_id"), rs.getString("nom"),rs.getDouble("prix"), rs.getString("description"), rs.getString("calories"), rs.getString("etat"), rs.getString("image"),rs.getInt("nbp") );
                PlatList.add(p);
            }
                System.out.println(PlatList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return PlatList;
   
    }
  
     @FXML
         public void ShowListe(){
         ObservableList<Plat> list = getPlatList();
         nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
         calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
         etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         nbp.setCellValueFactory(new PropertyValueFactory<>("nbp"));
        // image.setCellValueFactory(new PropertyValueFactory<>("image"));
         categories.setCellValueFactory(new PropertyValueFactory<>("categories_id"));

       // Set up custom cell factory for image column
        image.setCellFactory(column -> {
        TableCell<Plat, String> cell = new TableCell<Plat, String>() {
            private final ImageView imageView = new ImageView();
            
            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);

                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                Image image = new Image("file:src/uploads/" + imagePath + ".png") {};
                imageView.setImage(image);
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                setGraphic(imageView);
                }
            }
        };
        return cell;
    });  
 
        tvPlat.setItems(list);
         search();    
     }   
    @FXML
    public void getSelected(MouseEvent event) throws SQLException {
        index = tvPlat.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
         nom.setText(nom.getCellData(index).toString());
         prix.setText(prix.getCellData(index).toString());
         description.setText(description.getCellData(index).toString());
         calories.setText(calories.getCellData(index).toString());
         etat.setText(etat.getCellData(index).toString());
         nbp.setText(nbp.getCellData(index).toString());
         categories.setText(categories.getCellData(index).toString());
  
        ShowImg.setImage(new Image("file:src/uploads/" + imageplat.getCellData(index).toString() + ".png"));
        System.out.println(imageplat.getCellData(index).toString());    
    }  
   @FXML
public void showActions() {
   ObservableList<Plat> list = getPlatList();
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
       description.setCellValueFactory(new PropertyValueFactory<>("description"));
       calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
       etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
       nbp.setCellValueFactory(new PropertyValueFactory<>("nbp"));
       image.setCellValueFactory(new PropertyValueFactory<>("image"));
       categories.setCellValueFactory(new PropertyValueFactory<>("categories_id"));

    TableColumn<Plat, Void> colBtn = new TableColumn("Actions");

    Callback<TableColumn<Plat, Void>, TableCell<Plat, Void>> cellFactory = new Callback<TableColumn<Plat, Void>, TableCell<Plat, Void>>() {
        @Override
        public TableCell<Plat, Void> call(final TableColumn<Plat, Void> param) {
            final TableCell<Plat, Void> cell = new TableCell<Plat, Void>() {

                private final Button btn = new Button("Supprimer");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Plat data = getTableView().getItems().get(getIndex());
                        SupprimerPlat(data);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    };

    colBtn.setCellFactory(cellFactory);

    TableColumn<Plat, Void> colBtn2 = new TableColumn("Actions");

    Callback<TableColumn<Plat, Void>, TableCell<Plat, Void>> cellFactory2 = new Callback<TableColumn<Plat, Void>, TableCell<Plat, Void>>() {
        @Override
        public TableCell<Plat, Void> call(final TableColumn<Plat, Void> param) {
            final TableCell<Plat, Void> cell = new TableCell<Plat, Void>() {

                private final Button btn = new Button("Modifier");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        Plat data = getTableView().getItems().get(getIndex());
                            ModifierPlat(data);
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        }
    };

    colBtn2.setCellFactory(cellFactory2);

    tvPlat.setItems(list);

    tvPlat.getColumns().addAll(colBtn, colBtn2);
    
     search();    
}
     
  private void SupprimerPlat(Plat p) {
    PlatServices u=new PlatServices();
        try {
            u.Supprimer(p);
            
        } catch (SQLException ex) {
            Logger.getLogger(FXML_PlatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShowListe();
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("EnergyBox :: Error Message");
    alert.setHeaderText(null);
    alert.setContentText("Plat supprimé");
    alert.showAndWait();
}
  
 private void ModifierPlat(Plat m) {
    // chargement de la vue FXML pour la modification du menu
    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_ModifierPlat.fxml"));
    Parent root;
    try {
        root = loader.load();
    } catch (IOException ex) {
        Logger.getLogger(FXML_PlatController.class.getName()).log(Level.SEVERE, null, ex);
        return;
    }
    
    // récupération du contrôleur de la vue FXML pour la modification du menu
    FXML_ModifierPlatController controller = loader.getController();
    
    // passage des détails du menu sélectionné au contrôleur
    controller.setPlat(m);
    
    // création d'une nouvelle fenêtre pour la modification du menu
    Stage stage = new Stage();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}   
  
//****************************************Recherche-Avancée****************************************     
void search() {
    ObservableList<Plat> list = getPlatList();
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    description.setCellValueFactory(new PropertyValueFactory<>("description"));
    calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
    etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    nbp.setCellValueFactory(new PropertyValueFactory<>("nbp"));
    image.setCellValueFactory(new PropertyValueFactory<>("image"));
    categories.setCellValueFactory(new PropertyValueFactory<>("categories_id"));
    conx = MyDB.getInstance().getConx();

    ObservableList<Plat> dataList = getPlatList();
    tvPlat.setItems(dataList);

    FilteredList<Plat> filteredData = new FilteredList<>(dataList, b -> true);
    fxrecherche.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(plat -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (plat.getNom().toLowerCase().contains(lowerCaseFilter) ||
                    plat.getDescription().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches name or description
            } else {
                return false; // Does not match.
            }
        });
    });

    SortedList<Plat> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(tvPlat.comparatorProperty());
    tvPlat.setItems(sortedData);
}

//****************************************Recherche-Avancée****************************************

//****************************************Tri-Par-Prix****************************************
public void triParPrix() {
    ObservableList<Plat> dataList = getPlatList();
    Collections.sort(dataList, new Comparator<Plat>() {
        @Override
        public int compare(Plat m1, Plat m2) {
            // Tri ascendant par prix
            return Double.compare(m1.getPrix(), m2.getPrix());
        }
    });
    tvPlat.setItems(dataList);
}
//****************************************Fin-Tri-Par-Prix****************************************
 
//****************************************Tri-Par-Nom****************************************
public void triParNom() {
        ObservableList<Plat> dataList = getPlatList();
        Collections.sort(dataList, new Comparator<Plat>() {
            @Override
            public int compare(Plat m1, Plat m2) {
                return m1.getNom().compareToIgnoreCase(m2.getNom());
            }
        });
        tvPlat.setItems(dataList);
    }
//****************************************Fin-Tri-Par-Nom****************************************
 
 ////****************************************Exporter_EXCEL**************************************** 
public void export(List<Plat> plats, String fileName) throws IOException {
    // Create a workbook and a sheet
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Plats");
    
    // Define custom styles for the header and data cells
    CellStyle headerStyle = workbook.createCellStyle();
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerStyle.setFont(headerFont);
   // headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
   headerStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    
    CellStyle dataStyle = workbook.createCellStyle();
    dataStyle.setAlignment(HorizontalAlignment.CENTER);
    dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    dataStyle.setWrapText(true);
    
    // Create a header row
    Row headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("ID");
    headerRow.createCell(1).setCellValue("Category ID");
    headerRow.createCell(2).setCellValue("Nom");
    headerRow.createCell(3).setCellValue("Prix");
    headerRow.createCell(4).setCellValue("Description");
    headerRow.createCell(5).setCellValue("Calories");
    headerRow.createCell(6).setCellValue("Etat");
    headerRow.createCell(7).setCellValue("Image");
    headerRow.createCell(8).setCellValue("Nombre du plat");
    
    // Apply the custom header style to the header row cells
    for (Cell headerCell : headerRow) {
        headerCell.setCellStyle(headerStyle);
    }
    
    // Create data rows
    int rowNum = 1;
    for (Plat plat : plats) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(plat.getId());
        row.createCell(1).setCellValue(plat.getCategories_id());
        row.createCell(2).setCellValue(plat.getNom());
        row.createCell(3).setCellValue(plat.getPrix());
        row.createCell(4).setCellValue(plat.getDescription());
        row.createCell(5).setCellValue(plat.getCalories());
        row.createCell(6).setCellValue(plat.getEtat());
        row.createCell(7).setCellValue(plat.getImage());
        row.createCell(8).setCellValue(plat.getNbp());
        
        // Apply the custom data style to the data row cells
        for (Cell dataCell : row) {
            dataCell.setCellStyle(dataStyle);
        }
    }
    
    // Resize columns to fit their content
    for (int i = 0; i < headerRow.getLastCellNum(); i++) {
        sheet.autoSizeColumn(i);
    }
    
    // Get the current date and time
    Date currentDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String formattedDate = dateFormat.format(currentDate);
    
    // Add the formatted date to the file name
    int extensionIndex = fileName.lastIndexOf('.');
    String newFileName = fileName.substring(0, extensionIndex) + "_" + formattedDate + fileName.substring(extensionIndex);
    
    // Write the workbook to a file
    try (FileOutputStream outputStream = new FileOutputStream(newFileName)) {
        workbook.write(outputStream);
        JOptionPane.showMessageDialog(null, "Export successful!");
    }
    catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Export failed: " + e.getMessage());
    }
 
}
 ////****************************************Exporter_EXCEL**************************************** 
 

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 }
 
 /*  
      public void afficherPlat(){
         try {
            String req = "SELECT * FROM `plat`";
            stm = conx.createStatement();
             ResultSet rs = stm.executeQuery(req);
             List<Plat> menus = new ArrayList<Plat>();
            while(rs.next()){
                Plat p = new Plat(rs.getInt("id"), //or rst.getInt(1)
                     //    rs.getInt("categories_id"),
                         rs.getString("nom"),
                         rs.getInt("prix"),
                         rs.getString("description"),
                         rs.getString("calories"),
                         rs.getString("etat"),
                         rs.getString("image"),
                        rs.getInt("nbp"));
               menus.add(p);
            }
          Items.getChildren().clear();
            for (Plat m : menus) {
                 Label label = new Label(m.getNom() + ": " + m.getPrix() + ", " + m.getDescription() + ", " + m.getCalories() + ", " + m.getEtat() + ", " + m.getImage() + ", " + m.getNbp());
                Items.getChildren().add(label);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    */

