/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;
import service_event.EventService;

/**
 *
 * @author Souha
 */
public class Rating {
     int Id_user ;   
     int Id_event;
     int Stars; 
     String Name  ;
     

    public Rating(){
    }

    public Rating(int Id_user, int Id_event, int Stars) {
        this.Id_user = Id_user;
        this.Id_event = Id_event;
        this.Stars = Stars;
        EventService es=new EventService();
        this.Name = es.getById(Id_event).getName();
    }
    
 
    public Rating(int Id_user, int Id_event, int Stars, String Name) {
        this.Id_user = Id_user;
        this.Id_event = Id_event;
        this.Stars = Stars;
        
    }
     

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
     
     
     
    public void setId(int Id_user) {
        this.Id_user = Id_user;
    }

    public void setId_event(int Id_event) {
        this.Id_event = Id_event;
    }

    public void setStars(int Stars) {
        this.Stars = Stars;
    }

    public int getId_user() {
        return Id_user;
    }

    public int getId_event() {
        return Id_event;
    }

    public int getStars() {
        return Stars;
    }

    @Override
    public String toString() {
        return "RatingEnt{" + "Id_user=" + Id_user + ", Id_event=" + Id_event + ", Stars=" + Stars + ", Name=" + Name + '}';
    } 
}
