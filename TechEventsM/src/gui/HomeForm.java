/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.locataire.LocataireWelcomeForm;
import com.codename1.ui.Calendar;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;

/**
 *
 * @author Brahim
 */
public class HomeForm {
    Form f =new Form();
    public  HomeForm(){
    Label lb=new Label("welcome Home ♥");
    f.add(lb);
    Toolbar tb = f.getToolbar();
    LocataireWelcomeForm lwf=new LocataireWelcomeForm();
    tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {
            lwf.getMainForm().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
