/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Fournisseur;
import Interfaces.ChargesCRUD;
import Utils.MyDB;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed Ben Abid
 */
public class FournisseurService implements ChargesCRUD<Fournisseur>{

    public Connection conx= MyDB.getInstance().getConx();
    public Statement stm;
    @Override
    public void create(Fournisseur f) throws SQLException {
String sql = "INSERT INTO fournisseur (nom_fournisseur, contact_fournisseur,email_fournisseur,adresse_fournisseur) VALUES (?,?,?,?)";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setString(1, f.getNom_fournisseur());
            pstmt.setString(2, f.getContact_fournisseur());
            pstmt.setString(3,f.getEmail_fournisseur());
            pstmt.setString(4,f.getAdresse_fournisseur());
            
            pstmt.executeUpdate();
            System.out.println("Fournisseur ajouté");
            
                    
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }  
    }

    @Override
    public Fournisseur getOneById(int id) throws SQLException {
String sql = "SELECT * FROM fournisseur WHERE id = ?";
        try (
             PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Fournisseur person = new Fournisseur();
                person.setId(rs.getInt("id"));
                person.setNom_fournisseur(rs.getString("nom_fournisseur"));
                person.setContact_fournisseur(rs.getString("contact_fournisseur"));
                person.setEmail_fournisseur(rs.getString("email_fournisseur"));
                person.setAdresse_fournisseur(rs.getString("adresse_fournisseur"));

                return person;
            }
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
        return null;    
    }

    @Override
    public void update(Fournisseur person, int id) throws SQLException {
String sql = "UPDATE fournisseur SET nom_fournisseur = ?,contact_fournisseur = ? ,email_fournisseur= ?,adresse_fournisseur = ? WHERE id = ?";
        try ( 
               
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
             
            pstmt.setString(1, person.getNom_fournisseur());
            pstmt.setString(2, person.getContact_fournisseur());
            pstmt.setString(3, person.getEmail_fournisseur());
            pstmt.setString(4, person.getAdresse_fournisseur());
            pstmt.setInt(5, id);
            
          
            pstmt.executeUpdate();
            System.out.println("Fournisseur modifié");
            
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException {
String sql = "DELETE FROM fournisseur  WHERE id = ?";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("fournisseur supprimé");
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws SQLException {
String sql = "DELETE FROM fournisseur";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.executeUpdate();
            System.out.println("Liste des fournisseurs supprimés");
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Fournisseur> getAll() throws SQLException {
String sql = "Select * From fournisseur";
PreparedStatement pstmt = conx.prepareStatement(sql);


ResultSet rs = pstmt.executeQuery();
        List<Fournisseur> personnes = new ArrayList<Fournisseur>();
        while (rs.next()) {
            Fournisseur p = new Fournisseur(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("nom_fournisseur"),
                    rs.getString("contact_fournisseur"),
                    rs.getString("email_fournisseur"),
                    rs.getString("adresse_fournisseur"));
            personnes.add(p);
        }

        return personnes;  
    }
 
}
