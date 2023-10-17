/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author lenovo
 */
public class Reservation {
      private int id;
      private int idplat_id;
      private Date date;
      private String user_id;
      
   public Reservation() {
    }
    
    public Reservation(Date date) {
        this.date = date;
    }
    
    public Reservation(int id,Date date) {
        this.id = id;
        this.date = date;
    }
   
    public Reservation(int idplat_id,Date date,String user_id) {
        this.id = id;
        this.date = date;
        this.idplat_id = idplat_id;
        this.user_id = user_id;
    }
    
    public Reservation(int id,int idplat_id,Date date,String user_id) {
        this.id = id;
        this.date = date;
        this.idplat_id = idplat_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdplat_id() {
        return idplat_id;
    }

    public void setIdplat_id(int idplat_id) {
        this.idplat_id = idplat_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", idplat_id=" + idplat_id + ", date=" + date + ", user_id=" + user_id + '}';
    }
      
}
