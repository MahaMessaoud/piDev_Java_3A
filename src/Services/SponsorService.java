/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ICrudSponsor;
import Entities.Sponsor;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maham
 */
public class SponsorService implements ICrudSponsor<Sponsor> {

    public Connection conx;
    public Statement stm;

    public SponsorService() {
        conx = MyDB.getInstance().getConx();

    }

    @Override
    public void ajouter(Sponsor t) {
        try {
            String req = "INSERT INTO sponsor(nom_sponsor,donnation,image)VALUES(?,?,?)";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setString(1, t.getNom_sponsor());
            pst.setDouble(2, t.getDonnation());
            pst.setString(3, t.getImage());
            pst.executeUpdate();
            System.out.println("Sponsor Ajoutée !!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override

    public List<Sponsor> afficherListe(){
        List<Sponsor> list = new ArrayList<>();

        try {
            String req = "SELECT * from sponsor";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Sponsor(rs.getInt("id"), rs.getString("nom_sponsor"), rs.getDouble("donnation"), rs.getString("image")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void modifier(Sponsor t) {

        try {
            String req = "UPDATE sponsor SET nom_sponsor=?, Donnation=?, image=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(4, t.getId());
            pst.setString(1, t.getNom_sponsor());
            pst.setDouble(2, t.getDonnation());
            pst.setString(3, t.getImage());
            pst.executeUpdate();
            System.out.println("Sponsor " + t.getNom_sponsor() + " Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(Sponsor t) {
        try {
            String req = "DELETE FROM sponsor WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Sponsors suprimée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

   

}
