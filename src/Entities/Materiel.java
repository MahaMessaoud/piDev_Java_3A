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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ahmed Ben Abid
 */
public class Materiel {
    
    private int id; 
    private String nom_materiel;
    private String reference_materiel;
    private String date_maintenance;
    private int quantite_materiel;

    public Materiel() { 
    }

    public Materiel(int id, String nom_materiel, String reference_materiel,String date_maintenance, int quantite_materiel) {
        this.id = id;
        this.nom_materiel = nom_materiel;
        this.reference_materiel = reference_materiel;
        this.date_maintenance = date_maintenance;
        this.quantite_materiel = quantite_materiel;
    }

    public Materiel(String nom_materiel, String reference_materiel, String date_maintenance, int quantite_materiel) {
        this.nom_materiel = nom_materiel;
        this.reference_materiel = reference_materiel;
        this.date_maintenance = date_maintenance;
        this.quantite_materiel = quantite_materiel;
    }

    public Materiel(String nom_materiel, String reference_materiel) {
        this.nom_materiel = nom_materiel;
        this.reference_materiel = reference_materiel;
        this.date_maintenance = LocalDate.now().plusYears(1).toString();
        this.quantite_materiel = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_materiel() {
        return nom_materiel;
    }

    public void setNom_materiel(String nom_materiel) {
        this.nom_materiel = nom_materiel;
    }

    public String getReference_materiel() {
        return reference_materiel;
    }

    public void setReference_materiel(String reference_materiel) {
        this.reference_materiel = reference_materiel;
    }

    public String getDate_maintenance() {
        return date_maintenance;
    }

    
    public void setDate_maintenance(String date_maintenance){
        this.date_maintenance = date_maintenance;
    }

    @Override
    public String toString() {
        return nom_materiel ;
    }

    public int getQuantite_materiel() {
        return quantite_materiel;
    }

    public void setQuantite_materiel(int quantite_materiel) {
        this.quantite_materiel = quantite_materiel;
    }
      public Connection conx= MyDB.getInstance().getConx();

    
    public List<Materiel> getIdM()  {    

     String sql = "Select id From materiel";
     List<Materiel> personnes = new ArrayList<Materiel>();
        try(PreparedStatement pstmt = conx.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Materiel p = new Materiel();
        p.setId(rs.getInt("id"));//or rst.getInt(1)
                   
            personnes.add(p);
            
        }
         
        } catch (SQLException ex) {
            Logger.getLogger(Materiel.class.getName()).log(Level.SEVERE, null, ex);
        }

return personnes; 


        
    

 }
}
