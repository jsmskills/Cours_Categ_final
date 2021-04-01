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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author A
 */
public class Fxml_categorieController implements Initializable {

    @FXML
    private Pane pnlModif;
    @FXML
    private JFXTextField txt_modif_cours;
 

    @FXML
    private JFXButton btnModif;
    @FXML
    private JFXDatePicker date_picker_modif;
    @FXML
    private Pane p11;
    @FXML
    private Pane p31;
    @FXML
    private Button btnAjout1;
    @FXML
    private TextField txt11;
   
  
    @FXML
    private ImageView pic1;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Pane p1;
    @FXML
    private Pane p3;
    @FXML
    private Button btnAjout;
    @FXML
    private Pane paneButton1;
    @FXML
    private Button btnA;

    @FXML
    private JFXTextField txt_add;
@FXML
   
    private TextField txt1;
  
    
@FXML
    private Label label;

    private ServiceCours serviceCours = new ServiceCours();
    private ServiceCategorie serviceCategorie = new ServiceCategorie();
    @FXML
    private Pane pane3;
    
   
  
   

    @FXML
    private Button btnca;
    @FXML
    private TableView<Categorie_cours> t;
    @FXML
    private TableColumn<?, ?> type;
  
  
   

    TableColumn<Categorie_cours, Void> colBtn;
    TableColumn<Categorie_cours, Void> colSupprimerBtn;

   

    

    Categorie_cours coursCourant = new Categorie_cours();
  
 
    @FXML
    private TextField txt_filter;
    
    @FXML
    private Pane pnlStat;
   
  
    
   
    @FXML
    private StackPane stackPane;
    /**
     * Initializes the controller class.
     */
    
     
    @FXML
    private Pane pnltable;
    @FXML
    private StackPane sp;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       t.refresh();
        colBtn = new TableColumn<>("Modifier");  // colonne modifier
        colBtn.setMinWidth(100);//min largeur 100 
        colBtn.setMaxWidth(100);//max largeur 100
        t.getColumns().add(colBtn);// zidna col

        addButtonModifToTable();

        stackPane.setVisible(false);

        colSupprimerBtn = new TableColumn<>("Supprimer");
        colSupprimerBtn.setMinWidth(100);
        colSupprimerBtn.setMaxWidth(100);
        t.getColumns().add(colSupprimerBtn);
        addButtonDeleteToTable();

