/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.ListPlann;
import Entities.Planning;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Services.PlanningService;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class ListPlanningFrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherPlanningLundi();
        AfficherPlanningMardi();
        AfficherPlanningMercredi();
        AfficherPlanningJeudi();
        AfficherPlanningVendredi();
        AfficherPlanningSamedi();
        AfficherPlanningDimanche();
       
    }  
    
    @FXML
    private TableColumn<ListPlann, String> Lundi;

    @FXML
    private TableColumn<ListPlann, String> Mardi;
    
    @FXML
    private TableColumn<ListPlann, String> Dimanche;

    @FXML
    private TableColumn<ListPlann, String> Jeudi;

    @FXML
    private TableColumn<ListPlann, String> Mercredi;

    @FXML
    private TableColumn<ListPlann, String> Samedi;

    @FXML
    private TableColumn<ListPlann, String> Vendredi;
    
    @FXML
    private TableColumn<ListPlann,String> HeurePlanning;

    @FXML
    private TableView<ListPlann> tablePlanningLundi;

    @FXML
    private TableView<ListPlann> tablePlanningMardi;
    
    @FXML
    private TableView<ListPlann> tablePlanningDimanche;

    @FXML
    private TableView<ListPlann> tablePlanningJeudi;

    @FXML
    private TableView<ListPlann> tablePlanningMercredi;

    @FXML
    private TableView<ListPlann> tablePlanningSamedi;

    @FXML
    private TableView<ListPlann> tablePlanningVendredi;
    
    @FXML
    private Button btnConsulterCours;

    @FXML
    private Button btnConsulterActivite;
    
    @FXML
    private AnchorPane listPlanningPane;
    
   
    @FXML
    void go_ListCours(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("listCoursFront.fxml"));
        listPlanningPane.getChildren().removeAll();
        listPlanningPane.getChildren().setAll(fxml);
    }

    @FXML
    void go_ListActivite(ActionEvent event) throws IOException {
        Parent fxml= FXMLLoader.load(getClass().getResource("listActiviteFront.fxml"));
        listPlanningPane.getChildren().removeAll();
        listPlanningPane.getChildren().setAll(fxml);
    }
    

    
    
    
    ObservableList<ListPlann> dataLundi = FXCollections.observableArrayList();
    ObservableList<ListPlann> dataMardi = FXCollections.observableArrayList();
    ObservableList<ListPlann> dataMercredi = FXCollections.observableArrayList();
    ObservableList<ListPlann> dataJeudi = FXCollections.observableArrayList();
    ObservableList<ListPlann> dataVendredi = FXCollections.observableArrayList();
    ObservableList<ListPlann> dataSamedi = FXCollections.observableArrayList();
    ObservableList<ListPlann> dataDimanche = FXCollections.observableArrayList();
    
 
    
    public void AfficherPlanningLundi()
    {
        PlanningService ps = new PlanningService();
        
        
        ps.AfficherLundi().stream().forEach((c) -> {
            dataLundi.add(c);
        });
        Lundi.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        //HeurePlanning.setCellValueFactory(new PropertyValueFactory<>("heure_planning"));
        tablePlanningLundi.setItems(dataLundi);
        
    }
    
      
    
    public void AfficherPlanningMardi()
    {
        PlanningService ps = new PlanningService();
        
        
        ps.ShowMardi().stream().forEach((c) -> {
            dataMardi.add(c);
        });
        Mardi.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        tablePlanningMardi.setItems(dataMardi);
        
    }
    
    public void AfficherPlanningMercredi()
    {
        PlanningService ps = new PlanningService();
        
        
        ps.ShowMercredi().stream().forEach((c) -> {
            dataMercredi.add(c);
        });
        Mercredi.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        tablePlanningMercredi.setItems(dataMercredi);
        
    }
    
    public void AfficherPlanningJeudi()
    {
        PlanningService ps = new PlanningService();
        
        
        ps.ShowJeudi().stream().forEach((c) -> {
            dataJeudi.add(c);
        });
        Jeudi.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        tablePlanningJeudi.setItems(dataJeudi);
        
    }
    
    public void AfficherPlanningVendredi()
    {
        PlanningService ps = new PlanningService();
        
        
        ps.ShowVendredi().stream().forEach((c) -> {
            dataVendredi.add(c);
        });
        Vendredi.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        tablePlanningVendredi.setItems(dataVendredi);
        
    }
    
    public void AfficherPlanningSamedi()
    {
        PlanningService ps = new PlanningService();
        
        
        ps.ShowSamedi().stream().forEach((c) -> {
            dataSamedi.add(c);
        });
        Samedi.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        tablePlanningSamedi.setItems(dataSamedi);
        
    }
    
    public void AfficherPlanningDimanche()
    {
        PlanningService ps = new PlanningService();
        
        
        ps.ShowDimanche().stream().forEach((c) -> {
            dataDimanche.add(c);
        });
        Dimanche.setCellValueFactory(new PropertyValueFactory<>("nom_cours"));
        tablePlanningDimanche.setItems(dataDimanche);
        
    }
    
}
