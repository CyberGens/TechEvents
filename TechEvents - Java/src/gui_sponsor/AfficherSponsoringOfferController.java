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
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import service.SponsoringOfferService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class AfficherSponsoringOfferController implements Initializable {

    @FXML
    private TableView<SponsoringOffer> table;

    @FXML
    private TableColumn<SponsoringOffer, Integer> idOffrSpg;

    @FXML
    private TableColumn<SponsoringOffer, Date> dtD;

    @FXML
    private TableColumn<SponsoringOffer, Date> dtF;

    @FXML
    private TableColumn<SponsoringOffer, String> descSpg;

    @FXML
    private Button btnajouterSpg;

    @FXML
    private Button btnsupprimerSpg;

    @FXML
    private Button btnmodifierSpg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        SponsoringOfferService sos = new SponsoringOfferService();
        ArrayList<SponsoringOffer> spon = (ArrayList<SponsoringOffer>) sos.afficherListSponsoringOffer();
        ObservableList<SponsoringOffer> obs = FXCollections.observableArrayList(spon);
        table.setItems(obs);
        idOffrSpg.setCellValueFactory(new PropertyValueFactory<>("id"));
        dtD.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dtF.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        descSpg.setCellValueFactory(new PropertyValueFactory<>("Description"));

    }

    @FXML
    private void Openajout(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ajouterSponsoringOffer.fxml"));
            btnajouterSpg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjouterSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("bbb");

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
            System.err.println("bbb");

        }
    }

    @FXML
    private void Opensupprimer(ActionEvent event) throws IOException {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("supprimerSponsoringOffer.fxml"));
            btnsupprimerSpg.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherSponsoringOfferController.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("bbb");

        }
    }

}