        paneButton1.setVisible(true);
        try {
            obListcat = serviceCategorie.AfficherCategorie();
            p3.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Fxml_coursController.class.getName()).log(Level.SEVERE, null, ex);
        }
        t.setItems(obListcat);
        // TODO
        filterMethod();

    }    
    
        


 @FXML
    private void affichercat(ActionEvent event) throws IOException{

    

      

          Parent cat=FXMLLoader.load(getClass().getResource("fxml_cours.fxml"));
       Scene scen=new Scene(cat);
       Stage s =new Stage();
       s.setScene(scen);
       s.show();
         
      

    }
    @FXML
    private void ajouterCategorie(ActionEvent event) {
        Image img= new  Image("/icon_check.png");
        serviceCategorie.AddCategorie(new Categorie_cours(txt_add.getText()));
        
        Notifications notificationBuilder2 = Notifications.create()
                    .title("Ajoutée avec succés!")
                    .text("Une nouvelle catégorie est sauvegardée ")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(7))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked on");
                }
            });
                  notificationBuilder2.show();
        
     
    }
        
     ObservableList<Categorie_cours> obListcat;

   @FXML
    private void afficherCategorie(ActionEvent event) throws SQLException {

        t.refresh();
        obListcat.clear();
        obListcat = serviceCategorie.AfficherCategorie();

        if (event.getSource().equals(btnA)) {

            addButtonModifToTable();
            pane3.setVisible(true);
            new FadeOut(p1);
            p1.setVisible(false);
            new ZoomIn(pane3).play();
            pane3.toFront();
            stackPane.setVisible(false);
            pnlStat.setVisible(false);
            obListcat =serviceCategorie.AfficherCategorie();

            type.setCellValueFactory(new PropertyValueFactory<>("type"));
       

            System.out.println("table list  contains= " + obListcat);
            obListcat = serviceCategorie.AfficherCategorie();
            t.setItems(obListcat);
        }

    }
   Button btn;
    int id;

    public void addButtonModifToTable() {

        // comme ça on peut ajouter dans table view  un bouton 
        Callback<TableColumn<Categorie_cours, Void>, TableCell<Categorie_cours, Void>> cellFactory = new Callback<TableColumn<Categorie_cours, Void>, TableCell<Categorie_cours, Void>>() { // table predifinie 
            @Override
            public TableCell<Categorie_cours, Void> call(final TableColumn<Categorie_cours, Void> param) {

                final TableCell<Categorie_cours, Void> cell = new TableCell<Categorie_cours, Void>() {

                    {
                        // en cliquant sur le bouton

                        btn = new JFXButton("Modifier");
                        btn.setStyle("-fx-background-color:black");
                        btn.setStyle("-fx-pref-width: 200px;");
                        btn.setOnAction((ActionEvent event) -> {

                            //l'interface apres avoir cliqué sur le bouton
                            t.setVisible(false);
                           
                            new ZoomIn(pnlModif).play();
                            pnlModif.toFront();

                            //les donnees de la ligne selectionnées vont etre envoyé a la  pour faire modification
                            coursCourant = t.getSelectionModel().getSelectedItem();
                           
                               System.out.println(coursCourant.getType()+ "gggggggggg");
                            txt_modif_cours.setText(String.valueOf(coursCourant.getType()));
                          
                            id = coursCourant.getId();
                            System.out.println("id==" + id);

                           
                        });

                    }
                    //pour afficher le bouton Modifier dans la liste

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory); //pour chaque ajout de ligne ( on ajoute un bouton) 

    }
    Button btnSupprimer;
    Categorie_cours c = new Categorie_cours();

    public Fxml_categorieController() {
    }

    public void addButtonDeleteToTable() {

        Callback<TableColumn<Categorie_cours, Void>, TableCell<Categorie_cours, Void>> cellFactory = new Callback<TableColumn<Categorie_cours, Void>, TableCell<Categorie_cours, Void>>() {
            
            @Override
            public TableCell<Categorie_cours, Void> call(final TableColumn<Categorie_cours, Void> param) {

                final TableCell<Categorie_cours, Void> cell = new TableCell<Categorie_cours, Void>() {

                    {

                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            c = t.getSelectionModel().getSelectedItem();   // recuperer la ligne selectionné dans c.
                            try {
                                showConfirmation(c); // affichage d'alerte oui   ou non  
                            } catch (SQLException ex) {
                                Logger.getLogger(Fxml_categorieController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                    }

                    // pour afficher le bouton supprimer dans la liste 
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btnSupprimer);
                        }
                    }
                };
                return cell;
            }
        };
        // pour ajouter dasn chaque nouvelle ligne un bouton supprimer
        colSupprimerBtn.setCellFactory(cellFactory);

    }
private void showConfirmationModify() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("En Cours...");
        alert.setHeaderText("Categorie  a été modifié avec succés!!!");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {

        }
        //oke button is pressed

    }
    @FXML
    private void modif(ActionEvent event) throws SQLException {

        if (event.getSource().equals(btnModif)) { // lorsqu'on clique sur le bouton 

            addButtonModifToTable();

        }
        System.out.println("hello modify" + txt_modif_cours.getText());// 
        coursCourant.setType(txt_modif_cours.getText()); // courcourant est la ligne selectionné  => la modification sera effectué dans le titre ancien 
       
        // mm chose
        try {
            serviceCategorie.modifyCategorie(coursCourant); // modification dans la base de données

        } catch (Exception e) {
            System.out.println("exception" + e.getMessage());
        }
        showConfirmationModify();

    }

    //Metier
    //Metier Relation
    public void filterMethod() {  //methode de recherche

        FilteredList<Categorie_cours> filteredList = new FilteredList<>(obListcat, b -> true);// filtrage des données existantes dans la table view

        txt_filter.textProperty().addListener((observable, oldValue, newValue) -> { // il y'aura une action en cliquant sur le txt filter 

            if (txt_filter.getText().isEmpty()) {
                //Reload button
                addButtonModifToTable();// il faut  l'ajouter car lorsqu'on termine de rechercher il y'aura les données eli mawjoudin melouel mais sans le bouton

            }
            filteredList.setPredicate(categorie -> {
                if (newValue == null || newValue.isEmpty()) {
                    btn = new Button("Modifier");

                    return true;
                }

                //
                String lowerCaseFilter = newValue.toLowerCase(); // pour ne pas  avoir de probleme de maj et miniscule lors de la recherche

                if (categorie.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true;
               
                } else {

                    btn = new Button("Modifier");
                    return false;
                }
            });

        });

        //  pour le tri
        SortedList<Categorie_cours> sortedData = new SortedList<>(filteredList); // 

        sortedData.comparatorProperty().bind(t.comparatorProperty());

        t.setItems(sortedData);

    }

    @FXML
    private void filterAction(KeyEvent event) {

        filterMethod();
    }

 

    private void showConfirmation(Categorie_cours cours) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voullez vous vraiment supprimer??");
        alert.setContentText("La cours est tres efficace");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas de selection!");
        } else if (option.get() == ButtonType.OK) {
            serviceCategorie.DeleteCours(c);
            obListcat.clear();
            serviceCours.AfficherCours();
            addButtonModifToTable();
            addButtonDeleteToTable();
        } else if (option.get() == ButtonType.CANCEL) {
            this.label.setText("Exit!");
        } else {
            this.label.setText("-");
        }
    }



}
