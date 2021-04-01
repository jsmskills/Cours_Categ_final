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
import animatefx.animation.FadeOut;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import pidevclient.util.ZoomManager;

/**
 * FXML Controller class
 *
 * @author A
 */
public class Client_espaceController implements Initializable {
@FXML
    private Button btnAjout;
 
   
    @FXML
    private DatePicker date;

    private Label label;

    private ServiceCours serviceCours = new ServiceCours();
    private ServiceCategorie serviceCategorie = new ServiceCategorie();
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
    private Button btnca;
    @FXML
    private TableView<Cours> t;
    @FXML
    private TableColumn<?, ?> cours;
    @FXML
    private TableColumn<?, ?> dsc;
    @FXML
    private ImageView pic;
    @FXML
    private Pane paneButton1;

    TableColumn<Cours, Void> colBtn;
    TableColumn<Cours, Void> colSupprimerBtn;

    @FXML
    private Pane pnlModif;
    @FXML
    private JFXTextField txt_modif_cours;
    @FXML
    private JFXTextArea txt_desc_modif;

    Cours coursCourant = new Cours();
    @FXML
    private JFXButton btnModif;
    @FXML
    private JFXDatePicker date_picker_modif;
    @FXML
    private TextField txt_filter;
 
    @FXML
    private Pane pnlStat;
    @FXML
    private Pane p11;
    @FXML
    private Pane p31;
    @FXML
    private Button btnAjout1;
    @FXML
    private TextField txt11;
    @FXML
    private TextArea txt21;
    
    @FXML
    private ImageView pic1;
    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Colonne Modifier existant dans la table "historique"
        t.refresh();
       
   

 

        stackPane.setVisible(false);

      
     


        paneButton1.setVisible(true);
        try {
            obList = serviceCours.AfficherCours();
            p3.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Fxml_coursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        t.setItems(obList);
        // TODO
        filterMethod();

    }

    //Altert 
    private void showConfirmation() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cours...");
        alert.setHeaderText("Cours a été ajouté avec succés!!!");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

    }

   

   

    ObservableList<Cours> obList;
    List<Categorie_cours> obListcat;

    //trie
    @FXML
    private void affichercours(ActionEvent event) throws SQLException {

        t.refresh();
        obList.clear();
        obList = serviceCours.AfficherCours();

        if (event.getSource().equals(btnA)) {

         
            pane3.setVisible(true);
            new FadeOut(p1);
            p1.setVisible(false);
            new ZoomIn(pane3).play();
            pane3.toFront();
            stackPane.setVisible(false);
            pnlStat.setVisible(false);
            obList = serviceCours.AfficherCours();

            cours.setCellValueFactory(new PropertyValueFactory<>("titre"));
            dsc.setCellValueFactory(new PropertyValueFactory<>("Duree"));

            System.out.println("table list  contains= " + obList);
            obList = serviceCours.AfficherCours();
            t.setItems(obList);
        }

    }

     @FXML
    private void affichercat(ActionEvent event) throws IOException{

    

      

          Parent cat=FXMLLoader.load(getClass().getResource("fxml_categorie.fxml"));
       Scene scen=new Scene(cat);
       Stage s =new Stage();
       s.setScene(scen);
       s.show();
         
      

    }

    /**
     * Add Button
     *
     */
    //le bouton modifier de la table 
    Button btn;
    int id;

    Button btnSupprimer;
    Cours c = new Cours();

    public Client_espaceController() {
    }

    

   

    //Metier
    //Metier Relation
    public void filterMethod() {  //methode de recherche

        FilteredList<Cours> filteredList = new FilteredList<>(obList, b -> true);// filtrage des données existantes dans la table view

        txt_filter.textProperty().addListener((observable, oldValue, newValue) -> { // il y'aura une action en cliquant sur le txt filter 

            if (txt_filter.getText().isEmpty()) {
                //Reload button
               // il faut  l'ajouter car lorsqu'on termine de rechercher il y'aura les données eli mawjoudin melouel mais sans le bouton

            }
            filteredList.setPredicate(cours -> {
                if (newValue == null || newValue.isEmpty()) {
                    btn = new Button("Modifier");

                    return true;
                }

                //
                String lowerCaseFilter = newValue.toLowerCase(); // pour ne pas  avoir de probleme de maj et miniscule lors de la recherche

                if (cours.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else if (cours.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
                } else {

                    btn = new Button("Modifier");
                    return false;
                }

            });

        });

        //  pour le tri
        SortedList<Cours> sortedData = new SortedList<>(filteredList); // 

        sortedData.comparatorProperty().bind(t.comparatorProperty());

        t.setItems(sortedData);

    }

    @FXML
    private void filterAction(KeyEvent event) {

        filterMethod();
    }

    

   
    
    

}
