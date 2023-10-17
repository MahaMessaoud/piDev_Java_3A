/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Favoris;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;
import Entities.Plat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 *
 * @author lenovo
 */
public class FavorisServices {
    @FXML
    private ComboBox idPlatSelected;  
    public Connection conx;
    public Statement stm;

    public FavorisServices() {
        conx = MyDB.getInstance().getConx();

    }

   public void ajouterFavori(int idPlatF) {
    try {
        String req = "INSERT INTO favoris(idPlatF) VALUES (?)";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, idPlatF);
       // ps.setInt(2, iduser);
        ps.executeUpdate();
        System.out.println("Le plat a été ajouté aux favoris.");
    } catch (SQLException ex) {
        System.out.println("Une erreur s'est produite lors de l'ajout du plat aux favoris : " + ex.getMessage());
    }
}

    public List<Plat> getFavoris(int idUtilisateur) {
        List<Plat> favoris = new ArrayList<>();
        try {
            String req = "SELECT * FROM plat WHERE id IN (SELECT idPlat FROM favoris WHERE iduser = ?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Plat plat = new Plat();
                plat.setId(rs.getInt("id"));
                plat.setNom(rs.getString("nom"));
                plat.setPrix(rs.getDouble("prix"));
                plat.setCalories(rs.getString("calories"));
                plat.setDescription(rs.getString("description"));
                plat.setNbp(rs.getInt("nombre_plats"));
                favoris.add(plat);
            }
        } catch (SQLException ex) {
            System.out.println("Une erreur s'est produite lors de la récupération des favoris : " + ex.getMessage());
        }
        return favoris;
    }

    public ArrayList<Favoris> getFavorisList() throws SQLException {
        ArrayList<Favoris> favorisList = new ArrayList<>();
        String query = "SELECT * FROM favoris order by nbr desc";
        PreparedStatement stmt = conx.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Favoris favoris = new Favoris();
            favoris.setNomplat(rs.getString("Nomplat"));
            favoris.setNBR(rs.getInt("nbr"));
            favorisList.add(favoris);
        }
        rs.close();
        stmt.close();
        return favorisList;
    }

    public void ajouterFavori1(Favoris f) {
         try {
            String req = "INSERT INTO `favoris`(`idPlatF`,`Nomplat`, `nbr`) "
                    + "VALUES (?,?,?)";
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, f.getIdPlatF());
            ps.setString(2, f.getNomplat());
            ps.setInt(3, f.getNBR());
            ps.executeUpdate();
            System.out.println("Plat favoris ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public int getNBR(int id) throws SQLException {
    int nbr = 0;
    String req = "SELECT nbr FROM favoris WHERE idPlatF = ?";
    PreparedStatement ps = conx.prepareStatement(req);
    ps.setInt(1,id);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        nbr = rs.getInt("nbr");
    }
    return nbr;
}
       public void updateFavoris(int id, int nbr) throws SQLException {
    String req = "UPDATE favoris SET nbr = ? WHERE idPlatF = ?";
    PreparedStatement ps = conx.prepareStatement(req);
    ps.setInt(1, nbr);
    ps.setInt(2, id);
    ps.executeUpdate();
}
       
//Fonction appeler fl Recherche 
public List<Favoris> recuperer() throws SQLException {

    try {
        List<Favoris> favoris = new ArrayList<>();
        String req = "SELECT Nomplat FROM favoris";
        
        Statement st = conx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Favoris f = new Favoris();
            f.setNomplat(rs.getString("Nomplat"));
            favoris.add(f);
        }
        return favoris;
    } catch (SQLException ex) {
        Logger.getLogger(FavorisServices.class.getName()).log(Level.SEVERE, null, ex);
        throw ex; 
    }
}

}
