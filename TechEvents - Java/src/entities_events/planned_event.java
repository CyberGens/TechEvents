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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class planned_event  {
    private Event ID_event;
    private User ID;
    private int Num_plan;
    private String Level_interest;
    
    public planned_event() {
    }

    public planned_event (Event ID_event, User ID, int Num_plan, String Level_interest ){
       this.ID_event = ID_event;
        this.ID = ID;
        this.Num_plan= Num_plan;
        this.Level_interest  = Level_interest ;
      
    }

    public Event getID_event() {
        return ID_event;
    }

    public User getID() {
        return ID;
    }

    public int getNum_plan() {
        return Num_plan;
    }

    public String getLevel_interest() {
        return Level_interest;
    }

    public void setID_event(Event ID_event) {
        this.ID_event = ID_event;
    }

    public void setID(User ID) {
        this.ID = ID;
    }

    public void setNum_plan(int Num_plan) {
        this.Num_plan = Num_plan;
    }

    public void setLevel_interest(String Level_interest) {
        this.Level_interest = Level_interest;
    }

    @Override
    public String toString() {
        return "planned_event{" + "ID_event=" + ID_event + ", ID=" + ID + ", Num_plan=" + Num_plan + ", Level_interest=" + Level_interest + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.ID_event);
        hash = 29 * hash + Objects.hashCode(this.ID);
        hash = 29 * hash + this.Num_plan;
        hash = 29 * hash + Objects.hashCode(this.Level_interest);
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
        final planned_event other = (planned_event) obj;
        if (this.ID_event != other.ID_event) {
            return false;
        }
        if (this.ID != other.ID) {
            return false;
        }
        if (this.Num_plan != other.Num_plan) {
            return false;
        }
        if (!Objects.equals(this.Level_interest, other.Level_interest)) {
            return false;
        }
        return true;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
  public void Plan_Event(Stage stage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Plan_Event.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
 }

    


