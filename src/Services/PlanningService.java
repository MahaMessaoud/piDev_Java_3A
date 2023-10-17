/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CrudPlanning;
import Entities.ListPlann;
import Entities.Planning;
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
public class PlanningService implements CrudPlanning<Planning>{

    public Connection conx;
    public Statement stm;

    
    public PlanningService() {
        conx = MyDB.getInstance().getConx();

    }
    
    @Override
    public void ajouter(Planning p) {
        String req = 
                "INSERT INTO planning"
                + "(cours_id,date_planning,jour_planning,heure_planning)"
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, p.getCours_id());
            ps.setDate(2, new java.sql.Date(p.getDate_planning().getTime()));
            ps.setString(3, p.getJour_planning());
            ps.setInt(4, p.getHeure_planning());
            ps.executeUpdate();
            System.out.println("Planning Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Planning p) {
        String req = "UPDATE planning SET cours_id=?, date_planning=?, jour_planning=?, heure_planning=? WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(5, p.getId());
            pst.setInt(1, p.getCours_id());
            pst.setDate(2, new java.sql.Date(p.getDate_planning().getTime()));
            pst.setString(3, p.getJour_planning());
            pst.setInt(4, p.getHeure_planning());
            pst.executeUpdate();
            System.out.println("Planning le" + p.getDate_planning() + " est modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM planning WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Planning suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Planning> Show() {
        List<Planning> list = new ArrayList<>();

        try {
            String req = "SELECT * from planning";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Planning(rs.getInt("id"), rs.getInt("cours_id"), 
                        rs.getDate("date_planning"), rs.getString("jour_planning"), 
                        rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    
    public List<Planning> ShowLundi() {
        List<Planning> list = new ArrayList<>();

        try {
            String req = "SELECT cours_id,heure_planning from planning where jour_planning='Lundi'";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Planning(rs.getInt("cours_id"),rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
      
    
    
    public List<ListPlann> AfficherLundi() {
        List<ListPlann> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `planning` as pl , `cours` as cr WHERE pl.cours_id=cr.id and jour_planning='Lundi'";
        
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new ListPlann(rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("description_cours"),rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    
    
    public List<ListPlann> ShowMardi() {
        List<ListPlann> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `planning` as pl , `cours` as cr WHERE pl.cours_id=cr.id and jour_planning='Mardri'";
        
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new ListPlann(rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("description_cours"),rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List<ListPlann> ShowMercredi() {
         List<ListPlann> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `planning` as pl , `cours` as cr WHERE pl.cours_id=cr.id and jour_planning='Mercredi'";
        
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new ListPlann(rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("description_cours"),rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List<ListPlann> ShowJeudi() {
         List<ListPlann> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `planning` as pl , `cours` as cr WHERE pl.cours_id=cr.id and jour_planning='Jeudi'";
        
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new ListPlann(rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("description_cours"),rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List<ListPlann> ShowVendredi() {
         List<ListPlann> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `planning` as pl , `cours` as cr WHERE pl.cours_id=cr.id and jour_planning='Vendredi'";
        
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new ListPlann(rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("description_cours"),rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List<ListPlann> ShowSamedi() {
        List<ListPlann> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `planning` as pl , `cours` as cr WHERE pl.cours_id=cr.id and jour_planning='Samedi'";
        
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new ListPlann(rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("description_cours"),rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public List<ListPlann> ShowDimanche() {
         List<ListPlann> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `planning` as pl , `cours` as cr WHERE pl.cours_id=cr.id and jour_planning='Dimanche'";
        
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new ListPlann(rs.getString("nom_cours"),rs.getString("nom_coach"),rs.getString("description_cours"),rs.getInt("heure_planning")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Planning> Search(String t) {
        List<Planning> list1 = new ArrayList<>();
        List<Planning> list2 = Show();
        list1 = (list2.stream().filter(c -> c.getJour_planning().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }
    
    
    public List<Planning> triJourPlanning() {

        List<Planning> list1 = new ArrayList<>();
        List<Planning> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getJour_planning().compareTo(o2.getJour_planning())).collect(Collectors.toList());
        return list1;

    }
    /*public List<Planning> triHeurePlanning() {

        List<Planning> list1 = new ArrayList<>();
        List<Planning> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getHeure_planning().compareTo(o2.getHeure_planning())).collect(Collectors.toList());
        return list1;

    }*/
    public List<Planning> triDatePlanning() {

        List<Planning> list1 = new ArrayList<>();
        List<Planning> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getDate_planning().compareTo(o2.getDate_planning())).collect(Collectors.toList());
        return list1;

    }
}
