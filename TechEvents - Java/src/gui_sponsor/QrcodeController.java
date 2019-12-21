/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_sponsor;

import API.mail.Mail;
import entities.Ticket;
import entities.User;
import gui.SignInController;
import gui_events.View_EventsController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service_sponsor.QrService;
import service_sponsor.TicketService;

/**
 * FXML Controller class
 *
 * @author trabe
 */
public class QrcodeController implements Initializable {

    @FXML
    private Button btnacceuilSpg;
    @FXML
    private Button btngeticket;
    @FXML
    private TextField iduserT;
    @FXML
    private Label showmsg;
    @FXML
    private TextField ideventT;
    @FXML
    private ImageView qrImage;
    @FXML
    private Button btnSendMail;
    @FXML
    private TextField txtEmail;

    @FXML
    private Button btncreateqr;

    @FXML
    private TextField txtSujet;

    private final String ImagePath = "\\src\\gui_sponsor\\images\\";

    private User idUser;
    private int idEvent;
    public String imageFullPath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idUser = SignInController.session;
        idEvent = View_EventsController.selected.getID_event();
        iduserT.setText(Integer.toString(idUser.getId()));
        ideventT.setText(Integer.toString(idEvent));
        txtEmail.setText(idUser.getEmail());
        

    }

    public boolean verfierImage(int id1, int id2) {
        String req = "SELECT image_name FROM `tickets` WHERE `Id_user`=? and `ID_Event`=? ";

        if (req == null) {

            return false;
        }
        return true;
    }

    @FXML
    private void getQrCodeImage(ActionEvent event) throws IOException, SQLException {
        //idUser = Integer.parseInt(iduserT.getText());
        //idEvent = Integer.parseInt(ideventT.getText());
        idUser = View_EventsController.selected.getId_user();
        idEvent = View_EventsController.selected.getID_event();

        btngeticket.setOnAction(e -> {
            System.out.println(verfierImage(idUser.getId(), idEvent));
            if (!verfierImage(idUser.getId(), idEvent)) {
                System.out.println("image n'est pas existe ");

                QrService QS = new QrService();
                String uuidQr = QS.uuidRandom();
                String imageName = String.format("%d", System.currentTimeMillis());
                try {
                    QS.QrCodeGenerate(uuidQr, imageName);
                } catch (IOException ex) {
                    Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Ticket NewTicket = new Ticket(idUser.getId(), idEvent, uuidQr, imageName);
                try {
                    QS.saveQr(NewTicket);
                } catch (SQLException ex) {
                    Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("uuidQr : " + uuidQr);
                System.out.println("imageName : " + imageName);

            }

            System.out.println("image existe");

        }
        );

    }

    @FXML
    private void findT(ActionEvent event) throws IOException {
        //idUser = Integer.parseInt(iduserT.getText());
        idUser = View_EventsController.selected.getId_user();
        //idEvent = Integer.parseInt(ideventT.getText());
        idEvent = View_EventsController.selected.getID_event();
//        System.out.println("button find cliced" + idUser + idEvent);

        btngeticket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    System.out.println("button find2 cliced");

                    TicketService ts = new TicketService();
                    String imageName = ts.findTicket(idUser.getId(), idEvent);
                    if (imageName != "") {

                        System.out.println(imageName + ".jpg");
                        final String dir = System.getProperty("user.dir");

                        File file = new File(dir + ImagePath + imageName + ".jpg");

                        Image image = new Image(file.toURI().toString());
                        System.out.println(image);
                        qrImage.setFitHeight(100); //726
                        qrImage.setFitWidth(200); //500
                        qrImage.setImage(image);

                    } else {
                        final String dir = System.getProperty("user.dir");
                        File file = new File(dir + ImagePath + "notfound" + ".png");

                        Image image = new Image(file.toURI().toString());
                        System.out.println(image);
                        qrImage.setFitHeight(100); //726
                        qrImage.setFitWidth(200); //500
                        qrImage.setImage(image);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
// bassem.trabelsi.1@esprit.tn

    public void mailSend() throws SQLException {

        btnSendMail.setOnAction(e -> {
            try {
                System.out.println("button find2 cliced");
                String sujet = txtSujet.getText();
                String email = txtEmail.getText();
                TicketService ts = new TicketService();
                idUser.setId(Integer.parseInt(iduserT.getText())) ;
                idEvent = Integer.parseInt(ideventT.getText());

                final String dir = System.getProperty("user.dir");

                String ImageName = ts.findTicket(idUser.getId(), idEvent);
                String QrImagePath = dir + ImagePath + ImageName + ".jpg";
                Mail Letter = new Mail();

                Letter.envoyer(email, sujet, QrImagePath);
            } catch (SQLException ex) {
                Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void newQr() throws SQLException {

        btncreateqr.setOnAction(e -> {
            try {
                QrService QS = new QrService();
                String uuidQr = QS.uuidRandom();
                String imageName = String.format("%d", System.currentTimeMillis());

                try {
                    QS.QrCodeGenerate(uuidQr, imageName);
                } catch (IOException ex) {
                    Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
                }

                idUser.setId(Integer.parseInt(iduserT.getText()));
                idEvent = Integer.parseInt(ideventT.getText());
                Ticket NewTicket = new Ticket(idUser.getId(), idEvent, uuidQr, imageName);
                QS.saveQr(NewTicket);
                System.out.println("uuidQr : " + uuidQr);
                System.out.println("imageName : " + imageName);

            } catch (SQLException ex) {
                Logger.getLogger(QrcodeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
