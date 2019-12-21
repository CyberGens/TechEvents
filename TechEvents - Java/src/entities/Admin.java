/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Admin extends User {

    public Admin() {
    }

    public Admin(String username, String photo, String firstname, String lastname, String password, Date birthdate, String email, String phone, String address) {
        super(username, photo, firstname, lastname, password, birthdate, email, phone, address);
        this.role="Admin";
    }
    

    public Admin(int id, String username, String photo, String firstname, String lastname, String password, Date birthdate, String email, String phone, String address,String role) {
        super(id, username, photo, firstname, lastname, password, birthdate, email, phone, address,role);
        this.role="Admin";
    }

    @Override
    public String toString() {
        return "Admin{" + '}';
    }


    
}
