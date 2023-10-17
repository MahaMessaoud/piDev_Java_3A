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
 * @author azizo
 */
public interface CrudCours<Cours> {
    
    public void ajouter(Cours c);
    public void modifier(Cours c);
    public void supprimer(int id) throws SQLException;
    public List<Cours> Show();
    public List<Cours> Search(String t);
}
