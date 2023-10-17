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
public class Reclamation {
    
    private int id;
    private int category_id;
    private String objet_reclamation;
    private String texte_reclamation;
    private Date date_reclamation;
    private String nom_user_reclamation;
    private String email_user_reclamation;

    
    public Reclamation() {
    }

    public Reclamation(int id, int category_id, String objet_reclamation, String texte_reclamation, Date date_reclamation, String nom_user_reclamation, String email_user_reclamation) {
        this.id = id;
        this.category_id = category_id;
        this.objet_reclamation = objet_reclamation;
        this.texte_reclamation = texte_reclamation;
        this.date_reclamation = date_reclamation;
        this.nom_user_reclamation = nom_user_reclamation;
        this.email_user_reclamation = email_user_reclamation;
    }

    public Reclamation(int category_id, String objet_reclamation, String texte_reclamation, Date date_reclamation, String nom_user_reclamation, String email_user_reclamation) {
        this.category_id = category_id;
        this.objet_reclamation = objet_reclamation;
        this.texte_reclamation = texte_reclamation;
        this.date_reclamation = date_reclamation;
        this.nom_user_reclamation = nom_user_reclamation;
        this.email_user_reclamation = email_user_reclamation;
    }

    public Reclamation(String objet_reclamation, String texte_reclamation, Date date_reclamation, String nom_user_reclamation, String email_user_reclamation) {
        this.objet_reclamation = objet_reclamation;
        this.texte_reclamation = texte_reclamation;
        this.date_reclamation = date_reclamation;
        this.nom_user_reclamation = nom_user_reclamation;
        this.email_user_reclamation = email_user_reclamation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getObjet_reclamation() {
        return objet_reclamation;
    }

    public void setObjet_reclamation(String objet_reclamation) {
        this.objet_reclamation = objet_reclamation;
    }

    public String getTexte_reclamation() {
        return texte_reclamation;
    }

    public void setTexte_reclamation(String texte_reclamation) {
        this.texte_reclamation = texte_reclamation;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public String getNom_user_reclamation() {
        return nom_user_reclamation;
    }

    public void setNom_user_reclamation(String nom_user_reclamation) {
        this.nom_user_reclamation = nom_user_reclamation;
    }

    public String getEmail_user_reclamation() {
        return email_user_reclamation;
    }

    public void setEmail_user_reclamation(String email_user_reclamation) {
        this.email_user_reclamation = email_user_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + 
                ", category_id=" + category_id + 
                ", objet_reclamation=" + objet_reclamation + 
                ", texte_reclamation=" + texte_reclamation + 
                ", date_reclamation=" + date_reclamation + 
                ", nom_user_reclamation=" + nom_user_reclamation + 
                ", email_user_reclamation=" + email_user_reclamation + '}';
    }
    
    
    
}
