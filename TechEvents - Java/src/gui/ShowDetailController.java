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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.SponsorFileService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ShowDetailController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label user;
    @FXML
    private Label type;
    @FXML
    private Label description;
    @FXML
    private Label attachment;
    @FXML
    private Label status;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_refuse;
    @FXML
    private Button btn_accept;
    @FXML
    private Button btn_profile;
    @FXML
    private Button btn_pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            username.setText(ShowApplicationsController.selected.getId_user().getUsername() + "'s Application");
        } catch (NullPointerException ex) {
            ShowApplicationsController.selected = SignInController.file;
        }

        user.setText(ShowApplicationsController.selected.getId_user().getUsername());
        type.setText(ShowApplicationsController.selected.getType());
        description.setText(ShowApplicationsController.selected.getDescription());
        status.setText(ShowApplicationsController.selected.getStatus());
        attachment.setText(ShowApplicationsController.selected.getUrl());

        if (!ShowApplicationsController.selected.getStatus().equals("Accepted")) {
            btn_pdf.setVisible(false);
        }

        btn_back.setOnAction(e -> {
            Parent root;
            try {
                if (SignInController.session.getRole().equals("Admin")) {
                    root = FXMLLoader.load(getClass().getResource("showApplications.fxml"));
                } else {
                    root = FXMLLoader.load(getClass().getResource("showUser.fxml"));
                }
                btn_back.getScene().setRoot(root);
                ShowApplicationsController.selected = null;
            } catch (IOException ex) {
                Logger.getLogger(ShowDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btn_profile.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showProfile.fxml"));
                btn_profile.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        if (!SignInController.session.getRole().equals("Admin")) {
            btn_accept.setVisible(false);
            btn_refuse.setVisible(false);
        }
        btn_accept.setOnAction(e -> {
            SponsorFile sf = new SponsorFile();
            SponsorFileService fs = new SponsorFileService();
            sf = ShowApplicationsController.selected;
            sf.setStatus("Accepted");

            User u = new User();
            UserService us = new UserService();
            u = ShowApplicationsController.selected.getId_user();
            u.setRole(sf.getType());

            fs.update(sf);
            us.update(u);
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showApplications.fxml"));
                ShowApplicationsController.selected = null;
                btn_profile.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btn_refuse.setOnAction(e -> {
            SponsorFile sf = new SponsorFile();
            SponsorFileService fs = new SponsorFileService();
            sf = ShowApplicationsController.selected;
            sf.setStatus("Rejected");

            User u = new User();
            UserService us = new UserService();
            u = ShowApplicationsController.selected.getId_user();
            u.setRole("User");

            fs.update(sf);
            us.update(u);

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showApplications.fxml"));
                ShowApplicationsController.selected = null;
                btn_profile.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void Pdf(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("showPDF.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

}
