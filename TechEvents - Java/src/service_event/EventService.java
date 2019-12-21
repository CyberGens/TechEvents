/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_event;

import entities_events.Event;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.IService;
import service.UserService;
import util.DataSource;

/**
 *
 * @author Ghathenus
 */
public class EventService implements IService<Event> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private List<Event> Event;
        public EventService() {
        cnx=DataSource.getInstance().getConnexion(); 
    }

    public void insertPST(Event t) {
        String req = "Insert into event (ID_event,Name,Date,"
                + "Location,Max_Number,Sponsors,Description,Category,Fin_Status,id_user) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getID_event());
            pst.setString(2, t.getName());
            pst.setDate(3, t.getDate());
            pst.setString(4, t.getLocation());
            pst.setInt(5, t.getMax_Number());
            pst.setString(6, t.getSponsors());
            pst.setString(7, t.getDescription());
            pst.setString(8, t.getCategory());
            pst.setString(9, t.getFin_Status());
            pst.setInt(10, t.getId_user().getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        String req = "delete from Event where(ID_event=" + id + ")";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Event t) {
        String req = "update Event set  Name=?,Date=?, Location=?,Max_Number=?, Sponsors=?,Description=?,Category=?,Fin_Status=?   where  ID_Event=?";
        try {
            pst = cnx.prepareStatement(req);
         
            pst.setString(1, t.getName());
            pst.setDate(2, t.getDate());
            pst.setString(3, t.getLocation());
            pst.setInt(4, t.getMax_Number());
            pst.setString(5, t.getSponsors());
            pst.setString(6, t.getDescription());
            pst.setString(7, t.getCategory());
            pst.setString(8, t.getFin_Status());
            pst.setInt(9, t.getID_event());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public List<Event> getAll() {
        String req = "select * from Event";
        List<Event> list = new ArrayList<>();
        UserService us= new UserService();
            
             User u =new User() ;

        try {
             UserService us1= new UserService();
            
             User u1 =new User() ;
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u1=us1.getById(rs.getInt("Id_user"));
                list.add(new Event(rs.getInt("ID_event"),u1, rs.getString("Name"), rs.getDate("Date"),
                        rs.getString("Location"), rs.getInt("Max_Number"), rs.getString("Sponsors"), rs.getString("Description"),
                        rs.getString("Category"), rs.getString("Fin_Status")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    
    
    public ArrayList<Event> getByName(String Name) {
        String req = "select * from Event where Name like '"+Name+"%'";
        List<Event> list = new ArrayList<>();

        try {
            UserService us1= new UserService();
            
             User u1 =new User() ;
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u1=us1.getById(rs.getInt("Id_user"));
                list.add(new Event(rs.getInt("ID_event"),u1, rs.getString("Name"), rs.getDate("Date"),
                        rs.getString("Location"), rs.getInt("Max_Number"), rs.getString("Sponsors"), rs.getString("Description"),
                        rs.getString("Category"), rs.getString("Fin_Status")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Event>) list;
    }
    
    
    
    

    public Event getById(int id) {
        String req = "select * from Event where ID_Event=?";
        Event event = new Event();
        {
            try {
                UserService us1= new UserService();
             User u1 =new User() ;
                pst = cnx.prepareStatement(req);

                pst.setInt(1, id);

                rs = pst.executeQuery();

                while (rs.next()) {
                     u1=us1.getById(rs.getInt("Id_user"));
                    event = new Event(rs.getInt("ID_event"),u1, rs.getString("Name"), rs.getDate("Date"),
                            rs.getString("Location"), rs.getInt("Max_Number"), rs.getString("Sponsors"), rs.getString("Description"),
                            rs.getString("Category"), rs.getString("Fin_Status"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }

            return event;
        }
    }
     public List<Event> getmyEvents(User u) {
        String req = "select * from Event where Id_user= "+u.getId();
        List<Event> list = new ArrayList<>();

        try {
            UserService us1= new UserService();
             User u1 =new User() ;
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                u1=us1.getById(rs.getInt("Id_user"));
                list.add(new Event(rs.getInt("ID_event"), u1,rs.getString("Name"), rs.getDate("Date"),
                        rs.getString("Location"), rs.getInt("Max_Number"), rs.getString("Sponsors"), rs.getString("Description"),
                        rs.getString("Category"), rs.getString("Fin_Status")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    


}
