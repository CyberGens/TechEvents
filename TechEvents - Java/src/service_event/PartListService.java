/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_event;

import entities_events.Event;
import entities.User;
import entities_events.inscription;
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
import util.DataSource;

/**
 *
 * @author Ghathenus
 */
public class PartListService implements IService<inscription> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private List<inscription> inscription;
        public PartListService() {
        cnx=DataSource.getInstance().getConnexion(); 
    }

    public void insertPST(inscription p) {
        
        String req = "Insert into participants_list (ID_event,Num_plan,date) values (?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1,p.getId_event().getID_event());
            pst.setInt(2,p.getNum_plan().getNum_plan());
            pst.setDate(3, p.getDate());
           
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @Override
    public void update(inscription t) {
        String req = "update participants_list ID_event=?, Id_inscription=?,Date=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId_event().getID_event());
            pst.setInt(2, t.getNum_plan().getNum_plan());
            pst.setDate(3, t.getDate());
           

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<inscription> getAll() {
         String req = "select * from participants_list";
            ArrayList<inscription> list = new ArrayList<>();
        try {
           
            
            
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                EventService es= new EventService();
             InscriptionService is= new InscriptionService();      
                planned_event i =new planned_event() ;
                Event e =new Event() ;
                while (rs.next()) {
                    e=es.getById(rs.getInt("ID_event"));
                    i=is.getById(rs.getInt("Num_plan"));
                    
                    
                    list.add(new inscription(e, i, rs.getDate("Date")));
                    
                }
            }
        } catch (SQLException ex) { 
            Logger.getLogger(PartListService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        }
        
    

    public planned_event getById(planned_event t) {
        String req = "select * from planned_event where ID_Event=?";
        Event event = new Event();
        {
            try {
                 EventService es= new EventService();
                
             InscriptionService is= new InscriptionService();    
             planned_event i =new planned_event() ;
                Event e =new Event() ;
               

                pst = cnx.prepareStatement(req);

                pst.setInt(1, t.getID_event().getID_event());

                rs = pst.executeQuery();

                while (rs.next()) {
                    e=es.getById(rs.getInt("ID_event"));
                    i=is.getById(rs.getInt("Num_plan"));
                    
                inscription    inscription = new inscription(e,i, rs.getDate("Date"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
            }

            return t;
        }
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public inscription getById(inscription t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public inscription getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

   
    
