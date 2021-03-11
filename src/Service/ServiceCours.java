/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Categorie_cours;
import Entities.Cours;
import Services.IServiceCours;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author A
 */
public class ServiceCours implements IServiceCours{
    
    Connection cnx;

    public ServiceCours() {
        cnx=Maconnexion.getInstance().getConnection();
    }
    

    @Override
    public void AddCours(Cours c) {
        PreparedStatement pst;
        int idFormation = 1;
        try {
            String sql = "INSERT INTO cours (id_formation,Titre,description,duree)VALUES(?,?,?,?)";
            pst = cnx.prepareStatement(sql);// intialisation de la requete
            pst.setInt(1, idFormation);// pour mettre dans la bd l'id_formation dans la 1ere colonne 
            pst.setString(2, c.getTitre());
            pst.setString(3,c.getDescription());
            pst.setString(4, c.getDuree());
            pst.executeUpdate();
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
        

    
    
    ObservableList<Cours>obList = FXCollections.observableArrayList();
    @Override
    public ObservableList<Cours> AfficherCours() throws SQLException {
        obList.clear();
            Statement stm =cnx.createStatement();
        
        String query ="select * from `cours` ";
            
            ResultSet rst = stm.executeQuery(query); // l'execution de la requete (rst => les lignes dans la table )
                            Cours C = new Cours();

            while (rst.next()) {
                
                int id_formation = rst.getInt("id_formation"); // recuperer de la base de données 
               int id_cours = rst.getInt("id_cours"); //mm chose
               String titre = rst.getString("Titre"); // mm chose 
                String duree  = rst.getString("duree"); // mm chose 
                String desc = rst.getString("description"); // mm chose 
            
                obList.add(new Cours(id_cours,id_formation,titre,desc,duree)); // mettre ces valeurs de type "observalble list" dans table view oblist 
                    System.out.println("oblist)===>"+obList);
            }
            
        return obList;
    }

@Override
    public void DeleteCours(Cours c) {
       int n=0;
		PreparedStatement st; // initialisation
		try {
			
			
			st= cnx.prepareStatement("DELETE FROM `cours` WHERE `id_cours`=?");
			
			st.setInt(1,c.getId_cours());
			
			
			n= st.executeUpdate();
			st.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
    } 
    

   @Override
    public void modifyCours(Cours c ) {
        
        String sql2="UPDATE cours SET Titre=?,description=?,duree=? WHERE id_cours=?";
        try{
            
             PreparedStatement pstmt = cnx.prepareStatement(sql2);
            
            pstmt.setString(1,c.getTitre());
            pstmt.setString(2,c.getDescription());
            pstmt.setString(3,c.getDuree());
            pstmt.setInt(4,c.getId_cours());
          if(pstmt.executeUpdate() >0) {
                System.out.println("Parfait,  cours a ete modifié avec success!!");
            }
            else 
                System.out.println("Echec de modification");
            pstmt.close();
            
       }catch (SQLException ex) {
           System.out.println("Modify cours =="+ex.getMessage());
       }
    }
        //
    
    @Override
    public Cours getCoursById(int id) {
               Cours c = new Cours(); // vide
        try {
            String sql = "SELECT * FROM cours WHERE id_cours ="+id; //pour recuperer le cours selon son id = > si on met "getcoursbyid(5)" le cours de id 5 sera affciché
           Statement stm = cnx.createStatement();
            ResultSet rst= stm.executeQuery(sql);
            while(rst.next()) {
                c.setDescription(rst.getString("description"));// mettre dans c 
                c.setTitre(rst.getString("titre"));
                c.setId_formation(1);
                c.setId_cours(rst.getInt("id_cours"));
                
            }
            
        } catch (SQLException ex) {
                System.out.println("error"+ex.getMessage());
        }
        return c;
    }

  @Override
    public int getNbrCours() {
        String sql="SELECT COUNT(*) FROM cours";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1); // nombre de cours selon l'id (1ere collone)
                        }
        }catch(Exception e) {
            e.printStackTrace(); 
        }
        return countIdRec;
    }
    
  @Override
    public int getNbrEvaluation() {
        String sql="SELECT COUNT(*) FROM evaluation";
        int v=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           v= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return v;
    }


    
}
