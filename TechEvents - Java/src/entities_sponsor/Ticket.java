/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author trabe
 */
public class Ticket {

    private int Id_ticket;
    private int Id_user;
    private int ID_Event;
    private String QR_code;
    private String image_name;

    public Ticket() {
    }

    public Ticket(int Id_user, int ID_Event, String QR_code, String image_name) {
        this.Id_user = Id_user;
        this.ID_Event = ID_Event;
        this.QR_code = QR_code;
        this.image_name = image_name;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getId_ticket() {
        return Id_ticket;
    }

    public void setId_ticket(int Id_ticket) {
        this.Id_ticket = Id_ticket;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }

    public int getID_Event() {
        return ID_Event;
    }

    public void setID_Event(int ID_Event) {
        this.ID_Event = ID_Event;
    }

    public String getQR_code() {
        return QR_code;
    }

    public void setQR_code(String QR_code) {
        this.QR_code = QR_code;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Ticket other = (Ticket) obj;
        if (this.Id_ticket != other.Id_ticket) {
            return false;
        }
        if (this.Id_user != other.Id_user) {
            return false;
        }
        if (this.ID_Event != other.ID_Event) {
            return false;
        }
        if (!Objects.equals(this.QR_code, other.QR_code)) {
            return false;
        }
        if (!Objects.equals(this.image_name, other.image_name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ticket{" + "Id_ticket=" + Id_ticket + ", Id_user=" + Id_user + ", ID_Event=" + ID_Event + ", QR_code=" + QR_code + ", image_name=" + image_name + '}';
    }
    
    
    

}
