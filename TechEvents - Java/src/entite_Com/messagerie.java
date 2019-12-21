/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite_Com;

import entities.User;
import java.util.Objects;

/**
 *
 * @author USER
 */
public class messagerie {
    private int id_message;
    public User id_User;
    private String message;
    private String sujet; 

    public messagerie() {
    }

    public messagerie(int id_message, User id_User, String message, String sujet) {
        this.id_message = id_message;
        this.id_User = id_User;
        this.message = message;
        this.sujet = sujet;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public User getId_User() {
        return id_User;
    }

    public void setId_User(User id_User) {
        this.id_User = id_User;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id_message;
        hash = 53 * hash + Objects.hashCode(this.id_User);
        hash = 53 * hash + Objects.hashCode(this.message);
        hash = 53 * hash + Objects.hashCode(this.sujet);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final messagerie other = (messagerie) obj;
        if (this.id_message != other.id_message) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.id_User, other.id_User)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "messagerie{" + "id_message=" + id_message + ", id_User=" + id_User + ", message=" + message + ", sujet=" + sujet + '}';
    }



}

