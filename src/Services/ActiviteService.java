/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activite;
import Entities.CrudActivite;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author azizo
 */

public class ActiviteService implements CrudActivite<Activite> {
    
    public Connection conx;
    public Statement stm;

    
    public ActiviteService() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(Activite a) {
        String req = 
                "INSERT INTO activite"
                + "(nom_activite,duree_activite,tenue_activite,difficulte_activite,image_activite,description_activite)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, a.getNom_activite());
            ps.setString(2, a.getDuree_activite());
            ps.setString(3, a.getTenue_activite());
            ps.setString(4, a.getDifficulte_activite());
            ps.setString(5, a.getImage_activite());
            ps.setString(6, a.getDescription_activite());
            ps.executeUpdate();
            System.out.println("Activité Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Activite a) {
        try {
            String req = "UPDATE activite SET nom_activite=?, duree_activite=?, tenue_activite=?, difficulte_activite=?, image_activite=?, description_activite=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(7, a.getId());
            pst.setString(1, a.getNom_activite());
            pst.setString(2, a.getDuree_activite());
            pst.setString(3, a.getTenue_activite());
            pst.setString(4, a.getDifficulte_activite());
            pst.setString(5, a.getImage_activite());
            pst.setString(6, a.getDescription_activite());
            pst.executeUpdate();
            System.out.println("Activite " + a.getNom_activite() + " Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    

    @Override
    public List<Activite> Show() {
        List<Activite> list = new ArrayList<>();

        try {
            String req = "SELECT * from activite";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Activite(rs.getInt("id"), rs.getString("nom_activite"), 
                        rs.getString("duree_activite"), rs.getString("tenue_activite"), 
                        rs.getString("difficulte_activite"), rs.getString("image_activite"), 
                        rs.getString("description_activite")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
        

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM activite WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Activité suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Activite> Search(String t) {
        List<Activite> list1 = new ArrayList<>();
        List<Activite> list2 = Show();
        list1 = (list2.stream().filter(c -> c.getNom_activite().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }
    
    
    public List<Activite> triNomActivite() {

        List<Activite> list1 = new ArrayList<>();
        List<Activite> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getNom_activite().compareTo(o2.getNom_activite())).collect(Collectors.toList());
        return list1;

    }
    public List<Activite> triTenueActivite() {

        List<Activite> list1 = new ArrayList<>();
        List<Activite> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getTenue_activite().compareTo(o2.getTenue_activite())).collect(Collectors.toList());
        return list1;

    }
    public List<Activite> triDiffActivite() {

        List<Activite> list1 = new ArrayList<>();
        List<Activite> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getDifficulte_activite().compareTo(o2.getDifficulte_activite())).collect(Collectors.toList());
        return list1;

    }
    public List<Activite> triDescriptionActivite() {

        List<Activite> list1 = new ArrayList<>();
        List<Activite> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getDescription_activite().compareTo(o2.getDescription_activite())).collect(Collectors.toList());
        return list1;

    }

    public Activite findById(int id) throws SQLException {
        Activite act = new Activite();

        try {
            String req = "SELECT * from activite where id='"+id+"'";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                act.setId(rs.getInt("id"));
                act.setNom_activite(rs.getString("nom_activite"));
                act.setDuree_activite(rs.getString("duree_activite"));
                act.setTenue_activite(rs.getString("tenue_activite"));
                act.setDifficulte_activite(rs.getString("difficulte_activite"));
                act.setImage_activite(rs.getString("image_activite"));
                act.setDescription_activite(rs.getString("description_activite"));
                        
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return act;
    }    
}
