/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Com;

import entities_events.Event;
import entite.Question;
import entite.Reponse;
import entite.SessionUser;
//import static gui.QuestionEController.chosenEvent;
//import static gui.QuestionEController.index;
//import static gui.QuestionEController.listReponse;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import jdk.nashorn.internal.runtime.regexp.joni.ast.ConsAltNode;
import service_Com.QuestionService;
import service_Com.ReponseService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class QuestionEController implements Initializable {

    public static Event chosenEvent;
    public static int index = 0;
    public static ArrayList<Reponse> listReponse = new ArrayList();
    public static int numReponseAdded = 0;

    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private RadioButton Oui;
    @FXML
    private RadioButton non;
    @FXML
    private Label labelQuestion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
ArrayList<Question> listQuestions=new ArrayList<Question>();

        QuestionService qs = new QuestionService();
        Question q = new Question();
        chosenEvent = new Event(10, "", null, "", 2, "", "", "", "", 2);

        //q = qs.getById(chosenEvent.getID_event());
        if (true) {
            listQuestions.add(q);
            ArrayList listReponse = new ArrayList();
            previousButton.setVisible(false);
        }

        System.out.println("response list: " + listReponse.toString());
        System.out.println("response list size: " + listReponse.size());

        final ToggleGroup group = new ToggleGroup();
        Oui.setToggleGroup(group);
        non.setToggleGroup(group);

        if (index < listQuestions.size()) {
            Question question = listQuestions.get(index);
            labelQuestion.setText(question.getQuestion());
            labelQuestion.setWrapText(true);
        }

        if (index == listQuestions.size() - 1) {
            nextButton.setText("Terminer");
        }

    }

    /* private void next(ActionEvent event) {

        if (!oneIsChecked()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("please select answer!");
            alert.show();
            return;
        }
      
        if (Oui.isSelected()) {
            Reponse rep = new Reponse("Oui", Session.getConnectedUser(), listQuestions.get(index));
            listReponse.add(rep);
        } else {
     
             Reponse rep = new Reponse("non", Session.getConnectedUser(), listQuestions.get(index));
          
          
              listReponse.add(rep);
        }
        index++;
        if (index >= listQuestions.size()) {
            // End
            System.out.println("liste reponses: " + listReponse);
            for (int i = 0; i < listReponse.size(); i++) {
                boolean isReponseAdded = ReponseService.getInstance().add(listReponse.get(i));
                if (isReponseAdded) {
                    numReponseAdded++;
                }
            }

            if (numReponseAdded == listReponse.size()) {
               /*
                GET RESULT
                
                int numOui = 0;
                for (int i = 0; i < listReponse.size(); i++) {
                    if (listReponse.get(i).getContenu().equals("oui")) {
                        numOui++;
                    }
                }
                double numOuiDouble = numOui;
                double resultMaladie = (numOuiDouble / listReponse.size()) * 100;
                String malade = "jawek fesfes";
                if (resultMaladie >= 50) {
                    malade = "mriiiiiiidh";
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Resultat maladie: " + chosenEvent.getName());
                alert.setContentText("Vous éte " + malade + ", avec pourcentage: " + resultMaladie + "%");
                alert.show();

                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/gui/ListEvents.fxml"));
                    Parent content = loader.load();
                    Scene scene = new Scene(content);

                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("error when adding Reponse!");
                alert.show();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/gui/QuestionE.fxml"));
                Parent content = loader.load();

                Scene scene = new Scene(content);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }*/

    private void previous(ActionEvent event) {
        if (index > 0) {
            index--;
            listReponse.remove(index);
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/gui/AjoutQuestion.fxml"));
                Parent content = loader.load();

                Scene scene = new Scene(content);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(QuestionEController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private boolean oneIsChecked() {
        return Oui.isSelected() || non.isSelected();
    }

    private void annuler(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/gui/ListEvents.fxml"));
            Parent content = loader.load();
            Scene scene = new Scene(content);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(QuestionEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
