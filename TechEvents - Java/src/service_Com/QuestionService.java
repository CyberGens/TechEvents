/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_Com;

import com.sun.java.accessibility.util.EventID;
import entite.Event;
import entite.Question;
import entite.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class QuestionService {
    private Connection cnx;

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private static QuestionService instance;

    public QuestionService() {
cnx=DataSource.getInstance().getConnexion();
    }

    public static QuestionService getInstance() {
        if (instance == null) {
            return new QuestionService();
        }
        return instance;
    }

   /* public boolean add(Question question) {

        boolean isAdded = false;
        try {

            String req = "insert into question (question,User_id,Maladie_id) values(?,?,?)";

            Connection cnx = DataSource.getInstance().getConnexion();
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);

            pst.setString(1, question.getQuestion());
            pst.setInt(2, question.getEvent().getId_user());
            pst.setInt(3, question.getEvent().getID_event());
            int result = pst.executeUpdate();

            if (result > 0) {
                isAdded = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }

    public boolean delete(Question question) {
        boolean isDeleted = false;
        try {

            Connection cnx = DataSource.getInstance().getConnexion();
            Statement ste = cnx.createStatement();

            int result = ste.executeUpdate("DELETE FROM question\n"
                    + "WHERE id=" + question.getId());

            if (result > 0) {
                isDeleted = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDeleted;
    }/*
    public ArrayList<Question> getByEventId(int Eventid) {
        ArrayList<Question> listQuestion = new ArrayList();
        try {
             Connection cnx=DataSource.getInstance().getConnexion();
            String req="SELECT * FROM question where Event_id=" + Eventid;
             Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            

            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                User user =UserService.getInstance().getById(rs.getInt("id_user"));
                Event event=EventService.getInstance().findById(rs.getInt("Event_id"));
                    question.setUser1(user);
                question.setEvent(event);
                listQuestion.add(question); 
          
     
    
    public Question getById(int id) {
        String req = "select * from sponsor_file where id_file=?";
        Question q = new Question();
        User u=new User();
       Event e =new Event();
        
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                q = new Question(rs.getInt("id"), rs.getString("question"), u,e);
                  
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   /* public Question getById(int id) {
        
        Question event = null;
        try {
            String req="SELECT * FROM event WHERE id_event=?";
            Connection cnx = DataSource.getInstance().getConnexion();
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery();

            while (rs.next()) {
                event = new Question();
                event.setId(rs.getInt("id"));
                event.setQuestion(rs.getString("question"));
                int idUser = rs.getInt("User_id");
                event.setUser1(UserService.getInstance().getById(idUser));
                event.setEvent(null);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }*/
}
