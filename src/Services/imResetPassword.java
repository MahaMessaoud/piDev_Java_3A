/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reset;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author maham
 */
public class imResetPassword {

    public Connection conx;

    public imResetPassword() {
        conx = MyDB.getInstance().getConx();

    }

    // public boolean ajout(Reset t) {
    //        try {
    //            String req = "SELECT * from user where email=?";
    //            PreparedStatement pst = conx.prepareStatement(req);
    //            pst.setString(1, t.getEmail());
    //            ResultSet rs = pst.executeQuery();
    //            if (rs.next()) {
    //                String reqs = "INSERT INTO reset(email,code,timeMils)VALUES(?,?,?)";
    //                PreparedStatement psts = conx.prepareStatement(reqs);
    //                psts.setString(1, t.getEmail());
    //                psts.setInt(2, t.getCode());
    //                psts.setString(3, t.getTimeMils());
    //                psts.executeUpdate();
    //                return true;
    //            } else {
    //                return false;
    //            }
    //
    //        } catch (SQLException e) {
    //
    //            System.out.println("Error inserting reset data:");
    //            System.out.println(e.getMessage());
    //            e.printStackTrace();
    //            
    //        }
    //        return true;
//    public boolean ajout(Reset t) {
//        try {
//            String req = "SELECT * from user where email=?";
//            PreparedStatement pst = conx.prepareStatement(req);
//            pst.setString(1, t.getEmail());
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                String reqs = "INSERT INTO reset(email,code,timeMils)VALUES(?,?,?)";
//                PreparedStatement psts = conx.prepareStatement(reqs);
//                psts.setString(1, t.getEmail());
//                psts.setInt(2, t.getCode());
//                psts.setString(3, t.getTimeMils());
//                psts.executeUpdate();
//
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException e) {
//            System.out.println("Error inserting reset data:");
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//        return true;
//    }
public boolean ajout(Reset t) {
    try {
        String req = "SELECT * from user where email=?";
        PreparedStatement pst = conx.prepareStatement(req);
        pst.setString(1, t.getEmail());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String reqs = "INSERT INTO reset(email, code, timeMils) VALUES (?, ?, ?)";
            PreparedStatement psts = conx.prepareStatement(reqs);
            psts.setString(1, t.getEmail());
            psts.setInt(2, t.getCode());
            psts.setString(3, t.getTimeMils());
            psts.executeUpdate();

            return true;
        } else {
            return false;
        }
    } catch (SQLException e) {
        System.out.println("Error inserting reset data:");
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
    return false;
}

}
