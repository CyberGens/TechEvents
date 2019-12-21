/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.SponsorFile;
import entities.User;
import static gui.ShowUserController.selected;
import java.io.IOException;
import java.net.URL;
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
import service.SponsorFileService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ShowApplicationsController implements Initializable {

    @FXML
    private TableView<SponsorFile> table;
    @FXML
    private TableColumn<SponsorFile, Integer> col_id;
    @FXML
    private TableColumn<SponsorFile, String> col_type;
    @FXML
    private TableColumn<SponsorFile, String> col_desc;
    @FXML
    private TableColumn<SponsorFile, String> col_status;
    @FXML
    private Button btn_detail;

    public static SponsorFile selected = new SponsorFile();
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SponsorFileService fs = new SponsorFileService();
        UserService us = new UserService();
        ArrayList<SponsorFile> file = (ArrayList<SponsorFile>) fs.getAll();
        ObservableList<SponsorFile> obs = FXCollections.observableArrayList(file);
        table.setItems(obs);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        //col_url.setCellValueFactory(new PropertyValueFactory<>("url"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        btn_detail.setOnAction(e -> {
            Parent root;
            SponsorFile selection = table.getSelectionModel().getSelectedItem();
            selected = selection;
            ShowUserController.selected = us.getById(selection.getId_user().getId());
            try {
                root = FXMLLoader.load(getClass().getResource("showDetail.fxml"));
                btn_detail.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        btn_back.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showUser.fxml"));
                btn_back.getScene().setRoot(root);
                ShowApplicationsController.selected = null;
            } catch (IOException ex) {
                Logger.getLogger(ShowDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
