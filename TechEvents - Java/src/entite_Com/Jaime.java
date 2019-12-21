/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author USER
 */
public class Jaime {
    private int Id_User;
    private int Id_Sujet;
    private int valeur_jaime;

    public Jaime() {
    }

    public Jaime(int Id_user, int Id_Sujet, int valeur_jaime) {
        this.Id_User = Id_user;
        this.Id_Sujet = Id_Sujet;
        this.valeur_jaime = valeur_jaime;
    }

    public int getId_user() {
        return Id_User;
    }

    public void setId_user(int Id_user) {
        this.Id_User = Id_user;
    }

    public int getId_Sujet() {
        return Id_Sujet;
    }

    public void setId_Sujet(int Id_Sujet) {
        this.Id_Sujet = Id_Sujet;
    }

    public int getValeur_jaime() {
        return valeur_jaime;
    }

    public void setValeur_jaime(int valeur_jaime) {
        this.valeur_jaime = valeur_jaime;
    }
}

