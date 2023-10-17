/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author lenovo
 */
public class Menu {
      private int id;
      private String categories;
      private String descriptionmenu;
      private String user_id;
      private int nbplats;
      
      
    public Menu() {
    }
    
    public Menu(String categories,String descriptionmenu) {
        this.categories = categories;
        this.descriptionmenu = descriptionmenu;
    }
    
    public Menu(int id,String categories,String descriptionmenu) {
        this.id = id;
        this.categories = categories;
        this.descriptionmenu = descriptionmenu;
    }
    
    public Menu(int nbplats,String categories, String user_id,String descriptionmenu) {
        this.categories = categories;
        this.user_id = user_id;
        this.descriptionmenu = descriptionmenu;
        this.nbplats = nbplats;
    }
    
    public Menu(int id,String categories,String descriptionmenu, String user_id,int nbplats) {
        this.id = id;
         this.categories = categories;
      this.user_id = user_id;
        this.descriptionmenu = descriptionmenu;
        this.nbplats = nbplats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbplats() {
        return nbplats;
    }

    public void setNbplats(int nbplats) {
        this.nbplats = nbplats;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescriptionmenu() {
        return descriptionmenu;
    }

    public void setDescriptionmenu(String descriptionmenu) {
        this.descriptionmenu = descriptionmenu;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", categories=" + categories + ", descriptionmenu=" + descriptionmenu + ", user_id=" + user_id + ", nbplats=" + nbplats + '}';
    }


}
