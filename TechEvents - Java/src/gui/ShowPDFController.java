/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.SponsorFile;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ShowPDFController implements Initializable {

    @FXML
    private Label application;
    @FXML
    private Label date;
    @FXML
    private Button btn_back;
    @FXML
    private Label description;
    Window wind;
    @FXML
    private Pane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SponsorFile sf=new SponsorFile();
        sf=ShowApplicationsController.selected;
        application.setText(sf.getId_user().getUsername()+
                "'s " + sf.getType()+ " Application");
        date.setText("Issued on: " + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        description.setText("Congratulations "+sf.getId_user().getUsername()+
                "! Your "+sf.getType()+" application has been accepted. Your have become a "
                +sf.getType()+" and will have access to new features and functionalities!");
        
    }

    @FXML
    private void Print(ActionEvent event) throws IOException {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.showPrintDialog(wind);
            job.printPage(pane);
            job.endJob();
        }

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("showApplications.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

}
