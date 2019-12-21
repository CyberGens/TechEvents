/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_lodger;

import entities_lodger.ReservationLoc;
import util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brahim
 */
public class ReservationLocService {
private Statement ste;
private ResultSet rs;
private PreparedStatement pste;
private Connection cnx;
public ReservationLocService(){
cnx=DataSource.getInstance().getConnexion();
}
public void insert(ReservationLoc rl){
//Local(String nom, String adresse, float prix, boolean etatr, float surface,int capacite)     
//id_reservation	id_owner	id_local	id_user	date_debut	date_fin

String req="insert into reservation(id_owner,id_local,id_user,date_debut,date_fin) values(?,?,?,?,?)";    
    try {
        pste=cnx.prepareStatement(req);
        pste.setInt(1,rl.getIdOwner().getId());
        pste.setInt(2,rl.getIdLoc());
        pste.setInt(3,rl.getIdUser().getId());
        pste.setDate(4,rl.getDateDeb());
        pste.setDate(5,rl.getDateFin());
        pste.executeUpdate();
         System.out.println("Inserted successfully");
    } catch (SQLException ex) {
        Logger.getLogger(LocalService.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed to insert id");
    }
}


}
