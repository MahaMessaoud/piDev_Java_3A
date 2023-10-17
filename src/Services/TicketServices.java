/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CompetitionUser;
import Entity.Competition;
import Entity.ICRUDticket;
import Entity.Ticket;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Salima
 */
public class TicketServices implements ICRUDticket<Ticket> {

    public Connection conx;
    public Statement stm;

    public TicketServices() {
        conx = MyDB.getInstance().getConx();

    }

    @Override
    public void ajouterr(Ticket t) {
        // Vérifier que la clé étrangère est valide
        int competitionId = t.getId_competition();
        if (!existeCompetition(competitionId)) {
            System.out.println("La compétition n'existe pas");
            return;
        }

        // Insérer la ligne dans la table ticket
        String req = "INSERT INTO ticket(competition_id,description_ticket) "
                + "VALUES (?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, competitionId);
            ps.setString(2, t.getDescription());
            ps.executeUpdate();
            System.out.println("Ticket ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ReserverCompTicket(CompetitionUser c) {
        String req = "INSERT INTO competition_user(competition_id,user_id)"
                + "VALUES (?,?)";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, c.getIdCompetition());
            ps.setInt(2, c.getIdUser());
ps.executeUpdate();
            System.out.println("Competition reservee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
    }

// Vérifier si une compétition existe dans la table competition
    private boolean existeCompetition(int competitionId) {
        String req = "SELECT COUNT(*) FROM competition WHERE id=?";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, competitionId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int existeTicket(int competitionId) {
        String req = "SELECT id FROM ticket WHERE competition_id=?";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, competitionId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("id");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    @Override
    public List<Ticket> afficherListe() {
        List<Ticket> tickets = new ArrayList<>();
        String req = "SELECT * FROM ticket";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getInt("id"));
                t.setDescription(rs.getString("description_ticket"));
                t.setId_competition(rs.getInt("competition_id"));
                // récupérer la compétition associée à ce ticket
                int competitionId = rs.getInt("competition_id");
                String competitionReq = "SELECT * FROM competition WHERE id = ?";
                PreparedStatement competitionPs = conx.prepareStatement(competitionReq);
                competitionPs.setInt(1, competitionId);
                ResultSet competitionRs = competitionPs.executeQuery();
                if (competitionRs.next()) {
                    Competition c = new Competition();
                    c.setId(competitionId);
                    c.setNomCompetition(competitionRs.getString("nom_competition"));
                    c.setFraisCompetition(competitionRs.getInt("frais_competition"));
                    c.setDateCompetition(competitionRs.getDate("date_competition"));
                    c.setNbrMaxInscrit(competitionRs.getInt("nbr_max_inscrit"));
                    c.setEtatCompetition(competitionRs.getString("etat_competition"));
                    c.setNbrParticipants(competitionRs.getInt("nbr_participant"));
                    t.setCompetition(c);
                }

                System.out.println("La liste des tickets sont: " + t.toStringg());
                tickets.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tickets;
    }

    @Override
    public void modifierTicket(Ticket t) {
        String req = "UPDATE ticket SET competition_id=?,description_ticket=? WHERE id =?";
        try {
            PreparedStatement ps = conx.prepareStatement(req);

            ps.setInt(1, t.getCompetition().getId());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getId()); // spécifier l'ID de la compétition à mettre à jour      
            ps.executeUpdate();
            System.out.println("Ticket modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerTicket(int id) {
        String req = "DELETE FROM ticket WHERE id=?";
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Ticket supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Ticket afficher(int id) {
        String req = "SELECT t.id, t.description_ticket, c.nom_competition FROM ticket t JOIN competition c ON t.competition_id=c.id WHERE t.id=?";
        Ticket t = null;
        try {
            PreparedStatement ps = conx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t = new Ticket();
                t.setId(rs.getInt("id"));
                t.setDescription(rs.getString("description_ticket"));
                Competition competition = new Competition();
                competition.setNomCompetition(rs.getString("nom_competition"));
                t.setCompetition(competition);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //System.out.println(t.toStringg());
        return t;
    }

    @Override
    public String afficherDescription(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
