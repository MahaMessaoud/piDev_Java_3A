/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidesktop;

import Entities.Sponsor;
import Entities.User;
import Services.SponsorService;
import Services.UserService;
import Utils.MyDB;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
public class PIDesktop  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        MyDB db2 = MyDB.getInstance();
        MyDB db3 = MyDB.getInstance();
        //********** test Sponsor*******//
        SponsorService Sps = new SponsorService();
        //Sps.ajouter(new Sponsor("Délice", 1500, "path"));
        //System.out.println(Sps.afficherListe());
     //   Sps.modifier(new Sponsor(3, "mahaaaa", 15.501, "path2"));
        //Sps.supprimer(new Sponsor(4));
        //***************test user************//
        UserService uService = new UserService();
     //uService.registre(new User("maha221qqqqqqqqq22@gmail.com", "mahoqqqqqqquta", "ROLE_ADMIN", "maha1234569",13362222, Date.valueOf("2000-02-23"), "maha.jpg"));
          //uService.modifier(new User("lara@gmail.com", "mah", "ROLE_ADMIN", "maha1234569",13362222, Date.valueOf("2000-02-23"), "maha.jpg"));

      //  System.out.println(uService.afficherListe());
      
    //  uService.login(new User("slayem@gmail.com","1111"));
     // uService.login(new User("slayem@gmail.com","aaaa"));
     // uService.login(new User("haha@gmail.com","12345678"));
    }

}
