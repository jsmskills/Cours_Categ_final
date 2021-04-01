/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;





import Entities.*;
import Service.*;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.ZoomIn;
import com.email.durgesh.Email;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import pidevclient.util.ZoomManager;

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
    private Button btnStat;
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
    private DatePicker date1;
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
        colBtn = new TableColumn<>("Modifier");  // colonne modifier
        colBtn.setMinWidth(100);//min largeur 100 
        colBtn.setMaxWidth(100);//max largeur 100
        t.getColumns().add(colBtn);// zidna col

        addButtonModifToTable();

        stackPane.setVisible(false);

        colSupprimerBtn = new TableColumn<>("Supprimer");// pou
        colSupprimerBtn.setMinWidth(100);
        colSupprimerBtn.setMaxWidth(100);
        t.getColumns().add(colSupprimerBtn);
        addButtonDeleteToTable();

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

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cours...");
        alert.setHeaderText("Cours a été ajouté avec succés!!!");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

    }

    private void showConfirmationModify() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cours...");
        alert.setHeaderText("Cours a été modifié avec succés!!!");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {

        }
        //oke button is pressedoblist

    }
    
 

    @FXML
    private void ajoutercours(ActionEvent event) {
        
       
        
        
        Image img= new  Image("/icon_check.png");

        String titre = txt1.getText().toString();
        String description = txt2.getText().toString();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate datePickerValue = date.getValue();

        String dateConverted = d.format(datePickerValue);
        Cours cours = new Cours(titre, 1, description, dateConverted);
        if (event.getSource().equals(btnAjout)) {

            serviceCours.AddCours(cours);
            showConfirmation();
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajouté avec succés!")
                    .text("Un nouveau cours est sauvegardé ")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(7))
                    .position(Pos.TOP_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked on");
                }
            });
                  notificationBuilder.show();
                    
        }

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

            addButtonModifToTable();
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
    private void afficherclient(ActionEvent event) throws IOException{

    

      

          Parent cat=FXMLLoader.load(getClass().getResource("client_espace.fxml"));
       Scene scen=new Scene(cat);
       Stage s =new Stage();
       s.setScene(scen);
       s.show();
         
      

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

    public void addButtonModifToTable() {

        // comme ça on peut ajouter dans table view  un bouton 
        Callback<TableColumn<Cours, Void>, TableCell<Cours, Void>> cellFactory = new Callback<TableColumn<Cours, Void>, TableCell<Cours, Void>>() { // table predifinie 
            @Override
            public TableCell<Cours, Void> call(final TableColumn<Cours, Void> param) {

                final TableCell<Cours, Void> cell = new TableCell<Cours, Void>() {

                    {
                        // en cliquant sur le bouton

                        btn = new JFXButton("Modifier");
                        btn.setStyle("-fx-background-color:black");
                        btn.setStyle("-fx-pref-width: 200px;");
                        btn.setOnAction((ActionEvent event) -> {

                            //l'interface apres avoir cliqué sur le bouton
                            t.setVisible(false);
                            pic.setVisible(false);
                            new ZoomIn(pnlModif).play();
                            pnlModif.toFront();

                            //les donnees de la ligne selectionnées vont etre envoyé lel page jdida pour faire le modification
                            coursCourant = t.getSelectionModel().getSelectedItem();
                            txt_modif_cours.setText(String.valueOf(coursCourant.getTitre()));
                            txt_desc_modif.setText(String.valueOf(coursCourant.getDescription()));
                            System.out.println("duree ==>" + coursCourant.getDuree());
                            date_picker_modif.setValue(LocalDate.now());
                            id = coursCourant.getId_cours();
                            System.out.println("id==" + id);

                            //txt_mod_desc.setText(vehicule.getEtat());
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
    Cours c = new Cours();

    public Fxml_coursController() {
    }

    public void addButtonDeleteToTable() {

        Callback<TableColumn<Cours, Void>, TableCell<Cours, Void>> cellFactory = new Callback<TableColumn<Cours, Void>, TableCell<Cours, Void>>() {
            @Override
            public TableCell<Cours, Void> call(final TableColumn<Cours, Void> param) {

                final TableCell<Cours, Void> cell = new TableCell<Cours, Void>() {

                    {

                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer = new Button("Supprimer");
                        btnSupprimer.setOnAction((ActionEvent event) -> {

                            c = t.getSelectionModel().getSelectedItem();   // recuperer la ligne selectionné dans c.
                            try {
                                showConfirmation(c); // affichage d'alerte oui   ou non  
                            } catch (SQLException ex) {
                                Logger.getLogger(Fxml_coursController.class.getName()).log(Level.SEVERE, null, ex);
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
 
    @FXML
    private void modif(ActionEvent event) throws SQLException {

        if (event.getSource().equals(btnModif)) { // lorsqu'on clique sur le bouton 

            addButtonModifToTable();

        }
        System.out.println("hello modify" + txt_modif_cours.getText() + "," + txt_desc_modif.getText());// 
        coursCourant.setTitre(txt_modif_cours.getText()); // courcourant est la ligne selectionné  => la modification sera effectué dans le titre ancien 
        coursCourant.setDescription(txt_desc_modif.getText()); //mm chose
        coursCourant.setDuree(date_picker_modif.getValue().toString()); // mm chose
        try {
            serviceCours.modifyCours(coursCourant); // modification dans la base de données

        } catch (Exception e) {
            System.out.println("exception" + e.getMessage());
        }
        showConfirmationModify();
        
        //email
        try{ 
            Email email= new Email("eskills.learning2021@gmail.com", "allahisone777");
            email.setFrom("eskills.learning2021@gmail.com", "E Skills");
            email.setSubject("Modification du cours");
            email.setContent("vous avez modifié un cours", "text/html");
            email.addRecipient("emna.ferchichi@esprit.tn");
            email.send();
            
        }catch (Exception e){
            e.printStackTrace();
            
        }
        //email
        
        

        
    }

    //Metier
    //Metier Relation
    public void filterMethod() {  //methode de recherche

        FilteredList<Cours> filteredList = new FilteredList<>(obList, b -> true);// filtrage des données existantes dans la table view

        txt_filter.textProperty().addListener((observable, oldValue, newValue) -> { // il y'aura une action en cliquant sur le txt filter 

            if (txt_filter.getText().isEmpty()) {
                //Reload button
                addButtonModifToTable();// il faut  l'ajouter car lorsqu'on termine de rechercher il y'aura les données eli mawjoudin melouel mais sans le bouton

            }
            filteredList.setPredicate(cours -> {
                if (newValue == null || newValue.isEmpty()) {
                    btn = new Button("Modifier");
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

    @FXML
    private void handleStatostoqie(ActionEvent event) {

        if (event.getSource().equals(btnStat)) {
            stackPane.setVisible(true);
            p1.setVisible(false);
            new ZoomIn(pnlStat).play();
            pnlStat.toFront();// pour ouvrir l'interface de statiqtique avec zoomin
            System.out.println("click");

            NumberAxis xAxis = new NumberAxis();//instance des axes des x
            CategoryAxis yAxis = new CategoryAxis();// axe des y 

            XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(); // l'axe des x contient les nombres des moyennes des cours  et l'axe des y contient les String
            Pane pane = new Pane(); //
            pane.setPrefSize(600, 500); //size de pane hauteur et largeur
            BarChart<String, Number> bchart = new BarChart<String, Number>(yAxis, xAxis); //type graphique de statistique sous forme des bars
            bchart.setPrefSize(550, 450);
            bchart.setTitle("Summary");
            xAxis.setLabel("Values"); // nom des axes x
            xAxis.setTickLabelRotation(45);
            yAxis.setTickLabelRotation(45);
            yAxis.setLabel("Menus"); // nom des y 
            XYChart.Series series1 = new XYChart.Series(); // pour l'ensemble des points 
            XYChart.Series series2 = new XYChart.Series();
            //

            int nbrEvaluation = 6;
            series1.setName("Nombre des cours ");
            series2.setName("Nombre des cours évalués");

            ServiceCours serviceCours = new ServiceCours();
            double totale = serviceCours.getNbrCours() + nbrEvaluation;
            series1.getData().add(new XYChart.Data("Cours", serviceCours.getNbrCours()));
            series1.getData().add(new XYChart.Data("Cours Evalués", (serviceCours.getNbrEvaluation() / totale) * 100));
            series1.getData().add(new XYChart.Data("Evaluation", (serviceCours.getNbrCours() + serviceCours.getNbrEvaluation() / totale) * 100));

            //DecimalFormat df = new DecimalFormat("0.00");
            //Moyenne_Agqae_Input.setText(String.valueOf(df.format(econtroleemploye.getMoyenneAge()/totale)));S
            System.out.println("cours nombre==>" + series1);

            bchart.getData().addAll(series1);
            pane.getChildren().add(bchart);

            // aff des stat dans l'interface
            stackPane.getChildren().add(bchart);

            new ZoomManager(stackPane, bchart, series1);
        }

    }

    private void showConfirmation(Cours cours) throws SQLException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Suppression");
        alert.setHeaderText("Voullez vous vraiment supprimer??");
        alert.setContentText("La cours est tres efficace");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {
            this.label.setText("pas de selection!");
        } else if (option.get() == ButtonType.OK) {
            serviceCours.DeleteCours(cours);
            obList.clear();
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
