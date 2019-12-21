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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.UserService;
import java.sql.Date;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddUserController implements Initializable {

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
    private Button btn_add;
    @FXML
    private Button btn_apply;

    public static User session;
    @FXML
    private ImageView img1;
    @FXML
    private Button btn_browse;

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

        btn_add.setOnAction(e -> {

            if (email.getText().equalsIgnoreCase("")
                    || password.getText().equalsIgnoreCase("")
                    || username.getText().equalsIgnoreCase("")
                    || firstname.getText().equalsIgnoreCase("")
                    || lastname.getText().equalsIgnoreCase("")
                    || phone.getText().equalsIgnoreCase("")
                    || address.getText().equalsIgnoreCase("")) {

                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill ou all fields !");
                alert.show();

            } else if (!isValid(email.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Email!");
                alert.show();

            } else if (!isValidNumber(phone.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Phone Number!");
                alert.show();

            } else {
                try {
                    pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
                    String photo = "C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\TechEvents\\src\\img\\" + Current_file.getName();
                    pathto = FileSystems.getDefault().getPath(photo);
                    //Path targetDir = FileSystems.getDefault().getPath("C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\TechEvents\\src\\img\\images");
                    Files.copy(pathfrom, pathto, StandardCopyOption.REPLACE_EXISTING);

                    Date date = Date.valueOf(birthdate.getValue());
                    User u = new User(username.getText(), photo,
                            firstname.getText(), lastname.getText(), password.getText(), date,
                            email.getText(), phone.getText(), address.getText());
                    UserService us = new UserService();
                    us.insertPST(u);

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
                        btn_add.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        btn_apply.setOnAction(e -> {

            if (email.getText().equalsIgnoreCase("")
                    || password.getText().equalsIgnoreCase("")
                    || username.getText().equalsIgnoreCase("")
                    || firstname.getText().equalsIgnoreCase("")
                    || lastname.getText().equalsIgnoreCase("")
                    || phone.getText().equalsIgnoreCase("")
                    || address.getText().equalsIgnoreCase("")) {

                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill ou all fields !");
                alert.show();

            } else if (!isValid(email.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Email!");
                alert.show();

            } else if (!isValidNumber(phone.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Phone Number!");
                alert.show();

            } else {
                try {
                    pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
                    String photo = "C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\TechEvents\\src\\img\\" + Current_file.getName();
                    pathto = FileSystems.getDefault().getPath(photo);
                    Files.copy(pathfrom, pathto, StandardCopyOption.REPLACE_EXISTING);
                    Date date = Date.valueOf(birthdate.getValue());
                    User u = new User(username.getText(), photo,
                            firstname.getText(), lastname.getText(), password.getText(), date,
                            email.getText(), phone.getText(), address.getText());
                    UserService us = new UserService();
                    us.insertPST(u);

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("applySponsor.fxml"));
                        btn_add.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        /*btn_apply.setOnAction(e -> {
            try {
                pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
                String photo = "C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\TechEvents\\src\\img\\" + Current_file.getName();
                pathto = FileSystems.getDefault().getPath(photo);
                //Path targetDir = FileSystems.getDefault().getPath("C:\\Users\\Admin\\Desktop\\Esprit\\Projets\\PI Dev\\Ateliers\\TechEvents\\src\\img\\images");
                Files.copy(pathfrom, pathto, StandardCopyOption.REPLACE_EXISTING);

                Date date = Date.valueOf(birthdate.getValue());
                User u = new User(username.getText(), photo,
                        firstname.getText(), lastname.getText(), password.getText(), date,
                        email.getText(), phone.getText(), address.getText());
                UserService us = new UserService();
                us.insertPST(u);
                u = us.getByUsername(username.getText());
                AddUserController.session = u;

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("applySponsor.fxml"));
                    btn_add.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );*/

    }

    @FXML
    private void parcourir_image(ActionEvent event) {

        FileChooser fc = new FileChooser();
        Current_file = fc.showOpenDialog(null);
        if (Current_file != null) {
            Image images = new Image(Current_file.toURI().toString(), 100, 100, true, true);
            img1.setImage(images);
            try {
                fis = new FileInputStream(Current_file);
                file_image = Current_file.getName();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public static boolean isValid(String emailc) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emailc == null) {
            return false;
        }
        return pat.matcher(emailc).matches();

    }

    public static boolean isValidNumber(String number) {

        String emailRegex = "\\d{8}";

        Pattern pat = Pattern.compile(emailRegex);
        if (number == null) {
            return false;
        }
        return pat.matcher(number).matches();

    }
}
