/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie_cours;
import Entities.Cours;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author A
 */
public interface IServiceCategorie {
    public void AddCategorie(Categorie_cours cc);
    public  List<Categorie_cours> AfficherCategorie() throws SQLException;

    //Get By id === n7oto id kif n3ayto lel fonction yraj3lna categorie selon id heka
    public Categorie_cours getCategorieCoursById(int id);
      public void DeleteCours(Categorie_cours c) ;
}
