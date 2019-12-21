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
import gui_events.*;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class WelcomeController implements Initializable {

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
    private Button btn_update;
    @FXML
    private Button btn_list;
    @FXML
    private Button btn_signout;
    @FXML
    private Button btn_apply;
    @FXML
    private Button btn_event;
    @FXML
    private Button btn_rating;
    @FXML
    private Button btn_sponsor;
    @FXML
    private Button btn_places;
    @FXML
    private Button btn_lodger;
    @FXML
    private Button btn_com;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
            // TODO
            if (!SignInController.session.getRole().equals("Lodger"))
            {
                btn_lodger.setVisible(false);
            }
            try {
            FileInputStream path = new FileInputStream(SignInController.session.getPhoto());
            Image image = new Image(path);
            photo.setImage(image);

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ShowProfileController.class.getName()).log(Level.SEVERE, null, ex);
            try {
                photo.setImage(new Image(new FileInputStream("C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\arrive.jpg")));
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        username.setText(SignInController.session.getUsername() + "'s Profile");
        firstname.setText(SignInController.session.getFirstname());
        lastname.setText(SignInController.session.getLastname());
        email.setText(SignInController.session.getEmail());
        phone.setText(SignInController.session.getPhone());
        address.setText(SignInController.session.getAddress());
        
        btn_update.setOnAction(e->{
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("updateUser.fxml"));
                btn_update.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_com.setOnAction(e->{
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("../gui_com/reclamationUSER.fxml"));
                btn_update.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_apply.setOnAction(e->{
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("applySponsor.fxml"));
                btn_update.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_list.setOnAction(e->{
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showUser.fxml"));
                btn_list.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_signout.setOnAction(e->{
            Parent root;
            try {
                
                root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
                btn_list.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_event.setOnAction(e->{
            Parent root;
            try {
                
                root = FXMLLoader.load(getClass().getResource("../gui_events/Event.fxml"));
                btn_list.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_rating.setOnAction(e->{
            Parent root;
            try {
                
                root = FXMLLoader.load(getClass().getResource("../gui_rating/RatingHome.fxml"));
                btn_list.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_sponsor.setOnAction(e->{
            Parent root;
            try {
                
                root = FXMLLoader.load(getClass().getResource("../gui_sponsor/afficherSponsoringOffer.fxml"));
                btn_list.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_places.setOnAction(e->{
            Parent root;
            try {
                
                root = FXMLLoader.load(getClass().getResource("../gui_lodger/HomeLocal.fxml"));
                btn_list.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btn_lodger.setOnAction(e->{
            Parent root;
            try {
                
                root = FXMLLoader.load(getClass().getResource("../gui_lodger/GestionLocal.fxml"));
                btn_list.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
