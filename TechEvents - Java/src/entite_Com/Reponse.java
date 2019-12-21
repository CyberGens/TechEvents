/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite_Com;

import entite_Com.Question;
import entities.User;

/**
 *
 * @author esprit
 */
public class Reponse {
    
    private int id;
    private String contenu;
    private User user;
    private Question question;

    public Reponse() {
    }

    public Reponse(String contenu, User user, Question question) {
        this.contenu = contenu;
        this.user = user;
        this.question = question;
    }

    public Reponse(int id, String contenu, User user, Question question) {
        this.id = id;
        this.contenu = contenu;
        this.user= user;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", contenu=" + contenu + ", user=" + user+ ", question=" + question + '}';
    }

    
    
}
