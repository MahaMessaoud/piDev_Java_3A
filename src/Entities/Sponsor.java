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
public class Sponsor {

    public Integer id;
    public String nom_sponsor;
    public double donnation;
    public String image;

    public Sponsor() {
    }

    public Sponsor(Integer id, String nom_sponsor, double donnation, String image) {
        this.id = id;
        this.nom_sponsor = nom_sponsor;
        this.donnation = donnation;
        this.image = image;
    }

    public Sponsor(Integer id) {
        this.id = id;
    }

    public Sponsor(String nom_sponsor, double donnation, String image) {
        this.nom_sponsor = nom_sponsor;
        this.donnation = donnation;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getNom_sponsor() {
        return nom_sponsor;
    }

    public double getDonnation() {
        return donnation;
    }

    public String getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNom_sponsor(String nom_sponsor) {
        this.nom_sponsor = nom_sponsor;
    }

    public void setDonnation(double donnation) {
        this.donnation = donnation;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
      return  this.donnation+" "+this.id+" "+this.nom_sponsor+" "+this.image;
    }

}
