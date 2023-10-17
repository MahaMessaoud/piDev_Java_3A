/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author azizo
 */
public class Activite {
    
    private int id;
    private String nom_activite;
    private String duree_activite;
    private String tenue_activite;
    private String difficulte_activite;
    private String image_activite;
    private String description_activite;

    
    
    
    public Activite() {
    }

    public Activite(int id, String nom_activite, String duree_activite, String tenue_activite, String difficulte_activite, String image_activite, String description_activite) {
        this.id = id;
        this.nom_activite = nom_activite;
        this.duree_activite = duree_activite;
        this.tenue_activite = tenue_activite;
        this.difficulte_activite = difficulte_activite;
        this.image_activite = image_activite;
        this.description_activite = description_activite;
    }

    public Activite(String nom_activite, String duree_activite, String tenue_activite, String difficulte_activite, String image_activite, String description_activite) {
        this.nom_activite = nom_activite;
        this.duree_activite = duree_activite;
        this.tenue_activite = tenue_activite;
        this.difficulte_activite = difficulte_activite;
        this.image_activite = image_activite;
        this.description_activite = description_activite;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_activite() {
        return nom_activite;
    }

    public void setNom_activite(String nom_activite) {
        this.nom_activite = nom_activite;
    }

    public String getDuree_activite() {
        return duree_activite;
    }

    public void setDuree_activite(String duree_activite) {
        this.duree_activite = duree_activite;
    }

    public String getTenue_activite() {
        return tenue_activite;
    }

    public void setTenue_activite(String tenue_activite) {
        this.tenue_activite = tenue_activite;
    }

    public String getDifficulte_activite() {
        return difficulte_activite;
    }

    public void setDifficulte_activite(String difficulte_activite) {
        this.difficulte_activite = difficulte_activite;
    }

    public String getImage_activite() {
        return image_activite;
    }

    public void setImage_activite(String image_activite) {
        this.image_activite = image_activite;
    }

    public String getDescription_activite() {
        return description_activite;
    }

    public void setDescription_activite(String description_activite) {
        this.description_activite = description_activite;
    }

    @Override
    public String toString() {
        return "Activite{" + "id=" + id + 
                ", nom_activite=" + nom_activite + 
                ", duree_activite=" + duree_activite + 
                ", tenue_activite=" + tenue_activite + 
                ", difficulte_activite=" + difficulte_activite + 
                ", image_activite=" + image_activite + 
                ", description_activite=" + description_activite + '}';
    }
    
    
    
}
