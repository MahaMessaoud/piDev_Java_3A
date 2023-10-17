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
public class Planning {
    
    private int id;
    private int cours_id;
    private Date date_planning;
    private String jour_planning;
    private int heure_planning;

    
    public Planning() {
    }

    public Planning(int id, int cours_id, Date date_planning, String jour_planning, int heure_planning) {
        this.id = id;
        this.cours_id = cours_id;
        this.date_planning = date_planning;
        this.jour_planning = jour_planning;
        this.heure_planning = heure_planning;
    }

    public Planning(int cours_id, Date date_planning, String jour_planning, int heure_planning) {
        this.cours_id = cours_id;
        this.date_planning = date_planning;
        this.jour_planning = jour_planning;
        this.heure_planning = heure_planning;
    }

    public Planning(Date date_planning, String jour_planning, int heure_planning) {
        this.date_planning = date_planning;
        this.jour_planning = jour_planning;
        this.heure_planning = heure_planning;
    }

    public Planning(int cours_id, int heure_planning) {
        this.cours_id = cours_id;
        this.heure_planning = heure_planning;
    }

    public Planning(int cours_id) {
        this.cours_id = cours_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCours_id() {
        return cours_id;
    }

    public void setCours_id(int cours_id) {
        this.cours_id = cours_id;
    }

    public Date getDate_planning() {
        return date_planning;
    }

    public void setDate_planning(Date date_planning) {
        this.date_planning = date_planning;
    }

    public String getJour_planning() {
        return jour_planning;
    }

    public void setJour_planning(String jour_planning) {
        this.jour_planning = jour_planning;
    }

    public int getHeure_planning() {
        return heure_planning;
    }

    public void setHeure_planning(int heure_planning) {
        this.heure_planning = heure_planning;
    }

    @Override
    public String toString() {
        return "Planning{" + "id=" + id + 
                ", cours_id=" + cours_id + 
                ", date_planning=" + date_planning + 
                ", jour_planning=" + jour_planning + 
                ", heure_planning=" + heure_planning + '}';
    }
    
    
}
