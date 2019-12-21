/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_lodger;

import entities_lodger.Local;
import entities_lodger.ReservationLoc;
import entities.User;
import gui.SignInController;
import service_lodger.ReservationLocService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mail.Mailer;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class AjouterReservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label iw;
    @FXML
    private Label sw;
    
     @FXML
    private TextField id;
     @FXML
    private DatePicker dd;
     @FXML
    private DatePicker df;

    @FXML
    private Label ddw;
    @FXML
    private Label dfw;
    @FXML
    private Button v;
    @FXML
    private Button r;
    @FXML
    private Button retour;
    
    private Local l;
    private boolean ok;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*private int idReservation;
private User idOwner;
private int idLoc;
private User idUser;
private Date dateDeb;
private Date dateFin;*/
     dd.setValue(LocalDate.now());
     df.setValue(LocalDate.now());
    
    r.setOnAction(e -> {
            dd.setValue(LocalDate.now());
            df.setValue(LocalDate.now());
        });
        retour.setOnAction(e -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("HomeLocal.fxml"));
                v.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ModifierLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        r.setOnAction(e -> {
            
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("UserHome.fxml"));
                r.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ModifierLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        v.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                
            ok=true;
            if (dd.getValue().isBefore(LocalDate.now()))
            {alert.setContentText("Date non valide");
                alert.show(); 
                ddw.setText("donnez une date valide");
            ok=false;}
            if (df.getValue().isBefore(LocalDate.now()))
            {alert.setContentText("Date non valide");
                alert.show(); 
                dfw.setText("donnez une date valide");
            ok=false;}
            if (ok==true){
                sw.setText("Succés");
        Date dateDeb=Date.valueOf(dd.getValue());
        Date dateFin=Date.valueOf(df.getValue());
        HomeLocalController h=new HomeLocalController();
        User locataire =HomeLocalController.l.getUser();
        User user=SignInController.session;
        ReservationLoc r= new ReservationLoc(locataire,HomeLocalController.l.getId(), user, dateDeb, dateFin);
        ReservationLocService rls= new ReservationLocService();
        rls.insert(r);
        Mailer.send("zouhaira.gasmi@esprit.tn","183JFT1704",locataire.getEmail(),"Réservation TechEvents",
                "Votre Local"+HomeLocalController.l.getNom()+"a été reservé par:"+user.getUsername()
                        +".\n"+"vous pouvez le contacter par son mail:"+user.getEmail()+"ou par son numéro de telephone"
                        +user.getPhone());
        Mailer.send("zouhaira.gasmi@esprit.tn","183JFT1704",user.getEmail(),"Réservation TechEvents",
                "le Local"+HomeLocalController.l.getNom()+"a été reservé"+user.getUsername()
                        +".\n"+"vous pouvez le contacter le locataire"+locataire.getUsername()+" par son mail:"+user.getEmail()+"ou par son numéro de telephone"
                        +locataire.getPhone());
            }
        });    
    }    
    
}
