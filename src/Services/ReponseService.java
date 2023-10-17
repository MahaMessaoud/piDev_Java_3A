/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CrudReponse;
import Entities.Reponse;
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
public class ReponseService implements CrudReponse<Reponse>{

    public Connection conx;
    public Statement stm;

    
    public ReponseService() {
        conx = MyDB.getInstance().getConx();
    }
    
    
    @Override
    public void ajouter(Reponse r) {
        String req = 
                "INSERT INTO reponse"
                + "(reclamation_id,objet_reponse,date_reponse,piece_jointe,contenu_reponse)"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, r.getReclamation_id());
            ps.setString(2, r.getObjet_reponse());
            ps.setDate(3, new java.sql.Date(r.getDate_reponse().getTime()));
            ps.setString(4, r.getPiece_jointe());
            ps.setString(5, r.getContenu_reponse());
            ps.executeUpdate();
            System.out.println("Réponse Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Reponse r) {
        String req = "UPDATE reponse SET reclamation_id=?, objet_reponse=?, date_reponse=?, piece_jointe=?, contenu_reponse=? WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(6, r.getId());
            pst.setInt(1, r.getReclamation_id());
            pst.setString(2, r.getObjet_reponse());
            pst.setDate(3, new java.sql.Date(r.getDate_reponse().getTime()));
            pst.setString(4, r.getPiece_jointe());
            pst.setString(5, r.getContenu_reponse());
            pst.executeUpdate();
            System.out.println("Reponse sur la reclamation Num " + r.getReclamation_id() + " est modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM reponse WHERE id=?";
        try {
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Réponse suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    @Override
    public List<Reponse> Show() {
        List<Reponse> list = new ArrayList<>();

        try {
            String req = "SELECT * from reponse";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reponse(rs.getInt("id"), rs.getInt("reclamation_id"), 
                        rs.getString("objet_reponse"), rs.getDate("date_reponse"),
                        rs.getString("piece_jointe"), rs.getString("contenu_reponse")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Reponse> Search(String t) {
        List<Reponse> list1 = new ArrayList<>();
        List<Reponse> list2 = Show();
        list1 = (list2.stream().filter(c -> c.getContenu_reponse().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }
    
    
    public List<Reponse> triObjetReponse() {

        List<Reponse> list1 = new ArrayList<>();
        List<Reponse> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getObjet_reponse().compareTo(o2.getObjet_reponse())).collect(Collectors.toList());
        return list1;

    }
    public List<Reponse> triContenuReponse() {

        List<Reponse> list1 = new ArrayList<>();
        List<Reponse> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getContenu_reponse().compareTo(o2.getContenu_reponse())).collect(Collectors.toList());
        return list1;

    }
    public List<Reponse> triDateReponse() {

        List<Reponse> list1 = new ArrayList<>();
        List<Reponse> list2 = Show();

        list1 = list2.stream().sorted((o1, o2) -> o1.getDate_reponse().compareTo(o2.getDate_reponse())).collect(Collectors.toList());
        return list1;

    }
    
}
