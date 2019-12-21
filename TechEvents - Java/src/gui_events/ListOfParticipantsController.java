/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_events;

import entities_events.Event;

import entities_events.inscription;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service_event.EventService;
import service_event.PartListService;

/**
 * FXML Controller class
 *
 * @author Ghathenus
 */
public class ListOfParticipantsController implements Initializable {

    @FXML
    private TableColumn<inscription, Integer> inscr_id;
    @FXML
    private TableColumn<inscription, Date> date_id;
    @FXML
    private TableView<inscription> table1;
    @FXML
    private Button Menu_btn;

    /**
     * Initializes the controller class.
     */@Override
    public void initialize(URL url, ResourceBundle rb) {
        PartListService ps = new PartListService();
        ArrayList<inscription> inscription = (ArrayList<inscription>) ps.getAll();
        ObservableList<inscription> obs = FXCollections.observableArrayList(inscription);
        table1.setItems(obs);
       inscr_id.setCellValueFactory(new PropertyValueFactory<>("Inscription ID"));
        date_id.setCellValueFactory(new PropertyValueFactory<>("date"));
   
    

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
