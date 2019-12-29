/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.locataire;

import gui.locataire.LocataireWelcomeForm;
import Service.ServiceLocataire;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Calendar;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import static com.mycompany.myapp.MyApplication.getTheme;
import com.mycompany.myapp.entities.Local;
import gui.LoginForm;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Brahim
 */
public class VenuesForm {
     Form mainForm=new Form(BoxLayout.y());
     public VenuesForm() {
    LocataireWelcomeForm lwf=new LocataireWelcomeForm();
        
    mainForm.getToolbar().setUIID("title");
    mainForm.getToolbar().addCommandToRightBar("Back", getTheme().getImage("back-command.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                lwf.getMainForm().showBack();

            }
        });
         LocataireWelcomeForm.createToolbar(mainForm.getToolbar());
     Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     ServiceLocataire ser=new ServiceLocataire();
     ArrayList<Local> locaux=new ArrayList<>();
     locaux=ser.getLocalList();
      for (Local local : locaux){
            cnt.add(addLocal(local));
        }
      mainForm.add(cnt);
}
     public Container addLocal(Local l){
         
        Boolean hasimg=false;
        Label lbnom= new Label("Name:"+l.getNom());
        Label lbaddress= new Label("Address:"+l.getAdresse());
        Button btn=new Button("see more details");
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
        ActionListener ev=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VenuesDetailsForm ldf=new VenuesDetailsForm(l,false);
                ldf.getMainForm().show();
            }
        };
        
        btn.addActionListener(ev);
        
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cnt.addPointerPressedListener(ev);
        cnt.add(lbnom);
        cnt.add(btn);
        Container cnt2 = BorderLayout.center(cnt);
        cnt2.add(BorderLayout.EAST, img);
        cnt2.setLeadComponent(btn);
        return cnt2;
    }

    public Form getMainForm() {
        return mainForm;
    }

    public void setMainForm(Form mainForm) {
        this.mainForm = mainForm;
    }

    


}
