/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_events;

import entities_events.Event;
import entities_events.planned_event;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Ghathenus
 */
public class EventPlannedController implements Initializable {
    Window wind;


    @FXML
    private Label ID_EVENT1;
    @FXML
    private Button Menu_btn;
    @FXML
    private Pane pane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ID_EVENT1.setText(Integer.toString(Plan_EventController.pe1.getID_event().getID_event()));
        // TODO
    }    

    @FXML
    private void Print_pdf(ActionEvent event) {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.showPrintDialog(wind);
            job.printPage(pane);
            job.endJob();
    }
        
          Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("Plan_Event.fxml"));
        } catch (IOException ex) {

        }
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    
}
}
