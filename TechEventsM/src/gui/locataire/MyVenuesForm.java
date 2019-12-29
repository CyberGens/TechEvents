/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.locataire;

import Service.ServiceLocataire;
import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import static com.mycompany.myapp.MyApplication.getTheme;
import com.mycompany.myapp.entities.Local;
import gui.LoginForm;
import static gui.LoginForm.getCurrentUser;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Brahim
 */
public class MyVenuesForm {
     Form mainForm=new Form(BoxLayout.y());
     public MyVenuesForm() {
        
        
        mainForm.getToolbar().setUIID("title");
        mainForm.getToolbar().addCommandToRightBar("Back", getTheme().getImage("back-command.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                LocataireWelcomeForm lwf=new LocataireWelcomeForm();
                lwf.getMainForm().showBack();

            }
        });
         LocataireWelcomeForm.createToolbar(mainForm.getToolbar());
     Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     ServiceLocataire ser=new ServiceLocataire();
     ArrayList<Local> locaux=new ArrayList<>();
     locaux=ser.getLocalList();
      for (Local local : locaux){
          if(local.getUser().getId()== getCurrentUser().getId())
            cnt.add(addLocal(local));
        }
      mainForm.add(cnt);
      FloatingActionButton add = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD_LOCATION);
      mainForm.add(add);
      ActionListener ev1=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddVenueForm avf=new AddVenueForm();
                avf.getMainForm().show();

            }
        };
      add.addActionListener(ev1);
              
      
}
     public Container addLocal(Local l){ 
        Boolean hasimg=false;
        Label lbnom= new Label("Name:"+l.getNom());
        Label lbaddress= new Label("Address:"+l.getAdresse());
        Button btn=new Button("see more details");
        btn.setUIID("Label");
        Button btn1=new Button("Edit");
        btn1.setUIID("Label");
        String url;
        EncodedImage enc=null;
        Image img;
        if(l.getImg()!=null){
                url="http://localhost:8000/uploads/images/"+l.getImg();
                hasimg=true;
        }
        else url="http://localhost:8000/uploads/images/"+"No_Image.png";
        try {
            enc=EncodedImage.create("/load.gif");
        } catch (IOException ex) {};
        if(hasimg)
        img=URLImage.createToStorage(enc,l.getImg(), url,URLImage.RESIZE_SCALE);
        else img=URLImage.createToStorage(enc,"No_Image.png", url,URLImage.RESIZE_SCALE);
        
        Label image = new Label(img);
        ActionListener ev1=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VenuesDetailsForm ldf=new VenuesDetailsForm(l,true);
                ldf.getMainForm().show();
            }
        };
        ActionListener ev2=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EditVenueForm el=new EditVenueForm(l);
                el.getMainForm().show();
            }
        };
        
        btn.addActionListener(ev1);
        btn1.addActionListener(ev2);
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cnt.add(lbnom);
        cnt.add(btn);
        cnt.add(btn1);
        Container cnt2 = BorderLayout.center(cnt);
        cnt2.add(BorderLayout.EAST, img);
        //cnt2.setLeadComponent(btn);
        
        return cnt2;
    }

    public Form getMainForm() {
        return mainForm;
    }

    public void setMainForm(Form mainForm) {
        this.mainForm = mainForm;
    }

    

    
}
