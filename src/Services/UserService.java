/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.ICrudUser;
import Entities.Reset;
import Entities.User;
import Securite.BCrypt;
import Utils.MyDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author maham
 */
public class UserService implements ICrudUser<User> {

    public static int idUser;
    public static String email;
    public static String password;
    public static String username;
    public static Integer num_tel;
    public static Date date_n;
    public static String roles;
    public static String imageUser;
    public Connection conx;
    public Statement stm;

    public UserService() {
        conx = MyDB.getInstance().getConx();

    }

    @Override
    public void registre(User t) {
        // String subject = "Inscription au sein de ENERGYBOX";
        //String Object = " Bienvenu " + t.getUsername() + " A EnergyBox , Merci pour votre inscription";
        String hashed = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());

        try {
            String reqs = "INSERT INTO user(email, password, roles, username, num_tel, date_n,is_blocked,is_approved, image,qr_code,pathQr) VALUES('" + t.getEmail() + "','" + hashed + "','" + t.getRoles() + "','" + t.getUsername() + "','" + t.getNumtel() + "','" + t.getDate_n() +"','"+0+"','"+1+ "','" + t.getImage() + "','" + t.getQr_code() + "','" + t.getPathQr() + "')";
            Statement st = conx.createStatement();
            st.execute(reqs);
            //  sn.envoyer(t.getEmail(), subject, Object);

            System.out.println("Utilisateur ajoutée !");

        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    public void testEamil(String email) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rocketdevdb4", "root", "");
            String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                System.out.println("L'adresse e-mail existe dans la base de données.");
            } else {
                System.out.println("L'adresse e-mail n'existe pas dans la base de données.");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String login(User t) {
    String message="";
    String role = "";
    try {
        if (!t.getEmail().equals("") && !t.getPassword().equals("")) {
            String req = "SELECT * from user";
            PreparedStatement pst = conx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString("email").equals(t.getEmail()) && BCrypt.checkpw(t.getPassword(), rs.getString("password")) == true) {
                    if (rs.getBoolean("is_blocked")==false){
                        idUser = rs.getInt("id");
                        username = rs.getString("username");
                        roles = rs.getString("roles");
                        imageUser=rs.getString("image");
                        role = rs.getString("roles");
                        System.out.println(" Salut :" + username);
                        break;
                    }
                    else {
                        return message="Compte Desactivé ! Connectez Admin SVP !!!";
                    }
                } else {
                    System.err.println("Verifier vos donneés !");
                }
            }
            // Add a failure message if the loop did not find a match
            if (role.equals("")) {
                message = "Verifier vos donnes";
            }
        } else {
            System.out.println("champs vide");
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
    // Return the role variable if successful, otherwise return the message variable
    return role.equals("") ? message : role;
}


    public String loginQr(User t) {
        String role = "";
        try {
            if (!t.getEmail().equals("") && !t.getPassword().equals("")) {
                String req = "SELECT * from user";
                PreparedStatement pst = conx.prepareStatement(req);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    if (rs.getString("email").equals(t.getEmail()) && t.getPassword().equals(rs.getString("password"))) {
                        idUser = rs.getInt("id");
                        username = rs.getString("username");
                        roles = rs.getString("roles");
                        imageUser=rs.getString("image");
                        role = rs.getString("roles");
                        System.out.println(" Salut :" + username);
                    } else {
                        System.err.println("Verifier vos donneés !");
                    }
                }
            } else {
                System.out.println("champs vide");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return role;
    }

    @Override
    public void Supprimer(User t) {
        try {
            String req = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Utilisateur suprimée !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    public void modifier(User t) {
//        String hashed = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
//
//        try {
//            String reqs = "UPDATE user SET email=?,password=?,roles=?,username=?,num_tel=?, date_n=?, image=? WHERE id=?";
//            PreparedStatement pst = conx.prepareStatement(reqs);
//            
//            pst.setInt(1, t.getId());
//            pst.setString(2, t.getEmail());
//            pst.setString(3, t.getPassword());
//            pst.setString(4, t.getRoles());
//            pst.setString(5, t.getUsername());
//            pst.setInt(6, t.getNumtel());
//            pst.setDate(7, new java.sql.Date(t.getDate_n().getTime()));
//            //pst.setString(5, hashed);
//            pst.setString(8, t.getImage());
//            pst.executeUpdate();
//            System.out.println("Utilisateur Modifiée !");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    public void modifier(User t) {
//        String hashed = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
//
//        try {
//            String reqs = "UPDATE user SET email=?,username=?,num_tel=?, date_n=?  WHERE id=?";
//            PreparedStatement pst = conx.prepareStatement(reqs);
//
//            pst.setString(1, t.getEmail());
//            pst.setString(2, t.getUsername());
//            pst.setInt(3, t.getNumtel());
//            pst.setDate(4, new java.sql.Date(t.getDate_n().getTime()));
//            pst.setInt(5, t.getId());
//
//            int rowsUpdated = pst.executeUpdate();
//            if (rowsUpdated > 0) {
//                System.out.println("Utilisateur Modifié !");
//            } else {
//                System.out.println("Erreur lors de la modification de l'utilisateur");
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    @Override

public void modifier(User t) {
    String hashed = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());

    try {
        String reqs = "UPDATE user SET email=?,username=?,num_tel=?, date_n=?  WHERE id=?";
        PreparedStatement pst = conx.prepareStatement(reqs);

        pst.setString(1, t.getEmail());
        pst.setString(2, t.getUsername());
        pst.setInt(3, t.getNumtel());
        pst.setDate(4, new java.sql.Date(t.getDate_n().getTime()));
        pst.setInt(5, t.getId());

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Utilisateur Modifié !");
        } else {
            System.out.println("Erreur lors de la modification de l'utilisateur");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
    public List<User> Search(String t) {
        List<User> list1 = new ArrayList<>();
        List<User> list2 = afficherListe();
        list1 = (list2.stream().filter(c -> c.getUsername().startsWith(t)).collect(Collectors.toList()));

        return list1;
    }

    @Override
    public List<User> afficherListe() {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * from user";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next() && (rs.getInt("id") != idUser)) {
                list.add(new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getInt("num_tel"), rs.getDate("date_n"), rs.getString("roles"),
                        rs.getString("image"), rs.getBoolean("is_blocked"), rs.getBoolean("is_approved")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<User> afficherListeTable() {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * from user";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next() && (rs.getInt("id") != idUser)) {
                list.add(new User(rs.getString("email"), rs.getString("username"), rs.getDate("date_n"), rs.getString("image")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<User> afficherProfile() {
        List<User> list = new ArrayList<>();
        try {
            String req = "SELECT * from user";
            Statement st = conx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next() && (rs.getInt("id") != idUser)) {
                list.add(new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("username"), rs.getInt("num_tel"), rs.getDate("date_n"), rs.getString("roles"),
                        rs.getString("image"), rs.getBoolean("is_blocked"), rs.getBoolean("is_approved")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public User findById(int id) throws SQLException {
        User u = new User();
        try {
            String sql = "SELECT * FROM user WHERE id = " + id;
            Statement ste1 = conx.createStatement();
            ResultSet rs = ste1.executeQuery(sql);
            while (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setDate_n(rs.getDate("date_n"));
                u.setEmail(rs.getString("email"));
                u.setNumtel(rs.getInt("num_tel"));
                u.setImage(rs.getString("image"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    public void ban(User u) throws SQLException {

        String req = "UPDATE user SET is_blocked = 1 where id = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, u.getId());
        ps.executeUpdate();

    }

    public void unban(User u) throws SQLException {

        String req = "UPDATE user SET is_blocked = 0 where id = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, u.getId());
        ps.executeUpdate();

    }

    public List<User> triDate() {

        List<User> list1 = new ArrayList<>();
        List<User> list2 = afficherListe();

        list1 = list2.stream().sorted((o1, o2) -> o1.getDate_n().compareTo(o2.getDate_n())).collect(Collectors.toList());
        return list1;

    }

    public List<User> triEmail() {

        List<User> list1 = new ArrayList<>();
        List<User> list2 = afficherListe();

        list1 = list2.stream().sorted((o1, o2) -> o1.getEmail().compareTo(o2.getEmail())).collect(Collectors.toList());
        return list1;

    }

    public boolean emailExists(String email) {
        try {
            Connection conn = MyDB.getInstance().getConx();
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE email=?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean reset(Reset t) {
        long end = System.currentTimeMillis();
        try {
            String req = "SELECT * from reset where code=?";
            PreparedStatement pst = conx.prepareStatement(req);
            pst.setInt(1, t.getCode());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                long StartTime = Long.parseLong(rs.getString("timeMils"));
                long calT = end - StartTime;
                if (calT < 120000) {
                    email = rs.getString("email");
                    return true;
                } else {
                    String reqs = "DELETE FROM reset WHERE code=?";
                    PreparedStatement psts = conx.prepareStatement(reqs);
                    psts.setInt(1, t.getCode());
                    psts.executeUpdate();
                    System.err.println("Time OUT !! Code Introuvable.");
                    return false;
                }
            } else {
                System.err.println("Code Incorrect !");
                return false;
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;

        }
    }

    public void modifierMdp(User t) throws SQLException {
        String hashed = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());

        try {

            System.out.println(t.getEmail()+"+"+t.getPassword());
            String reqs = "UPDATE user SET password=? WHERE email=?";
            PreparedStatement pst = conx.prepareStatement(reqs);

            pst.setString(2, t.getEmail());
            pst.setString(1, hashed);
            System.out.println(t.getEmail()+"+"+hashed);

            pst.executeUpdate();

            System.out.println("Mot de passe modifié !");

        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du mot de passe :");
            System.out.println(ex.getMessage());
            conx.rollback(); // Annuler la transaction en cas d'erreur

        }
    }

}
