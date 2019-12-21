/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_lodger;

import entities_lodger.Local;
import entities.User;
import service_lodger.LocalService;
import service.UserService;
import com.itextpdf.text.BaseColor;
import gui.SignInController;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mail.Mailer;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class AjouterLocalController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    /**
     * Initializes the controller class.
     */ 
     @FXML
    private Label nw;
    @FXML
    private Label aw;
    @FXML
    private Label pw;
    @FXML
    private Label sw;
    @FXML
    private Label cw;
    @FXML
    private Label acde;
    
    @FXML
    private TextField nl;
    @FXML
    private TextField ad;
    @FXML
    private TextField sl;
    @FXML
    private TextField cl;
    @FXML
    private TextField pu;
    
    @FXML
    private Button v;
    @FXML
    private Button img;
    @FXML
    private Button a;
    @FXML
    private Button retour;
    private boolean ok;
    private String imagepath;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     v.setOnAction(e->{
          //Local(String nom, String adresse, float prix,float surface,int capacite)  
         Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null); 
          ok=true;
              if(nl.getText().length()<5)
              {
              ok=false;
              alert.setContentText("nom non valide");
                alert.show();   
              nw.setText("donnez un nom ayant\nau moins 5 caractères");
              }
              else nw.setText("");
              if(ad.getText().length()<10)
              {
              ok=false;
                alert.setContentText("adresse non valide");
                alert.show();   
              aw.setText("donnez une adresse ayant\nau moins 10 caractères");
              }
              else aw.setText("");
              if(pu.getText().length()==0||!pu.getText().matches("\\d+(\\.\\d+)?"))
              {
              ok=false;
                alert.setContentText("prix non valide");
                alert.show();   
              pw.setText("donnez un nombre\nvalide");
              }
              else pw.setText("");
              if(sl.getText().length()==0||!sl.getText().matches("\\d+(\\.\\d+)?"))
              {
              ok=false;
              alert.setContentText("surface non valide");
              alert.show(); 
              sw.setText("donnez un nombre\nvalide");
              }
              else sw.setText("");
              if(cl.getText().length()==0||!cl.getText().matches("\\d+"))
              {
              ok=false;
              alert.setContentText("capacité non valide");
                alert.show(); 
              cw.setText("donnez un entier\nvalide");
              }
              else cw.setText("");
              if(!SignInController.session.getRole().equals("Lodger"))
              {
              ok=false;
              acde.setText("Access Denied\n(Locataire only)");}
              if (ok==true)
              {
                  try {
              
              Local l= new Local(SignInController.session,nl.getText(),ad.getText(),Float.parseFloat(pu.getText())
                      ,Float.parseFloat(sl.getText()),Integer.parseInt(cl.getText()),imagepath) ;
              LocalService ls = new LocalService();
              ls.insert(l);
              Parent root ;
              root=FXMLLoader.load(getClass().getResource("GestionLocal.fxml"));
              v.getScene().setRoot(root);
                      
                      } catch (IOException ex) {
              Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
          }
                }
        
      });
         a.setOnAction(e->{
          nl.setText("");
          ad.setText("");
          pu.setText("");
          sl.setText("");
          cl.setText("");
        });  
         img.setOnAction(e->{
          FileChooser chooser = new FileChooser();
    chooser.setTitle("Open File");
    chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
    File file = chooser.showOpenDialog(new Stage());
    if(file != null) {
              
              try {
                  imagepath = file.toURI().toURL().toString();
              } catch (MalformedURLException ex) {
                  Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
              }
                  System.out.println(imagepath);
              
}img.setText(imagepath);
        }); 
            retour.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("GestionLocal.fxml"));
             retour.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             }); 
    }    
}
