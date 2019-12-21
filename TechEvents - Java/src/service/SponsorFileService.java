/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.SponsorFile;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author Admin
 */
public class SponsorFileService implements IService<SponsorFile> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public SponsorFileService() {
        cnx = DataSource.getInstance().getConnexion();

    }

    
    public void insert(SponsorFile t,User u) {
        try {
            String req = "Insert into sponsor_file (id_user,url,description,type) values (?,?,?,?)";
            pst = cnx.prepareStatement(req);
            pst.setInt(1, u.getId());
            pst.setString(2, t.getUrl());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getType());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SponsorFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "delete from sponsor_file where(id_file=" + id + ")";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(SponsorFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(SponsorFile t) {
        try {
            String req = "update sponsor_file set url=?,description=?,type=?,status=? where id_file=?";
            pst = cnx.prepareStatement(req);
            pst.setString(1, t.getUrl());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getType());
            pst.setString(4, t.getStatus());
            pst.setInt(5, t.getId_file());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SponsorFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<SponsorFile> getAll() {
        String req = "select * from sponsor_file";
        List<SponsorFile> file = new ArrayList<>();
        User u=new User();
        UserService us=new UserService();
        SponsorFileService es=new SponsorFileService();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);

            while (rs.next()) {
                u=us.getById(rs.getInt("id_user"));
                file.add(new SponsorFile(rs.getInt("id_file"),u, rs.getString("url"),
                        rs.getString("description"),rs.getString("type"),rs.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsorFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    @Override
    public SponsorFile getById(int id) {
        String req = "select * from sponsor_file where id_file=?";
        SponsorFile file = new SponsorFile();
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                file = new SponsorFile(rs.getInt("id_file"), null, rs.getString("url"),
                        rs.getString("description"),rs.getString("type"),rs.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsorFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }
    
    public SponsorFile getByUser(User u) {
        String req = "select * from sponsor_file where id_user=?";
        SponsorFile file = new SponsorFile();
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, u.getId());
            rs = pst.executeQuery();
            while (rs.next()) {
                file = new SponsorFile(rs.getInt("id_file"), u, rs.getString("url"),
                        rs.getString("description"),rs.getString("type"),rs.getString("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsorFileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    @Override
    public void insertPST(SponsorFile t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
