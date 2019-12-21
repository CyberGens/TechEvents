/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_sponsor;

import entities.SponsoringOffer;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.SponsoringOfferService;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class SupprimerSponsoringOfferController implements Initializable {

//    @FXML
//    private Button btnmodifierSpg;
    @FXML
    private Button btnSupp;
    @FXML
    private TextField idspgd;
   @FXML
    private Label showmsg;
    @FXML
    private Button btnacceuilSpg;

    private int id;  //idspg pour la supp

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    
    }

    
       @FXML
    private void SuppSpg(ActionEvent event) throws IOException {
                id =  Integer.parseInt(idspgd.getText());
          btnSupp.setOnAction(e -> {
            SponsoringOfferService sos = new SponsoringOfferService();

            SponsoringOffer so = sos.getById(id);
            sos.deleteSponsoringOffer(id);
            showmsg.setText("Votre Offre a ete supprimer avec succée !");
                     
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("AfficherSponsoringOfferController.fxml"));
                btnSupp.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AfficherSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
       );
    }
    
    
    @FXML
    private void Openacc(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("afficherSponsoringOffer.fxml"));
            btnacceuilSpg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(SupprimerSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("bbb");

        }
    }

}
