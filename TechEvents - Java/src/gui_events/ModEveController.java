/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_events;

import entities_events.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service_event.EventService;

/**
 * FXML Controller class
 *
 * @author Ghathenus
 */
public class ModEveController implements Initializable {

    @FXML
    private Button mod_button;
    @FXML
    private TextField Name_field;
    @FXML
    private DatePicker date_field;
    @FXML
    private TextField location_field;
    @FXML
    private TextField number_field;
    @FXML
    private TextArea sponsor_field;
    @FXML
    private TextArea desc_field;
    @FXML
    private TextField status_field;
    @FXML
    private ComboBox<String> cat_field;
    @FXML
    private CheckBox check_field;
    @FXML
    private Button Menu_btn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
cat_field.getItems().removeAll(cat_field.getItems());
cat_field.getItems().addAll("Conference","Gaming Event","Forum");
cat_field.getSelectionModel().select("Gaming Event");
      
            
            date_field.setValue(View_EventsController.selected.getDate().toLocalDate());
            
            Name_field.setText(View_EventsController.selected.getName());
            location_field.setText(View_EventsController.selected.getLocation());
            
                    sponsor_field.setText(View_EventsController.selected.getSponsors());
                    desc_field.setText(View_EventsController.selected.getDescription());
              cat_field.setValue(View_EventsController.selected.getCategory());
                    status_field.setText(View_EventsController.selected.getFin_Status());
            EventService es = new EventService();

          
              cat_field.getItems().removeAll(cat_field.getItems());
cat_field.getItems().addAll("Conference","Gaming Event","Forum");
cat_field.getSelectionModel().select("Gaming Event");
        mod_button.setOnAction(e -> {
           
            Date date = Date.valueOf(date_field.getValue());
            EventService os = new EventService();
            Event ev1 = new Event();
            ev1=View_EventsController.selected;
            ev1.setName(Name_field.getText());
            ev1.setLocation(location_field.getText());
            ev1.setSponsors(sponsor_field.getText());
            ev1.setDescription(desc_field.getText());
            ev1.setFin_Status(status_field.getText());
            ev1.setCategory(cat_field.getValue());
            
           ev1.setDate(date);

            es.update(ev1);
              
            
        
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("View_Events.fxml"));
                 mod_button.getScene().setRoot(root);
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

    }
}
