/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author Ahmed Ben Abid
 */
public class Comment {
    private int id;
    private String decription;
    private String dateCom;
    private int post_id;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    

   

    public Comment(int id, String decription, String dateCom, int post_id,int user_id) {
        this.id = id;
        this.decription = decription;
        this.dateCom = dateCom;
        this.post_id = post_id;
        this.user_id=user_id;
    }

    public Comment(String decription,int user_id) {
        this.decription = decription;
        this.user_id=user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getDateCom() {
        return dateCom;
    }

    public void setDateCom(String dateCom) {
        this.dateCom = dateCom;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public Comment() {
    }

   

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", decription=" + decription + ", dateCom=" + dateCom + ", post_id=" + post_id + '}';
    }

    public Comment(String decription, int post_id,int user_id) {
        this.decription = decription;
        this.post_id = post_id;
        this.dateCom = LocalDate.now().toString();
        this.user_id=user_id;
    }
}
