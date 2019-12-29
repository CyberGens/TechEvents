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
import java.util.Date;
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
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setUsername(obj.get("username").toString());
                u.setPassword(obj.get("password").toString());
                u.setFirstname(obj.get("firstname").toString());
                u.setLastname(obj.get("lastname").toString());
                //birthdate
                u.setAddress(obj.get("address").toString()); 
                u.setPhone(obj.get("phoneNumber").toString()); 
                u.setRole(obj.get("role").toString());
                u.setEmail(obj.get("email").toString());
                u.setPhoto(obj.get("photo").toString());                
                listUsers.add(u);
            }

        } catch (IOException ex) {
        }
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
        String url = "http://localhost:8000/wslogin";
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
 
}
