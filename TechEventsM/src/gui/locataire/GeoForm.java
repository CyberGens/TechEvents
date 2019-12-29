/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.locataire;

import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.Util;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Local;

/**
 *
 * @author Brahim
 */
public class GeoForm {
    private static final String HTML_API_KEY = "AIzaSyC6knT6q4r698j3-b5Ki8MwgnFoHp12f_s";
    private Form mainForm;
/*Form mainForm = new Form("Browser", new BorderLayout());
public static final String VIEWPORT_SCRIPT = "viewport = document.querySelector(\"meta[name=viewport]\");\r\n" + 
            "viewport.setAttribute('content', 'width=1920px, height=1920px, initial-scale=0.25, maximum-scale=4.0, user-scalable=1');";
    public GeoForm(int id){
        BrowserComponent browser = new BrowserComponent();/*
        browser.addWebEventListener(BrowserComponent.onLoad, (e) -> {
        browser.execute(VIEWPORT_SCRIPT);
    });
        browser.setURL("http://localhost/techevents/web/app_dev.php/locataire/local/geo/"+String.valueOf(id));
        //browser.setURL("http://www.facebook.com");
        LocataireWelcomeForm lwf=new LocataireWelcomeForm();
        //mainForm.setToolbar(lwf.getTb());
        mainForm.add(BorderLayout.CENTER, browser);   
    }

    public Form getMainForm() {
        return mainForm;
    }

    public void setMainForm(Form mainForm) {
        this.mainForm = mainForm;
    }*/
    public GeoForm(Local l){
    Form mainForm = new Form("Native Maps Test");
        mainForm.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setCameraPosition(new Coord(l.getY(), l.getX()));
       /*  Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom( )
                )
        );*/
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
            cnt.setCameraPosition(new Coord(-33.867, 151.206));
        });
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 3);
        Button panTo = new Button("Pan To");
        panTo.addActionListener(e->{
            //bounds.extend(new google.maps.LatLng('66.057878', '-22.579047')); // Iceland
            //bounds.extend(new google.maps.LatLng('37.961952', '43.878878')); // Turkey
            Coord c1 = new Coord(49.0986192, -122.6764454);
            Coord c2 = new Coord(49.2577142, -123.1941149);
            //Coord center = new Coord(c1.getLatitude()/2 +  c2.getLatitude() / 2, c1.getLongitude()/2 + c2.getLongitude()/2 );
            Coord center = new Coord(49.1110928, -122.9414646);
            
            float zoom = cnt.getZoom();
            
            boolean[] finished = new boolean[1];
            cnt.addMapListener(new MapListener() {

                @Override
                public void mapPositionUpdated(Component source, int zoom, Coord c) {
                    
                    if (Math.abs(c.getLatitude() - center.getLatitude()) > .001 || Math.abs(c.getLongitude() - center.getLongitude()) > .001) {
                        return;
                    }
                    finished[0] = true;
                    synchronized(finished) {
                        final MapListener fthis = this;
                        Display.getInstance().callSerially(()->{
                            cnt.removeMapListener(fthis);
                        });
                        finished.notify();
                    }
                    
                }
                
            });
            cnt.zoom(center, (int)zoom);
            while (!finished[0]) {
                Display.getInstance().invokeAndBlock(()->{
                    while (!finished[0]) {
                        Util.wait(finished, 100);
                    }
                });
            }
            BoundingBox box = cnt.getBoundingBox();
            if (!box.contains(c1) || !box.contains(c2)) {
                while (!box.contains(c1) || !box.contains(c2)) {
                    if (!box.contains(c1)) {
                        System.out.println("Box "+box+" doesn't contain "+c1);
                    }
                    if (!box.contains(c1)) {
                        System.out.println("Box "+box+" doesn't contain "+c2);
                    }
                    zoom -= 1;
                    final boolean[] done = new boolean[1];
                    
                    final int fzoom = (int)zoom;
                    cnt.addMapListener(new MapListener() {

                        @Override
                        public void mapPositionUpdated(Component source, int zm, Coord center) {
                            
                            if (zm == fzoom) {
                                final MapListener fthis = this;
                                Display.getInstance().callSerially(()->{
                                    cnt.removeMapListener(fthis);
                                });
                                
                                done[0] = true;
                                synchronized(done) {
                                    done.notify();
                                }
                            }
                        }
                        
                    });
                    cnt.zoom(center, (int)zoom);
                    while (!done[0]) {
                        Display.getInstance().invokeAndBlock(()->{
                            while (!done[0]) {
                                Util.wait(done, 100);
                            }
                        });
                    }
                    box = cnt.getBoundingBox();
                    System.out.println("Zoom now "+zoom);
                    
                }
            } else if (box.contains(c1) && box.contains(c2)) {
                while (box.contains(c1) && box.contains(c2)) {
                    zoom += 1;
                    final boolean[] done = new boolean[1];
                    
                    final int fzoom = (int)zoom;
                    cnt.addMapListener(new MapListener() {
                        public void mapPositionUpdated(Component source, int zm, Coord center)  {
                            if (zm == fzoom) {
                                final MapListener fthis = this;
                                Display.getInstance().callSerially(()->{
                                    cnt.removeMapListener(fthis);
                                });
                                done[0] = true;
                                synchronized(done) {
                                    done.notify();
                                }
                            }
                        }
                    });
                    cnt.zoom(center, (int)zoom);
                    while (!done[0]) {
                        Display.getInstance().invokeAndBlock(()->{
                            while (!done[0]) {
                                Util.wait(done, 100);
                            }
                        });
                    }
                    box = cnt.getBoundingBox();
                    
                }
                zoom -= 1;
                cnt.zoom(center, (int)zoom);
                cnt.addTapListener(null);
            }
            
        });
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom(panTo, btnMoveCamera)
                )
        );
        mainForm.add(BorderLayout.CENTER, root);
}

    public Form getMainForm() {
        return mainForm;
    }

    public void setMainForm(Form mainForm) {
        this.mainForm = mainForm;
    }
    
}