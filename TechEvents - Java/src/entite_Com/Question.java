/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite_Com;

import entities.User;
import entities_events.Event;

/**
 *
 * @author esprit
 */
public class Question {
    
    private int id;
    private String question;
    private User user1;
    private Event event;

    public Question() {
    }

    public Question(int id, String question, User user1, Event event) {
        this.id = id;
        this.question = question;
        this.user1 = user1;
        this.event = event;
    }

    public Question(String question, User user1, Event event) {
        this.question = question;
        this.user1 = user1;
        this.event = event;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", question=" + question + ", user1=" + user1 + ", event=" + event + '}';
    }
  
    
}

 