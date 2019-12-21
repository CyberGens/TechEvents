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
public class Lodger extends User {

    public Lodger() {
    }

    public Lodger(String username, String photo, String firstname, String lastname, String password, Date birthdate, String email, String phone, String address) {
        super(username, photo, firstname, lastname, password, birthdate, email, phone, address);
        this.role = "Lodger";
    }

    public Lodger(int id, String username, String photo, String firstname, String lastname, String password, Date birthdate, String email, String phone, String address,String role) {
        super(id, username, photo, firstname, lastname, password, birthdate, email, phone, address,role);
        this.role = "Lodger";
    }

    @Override
    public String toString() {
        return "Lodger{" + "}";
    }

}
