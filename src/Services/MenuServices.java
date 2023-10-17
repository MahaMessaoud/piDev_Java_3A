/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Services.Icrud;
import Entities.Menu;
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
public class MenuServices implements Icrud<Menu> {

    public Connection conx;
    public Statement stm;

    public MenuServices() {
        conx = MyDB.getInstance().getConx();

    }

    @Override
    public void ajouter(Menu m) {
         String req = "INSERT INTO `menu`(`categories`, `descriptionmenu`) VALUES ('" + m.getCategories() + "','" + m.getDescriptionmenu() + "')";
        try {
            stm = conx.createStatement();
            stm.executeUpdate(req);
            System.out.println("Menu est bien ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void ajouterr(Menu m) {
       String req = "INSERT INTO `menu`(`categories`, `descriptionmenu`) "
                + "VALUES (?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, m.getCategories());
            ps.setString(2, m.getDescriptionmenu());
            ps.executeUpdate();
            System.out.println("Menu ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Menu> afficherListe() throws SQLException {
       String req = "SELECT * FROM `menu`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<Menu> menus = new ArrayList<Menu>();
        while (rs.next()) {
            Menu m = new Menu(rs.getInt("id"), //or rst.getInt(1)
                    rs.getString("categories"),
                    rs.getString(3));
            menus.add(m);
        }

        return menus;
    }
     
  /*  @Override
    public void supprimer(int id ) {
     try {
            String req = "DELETE FROM menu WHERE id = " + id;
            Statement st = conx.createStatement();
            st.executeUpdate(req);
            System.out.println("Menu supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
*/
  /*  @Override
    public void modifier(Menu m) {
       try {
            String req = "UPDATE menu SET categories = '" + m.getCategories() + "', descriptionmenu = '" + m.getDescriptionmenu() + "'  WHERE menu.id = " + m.getId();
            Statement st = conx.createStatement();
            st.executeUpdate(req);
            System.out.println("Menu mis à jour !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/ 
    
   
    @Override
    public void Supprimer(Menu t) throws SQLException {
        try {
            String requete="DELETE FROM menu WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1,t.getId());
            pst.executeUpdate();
           
            System.out.println("Menu est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }
    }
    
    @Override
    public void Modifier(Menu m) throws SQLException {
        String requete = "UPDATE menu SET categories=?, descriptionmenu=? WHERE id=?";
        PreparedStatement pst = conx.prepareStatement(requete);
        pst.setString(1, m.getCategories());
        pst.setString(2, m.getDescriptionmenu());
        pst.setInt(3, m.getId());
        pst.executeUpdate();
        System.out.println("Menu modifié avec succès");
    }
    
 //  Staamltha bch ngeti l id ki najouti plat yjiwni les menus li fama 
    @Override
    public int idmenu(String nom) {
         String req = "SELECT id FROM menu WHERE categories = ?";
    try {
        PreparedStatement pstmt = conx.prepareStatement(req);
        pstmt.setString(1, nom);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            return 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }    }

    
    
    @Override
    public List<Menu> afficherListe1(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
