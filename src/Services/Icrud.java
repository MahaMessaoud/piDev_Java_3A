/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Menu;
import Entities.Plat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface Icrud<T>  {
     public void ajouter(T m);
    public void ajouterr(T m);
    public List<T> afficherListe() throws SQLException ;
    public List<T> afficherListe1(int id) throws SQLException ;
   // public void supprimer(int id);
  //  public void modifier(T m);
    public void Supprimer(T t) throws SQLException ;
    public void Modifier(T m) throws SQLException ;
    public int idmenu(String nom);

}
