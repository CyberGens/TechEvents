/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Local;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.entities.User;
import static gui.LoginForm.getCurrentUser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Brahim
 */
public class ServiceLocataire {
public ArrayList<Local> parseListLocalJson(String json) {

        ArrayList<Local> listLocal = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                Local l=new Local();
                Map<String, Object> obju = (Map<String, Object>) (obj.get("idUser"));
                 float id = Float.parseFloat(obju.get("id").toString());
                u.setId((int) id);
                u.setUsername(obju.get("username").toString());
                u.setPassword(obju.get("password").toString());
                u.setFirstname(obju.get("firstname").toString());
                u.setLastname(obju.get("lastname").toString());
                //birthdate 
                u.setAddress(obju.get("address").toString()); 
                u.setPhone(obju.get("phoneNumber").toString()); 
                u.setRole(obju.get("role").toString());
                u.setEmail(obju.get("email").toString());
                u.setPhoto(obju.get("photo").toString());                
                l.setUser(u);
                l.setAdresse(obj.get("address").toString());
                float cap=Float.parseFloat(obj.get("capacity").toString());
                l.setCapacite((int)cap);
                float idloc=Float.parseFloat(obj.get("idLoc").toString());
                l.setId((int)idloc);
                if(obj.get("img")!=null)
                l.setImg(obj.get("img").toString());
                else l.setImg("No_Image.png");
                l.setNom(obj.get("name").toString()); 
                l.setPrix(Float.parseFloat(obj.get("price").toString())); 
                l.setSurface(Float.parseFloat(obj.get("surface").toString()));
                l.setX(Float.parseFloat(obj.get("x").toString())); 
                l.setY(Float.parseFloat(obj.get("y").toString()));
                listLocal.add(l);
            }

        } catch (IOException ex) {
        }
        return listLocal;

    }
