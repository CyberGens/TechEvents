/*
 * Copyright (c) 2014, Codename One LTD. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gui.locataire;

import gui.locataire.VenuesDetailsForm;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.googlemaps.MapContainer.MapObject;
import com.codename1.io.Util;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Local;
import static gui.LoginForm.getCurrentUser;
import java.io.IOException;

public class GoogleMapsForm {

    private static final String HTML_API_KEY = "AIzaSyC6knT6q4r698j3-b5Ki8MwgnFoHp12f_s";

    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
            Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
            UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     Form googleMapsForm = new Form("Native Maps Test");
    public GoogleMapsForm(Local l){
        
        googleMapsForm.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        //cnt.setCameraPosition(new Coord(l.getY(), l.getX()));//this breaks the code //because the Google map is not loaded yet
        cnt.addMapListener(new MapListener() {

            @Override
            public void mapPositionUpdated(Component source, int zoom, Coord center) {
                System.out.println("Map position updated: zoom="+zoom+", Center="+center);
            }
            
        });
        
        cnt.addLongPressListener(e->{
            System.out.println("Long press");
            ToastBar.showMessage("Received longPress at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
        cnt.addTapListener(e->{
            ToastBar.showMessage("Received tap at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
        
        int maxZoom = cnt.getMaxZoom();
        System.out.println("Max zoom is "+maxZoom);
        Button btnMoveCamera = new Button("Move Camera");
        btnMoveCamera.addActionListener(e->{
            cnt.setCameraPosition(new Coord(l.getY(),l.getX()));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
        
        Button btnAddMarker = new Button("Go To Location");
        btnAddMarker.addActionListener(e->{
           
            cnt.zoom(new Coord(l.getY(),l.getX()),14);
            cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCameraPosition(), "Hi marker", "Optional long description", new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("Bounding box is "+cnt.getBoundingBox());
                    ToastBar.showMessage("Name:"+l.getNom()+"\n Address:"+l.getAdresse(), FontImage.MATERIAL_PLACE);
                }
            });
            
        });
        Button zoomin = new Button("Zoom");
        zoomin.addActionListener(e->{
           
            cnt.zoom(new Coord(l.getY(),l.getX()),18);
            cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCameraPosition(), "Hi marker z", "Optional long description", new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("Bounding box is "+cnt.getBoundingBox());
                    ToastBar.showMessage("Name:"+l.getNom()+"\n Address:"+l.getAdresse(), FontImage.MATERIAL_PLACE);
                }
            });
            
        });
        cnt.addTapListener(e->{
            if (tapDisabled) {
                return;
            }
            tapDisabled = true;
            TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2->{
                String txt = enterName.getText();
                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCoordAtPosition(e.getX(), e.getY()), enterName.getText(), "", e3->{
                    ToastBar.showMessage("Name:"+l.getNom()+"\n Address:"+l.getAdresse(), FontImage.MATERIAL_PLACE);
                });
                dlg.dispose();
                tapDisabled = false;
            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();
        });
        FloatingActionButton nextForm = FloatingActionButton.createFAB(FontImage.MATERIAL_BACKSPACE);
        nextForm.addActionListener(e->{
            Form form = new Form("Hello World");
            Button b1 = $(new Button("Back to venue"))
                    .addActionListener(e2->{
                        if(getCurrentUser().getId()==l.getUser().getId()){
                        VenuesDetailsForm ldf=new VenuesDetailsForm(l,true);
                        ldf.getMainForm().show();}
                        else{ VenuesDetailsForm ldf=new VenuesDetailsForm(l,false);
                        ldf.getMainForm().show();}    
                    })
                    .asComponent(Button.class);

            Button back = $(new Button("Back to Map"))
                    .addActionListener(e2->{
                        googleMapsForm.showBack();
                    })
                    .asComponent(Button.class);
            form.add(b1).add(back);
            form.show();
        });
        
        
        
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(nextForm.bindFabToContainer(cnt)),
                BorderLayout.south(
                        FlowLayout.encloseBottom(btnAddMarker,zoomin)
                )
        );
        
        googleMapsForm.add(BorderLayout.CENTER, root);
        googleMapsForm.show();
        
    }
    boolean tapDisabled = false;


    public Form getGoogleMapsForm() {
        return googleMapsForm;
    }

    public void setGoogleMapsForm(Form googleMapsForm) {
        this.googleMapsForm = googleMapsForm;
    }
    

}
