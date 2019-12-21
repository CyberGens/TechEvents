/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Admin;
import entities.SponsorFile;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.SponsorFileService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SignInController implements Initializable {

    @FXML
    private Button btn_add;
    @FXML
    private Button btn_signin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public static User session = new User();
    public static SponsorFile file = new SponsorFile();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_add.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("addUser.fxml"));
                btn_add.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_signin.setOnAction(e -> {
            try {
                UserService us = new UserService();
                SponsorFileService fs = new SponsorFileService();
                User u = new User();
                u = us.getByUsername(username.getText());
                SignInController.session = u;
                SignInController.file = fs.getByUser(u);
                Parent root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
                

                if (us.authenticate(username.getText(), password.getText())) {
                    if (SignInController.session.getRole().equals("Admin")) {
                        root = FXMLLoader.load(getClass().getResource("showUser.fxml"));
                    } else {
                        root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                    }

                    btn_signin.getScene().setRoot(root);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Login!");
                alert.show();
                }

            } catch (IOException ex) {
                Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    }

}
