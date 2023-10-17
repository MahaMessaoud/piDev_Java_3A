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
public interface CrudReponse<Rep> {
    
    public void ajouter(Rep r);
    public void modifier(Rep r);
    public void supprimer(int id) throws SQLException;
    public List<Reponse> Show();
    public List<Reponse> Search(String t);
}
