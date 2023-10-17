/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Comment;
import Interfaces.BlogCRUD;
import Utils.MyDB;


import java.sql.*;
import java.util.List;

/**
 *
 * @author Ahmed Ben Abid
 */
public class CommentService implements BlogCRUD<Comment>{
 public Connection conx= MyDB.getInstance().getConx();
    public Statement stm;

    
    @Override
    public void create(Comment person) throws SQLException {
        
String sql = "INSERT INTO comment (description, post_id,date_com,user_id) VALUES (?, ?,?,?)";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setString(1, person.getDecription());
            pstmt.setInt(2, person.getPost_id());
             pstmt.setString(3, person.getDateCom());
             pstmt.setInt(4, person.getUser_id());
            pstmt.executeUpdate();
                    System.out.println("Commentaire ajouté");
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }        
    }

    @Override
    public Comment getOneById(int id) throws SQLException {
 String sql = "SELECT * FROM comment WHERE id = ?";
        try (
             PreparedStatement pstmt = conx.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Comment person = new Comment();
                person.setId(rs.getInt("id"));
                person.setDecription(rs.getString("description"));
                person.setDateCom(rs.getString("date_com"));
                person.setPost_id(rs.getInt("post_id"));
                return person;
            }
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
        return null;    }

    @Override
    public void update(Comment person, int id) throws SQLException {
// String sql2 = "SELECT * FROM Comment WHERE id = ?";
//         PreparedStatement pstmt1 = conx.prepareStatement(sql2); 
//            pstmt1.setInt(1, id);
//            ResultSet rs = pstmt1.executeQuery();
//            System.out.println("Comment retrieved");
    
        String sql = "UPDATE comment SET description = ? WHERE id = ?";
        try ( 
               
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
             
            pstmt.setString(1, person.getDecription());
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Comment updated");
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }    }

    @Override
    public void delete(int id) throws SQLException {
String sql = "DELETE FROM comment WHERE id = ?";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    
    public void deleteAllComments(int id) throws SQLException {
String sql = "DELETE FROM comment WHERE post_id = ?";
        try {
             PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
System.out.println(e.getMessage());
        }
    }

    @Override
    public List getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
