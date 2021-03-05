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
public class Workshop_Cours extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml_cours.fxml"));
        
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
        //Categorie_cours categorie_cours = new Categorie_cours("Web");
        //serviceCategorie.AddCategorie(categorie_cours);

       // Categorie_cours categorie_cours1 = new Categorie_cours("Security");
       // serviceCategorie.AddCategorie(categorie_cours1);

        //Categorie_cours categorie_cours2= new Categorie_cours("Mobile");
        //serviceCategorie.AddCategorie(categorie_cours2);
        
        //Fin Ajout Categorie
        
        
        //Recuperer categorie cours selon id
        Categorie_cours categorieCours = serviceCategorie.getCategorieCoursById(12);
                System.out.println("Categorie by id est =="+categorieCours);

        //Modifier le categorie cours courant
        categorieCours.setType("DEv");
        serviceCategorie.modifyCategorie(categorieCours);
        
        
        
        /*******CRUD COURS**********/
        ServiceCours s = new ServiceCours();
        
        //Affichage des cours 
         List<Cours> cours = s.AfficherCours() ;

          //Recuperer  cours selon id
        Cours coursById = s.getCoursById(36);
                System.out.println("Cours by id est =="+coursById);

        //Modifier le categorie cours courant
        coursById.setDescription("Python");
        coursById.setTitre("Web Django");
        coursById.setDuree("2010-05-06");
        s.modifyCours(coursById);
        
        
        Cours c1 = new Cours(34,1,"aaaaaaa","abcd","2heures"); 
        //sc.modifyCours(c1);
        //s.DeleteCours(c1);
        for (Cours c: cours){
            System.out.println("cous="+c.toString());
        }     
        
              
      
                launch(args);
    }
    
}
