/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author A
 */
public class Categorie_cours {
    private int id;
    private String type;

    public Categorie_cours() {
    }

    public Categorie_cours(int id, String type) {
        this.id = id;
        this.type = type;
    }
    
    
    public Categorie_cours(String type) {
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    @Override
    public String toString() {
        return "Categorie_cours{" + "id=" + id + ", type=" + type + "}\n";
    }

    
    
    
}
