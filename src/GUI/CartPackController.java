/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Pack;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import GUI.MyListener;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class CartPackController implements Initializable {
 @FXML
    private Label type;

    @FXML
    private Label prix;

    @FXML
    private Label disponibilite;
    MyListener myListener;
 @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(pack);
    }
    @FXML
    private Label places;
    Pack pack ;
    public void setData (Pack pack, MyListener myListener){
    this.pack = pack ;
    this.myListener = myListener;
    type.setText(pack.getTypePack());
    String p = String.valueOf(pack.getMontantPack());
    prix.setText("Prix : "+p+" DT ");
    String d = String.valueOf(pack.getDisponibilitePack());
    disponibilite.setText("Capacités : " + d);
     String pl = String.valueOf(pack.getPlacesPack());
     places.setText("Réservés : "+ pl );
    
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
