/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Competition;
import Entity.ICRUDcompetition;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Salima
 */
public class CompetitionServices implements ICRUDcompetition<Competition> {
       public Connection conx;
       public Statement stm;

    public CompetitionServices() {
        conx = MyDB.getInstance().getConx();

    }


   @Override
    public void ajouterr(Competition c) {
    String req = "INSERT INTO competition(nom_competition,frais_competition,date_competition,nbr_max_inscrit, etat_competition,nbr_participant) "
                + "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setString(1, c.getNomCompetition());
            ps.setInt(2, c.getFraisCompetition());
            ps.setDate(3, new java.sql.Date(c.getDateCompetition().getTime()));
            ps.setInt(4, c.getNbrMaxInscrit());
            ps.setString(5,c.getEtatCompetition());
            ps.setInt(6,c.getNbrParticipants());
            ps.executeUpdate();
            System.out.println("Competition ajoutée");
        }   
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public List<Competition> afficherListe() throws SQLException {
        List<Competition> competitions = new ArrayList<>();
        String req = "SELECT * FROM competition";
    try {
        PreparedStatement ps = conx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
        Competition c = new Competition();
            c.setId(rs.getInt("id"));
            c.setNomCompetition(rs.getString("nom_competition"));
            c.setFraisCompetition(rs.getInt("frais_competition"));
            c.setDateCompetition(rs.getDate("date_competition"));
            c.setNbrMaxInscrit(rs.getInt("nbr_max_inscrit"));
            c.setEtatCompetition(rs.getString("etat_competition"));
            c.setNbrParticipants(rs.getInt("nbr_participant"));
            System.out.println("the added Competitions are "+c.toString());
            competitions.add(c);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return competitions;
}
    
    
@Override
public void modifierCompetition(Competition competition) {
    String req = "UPDATE competition SET nom_competition=?, frais_competition=?, date_competition=?, nbr_max_inscrit=?, etat_competition=?,nbr_participant=? WHERE id =?";
    try {
        PreparedStatement ps = conx.prepareStatement(req);
       
        ps.setString(1, competition.getNomCompetition());
        ps.setInt(2, competition.getFraisCompetition());
        ps.setDate(3, new java.sql.Date(competition.getDateCompetition().getTime()));
        ps.setInt(4, competition.getNbrMaxInscrit());
        ps.setString(5, competition.getEtatCompetition());
        ps.setInt(6, competition.getNbrParticipants());
        ps.setInt(7, competition.getId()); // spécifier l'ID de la compétition à mettre à jour
       
        ps.executeUpdate();
        System.out.println("Competition modifiée");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



       @Override
    public void supprimerCompetition(int id) throws SQLException {
    String req = "DELETE FROM competition WHERE id=?";
    try {
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Competition supprimée");      
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
 public Competition afficherCompetition(int id) {
    Competition cmp = null;
    String req = "SELECT * FROM competition WHERE id=?";
    try {
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cmp = new Competition();
            cmp.setId(rs.getInt("id"));
            cmp.setNomCompetition(rs.getString("nom_competition"));
            cmp.setFraisCompetition(rs.getInt("frais_competition"));
            cmp.setDateCompetition(rs.getDate("date_competition"));
            cmp.setNbrMaxInscrit(rs.getInt("nbr_max_inscrit"));
            cmp.setEtatCompetition(rs.getString("etat_competition"));
            cmp.setNbrParticipants(rs.getInt("nbr_participant"));
            System.out.println("La compétition cherchée est : " + cmp.toString());
        } else {
            System.out.println("La compétition avec l'ID donné n'existe pas, veuillez attendre l'admin pour qu'il l'ajoute");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return cmp;
}
 
public List<Competition> triDate() throws SQLException {

        List<Competition> list1 = new ArrayList<>();
        List<Competition> list2 = afficherListe();

        list1 = list2.stream().sorted((o1, o2) -> o1.getDateCompetition().compareTo(o2.getDateCompetition())).collect(Collectors.toList());
        return list1;

    }
    public List<Competition> triNom() throws SQLException {

        List<Competition> list1 = new ArrayList<>();
        List<Competition> list2 = afficherListe();

        list1 = list2.stream().sorted((o1, o2) -> o1.getNomCompetition().compareTo(o2.getNomCompetition())).collect(Collectors.toList());
        return list1;

    }


}
