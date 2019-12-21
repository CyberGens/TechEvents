/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_rating;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Souha
 */
public class RatingHomeController implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private Button RatingFast;
    @FXML
    private Button Results;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void ratingFast(ActionEvent event) {
        try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingFast.fxml"));
            Parent root;
            root = loader.load();
            RatingFastController irc = loader.getController();
            RatingFast.getScene().setRoot(root);       
        } catch (IOException ex) {
            Logger.getLogger(RatingFastController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(ActionEvent event) {    
    }

    @FXML
    private void Results(ActionEvent event) {
        try {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingResults.fxml"));
            Parent root;
            root = loader.load();
            RatingResultsController irc = loader.getController();
            Results.getScene().setRoot(root);       
        } catch (IOException ex) {
            Logger.getLogger(RatingResultsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
       
