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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Brahim
 */
public class ServiceUser {


    public ArrayList<User> parseListUserJson(String json) {

        ArrayList<User> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User u = new User();

                float id = Float.parseFloat(obj.get("id").toString());

                u.setId((int) id);
                u.setUsername(obj.get("username").toString());
                u.setPassword(obj.get("password").toString());
                System.out.println(u);
                
                listUsers.add(u);

            }

        } catch (IOException ex) {
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        System.out.println(listUsers);
        return listUsers;

    }
    
    
    ArrayList<User> listUsers = new ArrayList<>();
    
    public ArrayList<User> getUsersList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8000/wsusershowall");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                listUsers = ser.parseListUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }
    Boolean ok=false;
    ConnectionRequest con;
    public Boolean verify(String password,String hash){  
        String url = "http://localhost/login.php";
         ConnectionRequest con = new ConnectionRequest(url);
                       con.setPost(false);
                       con.addArgument("password", password);
                       con.addArgument("hash",hash);
                         NetworkManager.getInstance().addToQueueAndWait(con);
                       String response="";
                 
                    try {
                        response = new String(con.getResponseData(),"utf-8");
                    } catch (UnsupportedEncodingException ex) {
                  
                    }
                    
                        
                    
                       if (response.equals("true"))
                               ok= true;
                return ok;
    }
  /*   
    ArrayList<String> listSalt = new ArrayList<>();
    
    public ArrayList<String> getUsersSalt(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8000/wsusershowall");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                listSalt= ser.parseListUserJson(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUsers;
    }*/

}
