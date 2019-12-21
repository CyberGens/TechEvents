/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Com;

import entite.reclamation;
import gui.ReclamationUSERController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service_Com.ReclamationService;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModiferRecController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private TextField idrectxt;
    @FXML
    private TextField usertxt;
    @FXML
    private TextField objettxt;
    @FXML
    private TextField etattxt;
    @FXML
    private TextField descriptiontxt;
    @FXML
    private Button btnupdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        descriptiontxt.setText(ReclamationUSERController.sr.getDescription());
          etattxt.setText(Boolean.toString(ReclamationUSERController.sr.isEtat()));
        idrectxt.setText(String.valueOf(ReclamationUSERController.sr.getId_Reclamation()));
        objettxt.setText(ReclamationUSERController.sr.getObjet());
        usertxt.setText(String.valueOf(ReclamationUSERController.sr.getUser()));
        
        
        
    }    

    @FXML
    private void goback(ActionEvent event) {
        
                
                
    }

    @FXML
    private void cnfupdate(ActionEvent event) {
        ReclamationService recserv = new ReclamationService();
        reclamation r=new reclamation(Integer.valueOf(idrectxt.getText()), objettxt.getText(), descriptiontxt.getText(), Boolean.valueOf(etattxt.getText()),Integer.valueOf(usertxt.getText()) );
        recserv.insert(r);
        
        
    }
    
}
