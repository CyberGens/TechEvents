/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_events;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

import entities_events.Event;
import entities.User;
import gui.SignInController;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service_event.EventService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Ghathenus
 */
public class CreateEveController implements Initializable {

    @FXML
    private Button create_button;
    @FXML
    private TextField Id_field;
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
    private TextField uId_field;
    @FXML
    private Button Post_fb;
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
        create_button.setOnAction(e -> {
            
            if (Id_field.getText().equalsIgnoreCase("")
                    || Name_field.getText().equalsIgnoreCase("")
                    || desc_field.getText().equalsIgnoreCase("")
                    || number_field.getText().equalsIgnoreCase("")
                    || status_field.getText().equalsIgnoreCase("")
                    || cat_field.getValue().equalsIgnoreCase("")
                    || uId_field.getText().equalsIgnoreCase("")
                    || sponsor_field.getText().equalsIgnoreCase("")) {

                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please , You must fill out all the fields");
                alert.show();}
            // UserService us1= new UserService();
            // User u1 =new User() ;
           
            int d = Integer.parseInt(Id_field.getText());
            int n = Integer.parseInt(number_field.getText());
             //int m = Integer.parseInt(uId_field.getText());
             //u1=us1.getById(m);
             
            Date date = Date.valueOf(date_field.getValue());
           
            Event ev = new Event(d,SignInController.session, Name_field.getText(), date, location_field.getText(), n,
                    sponsor_field.getText(), desc_field.getText(), (String) cat_field.getValue(), status_field.getText());
            EventService es = new EventService();

            es.insertPST(ev);
            
        
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("View_Events.fxml"));
                 create_button.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        Post_fb.setOnAction(e -> {   String f ;
             f=(desc_field.getText());
              String accessToken="EAAEefTYzIwkBAPxxUcy279zGwteZA605KRXCCsjwP3tD0OW9ZBkNGQ1qThlHqcpq38nLRutd1Cwz7uJaJEoOtaMp30QJHZBbZCSKcjW3HcLZC4TWYdNXSOlmDjdDNNGHhdkccXhVxn4HQDgSQ4ggBNMp5Ot7Q0NYjvnsXf4VegL10ufH9ZADUEBExymZAZCL59MZD";
        FacebookClient fbClient= new DefaultFacebookClient(accessToken);
    FacebookType response= fbClient.publish("me/feed",FacebookType.class,Parameter.with("message",f));
   System.out.println("fb.com/"+response.getId());

    
});
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

