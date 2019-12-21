/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_events;

import gui_Com.ReclamationUSERController;
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
public class EventController implements Initializable {

    @FXML
    private Button CreateEve_Button;
    @FXML
    private Button Event_Button;
    @FXML
    private Button Plan_Event_Button;
    @FXML
    private Button Search_Event;
    @FXML
    private Button btn_back;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  Event_Button.setOnAction(e -> {
       Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("View_Events.fxml"));
                 Event_Button.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    );
    btn_back.setOnAction(e->{
        //(String id,String nom, String adresse, String prix, String surface,String capacite)
         
        try {
              Parent root ;
              root=FXMLLoader.load(getClass().getResource("../gui/welcome.fxml"));
              btn_back.getScene().setRoot(root);
                      
                      } catch (IOException ex) {
              Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
          }    
      });

  CreateEve_Button.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("CreateEve.fxml"));
                 CreateEve_Button.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    );
    

 
    
  Plan_Event_Button.setOnAction(e -> {
      Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Plan_Event.fxml"));
                  Plan_Event_Button.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    );
  
        Search_Event.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("SearchType.fxml"));
                 Search_Event.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    );
        
  }
    }    
    

    

