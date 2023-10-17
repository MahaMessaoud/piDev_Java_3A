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
public class CategoryReclamation {
    
    
    private int id;
    private String nom_category;
    private String description_category;
    private String priorite_category;

    
    public CategoryReclamation() {
    }

    public CategoryReclamation(int id, String nom_category, String description_category, String priorite_category) {
        this.id = id;
        this.nom_category = nom_category;
        this.description_category = description_category;
        this.priorite_category = priorite_category;
    }

    public CategoryReclamation(String nom_category, String description_category, String priorite_category) {
        this.nom_category = nom_category;
        this.description_category = description_category;
        this.priorite_category = priorite_category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_category() {
        return nom_category;
    }

    public void setNom_category(String nom_category) {
        this.nom_category = nom_category;
    }

    public String getDescription_category() {
        return description_category;
    }

    public void setDescription_category(String description_category) {
        this.description_category = description_category;
    }

    public String getPriorite_category() {
        return priorite_category;
    }

    public void setPriorite_category(String priorite_category) {
        this.priorite_category = priorite_category;
    }

    @Override
    public String toString() {
        return "CategoryReclamation{" + "id=" + id + 
                ", nom_category=" + nom_category + 
                ", description_category=" + description_category + 
                ", priorite_category=" + priorite_category + '}';
    }
    
    
}
