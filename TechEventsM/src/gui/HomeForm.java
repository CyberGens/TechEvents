/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Calendar;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

/**
 *
 * @author Brahim
 */
public class HomeForm {
    Form f =new Form();
    public  HomeForm(){
    Label lb=new Label("welcome Home ♥");
    f.add(lb);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
