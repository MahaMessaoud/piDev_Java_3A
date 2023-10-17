/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Charge;
import Entities.Materiel;
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
public class MaterielService implements ChargesCRUD<Materiel> {

    public Connection conx = MyDB.getInstance().getConx();

    @Override
    public void create(Materiel materiel) throws SQLException {
        String sql = "INSERT INTO materiel (nom_materiel, reference_materiel,date_maintenance_materiel,quantite_materiel) VALUES (?,?,?,?)";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setString(1, materiel.getNom_materiel());
            pstmt.setString(2, materiel.getReference_materiel());
            pstmt.setString(3, materiel.getDate_maintenance());
            pstmt.setInt(4, materiel.getQuantite_materiel());
            pstmt.executeUpdate();

            System.out.println("Matériel ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Materiel getOneById(int id) throws SQLException {
        String sql = "SELECT id,nom_materiel,reference_materiel,date_maintenance_materiel,quantite_materiel FROM materiel WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Materiel person = new Materiel();
                person.setId(rs.getInt("id"));
                person.setNom_materiel(rs.getString("nom_materiel"));
                person.setReference_materiel(rs.getString("reference_materiel"));
                person.setDate_maintenance(rs.getString("date_maintenance_materiel"));
                person.setQuantite_materiel(rs.getInt("quantite_materiel"));
                return person;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getCharges(Materiel m) throws SQLException{
    
    String sql = "Select * From charge where materiel_id = ?";
        PreparedStatement pstmt = conx.prepareStatement(sql);
pstmt.setInt(1, m.getId());
        ResultSet rs = pstmt.executeQuery();
        List<Charge> personnes = new ArrayList<Charge>();
        while (rs.next()) {
            Charge p = new Charge(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("date_arrivage_charge"),
                    rs.getInt("quantite_charge"),
                    rs.getString("materiel_id"),
                    rs.getString("fournisseur_id"));
            personnes.add(p);
        }

        return personnes.size();
    }
    
    
    @Override
    public void update(Materiel person, int id) throws SQLException {

        String sql = "UPDATE materiel SET nom_materiel = ?,reference_materiel = ? ,date_maintenance_materiel= ?,quantite_materiel = ? WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {

            pstmt.setString(1, person.getNom_materiel());
            pstmt.setString(2, person.getReference_materiel());
            pstmt.setString(3, person.getDate_maintenance());
            pstmt.setInt(4, person.getQuantite_materiel());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            System.out.println("Materiel modifié");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM materiel  WHERE id = ?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("materiel supprimé");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM materiel";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);

            pstmt.executeUpdate();
            System.out.println("materiels supprimés");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Materiel> getAll() throws SQLException {
        String sql = "Select * From Materiel";
        PreparedStatement pstmt = conx.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        List<Materiel> personnes = new ArrayList<Materiel>();
        while (rs.next()) {
            Materiel p = new Materiel(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("nom_materiel"),
                    rs.getString("reference_materiel"),
                    rs.getString("date_maintenance_materiel"),
                    rs.getInt("quantite_materiel"));
            personnes.add(p);
        }

        return personnes;
    }

}
