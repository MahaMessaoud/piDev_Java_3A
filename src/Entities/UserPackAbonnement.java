/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author wiem
 */
public class UserPackAbonnement {

    private int id;
    private String nomUser;
    private String typePack;
    private Date dateAchat;
    private Date dateFin;
    private String etatAbonnement;
    private String codePromo;
    private double montantAbonnement;

    public UserPackAbonnement(int id, String nomUser, String typePack, Date dateAchat, Date dateFin, String etatAbonnement, String codePromo, double montantAbonnement) {
        this.id = id;
        this.nomUser = nomUser;
        this.typePack = typePack;
        this.dateAchat = dateAchat;
        this.dateFin = dateFin;
        this.etatAbonnement = etatAbonnement;
        this.codePromo = codePromo;
        this.montantAbonnement = montantAbonnement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getTypePack() {
        return typePack;
    }

    public void setTypePack(String typePack) {
        this.typePack = typePack;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getEtatAbonnement() {
        return etatAbonnement;
    }

    public void setEtatAbonnement(String etatAbonnement) {
        this.etatAbonnement = etatAbonnement;
    }

    public String getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    public double getMontantAbonnement() {
        return montantAbonnement;
    }

    public void setMontantAbonnement(double montantAbonnement) {
        this.montantAbonnement = montantAbonnement;
    }

    @Override
    public String toString() {
        return "UserPackAbonnement{" + "id=" + id + ", nomUser=" + nomUser + ", typePack=" + typePack + ", dateAchat=" + dateAchat + ", dateFin=" + dateFin + ", etatAbonnement=" + etatAbonnement + ", codePromo=" + codePromo + ", montantAbonnement=" + montantAbonnement + '}';
    }
    
    
}
