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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed Ben Abid
 */
public class ChargeService implements ChargesCRUD<Charge> {

    public Connection conx = MyDB.getInstance().getConx();
    public Statement stm;

    public void createe(Charge f, int idf, int idm) throws SQLException {
        String sql = "INSERT INTO charge (fournisseur_id, materiel_id,quantite_charge,date_arrivage_charge) VALUES (?,?,?,?)";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {

            pstmt.setInt(1, idf);
            pstmt.setInt(2, idm);
            pstmt.setInt(3, f.getQuantite_charge());
            pstmt.setString(4, f.getDate_arrivage_charge());

            pstmt.executeUpdate();
            System.out.println("Charge affectée");
            MaterielService ms = new MaterielService();
            int oldqtmat = ms.getOneById(idm).getQuantite_materiel();
            int qtcharge = f.getQuantite_charge();
            Materiel newmat = ms.getOneById(idm);
            newmat.setQuantite_materiel(oldqtmat + qtcharge);
            ms.update(newmat, idm);
            System.out.println("Quantité affectée");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(Charge person) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Charge getOneById(int id) throws SQLException {
        String sql = "SELECT * FROM charge WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            FournisseurService fs = new FournisseurService();
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Charge person = new Charge();
                person.setId(rs.getInt("id"));
                person.setFournisseur_id(fs.getOneById(rs.getInt("fournisseur_id")).getNom_fournisseur());
                person.setMateriel_id(rs.getString("materiel_id"));
                person.setDate_arrivage_charge(rs.getString("date_arrivage_charge"));
                person.setQuantite_charge(rs.getInt("quantite_charge"));
                return person;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
   
    public Charge getOneByIdd(int id) throws SQLException {
        String sql = "SELECT * FROM charge WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {
            FournisseurService fs = new FournisseurService();
            MaterielService ms = new MaterielService();
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Charge person = new Charge();
                person.setId(rs.getInt("id"));
                person.setFournisseur_id(String.valueOf(fs.getOneById(rs.getInt("fournisseur_id")).getId()));
                person.setMateriel_id(String.valueOf(ms.getOneById(rs.getInt("materiel_id")).getId()));
                person.setDate_arrivage_charge(rs.getString("date_arrivage_charge"));
                person.setQuantite_charge(rs.getInt("quantite_charge"));
                return person;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public void update(Charge person, int id) throws SQLException {

        ChargeService chs = new ChargeService();
        MaterielService ms = new MaterielService();
        int idm = Integer.parseInt(chs.getOneById(id).getMateriel_id());
        int oldqtmat = ms.getOneById(idm).getQuantite_materiel();
        int qtcharge = chs.getOneById(id).getQuantite_charge();
        Materiel newmat = ms.getOneById(idm);
        newmat.setQuantite_materiel(oldqtmat - qtcharge);
        String sql = "UPDATE charge SET fournisseur_id = ?,materiel_id = ? ,date_arrivage_charge= ?,quantite_charge = ? WHERE id = ?";
        try (
                PreparedStatement pstmt = conx.prepareStatement(sql)) {

            pstmt.setString(1, person.getFournisseur_id());
            pstmt.setString(2, person.getMateriel_id());
            pstmt.setString(3, person.getDate_arrivage_charge());
            pstmt.setInt(4, person.getQuantite_charge());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();

            int idm2 = Integer.parseInt(getOneById(id).getMateriel_id());
            int oldqtmat2 = ms.getOneById(idm2).getQuantite_materiel();
            int qtcharge2 = person.getQuantite_charge();
            Materiel newmat2 = ms.getOneById(idm2);
            newmat2.setQuantite_materiel(oldqtmat2 + qtcharge2);

            ms.update(newmat, idm);
            ms.update(newmat2, idm2);
            System.out.println("charge modifié");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM charge  WHERE id = ?";
        try {
            PreparedStatement pstmt = conx.prepareStatement(sql);
            pstmt.setInt(1, id);
            ChargeService chs = new ChargeService();
            MaterielService ms = new MaterielService();
            int idm = Integer.parseInt(chs.getOneById(id).getMateriel_id());
            int oldqtmat = ms.getOneById(idm).getQuantite_materiel();
            int qtcharge = chs.getOneById(id).getQuantite_charge();
            Materiel newmat = ms.getOneById(idm);
            newmat.setQuantite_materiel(oldqtmat - qtcharge);
            ms.update(newmat, idm);

            pstmt.executeUpdate();
            System.out.println("Charge supprimée");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Charge> getAll() throws SQLException {
        String sql = "Select * From charge";
        PreparedStatement pstmt = conx.prepareStatement(sql);

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

        return personnes;
    }
     FournisseurService cs = new FournisseurService();
            MaterielService ms = new MaterielService();

 public List<Charge> getAll2() throws SQLException {
        String sql = "Select * From charge";
        PreparedStatement pstmt = conx.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        List<Charge> personnes = new ArrayList<Charge>();
        while (rs.next()) {
            Charge p = new Charge();
            p.setId(rs.getInt("id"));//or rst.getInt(1)
                     p.setFournisseur_id(cs.getOneById(rs.getInt("fournisseur_id")).getNom_fournisseur());//or rst.getInt(1)
                      p.setMateriel_id(ms.getOneById(rs.getInt("materiel_id")).getNom_materiel());//or rst.getInt(1)
                       p.setQuantite_charge(rs.getInt("quantite_charge"));//or rst.getInt(1)
                        p.setDate_arrivage_charge(rs.getString("date_arrivage_charge"));//or rst.getInt(1)
            personnes.add(p);
        }

        return personnes;
    }
}
