/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author fahdj
 */
public class Comments {
    private int id;

    
   private String commentaire;
   private String date;
   private String username;
public Comments(int id, String commentaire, String date, String username) {
        this.id = id;
        this.commentaire = commentaire;
        this.date = date;
        this.username = username;
    }

    public int getId() {
        return id;
    }







    public Comments(String commentaire, String date, String username) {
        this.commentaire = commentaire;
        this.date = date;
        this.username = username;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Comments{" + "commentaire=" + commentaire + ", date=" + date + ", username=" + username + '}';
    }
   
   
}
