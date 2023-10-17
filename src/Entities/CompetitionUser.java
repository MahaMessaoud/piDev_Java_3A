/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author maham
 */
public class CompetitionUser {
    
    private int id,idUser,idCompetition;

    public CompetitionUser(int id, int idUser, int idCompetition) {
        this.id = id;
        this.idUser = idUser;
        this.idCompetition = idCompetition;
    }

    public CompetitionUser() {
    }

    public CompetitionUser(int idUser, int idCompetition) {
        this.idUser = idUser;
        this.idCompetition = idCompetition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    @Override
    public String toString() {
        return "CompetitionUser{" + "id=" + id + ", idUser=" + idUser + ", idCompetition=" + idCompetition + '}';
    }
    
    
    
    
}
