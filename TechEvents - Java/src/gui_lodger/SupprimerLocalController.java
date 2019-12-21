/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_lodger;

import entities_lodger.Local;
import service_lodger.LocalService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Brahim
 */

public class SupprimerLocalController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Label iw;
    
     @FXML
    private TextField id;

    @FXML
    private Button v;
    @FXML
    private Button a;
    @FXML
    private Button retour;
    
    private Local l;
    private boolean ok;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    GestionLocalController alc = new GestionLocalController();
        l = alc.getL();
        if (l.getUser().getId() > 0) 
            id.setText(String.valueOf(l.getUser().getId()));
        v.setOnAction(e -> {
            LocalService ls=new LocalService();
            ok = true;
             if (id.getText().length() == 0 || !id.getText().matches("\\d+")) {
                String ch="";
                ok = false;
                ch="donnez un entier\nvalide";
            } else if (!ls.VerifIdLoc(Integer.valueOf(id.getText()))){
                iw.setText("Local inexistant\nou vous avez pas le droit");
            }
            else iw.setText("");
        if(ok==true){
            try { //modifier(int id,String nom, String adresse, float prix, float surface,int capacite)
                    ls.delete(Integer.valueOf(id.getText()));

                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("GestionLocal.fxml"));
                    v.getScene().setRoot(root);

                } catch (IOException ex) {
                    Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        a.setOnAction(e -> {
            id.setText("");
        });
        retour.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("GestionLocal.fxml"));
                retour.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ModifierLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        }
    
    }    
    

