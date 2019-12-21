/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_rating;

import entities_events.Event;
import entities.Rating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author Souha
 */
public class RatingService {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public RatingService() {
        cnx = DataSource.getInstance().getConnexion();
    }

    /**
     *
     * @param r
     * @param
     */
    public void insert(Rating r) {
        String req = "Insert into rating (Id_user,ID_Event,Stars) values (?,?,?)"; 
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(3, r.getStars());
            pst.setInt(2, r.getId_event());
            //pst.setString(2, r.getName());
            pst.setInt(1, r.getId_user()); 
            int executeUpdate = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RatingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void delete(int id_even,int id_user) throws SQLException{
        try {
        pst=cnx.prepareStatement("delete from rating where Id_user=? && ID_Event=?");
        pst.setInt(1, id_user);
        pst.setInt(2, id_even);
        pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RatingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int id_user,int id_eve,int score) {
        try {
            pst=cnx.prepareStatement("update rating set Stars= ? where Id_user=? && ID_Event=?");
            pst.setInt(2, id_user);
            pst.setInt(3, id_eve);
            pst.setInt(1, score);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RatingService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Afficher tous les club comfirmés
    public List<Rating> getAll() throws SQLException{
        List<Rating> listerat =new ArrayList<>();
        PreparedStatement pt;
            pt = cnx.prepareStatement("select * from rating");
            ResultSet rs=pt.executeQuery();
            while(rs.next())
            {
                listerat.add(new Rating(rs.getInt("Id_user"),rs.getInt("ID_event"), rs.getInt("Stars")));
            }
            return listerat;
    }
    
    // Afficher tous les club comfirmés
    public List<Event> alleven() throws SQLException{
        List<Event> listeven =new ArrayList<>();
        PreparedStatement pt;
            pt = cnx.prepareStatement("select * from Event");
            ResultSet rs=pt.executeQuery();
            while(rs.next())
            {
                listeven.add(new Event(rs.getInt("ID_event"), rs.getString("Stars")));
            }
            return listeven;
    }
    
    public int RateInfo(int id_even, int id_user) throws SQLException{
         int rate=0;
         PreparedStatement pt=cnx.prepareStatement("select Stars from rating where ID_EVENT=? && Id_user=?");
         pt.setInt(1, id_even);
         pt.setInt(2, id_user);
         ResultSet rs=pt.executeQuery();
         if(rs.next())
         {
            rate=rs.getInt(1);
         }
         return rate;
    }
    
}
