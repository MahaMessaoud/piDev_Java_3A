/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author maham
 */
public class ListPlann {

    private int id;
    private String jour_planning;
    private String nom_cours;
    private String nom_coach;
    private String description_cours;
    private int heure_planning;

    public ListPlann() {
    }

    public void setHeure_planning(int heure_planning) {
        this.heure_planning = heure_planning;
    }

    public int getHeure_planning() {
        return heure_planning;
    }

    public ListPlann(String nom_cours, String nom_coach, String description_cours, int heure_planning) {
        this.nom_cours = nom_cours;
        this.nom_coach = nom_coach;
        this.description_cours = description_cours;
        this.heure_planning = heure_planning;
    }

    public ListPlann(int id, String jour_planning, String nom_cours, String nom_coach, String description_cours) {
        this.id = id;
        this.jour_planning = jour_planning;
        this.nom_cours = nom_cours;
        this.nom_coach = nom_coach;
        this.description_cours = description_cours;
    }

    public ListPlann(String jour_planning, String nom_cours, String nom_coach, String description_cours) {
        this.jour_planning = jour_planning;
        this.nom_cours = nom_cours;
        this.nom_coach = nom_coach;
        this.description_cours = description_cours;
    }

    public int getId() {
        return id;
    }

    public String getJour_planning() {
        return jour_planning;
    }

    public String getNom_cours() {
        return nom_cours;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public String getDescription_cours() {
        return description_cours;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJour_planning(String jour_planning) {
        this.jour_planning = jour_planning;
    }

    public void setNom_cours(String nom_cours) {
        this.nom_cours = nom_cours;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public void setDescription_cours(String description_cours) {
        this.description_cours = description_cours;
    }

}
