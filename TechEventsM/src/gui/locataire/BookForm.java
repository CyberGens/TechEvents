/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.locataire;

import Service.ServiceLocataire;
import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import static com.mycompany.myapp.MyApplication.getTheme;
import com.mycompany.myapp.entities.Local;
import com.mycompany.myapp.entities.Reservation;
import gui.LoginForm;

/**
 *
 * @author Brahim
 */
public class BookForm {
    Form mainForm=new Form();
    public BookForm(Local l){
    VenuesDetailsForm vdf=new VenuesDetailsForm(l, false);
    mainForm.getToolbar().setUIID("title");
        mainForm.getToolbar().addCommandToRightBar("Back", getTheme().getImage("back-command.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                vdf.getMainForm().show();

            }
        });
        LocataireWelcomeForm.createToolbar(mainForm.getToolbar());
        Picker dd = new Picker();
        dd.setType(Display.PICKER_TYPE_DATE);
        Picker df = new Picker();
        df.setType(Display.PICKER_TYPE_DATE);
        Label lb1=new Label("Starting Date:");
        Label lb2=new Label("Ending Date:");
        Button validate=new Button("Validate");
        Reservation r=new Reservation();
        r.setDateDeb(dd.getDate());
        r.setDateFin(df.getDate());
        r.setIdLoc(l);
        r.setIdOwner(l.getUser());
        r.setIdUser(LoginForm.getCurrentUser());
        mainForm.add(lb1);
        mainForm.add(dd);
        mainForm.add(lb2);
        mainForm.add(df);
        mainForm.add(validate);
        /* ActionListener a=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            ServiceLocataire sl =new ServiceLocataire();
            if(sl.ajouterReservation(r))
                 ToastBar.showMessage("Succcess", FontImage.MATERIAL_PLACE);
            else  {ToastBar.showMessage("this Venue is already booked! chech the venue's calendar", FontImage.MATERIAL_PLACE);
           CalendarForm cf=new CalendarForm(l);
           Button showcal=new Button("Show reservations");
           ActionListener cala=new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                 cf.getMainForm().show();
                }
            };
            showcal.addActionListener(cala);
            mainForm.add(showcal);
            }
        }
    };
         validate.addActionListener(a);*/
    }

    public Form getMainForm() {
        return mainForm;
    }

    public void setMainForm(Form mainForm) {
        this.mainForm = mainForm;
    }
    
}
