/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_lodger;

import entities_lodger.Local;
import service_lodger.LocalService;
import com.itextpdf.text.BaseColor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import gui.SignInController;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Brahim
 */
public class GestionLocalController implements Initializable {

    @FXML
    private TableView<Local> tv;
    @FXML
    private TableColumn<Local, Integer> cid;
    @FXML
    private TableColumn<Local, String> cnl;
    @FXML
    private TableColumn<Local, String> cal;
    @FXML
    private TableColumn<Local, Float> cpu;
    @FXML
    private TableColumn<Local, Float> csl;
    @FXML
    private TableColumn<Local, Float> ccl;
    @FXML
    private Button ajouter;
    @FXML
    private Button gpdf;
    @FXML
    private Button view;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    private static Local l;
    private Stage stage;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*private int id;
private String nom;
private String adresse;
private float prix;
private float surface;
private int capacite;*/

        LocalService ls = new LocalService();
        int id = SignInController.session.getId();
        System.out.println(id);
        ArrayList<Local> pers = (ArrayList<Local>) ls.readById(id);
        ObservableList<Local> obs = FXCollections.observableArrayList(pers);
        tv.setItems(obs);
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cnl.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cal.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        cpu.setCellValueFactory(new PropertyValueFactory<>("prix"));
        csl.setCellValueFactory(new PropertyValueFactory<>("surface"));
        ccl.setCellValueFactory(new PropertyValueFactory<>("capacite"));

        supprimer.setOnAction(e -> {
            //Local(String nom, String adresse, float prix,float surface,int capacite)

            try {
                l = tv.getSelectionModel().getSelectedItem();
                if (!(l == null)) {
                    l = new Local();
                }
                Parent root;
                root = FXMLLoader.load(getClass().getResource("SupprimerLocal.fxml"));
                supprimer.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        modifier.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            l = tv.getSelectionModel().getSelectedItem();
            if (!(l == null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("ModifierLocal.fxml"));
                    modifier.getScene().setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(GestionLocalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        btn_back.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("../gui/welcome.fxml"));
                btn_back.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ajouter.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("AjouterLocal.fxml"));
                ajouter.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        view.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)
            l = tv.getSelectionModel().getSelectedItem();
            if (!(l == null)) {
                try {
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("View.fxml"));
                    ajouter.getScene().setRoot(root);

                } catch (IOException ex) {
                    Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_back.setOnAction(e -> {
            //(String id,String nom, String adresse, String prix, String surface,String capacite)

            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("../gui/welcome.fxml"));
                btn_back.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AjouterLocalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        gpdf.setOnAction(e -> {
            GeneratePdf();
        });
    }

    public Local getL() {
        return l;
    }

    public static void GeneratePdf() {
        Document document = new Document();

        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Admin\\Desktop\\MesLocaux.pdf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestionLocalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GestionLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.open();
        document.addTitle("Liste des locaux");
        document.addSubject("Techevents");
        document.addAuthor("CyberGen");
        document.addCreator("Aymen Brahim");
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("Liste des locaux:"));
        preface.add(new Paragraph("Repport genéré par: " + System.getProperty("user.name") + ", le" + new Date()));
        try {
            document.add(preface);
        } catch (DocumentException ex) {
            Logger.getLogger(GestionLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfPTable table = new PdfPTable(6); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        //Set Column widths
        float[] columnWidths = {0.75f, 2f, 2.5f, 1f, 1f, 1f};
        try {
            table.setWidths(columnWidths);
        } catch (DocumentException ex) {
            Logger.getLogger(GestionLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfPCell cell1 = new PdfPCell(new Paragraph("Id"));
        cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell2 = new PdfPCell(new Paragraph("Nom"));
        cell2.setBackgroundColor(BaseColor.GRAY);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell3 = new PdfPCell(new Paragraph("Adresse"));
        cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell4 = new PdfPCell(new Paragraph("Prix"));
        cell4.setBackgroundColor(BaseColor.GRAY);
        cell4.setPaddingLeft(10);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell5 = new PdfPCell(new Paragraph("Surface"));
        cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell5.setPaddingLeft(10);
        cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell6 = new PdfPCell(new Paragraph("Capacité"));
        cell6.setBackgroundColor(BaseColor.GRAY);
        cell6.setPaddingLeft(10);
        cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
        //To avoid having the cell border and the content overlap, if you are having thick cell borders
        //cell1.setUserBorderPadding(true);
        //cell2.setUserBorderPadding(true);
        //cell3.setUserBorderPadding(true);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        LocalService ls = new LocalService();

        ls.readById(GestionLocalController.l.getId()).forEach((Local loc) -> {
            PdfPCell cell11 = new PdfPCell(new Paragraph(String.valueOf(loc.getUser().getId())));
            cell11.setPaddingLeft(10);
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell21 = new PdfPCell(new Paragraph(loc.getNom()));
            cell21.setPaddingLeft(10);
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell31 = new PdfPCell(new Paragraph(loc.getAdresse()));
            cell31.setPaddingLeft(10);
            cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell41 = new PdfPCell(new Paragraph(String.valueOf(loc.getPrix())));
            cell41.setPaddingLeft(10);
            cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell51 = new PdfPCell(new Paragraph(String.valueOf(loc.getSurface())));
            cell51.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell51.setPaddingLeft(10);
            cell51.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell cell61 = new PdfPCell(new Paragraph(String.valueOf(loc.getCapacite())));
            cell61.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell61.setPaddingLeft(10);
            cell61.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell11.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell21.setBackgroundColor(BaseColor.GRAY);
            cell31.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell41.setBackgroundColor(BaseColor.GRAY);
            cell51.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell61.setBackgroundColor(BaseColor.GRAY);

            table.addCell(cell11);
            table.addCell(cell21);
            table.addCell(cell31);
            table.addCell(cell41);
            table.addCell(cell51);
            table.addCell(cell61);
        });

        try {
            document.add(table);
        } catch (DocumentException ex) {
            Logger.getLogger(GestionLocalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        document.close();
        writer.close();
    }
}
