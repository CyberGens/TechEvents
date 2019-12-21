/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_lodger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class ViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private Button retour;
     @FXML
    private ImageView img;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
        //HomeLocalController h=new HomeLocalController();
        Image image= new Image(HomeLocalController.l.getImg());
        img.setImage(image);
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
