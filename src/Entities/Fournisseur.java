/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed Ben Abid
 */
public class Fournisseur {
    private int id; 
    private String nom_fournisseur;
    private String contact_fournisseur;
    private String email_fournisseur;
    private String adresse_fournisseur;

    public Fournisseur() {
    }
 public Fournisseur(int id) {
     this.id = id;
    }
 public Fournisseur(int id, String nom_fournisseur) {
        this.id = id;
        this.nom_fournisseur = nom_fournisseur;
    }

    public Fournisseur(int id, String nom_fournisseur, String contact_fournisseur, String email_fournisseur, String adresse_fournisseur) {
        this.id = id;
        this.nom_fournisseur = nom_fournisseur;
        this.contact_fournisseur = contact_fournisseur;
        this.email_fournisseur = email_fournisseur;
        this.adresse_fournisseur = adresse_fournisseur;
    }

    public Fournisseur(String nom_fournisseur, String contact_fournisseur, String email_fournisseur, String adresse_fournisseur) {
        this.nom_fournisseur = nom_fournisseur;
        
        
         this.contact_fournisseur = contact_fournisseur;
        this.email_fournisseur = email_fournisseur;
        this.adresse_fournisseur = adresse_fournisseur;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_fournisseur() {
        return nom_fournisseur;
    }

    public void setNom_fournisseur(String nom_fournisseur) {
        this.nom_fournisseur = nom_fournisseur;
    }

    public String getContact_fournisseur() {
        return contact_fournisseur;
    }

    public void setContact_fournisseur(String contact_fournisseur) {
        this.contact_fournisseur = contact_fournisseur;
    }

    public String getEmail_fournisseur() {
        return email_fournisseur;
    }

    public void setEmail_fournisseur(String email_fournisseur) {
        this.email_fournisseur = email_fournisseur;
    }

    public String getAdresse_fournisseur() {
        return adresse_fournisseur;
    }

    public void setAdresse_fournisseur(String adresse_fournisseur) {
        this.adresse_fournisseur = adresse_fournisseur;
    }

    @Override
    public String toString() {
        return nom_fournisseur ;
    }
    
    public String toStringName(){
    return nom_fournisseur+"\n";
}
  public Connection conx= MyDB.getInstance().getConx();
  
 public List<Fournisseur> getIdF()  {    

     String sql = "Select id , nom_fournisseur From fournisseur";
     List<Fournisseur> personnes = new ArrayList<Fournisseur>();
        try(PreparedStatement pstmt = conx.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Fournisseur p = new Fournisseur(rs.getInt("id"),
            rs.getString("nom_fournisseur"));
      //or rst.getInt(1)
                   
            personnes.add(p);
            
        }
         
        } catch (SQLException ex) {
            Logger.getLogger(Fournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }

return personnes; 


        
    

 }
}
