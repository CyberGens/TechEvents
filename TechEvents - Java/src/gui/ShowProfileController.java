/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ShowProfileController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private ImageView photo;
    @FXML
    private Label firstname;
    @FXML
    private Label lastname;
    @FXML
    private Label email;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FileInputStream path = new FileInputStream(ShowUserController.selected.getPhoto());
            Image image = new Image(path);
            photo.setImage(image);

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ShowProfileController.class.getName()).log(Level.SEVERE, null, ex);
            try {
                photo.setImage(new Image(new FileInputStream("C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\arrive.jpg")));
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(ShowProfileController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        username.setText(ShowUserController.selected.getUsername() + "'s Profile");
        firstname.setText(ShowUserController.selected.getFirstname());
        lastname.setText(ShowUserController.selected.getLastname());
        email.setText(ShowUserController.selected.getEmail());
        phone.setText(ShowUserController.selected.getPhone());
        address.setText(ShowUserController.selected.getAddress());
        btn_back.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showUser.fxml"));
                btn_back.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);

            }
        });
    }

}
