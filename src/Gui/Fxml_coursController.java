/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Cours;
import Service.ServiceCours;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author A
 */
public class Fxml_coursController implements Initializable {

    @FXML
    private Button btnAjout;
    @FXML
    private TextField txt1;
    @FXML
    private TextArea txt2;
    @FXML
    private DatePicker date;


    private ServiceCours serviceCours = new ServiceCours();
    @FXML
    private Pane pane3;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Pane p1;
    @FXML
    private Pane p3;
    @FXML
    private Button btnA;
    @FXML
    private TableView<Cours> t;
    @FXML
    private TableColumn<?, ?> cours;
    @FXML
    private TableColumn<?, ?> dsc;
    @FXML
    private Pane paneButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            obList = serviceCours.AfficherCours();
        } catch (SQLException ex) {
            Logger.getLogger(Fxml_coursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        t.setItems(obList);
        // TODO
    }    

    
    @FXML
    private void ajoutercours(ActionEvent event) {
        
        String titre = txt1.getText().toString();
        String description = txt2.getText().toString();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        
        LocalDate datePickerValue = date.getValue();
        
        String dateConverted = d.format(datePickerValue);
        Cours cours = new  Cours(titre,1,description,dateConverted);
        if(event.getSource().equals(btnAjout)) {
            
            serviceCours.AddCours(cours);
            JOptionPane.showConfirmDialog(null,"Cours a été enregistrer avec success!!",null,JOptionPane.CANCEL_OPTION);
        }        
    }


    
    ObservableList<Cours>obList;

   

    @FXML
    private void modifiercours(ActionEvent event) {
        
    }

    @FXML
    private void supprimercours(ActionEvent event) {
    }

    @FXML
    private void affichercours(ActionEvent event) throws SQLException {
          
        if(event.getSource().equals(btnA)) {
            pane3.setVisible(true);
            new FadeOut(p1);
           p1.setVisible(false);
            new ZoomIn(pane3).play();
            pane3.toFront();
            obList = serviceCours.AfficherCours();
            
            t.refresh();
            
            obList.clear();
            
            obList = serviceCours.AfficherCours();
            
           
            cours.setCellValueFactory(new PropertyValueFactory<>("titre"));
            dsc.setCellValueFactory(new PropertyValueFactory<>("Duree"));
            
            System.out.println("table list  contains= "+obList);
                t.setItems(obList);
            
            
        }
    }
    
    
}
