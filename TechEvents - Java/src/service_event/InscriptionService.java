/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_event;

import entities_events.Event;
import entities.User;
import entities_events.planned_event;
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
public class InscriptionService implements IService<planned_event> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private List<planned_event> Event;
        public InscriptionService() {
        cnx=DataSource.getInstance().getConnexion(); 
    }

    public void insertPST(planned_event t) {
        String req = "Insert into planned_event (ID_event,Id_user,Num_plan,Level_interest)  values (?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getID_event().getID_event());
            pst.setInt(2, t.getID().getId());
            pst.setInt(3, t.getNum_plan());
            pst.setString(4, t.getLevel_interest());           
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "delete from Event where(id=" + id + ")";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(planned_event t) {
        String req = "update planned_event set ID_event=?, Id_user=?,Num_plan=?, Level_interest=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getID_event().getID_event());
            pst.setInt(2, t.getID().getId());
            pst.setInt(3, t.getNum_plan());
            pst.setString(4, t.getLevel_interest());
          

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public List<planned_event> getAll() {
        String req = "select * from planned_event";
        List<planned_event> list = new ArrayList<>();
             
             
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
             EventService es= new EventService();
             UserService us= new UserService();
             Event e =new Event() ;
             User u =new User() ;
            while (rs.next()) {
               e=es.getById(rs.getInt("ID_event"));
               u=us.getById(rs.getInt("Id_user"));
                list.add(new planned_event(e,u, rs.getInt("Num_plan"), rs.getString("Level_interest")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public planned_event getById(int id) {
        String req = "select * from planned_event where ID_Event=?";
        EventService es= new EventService();
             UserService us= new UserService();
             Event e =new Event() ;
             User u =new User() ;
              planned_event p= new planned_event();
        {
            try {
                pst = cnx.prepareStatement(req);

                pst.setInt(1, id);

                rs = pst.executeQuery();

                while (rs.next()) {
                    e=es.getById(rs.getInt("ID_event"));
               u=us.getById(rs.getInt("Id_user"));
               p= new planned_event(e,u, rs.getInt("Num_plan"), rs.getString("Level_interest"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(InscriptionService.class.getName()).log(Level.SEVERE, null, ex);
            }

            return p;
        }
    }

    public Event getById(Event t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public planned_event getById(planned_event t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
