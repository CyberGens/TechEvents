/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Com;

import entite_Com.reclamation;

import entities.User;
import gui_lodger.AjouterLocalController;
import java.io.IOException;
import service_Com.ReclamationService;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javafx.util.Callback;
import jdk.nashorn.internal.runtime.regexp.joni.constants.CCSTATE;

/**
 * FXML Controller class
 *
 * @author Zliaa
 */
public class ReclamationUSERController implements Initializable {
    public static reclamation sr =new reclamation();
    ReclamationService reclamationService;
    List<reclamation> lstRecalamation;

    @FXML
    private TextField objet;

    private ObservableList<reclamation> data;
    javafx.scene.image.Image icon;
    ImageView imageView;

    @FXML
    private TextArea Description;

    public static int zz = 0;
    @FXML
    private TableView<reclamation> tablet;
    @FXML
    private TableColumn<reclamation, Integer> id_Reclamationt;
    @FXML
    private TableColumn<reclamation, String> objett;
    @FXML
    private TableColumn<reclamation, String> Descriptiont;
    @FXML
    private TableColumn<reclamation, Boolean> etatt;
    @FXML
    private Button Reclamert;
    @FXML
    private AnchorPane ab;
    @FXML
    private Button txtbuttonmod;
    @FXML
    private Button btnDel;
    @FXML
    private Button id_imprimer;
    @FXML
    private Button Recl_btn;
    @FXML
    private Button Respond_btn;
    @FXML
    private Button qst_btn;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Recl_btn.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("reclamationAdmin.fxml"));
                 Recl_btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ReclamationUSERController.class.getName()).log(Level.SEVERE, null, ex);
                
            }
    }    );
        Respond_btn.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("RepondreRexlamation.fxml"));
                 Respond_btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ReclamationUSERController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    );
        
        btn_back.setOnAction(e->{
        //(String id,String nom, String adresse, String prix, String surface,String capacite)
         
        try {
              Parent root ;
              root=FXMLLoader.load(getClass().getResource("../gui/welcome.fxml"));
              btn_back.getScene().setRoot(root);
                      
                      } catch (IOException ex) {
              Logger.getLogger(ReclamationUSERController.class.getName()).log(Level.SEVERE, null, ex);
          }    
      });
        qst_btn.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("QuestionE.fxml"));
                 qst_btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ReclamationUSERController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }   );
        ReclamationService rs = new ReclamationService();
        ArrayList<reclamation> pers = (ArrayList<reclamation>) rs.getAll();
        ObservableList<reclamation> obs = FXCollections.observableArrayList(pers);
        tablet.setItems(obs);

        id_Reclamationt.setCellValueFactory(new PropertyValueFactory<>("id_Reclamation"));
        objett.setCellValueFactory(new PropertyValueFactory<>("objet"));
        Descriptiont.setCellValueFactory(new PropertyValueFactory<>("Description"));
        etatt.setCellValueFactory(new PropertyValueFactory<>("etat"));

        /* txtbuttonmod.setOnAction(e -> {
            Reclamation r = (Reclamation) table.getSelectionModel().getSelectedItem();
           cc = r.getId11();
            table.getItems().clear();
            obs.clear();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
                txtbuttonmod.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
    }    */
 /*  Reclamert.setOtnAction((event) -> {
            ajoutReclamtion();
        });
 }*/
 /* private void ajoutReclamtion() {
        reclamation reclamtion = new reclamation(objet.getText(), Description.getText(), true,5);
        reclamationService.insertPST(reclamtion);
        
        objet.setText("");
        Description.setText("");
                           loadDataFromDatabase();

    }*/
 /* private void loadDataFromDatabase() {
        List<reclamation> lista = new ArrayList<>();
        
        lista = reclamationService.getAll();
        
        ObservableList<reclamation> lista2 = FXCollections.observableArrayList(lista);
        
        ListeView.setCellFactory(new Callback<ListView<reclamation>, ListCell<reclamation>>() {
            @Override
            public ListCell<reclamation> call(ListView<reclamation> param) {
                return new AssociationListCell();
            }
        });
        
        ListeView.setItems(lista2);
    }*/
 /*  private void deleteaction(MouseEvent event) {
       reclamation r =  ListeView.getSelectionModel().getSelectedItem();
        reclamationService.delete(r.getId_Reclamation());
        
        objet.setText("");
        Description.setText("");
                           loadDataFromDatabase();

        
    }*/

 /* private void modifier(KeyEvent event) {
        reclamation r =  ListeView.getSelectionModel().getSelectedItem();
        reclamation b=new reclamation();
      
          reclamationService.update(b,r.getId_Reclamation());
        
    }*/
    }
    

    @FXML
    private void ajoutrec(ActionEvent event) {
        reclamation reclamtion = new reclamation(objet.getText(), Description.getText(), true, 5);
        ReclamationService rs = new ReclamationService();
        rs.insertPST(reclamtion);
        ArrayList<reclamation> pers = (ArrayList<reclamation>) rs.getAll();
        ObservableList<reclamation> obs = FXCollections.observableArrayList(pers);
        tablet.setItems(obs);

        id_Reclamationt.setCellValueFactory(new PropertyValueFactory<>("id_Reclamation"));
        objett.setCellValueFactory(new PropertyValueFactory<>("objet"));
        Descriptiont.setCellValueFactory(new PropertyValueFactory<>("Description"));
        etatt.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }

    @FXML
    private void modiferr(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        reclamation r = tablet.getSelectionModel().getSelectedItem();
        reclamation r1 = rs.getById(r);
        sr =r1;
        objett.setText(r1.getObjet());
        Descriptiont.setText(r1.getDescription());

    }

    @FXML
    private void delrec(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        reclamation r = tablet.getSelectionModel().getSelectedItem();
        rs.delete(r.getId_Reclamation());
        ArrayList<reclamation> pers = (ArrayList<reclamation>) rs.getAll();
        ObservableList<reclamation> obs = FXCollections.observableArrayList(pers);
        tablet.setItems(obs);

        id_Reclamationt.setCellValueFactory(new PropertyValueFactory<>("id_Reclamation"));
        objett.setCellValueFactory(new PropertyValueFactory<>("objet"));
        Descriptiont.setCellValueFactory(new PropertyValueFactory<>("Description"));
        etatt.setCellValueFactory(new PropertyValueFactory<>("etat"));

    }

    @FXML
    private void impression(ActionEvent event) {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tablet;
           job.printPage(root);
           job.endJob();

    }
    }
    
    
    }


/*private static class AssociationListCell extends ListCell<reclamation> {

        private HBox content;
        private Text Objet;
        private Text description;
                        private Text id;
        private Text etat;
        
        private ImageView imge;
        javafx.scene.image.Image im;

        public AssociationListCell() {
            super();
            Objet = new Text();
            description = new Text();
            etat = new Text();
            id = new Text();
            
            VBox vBox = new VBox(new HBox(new HBox(new Label("Id : "), id),
                    new Label("Objet : "), Objet),
                    new HBox(new Label("Description : "), description),
                    new HBox(new Label("Etat :"), etat)
            );
            
            content = new HBox(vBox);
            content.setSpacing(20);
            
        }*/
 /*  @Override
        protected void updateItem(reclamation item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null && !empty) {                
                Objet.setText(item.getObjet());
                description.setText(item.getDescription());
                  id.setText(Integer.toString(item.getId_Reclamation()));
                if (item.isEtat()) {
                    etat.setText("En Cours");
                } else {
                    etat.setText("Resolu");
                }
                
                setGraphic(content);
            } else {
                setGraphic(null);
            }
        }
    }
   
}*/
