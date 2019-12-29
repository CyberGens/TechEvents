/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.locataire;

import Service.ServiceLocataire;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextComponent;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.NumericConstraint;
import com.codename1.ui.validation.Validator;
import static com.mycompany.myapp.MyApplication.getTheme;
import com.mycompany.myapp.entities.Local;
import gui.LoginForm;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Brahim
 */
public class AddVenueForm {

    TextModeLayout tl = new TextModeLayout(3, 2);
    Form mainForm = new Form(tl);

    public AddVenueForm() {
        MyVenuesForm mvf = new MyVenuesForm();
        mainForm.getToolbar().setUIID("title");
        mainForm.getToolbar().addCommandToRightBar("Back", getTheme().getImage("back-command.png"), new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                mvf.getMainForm().show();

            }
        });
        Local l=new Local();
        LocataireWelcomeForm.createToolbar(mainForm.getToolbar());
        FloatingActionButton validate = FloatingActionButton.createFAB(FontImage.MATERIAL_CHECK_CIRCLE);
        TextComponent name = new TextComponent().label("Nom");
        TextComponent address = new TextComponent().label("Address");
        TextComponent price = new TextComponent().label("Price");
        TextComponent surface = new TextComponent().label("Surface");
        TextComponent capacity = new TextComponent().label("Capacity");
        TextComponent x = new TextComponent().label("Xcoord");
        TextComponent y = new TextComponent().label("Ycoord");
        mainForm.add(tl.createConstraint().horizontalSpan(2), address);
        mainForm.add(tl.createConstraint().widthPercentage(50), name);
        mainForm.add(tl.createConstraint().widthPercentage(50), price);
        mainForm.add(tl.createConstraint().horizontalSpan(2), surface);
        mainForm.add(tl.createConstraint().horizontalSpan(2), capacity);
        mainForm.add(tl.createConstraint().widthPercentage(50), x);
        mainForm.add(tl.createConstraint().widthPercentage(50), y);
        Validator val = new Validator();
        val.addConstraint(address, new LengthConstraint(6));
        val.addConstraint(name, new LengthConstraint(6));
        val.addConstraint(price, new NumericConstraint(true));
        val.addConstraint(surface, new NumericConstraint(true));
        val.addConstraint(capacity, new NumericConstraint(true));
        val.addConstraint(x, new NumericConstraint(true));
        val.addConstraint(y, new NumericConstraint(true));
        val.setValidateOnEveryKey(true);
        Button upload = new Button("Upload Image");
        ActionListener callback = e -> {
            if (e != null && e.getSource() != null) {
                String filePath = (String) e.getSource();
                MultipartRequest cr = new MultipartRequest();
                cr.setUrl("http://localhost:8000/locataire/wsuploadimage");
                cr.setPost(true);
                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                }
                cr.setFilename("file", "MyImage.jpg");
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                NetworkManager.getInstance().addToQueueAndWait(cr);
                 String response="";
                 
                    try {
                        response = new String(cr.getResponseData(),"utf-8");
                    } catch (UnsupportedEncodingException ex) {
                  
                    }
            if(response.equals("false"))
            {
             ToastBar.showMessage("Failed To upload Image", FontImage.MATERIAL_PLACE);
             l.setImg("No_Image.png");
            }
            else
            {
             ToastBar.showMessage("Image uploaded Succcessfully", FontImage.MATERIAL_PLACE);
             l.setImg(response);
                System.out.println(l.getImg());
            }
            }
            
        };
        ActionListener imageev = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (FileChooser.isAvailable()) {
                    FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
                } else {
                    Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
                }
            }
        };
        upload.addActionListener(imageev);
        ActionListener ev = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (val.isValid()) {
                    ServiceLocataire sl = new ServiceLocataire();
                    l.setAdresse(address.getText());
                    l.setNom(name.getText());
                    l.setPrix(Float.parseFloat(price.getText()));
                    l.setSurface(Float.parseFloat(surface.getText()));
                    l.setCapacite(Integer.parseInt(capacity.getText()));
                    l.setX(Float.parseFloat(x.getText()));
                    l.setY(Float.parseFloat(y.getText()));
                    l.setUser(LoginForm.getCurrentUser());
                    if (sl.ajouterLocal(l)) {
                       
                    } else {
                        ToastBar.showMessage("Error", FontImage.MATERIAL_PLACE);
                    }
                }
            }
        };
        validate.addActionListener(ev);
        mainForm.add(upload);
        mainForm.add(validate);
        mainForm.setEditOnShow(name.getField());
    }

    public Form getMainForm() {
        return mainForm;
    }

    public void setMainForm(Form mainForm) {
        this.mainForm = mainForm;
    }

}
