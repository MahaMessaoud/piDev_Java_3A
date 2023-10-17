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
public class Post {
    private int id ;
    private String title;
    private String details;
    private String image;
    private String date_post;
    private int rate;
    private double nbrRate;
    private int userId;

    public Post() {
    }

    public Post(int id, String title, String details, String image, String date_post, int rate,double nbrRate) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.image = image;
        this.date_post = date_post;
        this.rate = rate;
        this.nbrRate=nbrRate;
    }

    public Post(String title, String details, String image, String date_post, int rate, double nbrRate, int userId) {
        this.title = title;
        this.details = details;
        this.image = image;
        this.date_post = date_post;
        this.rate = rate;
        this.nbrRate = nbrRate;
        this.userId = userId;
    }

     public Post(int id,String title, String details, String image, String date_post, int rate, double nbrRate, int userId) {
         this.id=id;
        this.title = title;
        this.details = details;
        this.image = image;
        this.date_post = date_post;
        this.rate = rate;
        this.nbrRate = nbrRate;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
 

    public Post(String title, String details, String image, String date_post, int rate, double nbrRate) {
        this.title = title;
        this.details = details;
        this.image = image;
        this.date_post = date_post;
        this.rate = rate;
        this.nbrRate = nbrRate;
    }
    
    public void setNbrRate(double a){
    this.nbrRate=a;
    }
 public double getNbrRate() {
        return nbrRate;
    }

    public void setNbrRate(int nbrRate) {
        this.nbrRate = nbrRate;
    }
    public Post(String title, String details,String image, int userId) {
        this.title = title;
        this.details = details;
        this.image = image;
        this.date_post = LocalDate.now().toString();
        this.rate = 0;
        this.nbrRate=0;
        this.userId=userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate_post() {
        return date_post;
    }

    public void setDate_post(String date_post) {
        this.date_post = date_post;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", details=" + details + ", image=" + image + ", date_post=" + date_post + ", rate=" + rate + ", nbrRate=" + nbrRate + ", userId=" + userId + '}';
    }

   
    
    
    
    
    
    
    
}
