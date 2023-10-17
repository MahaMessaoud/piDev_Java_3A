/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author azizo
 */
public class Reponse {
    
    private int id;
    private int reclamation_id;
    private String objet_reponse;
    private Date date_reponse;
    private String piece_jointe;
    private String contenu_reponse;

    
    public Reponse() {
    }

    public Reponse(int id, int reclamation_id, String objet_reponse, Date date_reponse, String piece_jointe, String contenu_reponse) {
        this.id = id;
        this.reclamation_id = reclamation_id;
        this.objet_reponse = objet_reponse;
        this.date_reponse = date_reponse;
        this.piece_jointe = piece_jointe;
        this.contenu_reponse = contenu_reponse;
    }

    public Reponse(int reclamation_id, String objet_reponse, Date date_reponse, String piece_jointe, String contenu_reponse) {
        this.reclamation_id = reclamation_id;
        this.objet_reponse = objet_reponse;
        this.date_reponse = date_reponse;
        this.piece_jointe = piece_jointe;
        this.contenu_reponse = contenu_reponse;
    }

    public Reponse(String objet_reponse, Date date_reponse, String piece_jointe, String contenu_reponse) {
        this.objet_reponse = objet_reponse;
        this.date_reponse = date_reponse;
        this.piece_jointe = piece_jointe;
        this.contenu_reponse = contenu_reponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }

    public String getObjet_reponse() {
        return objet_reponse;
    }

    public void setObjet_reponse(String objet_reponse) {
        this.objet_reponse = objet_reponse;
    }

    public Date getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(Date date_reponse) {
        this.date_reponse = date_reponse;
    }

    public String getPiece_jointe() {
        return piece_jointe;
    }

    public void setPiece_jointe(String piece_jointe) {
        this.piece_jointe = piece_jointe;
    }

    public String getContenu_reponse() {
        return contenu_reponse;
    }

    public void setContenu_reponse(String contenu_reponse) {
        this.contenu_reponse = contenu_reponse;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + 
                ", reclamation_id=" + reclamation_id + 
                ", objet_reponse=" + objet_reponse + 
                ", date_reponse=" + date_reponse + 
                ", piece_jointe=" + piece_jointe + 
                ", contenu_reponse=" + contenu_reponse + '}';
    }
    
    
    
}
