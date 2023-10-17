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
public class Cours {
    
    
    private int id;
    private String nom_cours;
    private float prix_cours;
    private String nom_coach;
    private int age_min_cours;
    private String description_cours;

    
    
    public Cours() {
    }

    public Cours(int id, String nom_cours, float prix_cours, String nom_coach, int age_min_cours, String description_cours) {
        this.id = id;
        this.nom_cours = nom_cours;
        this.prix_cours = prix_cours;
        this.nom_coach = nom_coach;
        this.age_min_cours = age_min_cours;
        this.description_cours = description_cours;
    }

    public Cours(String nom_cours, float prix_cours, String nom_coach, int age_min_cours, String description_cours) {
        this.nom_cours = nom_cours;
        this.prix_cours = prix_cours;
        this.nom_coach = nom_coach;
        this.age_min_cours = age_min_cours;
        this.description_cours = description_cours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public float getPrix_cours() {
        return prix_cours;
    }

    public void setPrix_cours(float prix_cours) {
        this.prix_cours = prix_cours;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public int getAge_min_cours() {
        return age_min_cours;
    }

    public void setAge_min_cours(int age_min_cours) {
        this.age_min_cours = age_min_cours;
    }

    public String getDescription_cours() {
        return description_cours;
    }

    public void setDescription_cours(String description_cours) {
        this.description_cours = description_cours;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + 
                ", nom_cours=" + nom_cours + 
                ", prix_cours=" + prix_cours + 
                ", nom_coach=" + nom_coach + 
                ", age_min_cours=" + age_min_cours + 
                ", description_cours=" + description_cours + '}';
    }
    
    
    
}
