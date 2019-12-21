/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_lodger;

import entities_lodger.Local;
import gui.SignInController;
import service_lodger.LocalService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class HomeLocalController implements Initializable {

    /**
     * Initializes the controller class.
     */
 @FXML
    private TableView<Local> tv;
    @FXML
    private TableColumn<Local,Integer> cid;
    @FXML
    private TableColumn<Local,String> cnl;
    @FXML
    private TableColumn<Local,String> cal;
    @FXML
    private TableColumn<Local,Float> cpu;
    @FXML
    private TableColumn<Local,Float> csl;
    @FXML
    private TableColumn<Local,Float> ccl;
    
    @FXML
    private Button con;
    @FXML
    private Button img;
    @FXML
    private Button res;
    
    public static Local l;
    @FXML
    private Button btn_back;
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     LocalService ls=new LocalService();
        int id=SignInController.session.getId();
        System.out.println(id);
        ArrayList<Local> pers=(ArrayList<Local>) ls.readAll();
        ObservableList<Local> obs=FXCollections.observableArrayList(pers);
        tv.setItems(obs);
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cal.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cpu.setCellValueFactory(new PropertyValueFactory<>("prix"));
        csl.setCellValueFactory(new PropertyValueFactory<>("surface"));
        ccl.setCellValueFactory(new PropertyValueFactory<>("capacite"));
    img.setOnAction(e->{
        //(String id,String nom, String adresse, String prix, String surface,String capacite)
        l=tv.getSelectionModel().getSelectedItem();
            if (!(l==null))
            {
        try {
              Parent root ;
              root=FXMLLoader.load(getClass().getResource("View.fxml"));
              img.getScene().setRoot(root);
                      
                      } catch (IOException ex) {
              Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
          }
                      
        }
      });
    res.setOnAction(e->{
     l=tv.getSelectionModel().getSelectedItem();
            if (!(l==null))
            {
        try {
              Parent root ;
              root=FXMLLoader.load(getClass().getResource("AjouterReservation.fxml"));
              con.getScene().setRoot(root);
                      
                      } catch (IOException ex) {
              Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
          }
                      
        }    
    
    });
    btn_back.setOnAction(e->{
        //(String id,String nom, String adresse, String prix, String surface,String capacite)
         
        try {
              Parent root ;
              root=FXMLLoader.load(getClass().getResource("../gui/welcome.fxml"));
              btn_back.getScene().setRoot(root);
                      
                      } catch (IOException ex) {
              Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
          }    
      });
    con.setOnAction(e->{
     l=tv.getSelectionModel().getSelectedItem();
            if (!(l==null))
            {
        try {
              Parent root ;
              root=FXMLLoader.load(getClass().getResource("VoirContactLoc.fxml"));
              con.getScene().setRoot(root);
                      
                      } catch (IOException ex) {
              Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
          }
                      
        }    
    
    });
    
    }    
//public Local getL(){return l;}    
}
