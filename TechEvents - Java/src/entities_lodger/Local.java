/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities_lodger;

import entities.User;

/**
 *
 * @author Brahim
 */
public class Local {
private int id;
private User user;
private String nom;
private String adresse;
private float prix;
private float surface;
private int capacite;
private String img;

    public Local(String nom, String adresse, float prix, float surface, int capacite,String img) {
        this.nom = nom;
        this.adresse = adresse;
        this.prix = prix;
        this.surface = surface;
        this.capacite = capacite;
        this.img=img;
    }

    public Local(int id,User user, String nom, String adresse, float prix, float surface, int capacite,String img) {
        this.user = user;
        this.id=id;
        this.nom = nom;
        this.adresse = adresse;
        this.prix = prix;
        this.surface = surface;
        this.capacite = capacite;
        this.img=img;
    }
    public Local(User user, String nom, String adresse, float prix, float surface, int capacite,String img) {
        this.user = user;
        this.nom = nom;
        this.adresse = adresse;
        this.prix = prix;
        this.surface = surface;
        this.capacite = capacite;
        this.img=img;
    }

    public String getImg() {
        return img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Local(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public Local() {

    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }



    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

  

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Local{" + "User=" + user.toString()+"\n" + ", nom=" + nom + ", adresse=" + adresse + ", prix=" + prix + ", surface=" + surface + ", capacite=" + capacite + ", img=" + img + '}';
    }

   
}
