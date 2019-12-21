/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.User;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class UpdateUserController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField firstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private PasswordField password;
    @FXML
    private DatePicker birthdate;
    @FXML
    private TextArea address;
    @FXML
    private Button btn_update;
    @FXML
    private ImageView img1;

    private int id;  //Refers to user ID used in the update process
    @FXML
    private Button btn_browse;
    @FXML
    private ImageView image;

    final FileChooser fileChooser = new FileChooser();
    private Desktop desktop = Desktop.getDesktop();
    private String file_image;
    private Path pathfrom;
    private Path pathto;
    private File Current_file;
    private FileInputStream fis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        User u1 = SignInController.session;
        try {
            FileInputStream path = new FileInputStream(u1.getPhoto());
        } catch (FileNotFoundException ex) {
            try {
                //Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
                image.setImage(new Image(new FileInputStream("C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\arrive.jpg")));
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        id = u1.getId();
        username.setText(u1.getUsername());
        firstname.setText(u1.getFirstname());
        lastname.setText(u1.getLastname());
        password.setText(u1.getPassword());
        email.setText(u1.getEmail());
        address.setText(u1.getAddress());
        phone.setText(u1.getPhone());
        birthdate.setValue(u1.getBirthdate().toLocalDate());

        btn_update.setOnAction(e -> {

            pathfrom = FileSystems.getDefault().getPath(Current_file.getName());
            String photo = "C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\TechEvents\\src\\img\\" + Current_file.getName();
            pathto = FileSystems.getDefault().getPath(photo);
            try {
                Files.copy(pathfrom, pathto, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Date date = Date.valueOf(birthdate.getValue());
            UserService us = new UserService();
            User u = us.getById(id);
            u.setUsername(username.getText());
            u.setFirstname(firstname.getText());
            u.setLastname(lastname.getText());
            u.setPassword(password.getText());
            u.setBirthdate(date);
            u.setEmail(email.getText());
            u.setAddress(address.getText());
            u.setPhone(phone.getText());
            u.setPhoto(photo);
            us.update(u);
            SignInController.session = u;

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("showUser.fxml"));
                btn_update.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(UpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

    }

    @FXML
    private void parcourir_image(ActionEvent event) {

        FileChooser fc = new FileChooser();
        Current_file = fc.showOpenDialog(null);
        if (Current_file != null) {
            Image images = new Image(Current_file.toURI().toString(), 100, 100, true, true);
            image.setImage(images);
            try {
                fis = new FileInputStream(Current_file);
                file_image = Current_file.getName();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
