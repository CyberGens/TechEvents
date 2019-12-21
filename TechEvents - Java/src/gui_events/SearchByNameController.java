/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_events;

import entities_events.Event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service_event.EventService;

/**
 * FXML Controller class
 *
 * @author Ghathenus
 */
public class SearchByNameController implements Initializable {

    @FXML
    private Button Searchbynamebtn;
    @FXML
    private TextField Searchbyname;
    
    public static ArrayList<Event>liste = new ArrayList<Event>();
    @FXML
    private Button Menu_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Searchbynamebtn.setOnAction(e   ->{
              if (Searchbyname.getText().equalsIgnoreCase("") ){
                    

                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please , Search for something!");
                alert.show();}
            Parent root;
             EventService us = new EventService();
           liste = us.getByName(Searchbyname.getText());
            
            try {
                root = FXMLLoader.load(getClass().getResource("ListSearch.fxml"));
                 Searchbynamebtn.getScene().setRoot(root);
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
