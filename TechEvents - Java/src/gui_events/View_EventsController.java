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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service_event.EventService;

/**
 * FXML Controller class
 *
 * @author Ghathenus
 */
public class View_EventsController implements Initializable {

    @FXML
    private TableColumn<Event, String> name_col;
    @FXML
    private TableColumn<Event, Integer> id_col;
    @FXML
    private TableColumn<Event, Date> date_col;
    @FXML
    private TableColumn<Event, String> loc_col;
    @FXML
    private TableColumn<Event, String> desc_col;
    @FXML
    private TableColumn<Event, Integer> num_col;
    @FXML
    private Button Del_Eve_Btn;
    @FXML
    private Button Mod_Eve_Btn;
    @FXML
    private TableView<Event> Tabel_Event;
    public static Event selected = new Event();
    @FXML
    private Button Menu_btn;
    @FXML
    private Button btn_participate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventService us = new EventService();
        ArrayList<Event> sponsor = (ArrayList<Event>) us.getAll();
        ObservableList<Event> obs = FXCollections.observableArrayList(sponsor);
        Tabel_Event.setItems(obs);
        name_col.setCellValueFactory(new PropertyValueFactory<>("Name"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("ID_event"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("Date"));
        loc_col.setCellValueFactory(new PropertyValueFactory<>("Location"));
        desc_col.setCellValueFactory(new PropertyValueFactory<>("Description"));
        num_col.setCellValueFactory(new PropertyValueFactory<>("Max_number"));

        Del_Eve_Btn.setOnAction(e -> {
            Parent root;
            Event selection = Tabel_Event.getSelectionModel().getSelectedItem();
            Event ev = us.getById(selection.getID_event());
            us.delete(ev.getID_event());
            try {
                root = FXMLLoader.load(getClass().getResource("View_Events.fxml"));
                Del_Eve_Btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Mod_Eve_Btn.setOnAction(e -> {
            Parent root;
            Event selection = Tabel_Event.getSelectionModel().getSelectedItem();
            selected = us.getById(selection.getID_event());

            try {
                root = FXMLLoader.load(getClass().getResource("ModEve.fxml"));
                Del_Eve_Btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        );
        Menu_btn.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("Event.fxml"));
                Menu_btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btn_participate.setOnAction(e -> {
            Parent root;
            try {
                Event selection = Tabel_Event.getSelectionModel().getSelectedItem();
                Event ev = us.getById(selection.getID_event());
                root = FXMLLoader.load(getClass().getResource("../gui_sponsor/qrcode.fxml"));
                Menu_btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @FXML
    private void Pdf(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EventPDF.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }
}
