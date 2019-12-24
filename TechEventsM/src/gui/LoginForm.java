/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.ServiceUser;
import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.myapp.entities.User;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Brahim
 */
public class LoginForm {
 Form f;
  
    public LoginForm() {
        
        f = new Form();
        TextField username= new TextField("","Username");
        TextField password=new TextField("", "Password", 50, TextField.PASSWORD);
        Button login=new Button("Login");
        login.addActionListener((e) -> {
            ServiceUser ser = new ServiceUser();
            ArrayList<User> users= new ArrayList<>();
            users=ser.getUsersList();
            Boolean ok=false;
            
       
         
            for(User user : users)
            {     
                if(user.getUsername().equals(username.getText())&& ser.verify(password.getText(),user.getPassword())){
                    ok=true;
                }
            }
            if(ok)
            {
             HomeForm h=new HomeForm();
             h.getF().show();
            }
        });
        f.add(username);
        f.add(password);
        f.add(login);
        
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
