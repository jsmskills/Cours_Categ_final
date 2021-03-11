/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Cours;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author A
 */
public interface IServiceCours {
    
    
    public void AddCours(Cours c);
    public List<Cours>AfficherCours() throws SQLException; 
    public void DeleteCours(Cours c) ;
    public void modifyCours(Cours cours );
    public Cours getCoursById(int id);
        public int getNbrCours() ;
            public int getNbrEvaluation() ;

}
    

