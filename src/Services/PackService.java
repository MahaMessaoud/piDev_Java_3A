/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Pack;
import Entities.PackCRUD;

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
 * @author wiem
 */
public class PackService implements PackCRUD<Pack> {

    public Connection conx;
    public Statement stm;

    public PackService() {
        conx = MyDB.getInstance().getConx();

    }
//ne9sa

    @Override
    public void create(Pack pack) throws SQLException {
        String sql = "INSERT INTO Pack (type_pack , montant_pack , duree_pack  , description_pack , places_pack , disponibilite_pack , id) VALUES (?, ? , ? , ? ,? ,? ,?)";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setString(1, pack.getTypePack());
            pstmt.setFloat(2, (float) pack.getMontantPack());
            pstmt.setInt(3, pack.getDureePack());
            pstmt.setString(4, pack.getDescriptionPack());
            pstmt.setInt(5, pack.getPlacesPack());
            pstmt.setInt(6, pack.getDisponibilitePack());
            pstmt.setInt(7, pack.getId());
            pstmt.executeUpdate();
            System.out.println("Pack ajouté");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Pack getOneById(int id) throws SQLException {
        String sql = "SELECT * FROM pack WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Pack p = new Pack();
                p.setId(rs.getInt("id"));
                p.setTypePack(rs.getString("type_pack"));
                p.setMontantPack(rs.getFloat("montant_pack"));
                p.setDureePack(rs.getInt("duree_pack"));
                p.setDescriptionPack(rs.getString("description_pack"));
                p.setPlacesPack(rs.getInt("places_pack"));
                p.setDisponibilitePack(rs.getInt("disponibilite_pack"));
                p.setId(rs.getInt("id"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Pack pack, int id) throws SQLException {

        String sql = "UPDATE pack \n"
                + "SET type_pack = ?, montant_pack = ?, duree_pack = ?, description_pack = ?, places_pack = ?, disponibilite_pack = ? \n"
                + "WHERE id = ?;";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {

            pstmt.setString(1, pack.getTypePack());
            pstmt.setInt(2, (int) pack.getMontantPack());
            pstmt.setInt(3, pack.getDureePack());
            pstmt.setString(4, pack.getDescriptionPack());
            pstmt.setInt(5, pack.getPlacesPack());
            pstmt.setInt(6, pack.getDisponibilitePack());
            pstmt.setInt(7, id);
            pstmt.executeUpdate();
            System.out.println("Pack updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifier(Pack pack) {
        try {
            String req = "UPDATE pack \n"
                    + "SET type_pack = ?, montant_pack = ?, duree_pack = ?, description_pack = ?, places_pack = ?, disponibilite_pack = ? \n"
                    + "WHERE id = ?;";
            PreparedStatement pstmt = conx.prepareStatement(req);
            pstmt.setString(1, pack.getTypePack());
            pstmt.setInt(2, (int) pack.getMontantPack());
            pstmt.setInt(3, pack.getDureePack());
            pstmt.setString(4, pack.getDescriptionPack());
            pstmt.setInt(5, pack.getPlacesPack());
            pstmt.setInt(6, pack.getDisponibilitePack());
            pstmt.setInt(7, pack.getId());
            pstmt.executeUpdate();

            pstmt.executeUpdate();
            System.out.println("Pack " + pack.getTypePack() + " Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pack WHERE id = ?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void Supprimer(Pack t) {
        try {
            String req = "DELETE FROM pack WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("pack suprimée !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM pack";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Pack> getAll()  {
            List<Pack> packs = new ArrayList<Pack>();
            try{
        String sql = "Select * From Pack";
        
        PreparedStatement pstmt = conx.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
    
        while (rs.next()) {
            Pack p = new Pack(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("type_pack"),
                    rs.getFloat("montant_pack"),
                    rs.getInt("duree_pack"),
                    rs.getString("description_pack"),
                    rs.getInt("places_pack"),
                    rs.getInt("disponibilite_pack")
            );
            packs.add(p);
        }}
            catch(SQLException e ){
            System.err.println(e.getMessage());}

        return packs;
    }

    public void stat() throws SQLException {
        List<Pack> packs = this.getAll();

        int totalPlaces = 0;
        int minPlaces = Integer.MAX_VALUE;
        int maxPlaces = Integer.MIN_VALUE;
        for (Pack pack : packs) {
            totalPlaces += pack.getPlacesPack();
            minPlaces = Math.min(minPlaces, pack.getPlacesPack());
            maxPlaces = Math.max(maxPlaces, pack.getPlacesPack());
        }
        double averagePlaces = totalPlaces / (double) packs.size();

        System.out.println("Nombre total de places disponibles : " + totalPlaces);
        System.out.println("Moyenne de places disponibles : " + averagePlaces);
        System.out.println("Minimum de places disponibles : " + minPlaces);
        System.out.println("Maximum de places disponibles : " + maxPlaces);
    }

}
