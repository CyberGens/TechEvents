/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.locataire;

import gui.locataire.LocataireWelcomeForm;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import static com.mycompany.myapp.MyApplication.getTheme;
import com.mycompany.myapp.entities.Local;
import java.io.IOException;

/**
 *
 * @author Brahim
 */
public class VenuesDetailsForm {
    private Form mainForm=new Form(BoxLayout.y());
    public VenuesDetailsForm(Local l,Boolean isLocataire){
        LocataireWelcomeForm lwf= new LocataireWelcomeForm();
          mainForm.getToolbar().setUIID("title");

        mainForm.getToolbar().setUIID("Title");
    mainForm.getToolbar().addCommandToRightBar("Back", getTheme().getImage("back-command.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if(isLocataire)
                {
                MyVenuesForm mvf=new MyVenuesForm();
                mvf.getMainForm().showBack();
                }
                else
                {
                VenuesForm vf=new VenuesForm();
                vf.getMainForm().showBack();
                }
            }
        });
         LocataireWelcomeForm.createToolbar(mainForm.getToolbar());
        
        ActionListener ev1=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                GoogleMapsForm gf=new GoogleMapsForm(l);
                gf.getGoogleMapsForm().show();
                
            }
        };
        ActionListener ev2=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BookForm bf=new BookForm(l);
                bf.getMainForm().show();
                
            }
        };
         String url;
        EncodedImage enc=null;
        Image img;
        Boolean hasimg=false;
        if(l.getImg()!=null){
                url="http://localhost:8000/uploads/images/"+l.getImg();
                hasimg=true;
        }
        else url="http://localhost:8000/uploads/images/"+"No_Image.png";
        try {
            enc=EncodedImage.create("/load.gif");
        } catch (IOException ex) {};
        if(hasimg)
        img=URLImage.createToStorage(enc,l.getImg(), url,URLImage.RESIZE_SCALE)
                    .scaledEncoded(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayWidth());
        else img=URLImage.createToStorage(enc,"No_Image.png", url,URLImage.RESIZE_SCALE)     
                         .scaledEncoded(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayWidth());
        Label image = new Label(img);
        Container c=new Container();
        c.add(img);
        Label name=new Label("Name:"+l.getNom());
        Label address=new Label("Address:"+l.getAdresse());
        Label price=new Label("Price:"+l.getPrix());
        Label cap=new Label("Capacity:"+l.getCapacite());
        Label sur=new Label("Surface:"+l.getSurface());
        Button geo=new Button("Show geographic coordinance:");
        Button book =new Button("Book this venue");
        book.addActionListener(ev2);
        geo.addActionListener(ev1);
 
            
        mainForm.addAll(image,name,address,price,cap,sur,geo,book);
    }

    public Form getMainForm() {
        return mainForm;
    }

    public void setMainForm(Form mainForm) {
        this.mainForm = mainForm;
    }
    
}
