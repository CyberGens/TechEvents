/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.SponsoringOffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import util.DataSource;

/**
 *
 * @author trabe
 */
public class SponsoringOfferService {
     private final Connection cnx;
     private Statement ste;
     private PreparedStatement pst;
      private ResultSet rs;

    public SponsoringOfferService() {
          cnx=DataSource.getInstance().getConnexion(); 
    }
  

    /**
     *
     * @param s
     * @throws java.sql.SQLException
     */
   
   public void insertOffrSonsoring(SponsoringOffer s) throws SQLException {
     
        String req = "Insert into sponsoring_offer (date_debut,date_fin,Description) "
                + "values ('"+s.getDateDebut()
                +"','"+s.getDateFin()+"', '"
                +s.getDescription()+"');";
        ste=cnx.createStatement();
        ste.executeUpdate(req);
        

    }
   public void deleteSponsoringOffer(int id) {
        String req = "delete from sponsoring_offer where(Id_offer=" + id + ")";
        try {
            ste = cnx.createStatement();
                ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringOfferService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   
    public void updateOffrSonsoring(SponsoringOffer s) {
     //   String req = "update sponsoring_offer set(date_debut,date_fin,Description) values (?,?,?) where id_user=?";
       String req =  "update sponsoring_offer set date_debut=?,date_fin=?,Description=? where Id_offer=?";
        try {
            pst = cnx.prepareStatement(req);
        
            pst.setDate(1, s.getDateDebut());
            pst.setDate(2, s.getDateFin());
            pst.setString(3, s.getDescription());
            pst.setInt(4, s.getId());
            pst.executeUpdate();
            }
        
        catch (SQLException ex) {
            Logger.getLogger(SponsoringOfferService.class.getName()).log(Level.SEVERE, null, ex);
                                }
    }
 
    public List<SponsoringOffer> afficherListSponsoringOffer() {
        String req ="SELECT * FROM sponsoring_offer ORDER BY `Id_offer` ASC";
        List<SponsoringOffer> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new SponsoringOffer(
                        rs.getInt("id_offer"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getString("description")
                       ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringOfferService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
        public SponsoringOffer afficherSponsoringOfferParId(int i) {
        String req = "select * from sponsoring_offer where Id_offer=?";
        SponsoringOffer sponsoringOffer = new SponsoringOffer();
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, sponsoringOffer.getId());
            rs = pst.executeQuery();
            while (rs.next()) {
                sponsoringOffer  = new SponsoringOffer(rs.getInt("Id_offer"),
                      rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getString("Description")
                       );
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringOfferService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sponsoringOffer;
    }
    public SponsoringOffer getById(int id) {
        String req = "select * from sponsoring_offer where Id_offer=?";
        SponsoringOffer so = new SponsoringOffer();
        
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                 so= new SponsoringOffer(rs.getInt("Id_offer"),
                      rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        rs.getString("Description") );
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsoringOfferService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }


}
