/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Sponsor;
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
import service.SponsorService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ShowSponsorController implements Initializable {

    @FXML
    private TableView<Sponsor> table;
    @FXML
    private TableColumn<Sponsor, String> col_user;
    @FXML
    private TableColumn<Sponsor, String> col_emall;
    @FXML
    private TableColumn<Sponsor, Date> col_birthdate;
    @FXML
    private TableColumn<Sponsor, String> col_phone;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_lodger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SponsorService us = new SponsorService();
        ArrayList<Sponsor> sponsor = (ArrayList<Sponsor>) us.getAll();
        ObservableList<Sponsor> obs = FXCollections.observableArrayList(sponsor);
        table.setItems(obs);
        col_user.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_emall.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        btn_user.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showUser.fxml"));
                btn_user.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        btn_lodger.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showLodger.fxml"));
                btn_lodger.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    }

}
