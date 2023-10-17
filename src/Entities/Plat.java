/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
//pushi
/**
 *
 * @author lenovo
 */
public class Plat {
      private int id;
      private int categories_id;
      private String nom;
      private Double prix;
      private String description;
      private String calories;
      private String etat;
      private String image;
      private int nbp;
      
    public Plat() {
    }
    
    public Plat(String nom,double prix,String description,String calories,String etat,String image,int nbp) {
        this.nom = nom;
        this.prix = prix;
         this.description = description;
          this.calories = calories;
           this.etat = etat;
            this.image = image;
            this.nbp = nbp;
    }
    
    public Plat(int id,String nom,double prix,String description,String calories,String etat,String image,int nbp) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.calories = calories;
        this.etat = etat;
        this.image = image;
        this.nbp = nbp;
    }

    public Plat(int id, int categories_id, String nom, double prix, String description, String calories, String etat, String image, int nbp) {
        this.id = id;
        this.categories_id = categories_id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.calories = calories;
        this.etat = etat;
        this.image = image;
        this.nbp = nbp;
    }

    public Plat(int id, String nom, Double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

 
    public Plat(int id, String nom, double prix, String calories ,String image) {
         this.id = id;
        this.nom = nom;
        this.prix = prix;
         this.calories = calories;
          this.image = image;
    }

    public Plat(int id, String nom, double prix, String calories ,String description,int nbp ,String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.calories = calories;
        this.description = description;
        this.nbp = nbp;
        this.image = image;
    }
     public Plat(int id, String nom, double prix, String calories ,String description,int nbp ,String image,String etat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.calories = calories;
        this.description = description;
        this.nbp = nbp;
        this.image = image;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbp() {
        return nbp;
    }

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }

    @Override
    public String toString() {
        return "Plat{" + "id=" + id + ", categories_id=" + categories_id + ", nom=" + nom + ", prix=" + prix + ", description=" + description + ", calories=" + calories + ", etat=" + etat + ", image=" + image + ", nbp=" + nbp + '}';
    }
   
      
}





