/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entities.Admin;
import entities.Lodger;
import entities.Sponsor;
import entities.SponsorFile;
import entities.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.AdminService;
import service.LodgerService;
import service.SponsorFileService;
import service.SponsorService;
import service.UserService;

/**
 *
 * @author Admin
 */
public class DataSource {

    private String url = "jdbc:mysql://localhost:3306/techevents";
    private String username = "root";
    private String password = "";
    private Connection connexion;
    private static DataSource instance;

    private DataSource() {
        try {
            connexion = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;

    }

    public Connection getConnexion() {
        return connexion;
    }

    public static void main(String[] args) {

        DataSource ds1 = DataSource.getInstance();
        System.out.println(ds1);
        //Date d = Date.valueOf("1998-12-27");
        //Guest g = new Guest(1, "Nomad", "image");
        //Sponsor u = new Sponsor("gathenus", "gay", "Ghassen", "Belhouchet", "pwd", d, "email2@mail.com", "address", "54684564");
        //Sponsor u = new Sponsor("gathenus", "gay", "Ghassen", "Belhouchet", "pwd", d, "email2@mail.com", "address", "54684564");
        //Lodger u = new Lodger("bruh_sund.wav", "boi", "Bruh", "Bruvson", "pwd", d, "email3@mail.com", "address", "54697513");
        //LodgerService us = new LodgerService();
        UserService us = new UserService();
        User u=new User();
        u=us.getByUsername("lol123");
        //SponsorFile f=new SponsorFile(u, "non", "non", "Sponsor");
        SponsorFileService fs = new SponsorFileService();
        SponsorFile f=new SponsorFile("haha", "Not Funny", "Sponsor");
        f=fs.getById(24);
        f.setStatus("Refused");
        fs.update(f);
        System.out.println(fs.getAll());
        //ps.insert(p);
        //ps.insertpst(p);
        //us.insertPST(u);
        //System.out.println(ps.readAll());
        //System.out.println(us.authenticate("lol123","lol123"));
        //us.getAll().forEach(e->System.out.println(e));
        //System.out.println(us.getById(1));

    }

}
