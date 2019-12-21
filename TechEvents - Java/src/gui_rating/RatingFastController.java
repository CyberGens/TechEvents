/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_rating;

import entities_events.Event;
import gui.SignInController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.nashorn.internal.ir.CallNode;
import service_rating.RatingService;

/**
 * FXML Controller class
 *
 * @author Souha
 */
public class RatingFastController implements Initializable {

    @FXML
    private Button Back;
    @FXML
    private TableColumn<Event, String> colonneid;
    @FXML
    private TableColumn<Event, String> colonneeven;
    @FXML
    private Button boutondelete;
    @FXML
    private Button boutonUpdate;
    @FXML
    private Button boutonsadd;
    @FXML
    private Button boutonsinfo;
    @FXML
    private TableView<Event> tableeven;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            RatingService rs= new RatingService();
            List<Event> list= rs.alleven() ;
            ObservableList<Event> obslist = FXCollections.observableArrayList(list);
            colonneid.setCellValueFactory(new PropertyValueFactory<>("ID_Event"));
            colonneeven.setCellValueFactory(new PropertyValueFactory<>("Name"));
            tableeven.setItems(obslist);
        } catch (SQLException ex) {
            Logger.getLogger(RatingFastController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void updaterate(ActionEvent event) throws IOException, SQLException {
         if(tableeven.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement est selectionné");
            alert.showAndWait();
            }
         else{ 
             Event e=tableeven.getSelectionModel().getSelectedItem();
             RatingService rs= new RatingService();
             int rate=rs.RateInfo(e.getID_event(),SignInController.session.getId());
             if(rate==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Impossible");
             alert.setHeaderText(null);
             alert.setContentText("Tu n'as pas encore noté cet événement");
             alert.showAndWait();
             }
            else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RateUpdate.fxml"));
            Parent root;
            root = loader.load();
            RateUpdateController irc = loader.getController();
            irc.initData(e.getID_event());
            boutonUpdate.getScene().setRoot(root);
        }
      }
    }

    @FXML
    private void InfoRate(ActionEvent event) throws SQLException{
         if(tableeven.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement est selectionné");
            alert.showAndWait();
            }
         else{
             Event e=tableeven.getSelectionModel().getSelectedItem();
             RatingService rs= new RatingService();
             int rate=rs.RateInfo(e.getID_event(),SignInController.session.getId());
             if(rate==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Info");
             alert.setHeaderText(null);
             alert.setContentText("Tu n'as pas encore donner une note pour cet événement");
             alert.showAndWait();
             }
             else{
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Info");
             alert.setHeaderText(null);
             alert.setContentText("Tu as noté cet événement et la note est = "+rate);
             alert.showAndWait();
             }
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
    private void DeleteRate(ActionEvent event) throws SQLException{
         if(tableeven.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement est selectionné");
            alert.showAndWait();
            }
         else{
             Event e=tableeven.getSelectionModel().getSelectedItem();
             RatingService rs= new RatingService();
             int rate=rs.RateInfo(e.getID_event(),SignInController.session.getId());
             if(rate==0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Impossible");
             alert.setHeaderText(null);
             alert.setContentText("Tu n'as pas encore noté cet événement");
             alert.showAndWait();
             }
             else{
             rs.delete(e.getID_event(), SignInController.session.getId());
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Succés");
             alert.setHeaderText(null);
             alert.setContentText("Votre note a été bien supprimée");
             alert.showAndWait();
             }
         }
    }
    
        @FXML
    private void addrate(ActionEvent event) throws IOException, SQLException{
         if(tableeven.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement est selectionné");
            alert.showAndWait();
            }
         else{
         Event e=tableeven.getSelectionModel().getSelectedItem();
             RatingService rs= new RatingService();
             int rate=rs.RateInfo(e.getID_event(),SignInController.session.getId());
         if(rate!=0){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Impossible");
             alert.setHeaderText(null);
             alert.setContentText("Tu a déja donné un score pour cette événement");
             alert.showAndWait();
             }
         else{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("RateAdd.fxml"));
            Parent root;
            root = loader.load();
            RateAddController irc = loader.getController();
            irc.initData(e.getID_event(),e.getName());
            boutonsadd.getScene().setRoot(root);
        } 
    }
    }
}
