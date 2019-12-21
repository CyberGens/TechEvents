/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Com;


import entite_Com.reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import service_Com.ReclamationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReclamationAdminController implements Initializable {
    private ReclamationService ReclamationService;
    private List<reclamation> lstRecalamation;  
    private ObservableList<reclamation> data;
      @FXML
    private Button id_imprimer;

 
    

    @FXML
    private ListView<reclamation> listeView;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService =new ReclamationService();
        lstRecalamation=ReclamationService.listerMesReclamtion();
        data = FXCollections.observableArrayList(lstRecalamation);
        
        
        
        listeView.setCellFactory(new Callback<ListView<reclamation>, ListCell<reclamation>>() {
            @Override
            public ListCell<reclamation> call(ListView<reclamation> param) {
                return new AssociationListCell();
            }
        });
        
        listeView.setItems(data);
        
        
        
      
       
  }  
    private void reteur(ActionEvent event) {
          try {
              System.out.println("kniii");
              
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/AccueilZliaa.fxml"));
            Parent TableViewParent = loader.load();
            Scene scene = new Scene(TableViewParent);
            Stage primaryStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    

   
  public static class AssociationListCell extends ListCell<reclamation> {
        private HBox content;
        private Text objet;
        private Text description;
        public static reclamation reclam;
        Button Repondre = new Button("Repondre");
        private ImageView imge;
        javafx.scene.image.Image im;
        public AssociationListCell() {
                    super();
            objet = new Text();
            description = new Text();
            
            
            imge = new ImageView();
            imge.setFitHeight(100);
            imge.setFitWidth(100);
           
           VBox vBox = new VBox(new HBox(new Label("Objet : "),objet),new HBox(new Label("Description : "),description),Repondre);
          
         
            
            content = new HBox(imge, vBox);
            content.setSpacing(20);
            
           
        }
  }
  
  
     @FXML
    void impression(ActionEvent event) {
        System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.listeView;
           job.printPage(root);
           job.endJob();

    }
    }
    
    
    
    
    private void loadDataFromDatabase() {
        List<reclamation> lista = new ArrayList<>();
        
        lista =ReclamationService.getAll();
        
        ObservableList<reclamation> lista2 = FXCollections.observableArrayList(lista);
        
        listeView.setCellFactory(new Callback<ListView<reclamation>, ListCell<reclamation>>() {
            @Override
            public ListCell<reclamation> call(ListView<reclamation> param) {
                return new AssociationListCell();
            }
        });
        
        listeView.setItems(lista2);
    }
    
    
    

  


  

 /* @FXML
    private void deleteaction(MouseEvent event) {
       reclamation r =  listeView.getSelectionModel().getSelectedItem();
        ReclamationService.delete(r.getId_Reclamation());
        
             loadDataFromDatabase();

        
    }*/
    
   //SELECT * FROM `reclammation` WHERE 1 
   private void modifier(ActionEvent event) throws IOException {
             Parent tableViewParent = FXMLLoader.load(getClass().getResource("AfficherReclamation.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
            System.out.println("SELECT * FROM `reclammation` WHERE etat=0");
    }

    
    
    
    
    
    
    
    
    
    
}


    

