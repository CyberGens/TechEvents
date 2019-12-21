/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class UserService implements IService<User> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserService() {
        cnx = DataSource.getInstance().getConnexion();
    }

    @Override
    public void insertPST(User t) {
        String req = "Insert into User (Username,Photo,Firstname,Lastname,Password,Birthdate,Email,Phone_number,Address,Role) values (?,?,?,?,?,?,?,?,?,?)";
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
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        String req = "delete from User where(id_user=" + id + ")";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User t) {
        String req = "update user set username=?,photo=?,firstname=?,lastname=?,password=?,birthdate=?,email=?,phone_number=?,address=?,role=? where id_user=?";
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
            pst.setInt(11, t.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<User> getAll() {
        String req = "select * from User where (role LIKE 'User')";
        List<User> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<User> getAllSponsors() {
        String req = "select * from user where (role LIKE 'Sponsor')";
        List<User> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SponsorService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<User> getAllLodgers() {
        String req = "select * from user where (role LIKE 'Lodger')";
        List<User> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LodgerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<User> getAllAccounts() {
        String req = "select * from user";
        List<User> list = new ArrayList<>();
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(req);
            while (rs.next()) {
                list.add(new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LodgerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User getById(int id) {
        String req = "select * from user where id_user=?";
        User user = new User();
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public User getByUsername(String username) {
        String req = "select * from user where username=?";
        User user = new User();
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, username);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public boolean authenticate(String username,String password) {
        String req = "select * from user where (username=? AND password=?)";
        User user = new User();
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt("id_user"), rs.getString("username"), rs.getString("photo"),
                        rs.getString("firstname"), rs.getString("lastname"), rs.getString("password"), rs.getDate("birthdate"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getString("address"),rs.getString("role"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user.getId()!=0;
    }
    

}
