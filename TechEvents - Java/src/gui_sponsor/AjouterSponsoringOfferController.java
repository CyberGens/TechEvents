/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_sponsor;

import entities.SponsoringOffer;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import org.controlsfx.control.Notifications;
import service.SponsoringOfferService;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AjouterSponsoringOfferController implements Initializable {
   
    @FXML
    private DatePicker dateDebutSponsoringOffer;

    @FXML
    private DatePicker dateFinSponsoringOffer;

    @FXML
    private TextArea txtDescriptionSponsoringOffer;

    @FXML
    private Button btnSponsoringOffer;
       @FXML
    private Button btnacceuilSpg;

    @FXML
    private Button btnsupprimerSpg;

    @FXML
    private Button btnmodifierSpg;
    
    
//    @FXML
//    private Label msg;
      
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
            btnSponsoringOffer.setOnAction(e-> {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 if( dateFinSponsoringOffer.getValue().isBefore(dateDebutSponsoringOffer.getValue())
                ||txtDescriptionSponsoringOffer.getText().trim().isEmpty())
        {
            alert.setTitle("attention");
            alert.setContentText("veuillez remplir touts les champs et verifier la date ");
            alert.showAndWait();
            
        }
                 
                 else {
                Date dateD = Date.valueOf(dateDebutSponsoringOffer.getValue());
               Date dateF = Date.valueOf(dateFinSponsoringOffer.getValue());
                 String desc =txtDescriptionSponsoringOffer.getText();
         
                SponsoringOffer s = new SponsoringOffer(
                                                        dateD,
                                                        dateF,
                                                        desc
                                                        );
               SponsoringOfferService sos = new SponsoringOfferService();
                try {
                    sos.insertOffrSonsoring(s);
                } catch (SQLException ex) {
                    Logger.getLogger(AjouterSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Notifications.create()
                .title("félicitation")
                .text("votre Offre a ete ajouter!")
                .position(Pos.TOP_RIGHT)
                .showConfirm();
  
                    }
                       
    } );
            
            
            
      
    }
  
    
    
    @FXML
    private void Openacct(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("afficherSponsoringOffer.fxml"));
            btnacceuilSpg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("bbb");

        }
    }

    @FXML
    private void OpenModifier(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("modifierSponsoringOffer.fxml"));
            btnmodifierSpg.getScene().setRoot(root);
            System.out.println("button modifier cliced");
        } catch (IOException ex) {
            Logger.getLogger(AfficherSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("bbb");

        }
    }

    @FXML
    private void Opensupprimer(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("supprimerSponsoringOffer.fxml"));
            btnsupprimerSpg.getScene().setRoot(root);
             System.out.println("button supprimer cliced");
        } catch (IOException ex) {
            Logger.getLogger(AfficherSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("bbb");

        }
    }
    }

