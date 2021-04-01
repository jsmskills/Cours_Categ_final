/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Categorie_cours;
import Entities.Cours;
import Services.IServiceCategorie;
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
public class ServiceCategorie implements IServiceCategorie {

    Connection cnx;

    public ServiceCategorie() {
        cnx = Maconnexion.getInstance().getConnection();

    }

    @Override
    public void AddCategorie(Categorie_cours cc) {
        PreparedStatement pst;
        int idFormation = 1;
        try {
            String sql = "INSERT INTO Categorie_cours (type)VALUES(?)";
            pst = cnx.prepareStatement(sql);
            pst.setString(1, cc.getType());
            pst.executeUpdate();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    ObservableList<Categorie_cours> obList = FXCollections.observableArrayList();

    @Override
    public ObservableList<Categorie_cours> AfficherCategorie() throws SQLException {
 obList.clear();
            Statement stm =cnx.createStatement();
        
        String query ="select * from `categorie_cours` ";
            
            ResultSet rst = stm.executeQuery(query); // l'execution de la requete (rst => les lignes dans la table )
                            Cours C = new Cours();

            while (rst.next()) {
                
                int id = rst.getInt("id"); // recuperer de la base de données 
              
               String type = rst.getString("type"); // mm chose 
            
        
            
                obList.add(new Categorie_cours(id,type)); // mettre ces valeurs de type "observalble list" dans table view oblist 
                    System.out.println("oblist)===>"+obList);
            }
            
        return obList;
    }

    public void modifyCategorie(Categorie_cours c) {

        String sql2 = "UPDATE categorie_cours SET type=? WHERE id=?";
        try {

            PreparedStatement pstmt = cnx.prepareStatement(sql2);

            pstmt.setString(1, c.getType());
            pstmt.setInt(2, c.getId());

            if (pstmt.executeUpdate() > 0) {
                System.out.println("Parfait, Categorie cours a ete modifié avec success!!");
            } else {
                System.out.println("Echec de modification");
            }
            pstmt.close();

        } catch (SQLException ex) {
            System.out.println("execption modify categorie =" + ex.getMessage());
        }
    }

    @Override
    public Categorie_cours getCategorieCoursById(int id) {
        Categorie_cours c = new Categorie_cours();
        try {
            String sql = "SELECT * FROM categorie_cours WHERE id =" + id;
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(sql);
            while (rst.next()) {
                c.setType(rst.getString("type"));
                c.setId(rst.getInt("id"));

            }

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        return c;
    }

    @Override
    public void DeleteCours(Categorie_cours c) {
          int n=0;
		PreparedStatement st; // initialisation
		try {
			
			
			st= cnx.prepareStatement("DELETE FROM `categorie_cours` WHERE `id`=?");
			
			st.setInt(1,c.getId());
			
			
			n= st.executeUpdate();
			st.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
    }
}
