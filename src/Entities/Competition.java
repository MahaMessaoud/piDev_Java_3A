/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Salima
 */
public class Competition {
       private int id;
    private String nomCompetition;
    private int fraisCompetition;
    private Date dateCompetition;
    private int nbrMaxInscrit;
    private String etatCompetition;
    private int nbrParticipants;

    public Competition() {
    }
public Competition( String nomCompetition, int fraisCompetition, Date dateCompetition, int nbrMaxInscrit,String etatCompetition) {
        this.nomCompetition = nomCompetition;
        this.fraisCompetition = fraisCompetition;
        this.dateCompetition = dateCompetition;
        this.nbrMaxInscrit = nbrMaxInscrit;
        this.etatCompetition=etatCompetition;
        }

    public Competition(String nomCompetition, int fraisCompetition, Date dateCompetition, int nbrMaxInscrit,String etatCompetition,int nbrParticipants) {
        this.nomCompetition = nomCompetition;
        this.fraisCompetition = fraisCompetition;
        this.dateCompetition = dateCompetition;
        this.nbrMaxInscrit = nbrMaxInscrit;
        this.nbrParticipants = nbrParticipants;
        this.etatCompetition=etatCompetition;
        this.nbrParticipants=nbrParticipants;
    }

    public Competition(int id, String nomCompetition, int fraisCompetition, Date dateCompetition, int nbrMaxInscrit,String etatCompetition, int nbrParticipants) {
        this.id = id;
        this.nomCompetition = nomCompetition;
        this.fraisCompetition = fraisCompetition;
        this.dateCompetition = dateCompetition;
        this.nbrMaxInscrit = nbrMaxInscrit;
        this.nbrParticipants = nbrParticipants;
        this.etatCompetition=etatCompetition;
        this.nbrParticipants=nbrParticipants;
    }

        public Competition(int id, String nomCompetition, int fraisCompetition, Date dateCompetition, int nbrMaxInscrit,String etatCompetition) {
        this.id = id;
        this.nomCompetition = nomCompetition;
        this.fraisCompetition = fraisCompetition;
        this.dateCompetition = dateCompetition;
        this.nbrMaxInscrit = nbrMaxInscrit;
        this.etatCompetition=etatCompetition;
        }
        
        
    public int getId() {
        return id;
    }

    public String getNomCompetition() {
        return nomCompetition;
    }

    public void setEtatCompetition(String etatCompetition) {
        this.etatCompetition = etatCompetition;
    }

    public String getEtatCompetition() {
        return etatCompetition;
    }

    public int getFraisCompetition() {
        return fraisCompetition;
    }

    public Date getDateCompetition() {
        return dateCompetition;
    }

    public int getNbrMaxInscrit() {
        return nbrMaxInscrit;
    }

    public int getNbrParticipants() {
        return nbrParticipants;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomCompetition(String nomCompetition) {
        this.nomCompetition = nomCompetition;
    }

    public void setFraisCompetition(int fraisCompetition) {
        this.fraisCompetition = fraisCompetition;
    }

    public void setDateCompetition(Date dateCompetition) {
        this.dateCompetition = dateCompetition;
    }

    public void setNbrMaxInscrit(int nbrMaxInscrit) {
        this.nbrMaxInscrit = nbrMaxInscrit;
    }

    public void setNbrParticipants(int nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    @Override
    public String toString() {
        return "Competition{" + "id=" + id + ", nomCompetition=" + nomCompetition + ", fraisCompetition=" + fraisCompetition + ", dateCompetition=" + dateCompetition + ", nbrMaxInscrit=" + nbrMaxInscrit + ", etatCompetition=" + etatCompetition + ", nbrParticipants=" + nbrParticipants + '}';
    }

    public String nomCompetition(){
        return nomCompetition;
    }

    
}
