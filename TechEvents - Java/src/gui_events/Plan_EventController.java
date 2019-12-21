/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_events;

import entities_events.Event;
import entities.User;
import entities_events.planned_event;
import gui_events.CreateEveController;
import gui_events.EventController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service_event.EventService;
import service.UserService;
import service_event.InscriptionService;

/**
 * FXML Controller class
 *
 * @author Ghathenus
 */
public class Plan_EventController implements Initializable {

    @FXML
    private DatePicker Set_Date;
    @FXML
    private TextField Event_Name;
    @FXML
    private ComboBox<String> Interest;
    @FXML
    private CheckBox Reminder;
    private Button Plan_Event_but;
    @FXML
    private TextField Id_User;
    @FXML
    private Label Id_eve;
    @FXML
    private Label date;
    @FXML
    private Label Id;
    @FXML
    private Button Menu_btn;
    @FXML
    private Button Plan_Event;
    public static planned_event pe1 = new planned_event();

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Interest.getItems().removeAll(Interest.getItems());
Interest.getItems().addAll("Very","a little","not much");
Interest.getSelectionModel().select("a little");

        Plan_Event.setOnAction(e -> {
             if (Event_Name.getText().equalsIgnoreCase("")
                    || Id_User.getText().equalsIgnoreCase("")
                    ) {

                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please , You must fill out all the fields");
                alert.show();}
            Event ev = new Event();
            EventService es = new EventService();
            User u = new User();
            UserService us = new UserService();
            Date date = Date.valueOf(Set_Date.getValue());

            int d = Integer.parseInt(Event_Name.getText());
            int m = Integer.parseInt(Id_User.getText());
            ev = es.getById(d);
            u=us.getById(m);
            planned_event pe = new planned_event(ev, u, d + m + 10, (String) Interest.getValue());
            InscriptionService ps = new InscriptionService();

            ps.insertPST(pe);
            pe1=pe;
            Parent root;

            try {
                root = FXMLLoader.load(getClass().getResource("CreateEve.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(CreateEveController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    

    @FXML
    private void ToPDF(ActionEvent event) throws IOException {
        Parent tableViewParent;
        tableViewParent = FXMLLoader.load(getClass().getResource("EventPlanned.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
    }
}
