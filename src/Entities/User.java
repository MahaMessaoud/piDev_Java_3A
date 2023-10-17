/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author maham
 */
public class User {

    public User() {
    }

    private int id;
    private String email;
    private String password;
    private String username;
    private Integer num_tel;
    private Date date_n;
    private String roles;

    public User(int id) {
        this.id = id;
    }
    private String image;
    private boolean is_blocked=false;
    private boolean is_approved=true;
    private String qr_code;
    private String pathQr;
    

    public User(String email, String password, String roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String email, String password, String username, Integer num_tel, Date date_n, String roles, String image, boolean is_blocked, boolean is_approved, String qr_code) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
        this.roles = roles;
        this.image = image;
        this.is_blocked = false;
        this.is_approved = true;
        this.qr_code = qr_code;
    }

    public User(String username, String email, Integer num_tel, Date date_n, String image, String password, String Roles, String qr_code,String pathQr) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
        this.image = image;
        this.roles = Roles;
        this.qr_code=qr_code;
        this.pathQr=pathQr;
    }
   public User(String username, String email, Integer num_tel, Date date_n, boolean is_blocked,boolean is_approved,String image, String password, String Roles, String qr_code,String pathQr) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
        this.is_blocked=false;
        this.is_approved=true;
        this.image = image;
        this.roles = Roles;
        this.qr_code=qr_code;
        this.pathQr=pathQr;
    }
    public User(String email, String username, Date date_n, String image) {
        this.email = email;
        this.username = username;
        this.date_n = date_n;
        this.image = image;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String email, String password, String username, Integer num_tel, Date date_n, String roles, String image, boolean is_blocked, boolean is_approved) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
        this.roles = roles;
        this.image = image;
        this.is_blocked = false;
        this.is_approved = true;
    }

    public User(String email, String password, String roles, String username, Integer num_tel, Date date_n, String image) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
        this.roles = roles;
        this.image = image;
    }

    public User(int id, String email, String password, String username, Integer num_tel, Date date_n, String roles, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
        this.roles = roles;
        this.image = image;
    }

    public User(int id, String email, String username, Integer num_tel, Date date_n, String roles, String image, boolean is_blocked, boolean is_approved) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
        this.roles = roles;
        this.image = image;
        this.is_blocked = false;
        this.is_approved = true;
    }

    public Integer getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(Integer num_tel) {
        this.num_tel = num_tel;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPathQr() {
        return pathQr;
    }

    public void setPathQr(String pathQr) {
        this.pathQr = pathQr;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNumtel() {
        return num_tel;
    }

    public void setNumtel(Integer numtel) {
        this.num_tel = numtel;
    }

    public Date getDate_n() {
        return date_n;
    }

    public void setDate_n(Date date_n) {
        this.date_n = date_n;
    }

    public boolean isIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(boolean is_blocked) {
        this.is_blocked =false;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = true;
    }

    public String getRoles() {
        return roles;
    }

    public User(int id,String email, String username, Integer num_tel, Date date_n) {
        this.id=id;
        this.email = email;
        this.username = username;
        this.num_tel = num_tel;
        this.date_n = date_n;
    }

   

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", email=" + email + ", password=" + password + ", username=" + username + ", num_tel=" + num_tel + ", date_n=" + date_n + ", roles=" + roles + ", image=" + image + ", is_blocked=" + is_blocked + ", is_approved=" + is_approved + '}';
    }

}
