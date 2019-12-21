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
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;
import service.SponsoringOfferService;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class ModifierSponsoringOfferController implements Initializable {

    @FXML
    private Button btnacceuilSpg;
    @FXML
    private Button btnsupprimerSpg;
    @FXML
    private Button btnmodifierSpg;
    @FXML
    private Button btnajoutererSpg;
    @FXML
    private DatePicker dDSO;

    @FXML
    private DatePicker dFSO;

    @FXML
    private TextArea txtDSO;
    @FXML
    private Button btnSponsoringOffer;
    @FXML
    private TextField idspg;
    @FXML
    private Button btnchercherspg;
    @FXML
    private Label msgModification;
    private int id;  //idspg pour la modification

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void findId(ActionEvent event) throws IOException {
        id = Integer.parseInt(idspg.getText());
        btnchercherspg.setOnAction(e -> {
            System.out.println("button find cliced");
            SponsoringOfferService sos = new SponsoringOfferService();
            SponsoringOffer so = new SponsoringOffer();
            so = sos.getById(id);
            if (so.isEmpty()) {

                System.out.println("not found");
                
            } else {
                System.out.println("found");
                id = so.getId();
                dDSO.setValue(so.getDateDebut().toLocalDate());
                dFSO.setValue(so.getDateFin().toLocalDate());
                txtDSO.setText(so.getDescription());
            }

        });
    }

    @FXML
    private void UpdateSO(ActionEvent event) throws IOException {
        //id = Integer.parseInt(idspg.getText());
        btnmodifierSpg.setOnAction(e -> {
            Date dateD = Date.valueOf(dDSO.getValue());
            Date dateF = Date.valueOf(dFSO.getValue());
            SponsoringOfferService sos = new SponsoringOfferService();
            SponsoringOffer so = new SponsoringOffer();
            so = sos.getById(id);

            so.setDateDebut(dateD);
            so.setDateFin(dateF);
            so.setDescription(txtDSO.getText());
            sos.updateOffrSonsoring(so);
            msgModification.setText("Votre Offre a ete modifier avec succée !");
        }
        );
    }

    @FXML
    private void Openaccm(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("afficherSponsoringOffer.fxml"));
            btnacceuilSpg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjouterSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("bbb");

        }
    }

    @FXML
    private void Openajoutm(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ajouterSponsoringOffer.fxml"));
            btnajoutererSpg.getScene().setRoot(root);
            System.out.println("button modifier cliced");
        } catch (IOException ex) {
            Logger.getLogger(AfficherSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("bbb");

        }
    }

    @FXML
    private void Opensupprimerm(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("supprimerSponsoringOffer.fxml"));
            btnsupprimerSpg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println("bbb");

        }
    }

}
