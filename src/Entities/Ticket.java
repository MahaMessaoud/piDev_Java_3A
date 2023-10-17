/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Salima
 */
public class Ticket {
        private int id;
    private String description;
    private Competition competition;
    private int id_competition;
    //private String nomCompetition;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
  
public Ticket(String description,int id_competition)
{
         this.description = description;
         this.id_competition=id_competition;
}
    public Ticket(int id, String description, int id_competition) {
        this.id = id;
        this.description = description;
        this.id_competition= id_competition;
    }
    public Ticket(int id, String description,Competition competition)
    {
        this.id=id;
        this.description=description;
        this.competition=competition;
    }
        public Ticket(int id, String description,String nom)
    {
        this.id=id;
        this.description=description;
        this.competition.setNomCompetition(nom);
    }
    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }

    public int getId_competition() {
        return id_competition;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
 

    public String toStringg(){
        return "Ticket{" + "id=" + id + ", description=" + description + ", nomCompetition=" + competition.getNomCompetition() + '}';
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", description=" + description + ", idCompetition=" + id_competition + '}';
    }
    public String nomCompetition(){
        return competition.getNomCompetition();
    }

}
