/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lenovo
 */
public class Favoris {
     private int idFavoris;
     private int idPlatF;
      private int nbr;
      private String nomplat;

    public Favoris() {
    }

    public Favoris(int idFavoris, int idPlatF, int nbr, String nomplat) {
        this.idFavoris = idFavoris;
        this.idPlatF = idPlatF;
        this.nbr = nbr;
        this.nomplat = nomplat;
    }

    public String getNomplat() {
        return nomplat;
    }

    public void setNomplat(String nomplat) {
        this.nomplat = nomplat;
    }


    public int getIdFavoris() {
        return idFavoris;
    }

    public void setIdFavoris(int idFavoris) {
        this.idFavoris = idFavoris;
    }

    public int getIdPlatF() {
        return idPlatF;
    }

    public void setIdPlatF(int idPlatF) {
        this.idPlatF = idPlatF;
    }

    public int getNBR() {
        return nbr;
    }

    public void setNBR(int nbr) {
        this.nbr = nbr;
    }

    @Override
    public String toString() {
        return "Favoris{" + "idFavoris=" + idFavoris + ", idPlatF=" + idPlatF + ", NBR=" + nbr + '}';
    }

    
      
      
}
