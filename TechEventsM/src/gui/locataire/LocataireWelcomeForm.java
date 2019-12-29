/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.locataire;

import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import static com.mycompany.myapp.MyApplication.getTheme;
import gui.HomeForm;
import gui.LoginForm;

/**
 *
 * @author Brahim
 */
public class LocataireWelcomeForm {
    private Form mainForm =new Form(BoxLayout.y());
      public LocataireWelcomeForm(){
        
        mainForm.getToolbar().addCommandToRightBar("Back", getTheme().getImage("back-command.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeForm hf=new HomeForm();
                hf.getF().showBack();

            }
        });
        mainForm.getToolbar().setUIID("Title");
          createToolbar(mainForm.getToolbar());
          
        
      }

    public Form getMainForm() {
        return mainForm;
    }

    public void setMainForm(Form mainForm) {
        this.mainForm = mainForm;
    }
    public static void createToolbar(Toolbar tb){
        ActionListener ev1=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                VenuesForm lf=new VenuesForm();
                lf.getMainForm().show();
                
            }
        };
        ActionListener ev2=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MyBookingForm mbf=new MyBookingForm();
                mbf.getMainForm().show();
            }
        };
        ActionListener ev3=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MyVenuesForm mvf=new MyVenuesForm();
                mvf.getMainForm().show();
                
            }
        };
        ActionListener ev4=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MyVenuesBookingForm mvbf=new MyVenuesBookingForm();
                mvbf.getMainForm().show();
                
            }
        };
        tb.addMaterialCommandToSideMenu("venues", FontImage.MATERIAL_HOME_FILLED, ev1);
        tb.addMaterialCommandToSideMenu("My Bookings", FontImage.MATERIAL_SETTINGS, ev2);
        if(LoginForm.getCurrentUser().getRole().equals("Locataire"))
        {
        tb.addMaterialCommandToSideMenu("My Venues", FontImage.MATERIAL_SETTINGS, ev3);
        tb.addMaterialCommandToSideMenu("My venue's bookings", FontImage.MATERIAL_INFO, ev4);
         ActionListener ev5=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FileChooserDemo mvbf=new FileChooserDemo();
                mvbf.hi.show();
                
            }
        };
         tb.addMaterialCommandToSideMenu("File chooser", FontImage.MATERIAL_INFO, ev5);
        }
    }
}
