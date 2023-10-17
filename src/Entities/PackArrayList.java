/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author wiem
 */
public class PackArrayList {

    ArrayList<Pack> packs;

    public PackArrayList() {
    }

    public PackArrayList(ArrayList<Pack> packs) {
        this.packs = packs;
    }

    public void ajouterPack(Pack e) {
        this.packs.add(e);
    }

    public boolean rechercherPack(Pack e) {
        return this.packs.contains(e);
    }

    public boolean rechercherPack(String typePack) {
        for (int i = 0; i < this.packs.size(); i++) {
            if (this.packs.get(i).getTypePack().equals(typePack)) {
                return true;
            }
        }
        return false;
    }

    public boolean rechercherPack(int id) {
        for (int i = 0; i < this.packs.size(); i++) {
            if (this.packs.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }
}
