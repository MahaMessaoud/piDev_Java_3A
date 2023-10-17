/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maham
 */
public interface ICrudSponsor<T> {

     public void ajouter(T t);
    public void modifier(T t);
    public void supprimer(T t);
   // public List<T> afficher();

    public List<T> afficherListe() throws SQLException;

}
