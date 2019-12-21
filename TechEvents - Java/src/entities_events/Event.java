/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities_events;

import entities.User;
import java.io.IOException;
import java.sql.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ghathenus
 */
public class Event  {
    private int ID_event;
    private User Id_user; 
    private String Name;
    private Date Date;
    private String location;
    private int max_number;
    private String sponsors;
    private String description ;
    private String category;
private String fin_status;


    public Event() {
    }

    public Event(int ID_event, String Name) {
        this.ID_event = ID_event;
        this.Name = Name;
    }

    public Event (int ID_event, User Id_user,  String Name, Date Date, String location, int max_number, String sponsors, String description, 
            String category, String fin_status) {
       this.ID_event = ID_event;
       this.Id_user= Id_user;
        this.Name = Name;
        this.Date= Date;
        this.location = location;
        this.max_number = max_number;
        this.sponsors = sponsors;
        this.description= description;
        this.category = category;
        this.fin_status = fin_status;
    }
    

    public int getID_event() {
        return ID_event;
    }
    public User getId_user() {
        return Id_user;
    }

    public String getName() {
        return Name;
    }

    public Date getDate() {
        return Date;
    }

    public String getLocation() {
        return location;
    }

    public int getMax_Number() {
        return max_number;
    }

    public String getSponsors() {
        return sponsors;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getFin_Status() {
        return fin_status;
    }

    public void setID_event(int ID_event) {
        this.ID_event = ID_event;
    }

    public void setId_user(User Id_user) {
        this.Id_user = Id_user;
    }
    

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMax_number(int max_number) {
        this.max_number = max_number;
    }

    public void setSponsors(String sponsors) {
        this.sponsors = sponsors;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFin_Status(String fin_status) {
        this.fin_status = fin_status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.ID_event;
        hash = 59 * hash + Objects.hashCode(this.Id_user);
        hash = 59 * hash + Objects.hashCode(this.Name);
        hash = 59 * hash + Objects.hashCode(this.Date);
        hash = 59 * hash + Objects.hashCode(this.location);
        hash = 59 * hash + this.max_number;
        hash = 59 * hash + Objects.hashCode(this.sponsors);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.category);
        hash = 59 * hash + Objects.hashCode(this.fin_status);
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
        final Event other = (Event) obj;
        if (this.ID_event != other.ID_event) {
            return false;
        }
        if (this.max_number != other.max_number) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.sponsors, other.sponsors)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        if (!Objects.equals(this.fin_status, other.fin_status)) {
            return false;
        }
        if (!Objects.equals(this.Id_user, other.Id_user)) {
            return false;
        }
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{" + "ID_event=" + ID_event + ", Id_user=" + Id_user + ", Name=" + Name + ", Date=" + Date + ", location=" + location + ", max_number=" + max_number + ", sponsors=" + sponsors + ", description=" + description + ", category=" + category + ", fin_status=" + fin_status + '}';
    }

  
public void Event(Stage stage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Event.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
     public void CreateEve(Stage stage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("CreateEve.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
     public void ViewEvents(Stage stage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ViewEvents.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
}

