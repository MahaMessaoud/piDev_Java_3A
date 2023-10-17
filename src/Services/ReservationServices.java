/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import Services.Icrud;
import Entities.Menu;
import Entities.Plat;
import Entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;
/**
 *
 * @author lenovo
 */
public class ReservationServices implements Icrud<Reservation> {
    
    public Connection conx;
    public Statement stm;

    public ReservationServices() {
        conx = MyDB.getInstance().getConx();

    }

    @Override
    public void ajouter(Reservation m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterr(Reservation m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> afficherListe() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  //  @Override
  //  public void modifier(Reservation m) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   // }

    @Override
    public void Supprimer(Reservation t) throws SQLException {
      try {
            String requete="DELETE FROM reservation WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1,t.getId());
            pst.executeUpdate();
           
            System.out.println("Réservation est supprimée");
      } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }

    @Override
    public void Modifier(Reservation m) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int idmenu(String nom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> afficherListe1(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
