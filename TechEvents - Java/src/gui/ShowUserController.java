/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.SponsorFile;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import service.SponsorFileService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ShowUserController implements Initializable {

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> col_user;
    @FXML
    private TableColumn<User, String> col_emall;
    @FXML
    private TableColumn<User, Date> col_birthdate;
    @FXML
    private TableColumn<User, String> col_phone;
    @FXML
    private Button btn_sponsor;
    @FXML
    private Button btn_lodger;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_profile;

    public static User selected = new User();

    @FXML
    private TextField search;

    FilteredList<User> filteredData;
    @FXML
    private Button btn_welcome;
    @FXML
    private Button btn_detail;
    @FXML
    private Button btn_applications;
    @FXML
    private Label list;
    @FXML
    private Button btn_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list.setText("Account List");
        UserService us = new UserService();
        ArrayList<User> user = (ArrayList<User>) us.getAll();
        ObservableList<User> obs = FXCollections.observableArrayList(user);
        table.setItems(obs);
        col_user.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_emall.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        try {
            Load();
        } catch (SQLException ex) {
            Logger.getLogger(ShowUserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!SignInController.session.getRole().equals("Admin")) {
            btn_delete.setVisible(false);
            btn_applications.setVisible(false);
        }

        btn_delete.setOnAction(e -> {
            Parent root;
            User selection = table.getSelectionModel().getSelectedItem();
            User u = us.getByUsername(selection.getUsername());
            us.delete(u.getId());
            try {
                root = FXMLLoader.load(getClass().getResource("showUser.fxml"));
                btn_delete.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        btn_profile.setOnAction(e -> {
            Parent root;
            User selection = table.getSelectionModel().getSelectedItem();
            selected = us.getByUsername(selection.getUsername());
            try {
                root = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
                btn_delete.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        btn_welcome.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                btn_welcome.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        btn_applications.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showApplications.fxml"));
                btn_welcome.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        if (SignInController.file.getId_file() == 0) {
            btn_detail.setVisible(false);
        }

        btn_detail.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showDetail.fxml"));
                btn_detail.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    }

    private void Load() throws SQLException {
        //Connection con = mycon.getConnection();
        UserService us = new UserService();
        ArrayList<User> user = (ArrayList<User>) us.getAllAccounts();
        ObservableList<User> obs = FXCollections.observableArrayList(user);
        filteredData = new FilteredList<>(obs, e -> true);
        table.getItems().clear();

        ObservableList<User> liste1 = FXCollections.observableArrayList();

        liste1.addAll(us.getAllAccounts());

        table.setItems(liste1);

        col_user.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_emall.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        obs = FXCollections.observableArrayList(liste1);
        filteredData = new FilteredList<>(obs, e -> true);

    }

    @FXML
    private void advancedSearch(KeyEvent event) {

        search.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super User>) (User User) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (User.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (User.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<User> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }

    @FXML
    private void sponsor(ActionEvent event) {
        list.setText("Sponsor List");
        UserService us = new UserService();
        ArrayList<User> user = (ArrayList<User>) us.getAllSponsors();
        ObservableList<User> obs = FXCollections.observableArrayList(user);
        table.setItems(obs);
        col_user.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_emall.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

    }

    @FXML
    private void lodger(ActionEvent event) {
        list.setText("Lodger List");
        UserService us = new UserService();
        ArrayList<User> user = (ArrayList<User>) us.getAllLodgers();
        ObservableList<User> obs = FXCollections.observableArrayList(user);
        table.setItems(obs);
        col_user.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_emall.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

    }

    @FXML
    private void user(ActionEvent event) {
        list.setText("User List");
        UserService us = new UserService();
        ArrayList<User> user = (ArrayList<User>) us.getAll();
        ObservableList<User> obs = FXCollections.observableArrayList(user);
        table.setItems(obs);
        col_user.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_emall.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_birthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

    }

}
