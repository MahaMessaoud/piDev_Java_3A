/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Salima
 */
public interface ICRUDcompetition<P> {
   // public void ajouter(P p);
    public void ajouterr(P p);
    public List<P> afficherListe() throws SQLException ;
    public Competition afficherCompetition(int id);
    public void modifierCompetition(P p) throws SQLException;
    public void supprimerCompetition(int id) throws SQLException;
    
}

