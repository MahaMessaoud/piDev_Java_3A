/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Promotion;
import Entities.PromotionCRUD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;


public class PromotionService implements PromotionCRUD<Promotion> {

    public Connection conx;
    public Statement stm;

    public PromotionService() {
        conx = MyDB.getInstance().getConx();

    }

    @Override
    public void create(Promotion promotion) throws SQLException {
        String sql = "INSERT INTO Promotion (code_promotion , reduction_promotion , date_expiration , id) VALUES (?, ? , ? , ? )";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setString(1, promotion.getCodePromotion());
            pstmt.setFloat(2, (float) promotion.getReductionPromotion());
            pstmt.setDate(3, (Date) promotion.getDateExpiration());
            pstmt.setInt(4, promotion.getId());
            pstmt.executeUpdate();
            System.out.println("Promotion ajouté");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Promotion getOneById(int id) throws SQLException {
        String sql = "SELECT * FROM promotion WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Promotion p = new Promotion();
                p.setId(rs.getInt("id"));
                p.setCodePromotion(rs.getString("code_promotion"));
                p.setReductionPromotion(rs.getFloat("reduction_promotion"));
                p.setDateExpiration(rs.getDate("date_expiration"));
                p.setId(rs.getInt("id"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Promotion promotion, int id) throws SQLException {
        String sql = "UPDATE promotion SET code_promotion = ?, reduction_promotion = ? , date_expiration= ? WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {

            pstmt.setString(1, promotion.getCodePromotion());
            pstmt.setFloat(2, (float) promotion.getReductionPromotion());
            pstmt.setDate(3, (Date) promotion.getDateExpiration());
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Promotion updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modifier(Promotion a) {
        try {
            String req = "UPDATE promotion SET code_promotion=?, reduction_promotion=?, date_expiration=? WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(4, a.getId());
            pst.setString(1, a.getCodePromotion());
            pst.setDouble(2, a.getReductionPromotion());
            pst.setDate(3, new java.sql.Date(a.getDateExpiration().getTime()) );
          
            pst.executeUpdate();
            System.out.println("Promotion " + a.getCodePromotion() + " Modifiée !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM promotion WHERE id = ?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
       public void Supprimer(Promotion t) {
        try {
            String req = "DELETE FROM promotion WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("promotion suprimée !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM promotion";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Promotion> getAll() throws SQLException {
        String sql = "Select * From promotion";
        PreparedStatement pstmt = conx.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        List<Promotion> promotions = new ArrayList<Promotion>();
        while (rs.next()) {
            Promotion p = new Promotion(rs.getInt("id"),//or rst.getInt(1)
                    rs.getString("code_promotion"),
                    rs.getDouble("reduction_promotion"),
                    rs.getDate("date_expiration")
            );
            promotions.add(p);
        }
        return promotions;
    }
public Promotion findByCodePromotion(String codePromotion) throws SQLException {
    String sql = "SELECT * FROM promotion WHERE code_promotion = ?";
    try (PreparedStatement pstmt = conx.prepareStatement(sql)) {
        pstmt.setString(1, codePromotion);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Promotion promotion = new Promotion();
            promotion.setId(rs.getInt("id"));
            promotion.setCodePromotion(rs.getString("code_promotion"));
            promotion.setReductionPromotion(rs.getFloat("reduction_promotion"));
            return promotion;
        } else {
            return null;
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return null;
    }
}

    
}
