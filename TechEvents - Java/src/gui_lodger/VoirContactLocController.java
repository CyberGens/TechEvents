/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_lodger;

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
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class VoirContactLocController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label m;
    @FXML
    Label n;
    @FXML
    Label u;
    @FXML
    Button r;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    r.setOnAction(e->{  
            Parent root ;
         try {
             root=FXMLLoader.load(getClass().getResource("HomeLocal.fxml"));
             r.getScene().setRoot(root);
         } catch (IOException ex) {
             Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
         }
            
             }); 
        HomeLocalController h= new HomeLocalController();
        m.setText(HomeLocalController.l.getUser().getEmail());
        n.setText(HomeLocalController.l.getUser().getPhone());
        u.setText(HomeLocalController.l.getUser().getUsername());
            
            
            }    
    
}
