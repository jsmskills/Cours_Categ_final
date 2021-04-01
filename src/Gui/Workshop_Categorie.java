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
public class Workshop_Categorie extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //  Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentCategorie.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("fxml_categorie.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ServiceCategorie cs = new ServiceCategorie();
        List<Categorie_cours> categories = cs.AfficherCategorie();
        for (Categorie_cours cc : categories) {
            System.out.println("categories =" + cc.toString());
        }

        launch(args);
    }

}
