/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_lodger;

import entities_lodger.Local;
import entities.User;
import util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.UserService;

/**
 *
 * @author Brahim
 */
public class LocalService {
public LocalService(){
cnx=DataSource.getInstance().getConnexion();
}
private Statement ste;
private ResultSet rs;
private PreparedStatement pste;
private Connection cnx;
public void insert(Local l){
//Local(String nom, String adresse, float prix, boolean etatr, float surface,int capacite)         
String req="insert into local(Id_user,Name,Address,Price,Surface,Capacity,img) values(?,?,?,?,?,?,?)";    
    try {
        UserService us=new UserService();
        pste=cnx.prepareStatement(req);
        pste.setInt(1,l.getUser().getId());
        pste.setString(2,l.getNom());
        pste.setString(3,l.getAdresse());
        pste.setFloat(4,l.getPrix());
        pste.setFloat(5,l.getSurface());
        pste.setInt(6,l.getCapacite());
        pste.setString(7,l.getImg());
        pste.executeUpdate();
         System.out.println("Inserted successfully");
    } catch (SQLException ex) {
        Logger.getLogger(LocalService.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Failed to insert id");
    }
}
public  void modifier(int id,String nom, String adresse, float prix, float surface,int capacite,String img){
//Local(String nom, String adresse, float prix, boolean etatr, float surface,int capacite)         
String req="select * from local where ID_Loc="+id;
    LocalService ls=new LocalService();
    if(ls.VerifIdLoc(id))
    {
    req="update local set Name=?,Address=?,Price=?,Surface=?,Capacity=?,img=? where ID_Loc=?"; 


    try {
        pste=cnx.prepareStatement(req);
        pste.setString(1,nom);
        pste.setString(2,adresse);
        pste.setFloat(3,prix);
        pste.setFloat(4,surface);
        pste.setInt(5,capacite);
        pste.setString(6,img);
        pste.setInt(7,id);
        pste.executeUpdate();
        System.out.println("modification du local id="+id+"s'est terminé avec succés");
    } catch (SQLException ex) {
        Logger.getLogger(LocalService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    else System.out.println("Id à modifier non existant");
    
}
public  void delete(int id){
//Local(String nom, String adresse, float prix, boolean etatr, float surface,int capacite)         
    LocalService ls=new LocalService();
    if (ls.VerifIdLoc(id))
    {
    String req="delete from local where ID_Loc="+id; 


    try {
    ste=cnx.createStatement();
    ste.executeUpdate(req);
        System.out.println("Suppression du local id="+id+" s'est terminé avec succés");
    } catch (SQLException ex) {
        Logger.getLogger(LocalService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    else System.out.println("Id à supprimer non existant");
   
}
public List<Local> readAll(){
    String req="select* from local l join user e on e.Id_user=l.Id_user";
    List<Local> list=new ArrayList<>();
    try {
        UserService us=new UserService();
        ste=cnx.createStatement();
    rs=ste.executeQuery(req);
    Local loc;
    while(rs.next()){
         loc= new Local(rs.getInt("ID_Loc"),new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role")),rs.getString("Name"),rs.getString("Address"),rs.getFloat("Price")
                 ,rs.getFloat("Surface"),rs.getInt("Capacity"),rs.getString("img"));
                list.add(loc);
    }
     } catch (SQLException ex) {
        Logger.getLogger(LocalService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}
public List<Local> readById(int id){
    String req="select* from local l join user e on e.Id_user=l.Id_user where e.Id_user="+id;
    List<Local> list=new ArrayList<>();
    try {
        UserService us=new UserService();
        ste=cnx.createStatement();
    rs=ste.executeQuery(req);
    Local loc;
    while(rs.next()){
         loc= new Local(rs.getInt("ID_Loc"),new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role")),rs.getString("Name"),rs.getString("Address"),rs.getFloat("Price")
                 ,rs.getFloat("Surface"),rs.getInt("Capacity"),rs.getString("img"));
                list.add(loc);
    }
     } catch (SQLException ex) {
        Logger.getLogger(LocalService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}
public boolean VerifIdLoc(int id){
String req="select * from local where Id_user="+id;
    
    boolean ok=false;
    try {
        ste=cnx.createStatement();
        rs=ste.executeQuery(req);
        ok=rs.next(); 
    } catch (SQLException ex) {
        Logger.getLogger(LocalService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ok;
}
public String retLink(int id){
    String ch="";
    String req="select img from local where ID_Loc="+id;
    List<Local> list=new ArrayList<>();
    try {
        ste=cnx.createStatement();
    rs=ste.executeQuery(req);
    if(rs.next())
        ch=rs.getString("img");
     } catch (SQLException ex) {
        Logger.getLogger(LocalService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ch;
}
}