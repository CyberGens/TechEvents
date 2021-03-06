/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Admin;
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
public class AdminService implements IService<Admin> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public AdminService() {
        cnx = DataSource.getInstance().getConnexion();
    }

    @Override
    public void insertPST(Admin t) {
        String req = "Insert into user (username,photo,firstname,lastname,password,birthdate,email,phone_number,address,role) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, t.getUsername());
            pst.setString(2, t.getPhoto());
            pst.setString(3, t.getFirstname());
            pst.setString(4, t.getLastname());
            pst.setString(5, t.getPassword());
            pst.setDate(6, t.getBirthdate());
            pst.setString(7, t.getEmail());
            pst.setString(8, t.getPhone());
            pst.setString(9, t.getAddress());
            pst.setString(10, t.getRole());

            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "delete from user where(id_user=" + id + ")";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Admin t) {
        String req = "update user set username=?,photo=?, firstname=?, lastname=?, password=?, birthdate=?, email=?, phone_number=?, address=?, role=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, t.getUsername());
            pst.setString(2, t.getPhoto());
            pst.setString(3, t.getFirstname());
            pst.setString(4, t.getLastname());
            pst.setString(5, t.getPassword());
            pst.setDate(6, t.getBirthdate());
            pst.setString(7, t.getEmail());
            pst.setString(8, t.getPhone());
            pst.setString(9, t.getAddress());
            pst.setString(10, t.getRole());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Admin> getAll() {
        String req = "select * from user where (role LIKE 'Admin')";
        List<Admin> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Admin getById(int id) {
        String req = "select * from user where id_user=?";
        Admin admin = new Admin();
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                admin = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

}
