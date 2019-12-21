/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class User {

    private int id;
    private String username;
    private String photo;
    private String firstname;
    private String lastname;
    
    private String password;
    private Date birthdate;
    private String email;
    private String phone;
    private String address;
    protected String role;

    public User() {
    }

    public User(String username, String photo, String firstname, String lastname, String password, Date birthdate, String email, String phone, String address) {
        this.username = username;
        this.photo = photo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = "User";
    }

    public User(int id, String username, String photo, String firstname, String lastname, String password, Date birthdate, String email, String phone, String address, String role) {
        this.id = id;
        this.username = username;
        this.photo = photo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String toString() {
        return username;
        //return "User{" + "id=" + id + ", username=" + username + ", photo=" + photo + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password + ", birthdate=" + birthdate + ", email=" + email + ", phone=" + phone + "address=" + address + '}';
    }

}
