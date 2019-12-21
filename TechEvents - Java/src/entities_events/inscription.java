/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities_events;

import java.sql.Date;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class inscription  {
    private Event Id_event;
    private planned_event Num_plan; 
    private Date date;

    public inscription(Event Id_event, planned_event Num_plan, Date date) {
        this.Id_event = Id_event;
        this.Num_plan = Num_plan;
        this.date = date;
    }

    public Event getId_event() {
        return Id_event;
    }

    public planned_event getNum_plan() {
        return Num_plan;
    }

    public Date getDate() {
        return date;
    }

    public void setId_event(Event Id_event) {
        this.Id_event = Id_event;
    }

    public void setNum_plan(planned_event Num_plan) {
        this.Num_plan = Num_plan;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.Id_event);
        hash = 89 * hash + Objects.hashCode(this.Num_plan);
        hash = 89 * hash + Objects.hashCode(this.date);
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
        final inscription other = (inscription) obj;
        if (!Objects.equals(this.Id_event, other.Id_event)) {
            return false;
        }
        if (!Objects.equals(this.Num_plan, other.Num_plan)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "inscription{" + "Id_event=" + Id_event + ", Num_plan=" + Num_plan + ", date=" + date + '}';
    }
    
}
   
  
   
  
    
