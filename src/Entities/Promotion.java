/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;


/**
 *
 * @author wiem
 */
public class Promotion {

    private int id;
    private String codePromotion;
    private double reductionPromotion;
    private Date dateExpiration;

    public Promotion() {
    }

    public Promotion(int id, String codePromotion, double reductionPromotion, Date dateExpiration) {
        this.id = id;
        this.codePromotion = codePromotion;
        this.reductionPromotion = reductionPromotion;
       this.dateExpiration = dateExpiration;
    }

    public Promotion(String codePromotion, double reductionPromotion, Date dateExpiration) {
        this.codePromotion = codePromotion;
        this.reductionPromotion = reductionPromotion;
        this.dateExpiration = dateExpiration;
    }

     

    public int getId() {
        return id;
    }

    public String getCodePromotion() {
        return codePromotion;
    }

        public double getReductionPromotion() {
        return reductionPromotion;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodePromotion(String codePromotion) {
        this.codePromotion = codePromotion;
    }

    public void setReductionPromotion(double reductionPromotion) {
        this.reductionPromotion = reductionPromotion;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", code promotion=" + codePromotion + ", reduction =" + reductionPromotion + ", dateExpiration=" + dateExpiration + '}';
    }

}
