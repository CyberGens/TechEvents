/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_events;

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

/**
 * FXML Controller class
 *
 * @author Ghathenus
 */
public class SearchTypeController implements Initializable {

    @FXML
    private Button position;
    @FXML
    private Button Name;
    @FXML
    private Button Menu_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        position.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Search by position.fxml"));
                 position.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    );
      
        Name.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Search by name.fxml"));
                 Name.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    );
        Menu_btn.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Event.fxml"));
                 Menu_btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    );
        // TODO
    }    
    
}
