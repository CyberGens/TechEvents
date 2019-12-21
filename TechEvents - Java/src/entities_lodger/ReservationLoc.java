/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities_lodger;

import entities.User;
import java.sql.Date;

/**
 *
 * @author Brahim
 */
public class ReservationLoc {
private int idReservation;
private User idOwner;
private int idLoc;
private User idUser;
private Date dateDeb;
private Date dateFin;
    
    public ReservationLoc(int idReservation, User idOwner, int idLoc, User idUser, Date dateDeb, Date dateFin) {
        this.idReservation = idReservation;
        this.idOwner = idOwner;
        this.idLoc = idLoc;
        this.idUser = idUser;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }

    public ReservationLoc(User idOwner, int idLoc,User idUser,Date dateDeb, Date dateFin) {
        this.idOwner = idOwner;
        this.idLoc = idLoc;
        this.idUser = idUser;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
    }
    @Override
    public String toString() {
        return "ReservationLoc{" + "idReservation=" + idReservation + ", idOwner=" + idOwner + ", idLoc=" + idLoc + ", idUser=" + idUser + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + '}';
    }
    
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public User getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(User idOwner) {
        this.idOwner = idOwner;
    }

    public int getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(int idLoc) {
        this.idLoc = idLoc;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    
}
