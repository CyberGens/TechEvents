/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamail;

/**
 *
 * @author ASUS
 */
public class Mail {
     private String host;
    private String user;
    private String pass;
    private String to;
    private String from;
    private String subject;
    private String messageText;

    public Mail() {
        this.host = "smtp.gmail.com";
    }

    public Mail(String user, String pass, String to, String subject, String messageText) {
        this.host = "smtp.gmail.com";
        this.user = user;
        this.pass = pass;
        this.to = to;
        this.from = user;
        this.subject = subject;
        this.messageText = messageText;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    
}