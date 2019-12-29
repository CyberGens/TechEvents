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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import static com.mycompany.myapp.MyApplication.getTheme;
import com.mycompany.myapp.entities.Reservation;
import static gui.LoginForm.getCurrentUser;
import java.util.ArrayList;

/**
 *
 * @author Brahim
 */
public class MyVenuesBookingForm {
    Form mainForm=new Form();

    public MyVenuesBookingForm() {
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
     ArrayList<Reservation> reservations=new ArrayList<>();
     reservations=ser.getReservationList();
      for (Reservation reservation : reservations){
          if(reservation.getIdOwner().getId()== getCurrentUser().getId())
            cnt.add(addReservation(reservation));
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
     public Container addReservation(Reservation r){ 
        Boolean hasimg=false;
        Label lbnom= new Label("Venue's Name:"+r.getIdLoc().getNom());
        Label lbdd= new Label("Starting Date:"+r.getDateDeb().toString());
        Label lbdf= new Label("Ending Date:"+r.getDateFin().toString());
        Button btn=new Button("see more details about the venue");
        btn.setUIID("Label");
        Button btn1=new Button("Edit");
        btn1.setUIID("Label");
        ActionListener ev1=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VenuesDetailsForm ldf=new VenuesDetailsForm(r.getIdLoc(),true);
                ldf.getMainForm().show();
            }
        };
        /*ActionListener ev2=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EditVenueForm el=new EditVenueForm(l);
                el.getMainForm().show();
            }
        };*/
        
        btn.addActionListener(ev1);
        /*btn1.addActionListener(ev2);*/
        Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        cnt.add(lbnom);
        cnt.add(btn);
        cnt.add(btn1);
        Container cnt2 = BorderLayout.center(cnt);
        cnt2.add(BorderLayout.EAST, lbdd);
        cnt2.add(BorderLayout.EAST, lbdf);
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
