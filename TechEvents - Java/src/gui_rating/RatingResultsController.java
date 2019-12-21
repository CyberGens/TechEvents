/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_rating;

import entities_events.Event;
import entities.Rating;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service_rating.RatingService;

/**
 * FXML Controller class
 *
 * @author Souha
 */
public class RatingResultsController implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private TableColumn<Rating, String> colonneid;
    @FXML
    private TableColumn<Rating, String> colonneeven;
    @FXML
    private TableColumn<Rating, String> colonnescore;
    @FXML
    private Button boutonstat;
    @FXML
    private TableView<Rating> tableeven;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            RatingService rs= new RatingService();
            List<Rating> list= rs.getAll();
            ObservableList<Rating> obslist = FXCollections.observableArrayList(list);
            colonnescore.setCellValueFactory(new PropertyValueFactory<>("Stars"));
            colonneeven.setCellValueFactory(new PropertyValueFactory<>("Name"));
            tableeven.setItems(obslist);
        } catch (SQLException ex) {
            Logger.getLogger(RatingResultsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void back(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RatingHome.fxml"));
            Parent root;
            root = loader.load();
            RatingHomeController irc = loader.getController();
            Back.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RatingHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Stats(ActionEvent event) throws SQLException {
         
    }
    
}