public ArrayList<Reservation> parseListReservationJson(String json) {

        ArrayList<Reservation> listReservation = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> reservations = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) reservations.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                User o= new User();
                Local l=new Local();
                Reservation r=new Reservation();
                Map<String, Object> obju = (Map<String, Object>) (obj.get("idUser"));
                 float id = Float.parseFloat(obju.get("id").toString());
                u.setId((int) id);
                u.setUsername(obju.get("username").toString());
                u.setPassword(obju.get("password").toString());
                u.setFirstname(obju.get("firstname").toString());
                u.setLastname(obju.get("lastname").toString());
                //birthdate 
                u.setAddress(obju.get("address").toString()); 
                u.setPhone(obju.get("phoneNumber").toString()); 
                u.setRole(obju.get("role").toString());
                u.setEmail(obju.get("email").toString());
                u.setPhoto(obju.get("photo").toString());
                
                obju = (Map<String, Object>) (obj.get("idOwner"));
                float ido = Float.parseFloat(obju.get("id").toString());
                o.setId((int) ido);
                o.setUsername(obju.get("username").toString());
                o.setPassword(obju.get("password").toString());
                o.setFirstname(obju.get("firstname").toString());
                o.setLastname(obju.get("lastname").toString());
                //birthdate 
                o.setAddress(obju.get("address").toString()); 
                o.setPhone(obju.get("phoneNumber").toString()); 
                o.setRole(obju.get("role").toString());
                o.setEmail(obju.get("email").toString());
                o.setPhoto(obju.get("photo").toString());
                
                l.setUser(o);
                Map<String, Object> objl = (Map<String, Object>) (obj.get("idLocal"));
                l.setAdresse(objl.get("address").toString());
                float cap=Float.parseFloat(objl.get("capacity").toString());
                l.setCapacite((int)cap);
                float idloc=Float.parseFloat(objl.get("idLoc").toString());
                l.setId((int)idloc);
                if(objl.get("img")!=null)
                l.setImg(objl.get("img").toString());
                else l.setImg("No_Image.png");
                l.setNom(objl.get("name").toString()); 
                l.setPrix(Float.parseFloat(objl.get("price").toString())); 
                l.setSurface(Float.parseFloat(objl.get("surface").toString()));
                l.setX(Float.parseFloat(objl.get("x").toString())); 
                l.setY(Float.parseFloat(objl.get("y").toString()));
                 Map<String, Object> objd = (Map<String, Object>) (obj.get("dateDebut"));
                 float dd=Float.parseFloat(objd.get("timestamp").toString());
                 r.setDateDeb(new Date((int)dd));
                 objd = (Map<String, Object>) (obj.get("dateFin"));
                 float df=Float.parseFloat(objd.get("timestamp").toString());
                 r.setDateFin(new Date((int)dd));
                 r.setIdLoc(l);
                 r.setIdUser(u);
                 r.setIdOwner(o);
                listReservation.add(r);
            }

        } catch (IOException ex) {
        }
        System.out.println(listReservation);
        return listReservation;

    }
    
    public Boolean modifierLocal(Local l) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8000/locataire/wsmodifylocal";
        con.setUrl(Url);
        con.setPost(false);
                       con.addArgument("idU",String.valueOf(getCurrentUser().getId()));
                       con.addArgument("pw",getCurrentUser().getPassword());
                       con.addArgument("idL",String.valueOf(l.getId()));
                       con.addArgument("name",l.getNom());
                       con.addArgument("address",l.getAdresse());
                       con.addArgument("price",String.valueOf(l.getPrix()));
                       con.addArgument("surface",String.valueOf(l.getSurface()));
                       con.addArgument("capacity",String.valueOf(l.getCapacite()));
                       con.addArgument("img",l.getImg());
                       con.addArgument("x",String.valueOf(l.getY()));
                       con.addArgument("y",String.valueOf(l.getX()));
                       NetworkManager.getInstance().addToQueueAndWait(con);
                       String response="";
                    try {
                        response = new String(con.getResponseData(),"utf-8");
                    } catch (UnsupportedEncodingException ex) {
                  
                    }
                    
                        
                    
                       if (response.equals("true"))
                               return true;
                return false;
        
    }
   public Boolean ajouterLocal(Local l) {
        ConnectionRequest con = new ConnectionRequest();
        /*http://localhost:8000/locataire/
        wsnewlocal?idU=1&pw=123&name=json&address=blablabal&price=123&surface=123&capacity=123&x=1&y=2*/
        String Url = "http://localhost:8000/locataire/wsnewlocal";
        con.setUrl(Url);
        con.setPost(false);
                       con.addArgument("idU",String.valueOf(getCurrentUser().getId()));
                       con.addArgument("pw",getCurrentUser().getPassword());
                       con.addArgument("name",l.getNom());
                       con.addArgument("address",l.getAdresse());
                       con.addArgument("price",String.valueOf(l.getPrix()));
                       con.addArgument("surface",String.valueOf(l.getSurface()));
                       con.addArgument("capacity",String.valueOf(l.getCapacite()));
                       System.out.println(l.getImg()+"Service");
                       con.addArgument("img",l.getImg());
                       con.addArgument("x",String.valueOf(l.getY()));
                       con.addArgument("y",String.valueOf(l.getX()));
                       NetworkManager.getInstance().addToQueueAndWait(con);
                       String response="";
                    try {
                        response = new String(con.getResponseData(),"utf-8");
                    } catch (UnsupportedEncodingException ex) {
                  
                    }
                    
                        
                    
                       if (response.equals("true"))
                               return true;
                return false;
        
    }
   public Boolean ajouterReservation(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();
        /*http://localhost:8000/locataire/wsnewreservation?pw=123&idU=3&idL=1&dd=2014-12-1&df=2015-1-1*/
        String dd=new SimpleDateFormat("yyyy-MM-dd").format(r.getDateDeb());
        String df=new SimpleDateFormat("yyyy-MM-dd").format(r.getDateFin());
        String Url = "http://localhost:8000/locataire/wsnewreservation";
        con.setUrl(Url);
        con.setPost(false);
                       con.addArgument("idU",String.valueOf(getCurrentUser().getId()));
                       con.addArgument("pw",getCurrentUser().getPassword());
                       con.addArgument("idL",String.valueOf(r.getIdLoc().getId()));
                       con.addArgument("dd",dd);
                       con.addArgument("df",df);
                       NetworkManager.getInstance().addToQueueAndWait(con);
                       String response="";
                    try {
                        response = new String(con.getResponseData(),"utf-8");
                    } catch (UnsupportedEncodingException ex) {
                  
                    }
                    
                        
                    
                       if (response.equals("true"))
                               return true;
                return false;
        
    }
    
    ArrayList<Local> listLocal = new ArrayList<>();
    
    public ArrayList<Local> getLocalList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8000/locataire/wslocal");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceLocataire ser = new ServiceLocataire();
                listLocal = ser.parseListLocalJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listLocal;
    }
    ArrayList<Reservation> listReservation = new ArrayList<>();
    
    public ArrayList<Reservation> getReservationList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8000/locataire/wsreservation");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceLocataire ser = new ServiceLocataire();
                listReservation = ser.parseListReservationJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReservation;
    }
 
}
