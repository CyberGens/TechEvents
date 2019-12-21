/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Com;



import entite.messagerie;
import entite_Com.reclamation;
//import static gui_com.RepondreRexlamationController.ACCOUNT_SID;
//import static gui_com.RepondreRexlamationController.AUTH_TOKEN;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class RepondreRexlamationController implements Initializable {
     public static final String ACCOUNT_SID = "AC87f425f95dbe9a31beffd44419d948c2";
    public static final String AUTH_TOKEN = "0c6364671d0999bf3d9f9a972c0bea31";

    @FXML
    private Button Repondre;
    @FXML
    private Button Annuler;
    @FXML
    private Button Retour;
    @FXML
    private Text user;
    @FXML
    private Text objet;
    @FXML
    private TextArea Description;
    @FXML
    private TextArea rep;
    private reclamation recc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          recc = ReclamationAdminController.AssociationListCell.reclam;
          
          
          
        /* rep.setText(recc.getDescription());*/
        rep.setEditable(false);
/*        objet.setText(recc.getObjet());*/
/*        user.setText(recc.getuser().getFirstname() + recc.getuser().getLastname());*/
    }

    @FXML
    private void ajout(ActionEvent event) {
        if (rep.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OPPS");
            alert.setHeaderText("Erreur !!");
            alert.setContentText("SVp Remplire le Champ de Reponse  !!!");

                alert.showAndWait();
            
        }
       
        /* else {

            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            messagerie message =messagerie.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+21654293983"),
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    rep.getText())
                    .create();
            rep.setText("");
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OPPS");
            alert.setHeaderText("Erreur !!");
            alert.setContentText("Message Envoyer avec Succées ");

                alert.showAndWait();
        }*/
    }

    @FXML
    private void annuler(ActionEvent event) {
        rep.setText("");
    }

    @FXML
    private void Retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/reclamationAdmin.fxml"));
            Parent TableViewParent = loader.load();
            Scene scene = new Scene(TableViewParent);
            Stage primaryStage = (Stage) ((Node) (event.getSource())).getScene().getWindow();

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    
}

} 