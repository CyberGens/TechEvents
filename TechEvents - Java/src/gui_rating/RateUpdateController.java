/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_rating;

import entities.Rating;
import gui.SignInController;
import gui_rating.RatingFastController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import service_rating.RatingService;

/**
 * FXML Controller class
 *
 * @author Souha
 */
public class RateUpdateController implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private Button boutonsupdate;
    @FXML
    private RadioButton un;
    @FXML
    private RadioButton trois;
    @FXML
    private RadioButton quatres;
    @FXML
    private RadioButton deux;
    @FXML
    private RadioButton cinq;
    int eve;
    int score;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Back(ActionEvent event) {
        try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingFast.fxml"));
            Parent root;
            root = loader.load();
            RatingFastController irc = loader.getController();
            Back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RatingFastController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void update(ActionEvent event) {
        RatingService rs= new RatingService();
        if(un.isSelected()){
            score=1;
        }
        if(deux.isSelected()){
            score=2;
        }
        if(trois.isSelected()){
            score=3;
        }
        if(quatres.isSelected()){
            score=4;
        }
         if(cinq.isSelected()){
            score=5;
        }
        rs.update(SignInController.session.getId(),eve,score);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Succés");
             alert.setHeaderText(null);
             alert.setContentText("Votre score a été modifié");
             alert.showAndWait();
        Back(event);
    }

    void initData(int id_event) {
        eve=id_event;
    }
    
}
