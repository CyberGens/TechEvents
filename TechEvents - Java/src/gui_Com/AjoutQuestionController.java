/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Com;

import entite.Event;
import entite.Question;
import entite.SessionUser;
import entite.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.EventService;
import service.QuestionService;
import java.awt.Container;
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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutQuestionController implements Initializable {
      public static String nomEvent= "";
    public static ArrayList<String> listQuestions = new ArrayList();
    @FXML
    private Label labelQuestion;
    @FXML
    private TextArea Question;
    @FXML
    private Button qstnr_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qstnr_btn.setOnAction(e -> {
  Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("QuestionE.fxml"));
                 qstnr_btn.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(ReclamationUSERController.class.getName()).log(Level.SEVERE, null, ex);
        // TODO
        int index = listQuestions.size() + 1;
        String label = "Question: " + index;
        labelQuestion.setText(label);
    }
@FXML
    private void goBack(MouseEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/AjoutQuestion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void next(ActionEvent event) {
        String questionText = Question.getText();
        if (questionText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Veuiller entrer une question!");
            alert.show();
        } else {
            try {
                listQuestions.add(questionText);

                Parent root = FXMLLoader.load(getClass().getResource("/gui/AjoutQuestion.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjoutQuestionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void finish(ActionEvent event) {
        String questionText = Question.getText();
        if (questionText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.setTitle("");
            alert.setHeaderText(null);
            alert.setContentText("Veuiller entrer une question!");
            alert.show();
        } else {
            listQuestions.add(questionText);

            User u = new User();
         //   int idEventGenerated = EventService.getInstance().insertPST(new Event(nomEvent,u ));
         int idEventGenerated = 1;
            if (idEventGenerated == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("error event!");
                alert.show();
            } else {
                boolean isAllAdded = true;

                QuestionService qDao = QuestionService.getInstance();
                for (int i = 0; i < listQuestions.size(); i++) {
                    Event t = null;
                    Event addedEvent = EventService.getInstance().getById(t);
                    boolean isQuestionAdded = qDao.add(new Question(listQuestions.get(i), SessionUser.getConnectedUser(), addedEvent));
                    if (!isQuestionAdded) {
                        isAllAdded = false;
                    }
                }

                if (isAllAdded) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Questionss insérée avec succés!");
                    alert.show();

                    try {

                        Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLMaladies.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(AjoutQuestionController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("erreur!");
                    alert.show();
                }
            }

        }

    }

    private void cancel(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/view/article/FXMLMaladies.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

