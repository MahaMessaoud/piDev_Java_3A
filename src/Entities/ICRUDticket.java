/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.List;

/**
 *
 * @author Salima
 */
public interface ICRUDticket<T> {
    public void ajouterr(T t);
    public List<T> afficherListe() ;
    public void modifierTicket(T t);
    public Ticket afficher(int id);
    public void supprimerTicket(int id);
    public String afficherDescription(int id);
}
