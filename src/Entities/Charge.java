/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author Ahmed Ben Abid
 */
public class Charge {
    private int id;
    private String date_arrivage_charge;
    private int quantite_charge;
    private String materiel_id;
    private String fournisseur_id;

    public Charge() {
    }

    public Charge(String date_arrivage_charge, int quantite_charge, String materiel_id, String fournisseur_id) {
        this.date_arrivage_charge = date_arrivage_charge;
        this.quantite_charge = quantite_charge;
        this.materiel_id = materiel_id;
        this.fournisseur_id = fournisseur_id;
    }

    public Charge(int id, String date_arrivage_charge, int quantite_charge, String materiel_id, String fournisseur_id) {
        this.id = id;
        this.date_arrivage_charge = LocalDate.now().toString();
        this.quantite_charge = quantite_charge;
        this.materiel_id = materiel_id;
        this.fournisseur_id = fournisseur_id;
    }

    public Charge(String date_arrivage_charge, int quantite_charge) {
        this.date_arrivage_charge =  LocalDate.now().toString();
        this.quantite_charge = quantite_charge;
    }

    public Charge(int quantite_charge) {
        this.date_arrivage_charge = LocalDate.now().toString();
        this.quantite_charge = quantite_charge;
    }

    public Charge(int quantite_charge, String materiel_id, String fournisseur_id) {
                this.date_arrivage_charge = LocalDate.now().toString();

        this.quantite_charge = quantite_charge;
        this.materiel_id = materiel_id;
        this.fournisseur_id = fournisseur_id;
    }

    @Override
    public String toString() {
        return "Charge{" + "id=" + id + ", date_arrivage_charge=" + date_arrivage_charge + ", quantite_charge=" + quantite_charge + ", materiel_id=" + materiel_id + ", fournisseur_id=" + fournisseur_id + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_arrivage_charge() {
        return date_arrivage_charge;
    }

    public void setDate_arrivage_charge(String date_arrivage_charge) {
        this.date_arrivage_charge = date_arrivage_charge;
    }

    public int getQuantite_charge() {
        return quantite_charge;
    }

    public void setQuantite_charge(int quantite_charge) {
        this.quantite_charge = quantite_charge;
    }

    public String getMateriel_id() {
        return materiel_id;
    }

    public void setMateriel_id(String materiel_id) {
        this.materiel_id = materiel_id;
    }

    public String getFournisseur_id() {
        return fournisseur_id;
    }

    public void setFournisseur_id(String fournisseur_id) {
        this.fournisseur_id = fournisseur_id;
    }
    
    
    
    
    
}
