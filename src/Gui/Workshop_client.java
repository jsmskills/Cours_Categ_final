/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie_cours;
import Entities.Cours;
import Service.ServiceCategorie;
import Service.ServiceCours;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author A
 */
public class Workshop_client extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("client_espace.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        
        /*******CRUD CATEGORIE*******/
        
        
        ServiceCategorie serviceCategorie = new ServiceCategorie();
        List<Categorie_cours>results = new ArrayList<>();
       
        //Debut Affichage Categorie 
        results = serviceCategorie.AfficherCategorie();
        for(int i =0 ;i<results.size()-1;i++) {
            i++;
            System.out.println("Categorie numero "+i+"est :"+results.get(i));
        }
        //Fin Affichage Cateogire
        
        //Debut Ajout Categorie
       

      

        
        
        //Fin Ajout Categorie
        
        
        //Recuperer categorie cours selon id
        Categorie_cours categorieCours = serviceCategorie.getCategorieCoursById(30);
                System.out.println("Categorie by id est =="+categorieCours);

        //Modifier le categorie cours courant
        categorieCours.setType("Android");
       serviceCategorie.modifyCategorie(categorieCours);
         System.out.println("Categorie by id est =="+categorieCours);

      
        
        /*******CRUD COURS**********/
        ServiceCours s = new ServiceCours();
        
        //Affichage des cours 
         List<Cours> cours = s.AfficherCours() ;

          //Recuperer  cours selon id
        Cours coursById = s.getCoursById(50);
                System.out.println("Cours by id est ="+coursById);

        //Modifier le cours courant
        coursById.setDescription("COURS modifié");
        coursById.setTitre("javafx");
        coursById.setDuree("2022-07-08");
        //s.modifyCours(coursById);
        
        
        Cours c1 = new Cours(49,1,"Android ","c++","3heures"); 
        //s.modifyCours(c1);
        System.out.println("Cours by id est ="+coursById);

        //s.DeleteCours(c1);
        
        for (Cours c: cours){
           ; System.out.println("cours="+c.toString());
           
        }     
        
              
      
                launch(args);
    }
    
}
