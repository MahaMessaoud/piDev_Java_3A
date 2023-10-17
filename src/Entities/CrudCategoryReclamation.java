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
public interface CrudCategoryReclamation<Categ> {
    
    public void ajouter(Categ ca);
    public void modifier(Categ ca);
    public void supprimer(int id) throws SQLException;
    public List<CategoryReclamation> Show();
    public List<CategoryReclamation> Search(String t);
}
