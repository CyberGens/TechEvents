/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_Com;

import entities.User;
import entite_Com.reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Date;
import service.UserService;
import util.DataSource;

/**
 *
 * @author USER
 */
public class ReclamationService  implements IService<reclamation> {
	 private Connection cnx=DataSource.getInstance().getConnexion();
         

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
   
    
     public void insertPST(reclamation t) {
        String req = "INSERT INTO `reclammation`(`objet`, `description`, `etat`, `user`) VALUES  ('"+t.getObjet()+"','"+t.getDescription()+"','"+ 1+"','"+ 5+"')";
            
        try {
              ste= cnx.createStatement();
            System.out.println("Reclamation Added !!");
              ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
 
     public void delete(int  id_Reclamation) {
         
        String req = "delete from reclammation where(Id_Reclamation=" +  id_Reclamation + ")";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reclamation Deleted Successfully!!");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
         public void update(reclamation t,int  id_Reclamation) {
        String req = "update reclammation set('"+t.getId_Reclamation()
                +"','"+t.getObjet()+"','"+t.getDescription()+"','"+t.isEtat()+"','"+t.getUser()+"')";
        try {
            pst = cnx.prepareStatement(req);
             pst.setInt(1,t.getId_Reclamation());
             pst.setString(2,t.getObjet());
             pst.setString(3,t.getDescription());
             pst.setBoolean(4,t.isEtat());
             pst.setInt(5,t.getUser());
            
             
             
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
    public List<reclamation> getAll() {
        String req = "select * from reclammation";
        List<reclamation> list = new ArrayList<>();
        try {
               ste= cnx.createStatement();
              rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new reclamation(rs.getInt("id_Reclamation"),rs.getString("objet"), rs.getString("description"), rs.getBoolean("etat"),rs.getInt("user")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
                                    System.out.println(list);

        return list;
    }

    public reclamation getById(int id) {
         String req = "select * from reclammation where id_Reclamation=?";
        reclamation recl = new reclamation();
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            
            rs = pst.executeQuery();
              UserService us=new UserService();
            User u =new User();
            u=us.getById(rs.getInt("Id_user"));
            
            while (rs.next()) {
             recl= new reclamation(rs.getInt(" id_Reclamation"),rs.getString("objet"), rs.getString("description"), rs.getBoolean("etat"),u.getId() );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recl;

    }

    public Object readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void insert(reclamation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<reclamation> listerMesReclamtion() {
        ArrayList<reclamation> lstRecl = new ArrayList<reclamation>();
        UserService userService = new UserService();
            User us=new User();
         us.setId(1);
        try{
            Connection cnx=DataSource.getInstance().getConnexion();
            Statement ste = cnx.createStatement();
            ResultSet res; 
            res=pst.executeQuery("select * from reclammation where etat=1 order by(datecreation) desc");
            while(res.next()){
                
                int id_Reclamation = res.getInt("id_Reclamation");
                String objet=res.getString("objet");
                String description=res.getString("description");
                boolean etat=res.getBoolean("etat");
                int iduser = res.getInt("user");
              User user = new User();
                user.setId(iduser);
                //user=userService.RechercheUser(user);
                System.out.println(user);
                
                reclamation rec = new reclamation(id_Reclamation, objet, description, etat, user.getId());
                lstRecl.add(rec);                  
            }       
        }catch(Exception e)
        {
           System.out.println(""+e.getMessage());
        }   
          return lstRecl;
    }

    @Override
    public reclamation getById(reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

          }

     




     