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
public class Cours {
    private int id_cours;
    private int id_formation;
    private String titre;
    private String description;
    private String duree;
    

    public Cours() {
    }

    public Cours(int id_cours, int id_formation, String titre, String description, String duree) {
        this.id_cours = id_cours;
        this.id_formation = id_formation;
        this.titre = titre;
        this.description = description;
        this.duree = duree;
    }

    public Cours(int id_cours, String titre, int id_formation,String description, String duree) {
        this.id_formation = id_formation;
        this.titre = titre;
        this.id_formation = id_formation;
        this.description = description;
        this.duree = duree;
    }

    public Cours(String titre,int id_formation, String description, String duree) {
        this.titre = titre;
        this.description = description;
        this.id_formation = id_formation;
        this.duree = duree;
    }
    
    

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
    
    
        public int getId_cours() {
        return id_cours;
    }

    public void setId_cours(int id_cours) {
        this.id_cours = id_cours;
    }

    public int getId_formation() {
        return id_formation;
    }

    public void setId_formation(int id_formation) {
        this.id_formation = id_formation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
       
        
     
    

    @Override
    public String toString() {
        return "Cours{" + "id_cours=" + id_cours + ", id_formation=" + id_formation + ", titre=" + titre + ", description=" + description +"}\n";
    }
    
    
    
}
