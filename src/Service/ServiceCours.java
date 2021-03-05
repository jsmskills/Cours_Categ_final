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
            pst = cnx.prepareStatement(sql);
            pst.setInt(1, idFormation);
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
       
            Statement stm =cnx.createStatement();
        
        String query ="select * from `cours` ";
            
            ResultSet rst = stm.executeQuery(query);
                            Cours C = new Cours();

            while (rst.next()) {
                
                C.setId_formation(rst.getInt("id_formation"));
                C.setId_cours(rst.getInt("id_cours"));
                C.setTitre(rst.getString("Titre"));
                C.setDuree(rst.getString("duree"));
                C.setDescription(rst.getString("description"));
            
                obList.add(C);

            }
            
        return obList;
    }

@Override
    public void DeleteCours(Cours c) {
       int n=0;
		PreparedStatement st;
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
               Cours c = new Cours();
        try {
            String sql = "SELECT * FROM cours WHERE id_cours ="+id;
           Statement stm = cnx.createStatement();
            ResultSet rst= stm.executeQuery(sql);
            while(rst.next()) {
                c.setDescription(rst.getString("description"));
                c.setTitre(rst.getString("titre"));
                c.setId_formation(1);
                c.setId_cours(rst.getInt("id_cours"));
                
            }
            
        } catch (SQLException ex) {
                System.out.println("error"+ex.getMessage());
        }
        return c;
    }
    
}
