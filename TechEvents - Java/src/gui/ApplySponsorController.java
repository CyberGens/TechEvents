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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.SponsorFileService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ApplySponsorController implements Initializable {

    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField doc_url;
    @FXML
    private TextArea description;
    @FXML
    private Button btn_submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        type.getItems().removeAll(type.getItems());
        type.getItems().addAll("Sponsor", "Lodger");
        type.getSelectionModel().select("Sponsor");
        btn_submit.setOnAction(e -> {
            User u = new User();
            if (doc_url.getText().equals("") || description.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill ou all fields !");
                alert.show();
            }
            if (SignInController.session.getId() != 0) {
                u = SignInController.session;
            } else {
                u = AddUserController.session;
            }

            SponsorFile f = new SponsorFile(u, doc_url.getText(),
                    description.getText(), type.getValue());
            SponsorFileService fs = new SponsorFileService();
            fs.insert(f, u);

            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
                btn_submit.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AddUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
    }

}
