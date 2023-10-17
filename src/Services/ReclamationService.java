/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CrudReclamation;
import Entities.Reclamation;
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
public class ReclamationService implements CrudReclamation<Reclamation>{

    public Connection conx;
    public Statement stm;

    
    public ReclamationService() {
        conx = MyDB.getInstance().getConx();
    }
    
    @Override
    public void ajouter(Reclamation r) {
        String req = 
                "INSERT INTO reclamation"
                + "(category_id,objet_reclamation,texte_reclamation,date_reclamation,nom_user_reclamation,email_user_reclamation)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, r.getCategory_id());
            ps.setString(2, r.getObjet_reclamation());
            ps.setString(3, r.getTexte_reclamation());
            ps.setDate(4, new java.sql.Date(r.getDate_reclamation().getTime()));
            ps.setString(5, r.getNom_user_reclamation());
            ps.setString(6, r.getEmail_user_reclamation());
            ps.executeUpdate();
            System.out.println("Réclamation Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation r) {
        String req = "UPDATE reclamation "
                + "SET category_id=?, objet_reclamation=?, texte_reclamation=?, "
                + "date_reclamation=?, nom_user_reclamation=?, email_user_reclamation=? "
                + "WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(7, r.getId());
            pst.setInt(1, r.getCategory_id());
            pst.setString(2, r.getObjet_reclamation());
            pst.setString(3, r.getTexte_reclamation());
            pst.setDate(4, new java.sql.Date(r.getDate_reclamation().getTime()));
            pst.setString(5, r.getNom_user_reclamation());
            pst.setString(6, r.getEmail_user_reclamation());
            pst.executeUpdate();
            System.out.println("Réclamation de MR" + r.getNom_user_reclamation() + " est modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM reclamation WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Réclamation suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Reclamation> Show() {
        List<Reclamation> list = new ArrayList<>();

        try {
            String req = "SELECT * from reclamation";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getInt("category_id"), 
                        rs.getString("objet_reclamation"), rs.getString("texte_reclamation"), 
                        rs.getDate("date_reclamation"), rs.getString("nom_user_reclamation"), 
                        rs.getString("email_user_reclamation")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Reclamation> Search(String t) {
        List<Reclamation> list1 = new ArrayList<>();
        List<Reclamation> list2 = Show();
        list1 = (list2.stream().filter(c -> c.getNom_user_reclamation().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }
    
    
    public List<Reclamation> triObjetReclamation() {

        List<Reclamation> list1 = new ArrayList<>();
        List<Reclamation> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getObjet_reclamation().compareTo(o2.getObjet_reclamation())).collect(Collectors.toList());
        return list1;

    }
    public List<Reclamation> triTexteReclamation() {

        List<Reclamation> list1 = new ArrayList<>();
        List<Reclamation> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getTexte_reclamation().compareTo(o2.getTexte_reclamation())).collect(Collectors.toList());
        return list1;

    }
    public List<Reclamation> triDateReclamation() {

        List<Reclamation> list1 = new ArrayList<>();
        List<Reclamation> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getDate_reclamation().compareTo(o2.getDate_reclamation())).collect(Collectors.toList());
        return list1;

    }
    public List<Reclamation> triNomUserReclamation() {

        List<Reclamation> list1 = new ArrayList<>();
        List<Reclamation> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getNom_user_reclamation().compareTo(o2.getNom_user_reclamation())).collect(Collectors.toList());
        return list1;

    }
    public List<Reclamation> triEmailUserReclamation() {

        List<Reclamation> list1 = new ArrayList<>();
        List<Reclamation> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getEmail_user_reclamation().compareTo(o2.getEmail_user_reclamation())).collect(Collectors.toList());
        return list1;

    }
    
    
    public List<Reclamation> findById(int id) throws SQLException {
        List<Reclamation> list = new ArrayList<>();

        try {
            String req = "SELECT * from reclamation where id='"+id+"'";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("id"), rs.getInt("category_id"), 
                        rs.getString("objet_reclamation"), rs.getString("texte_reclamation"), rs.getDate("date_reclamation"), 
                        rs.getString("nom_user_reclamation"), rs.getString("email_user_reclamation")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
}
