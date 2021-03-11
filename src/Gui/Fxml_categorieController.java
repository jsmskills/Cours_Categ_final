/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Categorie_cours;
import Entities.Cours;
import Service.ServiceCategorie;
import animatefx.animation.FadeOut;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.fxml.Initializable;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

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
    private JFXTextArea txt_desc_modif;
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
    private TextArea txt21;
    @FXML
    private DatePicker date1;
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
    private Button btnStat;
    @FXML
    private JFXTextField txt_add;

    /**
     * Initializes the controller class.
     */
    
     ServiceCategorie serviceCategorie;
    @FXML
    private Pane pnltable;
    private StackPane sp;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serviceCategorie = new ServiceCategorie();
        l.setCellFactory(new Callback<ListView<Categorie_cours>, ListCell<Categorie_cours>>() {
            @Override
            public ListCell<Categorie_cours> call(ListView<Categorie_cours> param) {
                return new XCell();
            }
        });

                 
        
    }    
    
        private void showConfirmation() {
        
 
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Cours...");
      alert.setHeaderText("Categorie a été ajouté avec succés!!!");
 
      // option != null.
      Optional<ButtonType> option = alert.showAndWait();
      
 
     
   }



    @FXML
    private void ajouterCategorie(ActionEvent event) {
        serviceCategorie.AddCategorie(new Categorie_cours(txt_add.getText()));
        showConfirmation();
        
     
    }
        
    
  
   //trie

    @FXML
    private void modif(ActionEvent event) {
    }

    
    ObservableList<Categorie_cours>oList;
    List<Categorie_cours>listView = new ArrayList<>();
    ListView<Categorie_cours>l = new ListView<>();
    @FXML
    private void afficherCategorie(ActionEvent event) throws SQLException {

        listView = serviceCategorie.AfficherCategorie();
        Categorie_cours coursCategorie_cours = new Categorie_cours();
        l.setItems(FXCollections.observableArrayList(listView));
        System.out.println("cloiii");
       
         new ZoomIn(pnltable).play();
            pnltable.toFront();
            

            
    }
    

    @FXML
    private void handleStatostoqie(ActionEvent event) {
    }

    
    
      class XCell extends ListCell<Categorie_cours> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Button button = new Button("(>)");
        String lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pnltable, button);
            
            HBox.setHgrow(pnltable, Priority.ALWAYS);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(lastItem + " : " + event);
                }
            });
            pnltable.getChildren().add(hbox);
        }
        

        @Override
        protected void updateItem(Categorie_cours item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);  // No text in label of super class
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
             
                setGraphic(hbox);
            }
        }
    }


}
