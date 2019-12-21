/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class SponsorFile {

    private int id_file;
    private User id_user;
    private String url;
    private String description;
    private String type;
    private String status;

    public SponsorFile() {
    }

    public SponsorFile(String url, String description, String type) {
        this.url = url;
        this.description = description;
        this.type = type;
    }

    public SponsorFile(String url, String description, String type, String status) {
        this.url = url;
        this.description = description;
        this.type = type;
        this.status = status;
    }

    public SponsorFile(User id_user, String url, String description, String type) {
        this.id_user = id_user;
        this.url = url;
        this.description = description;
        this.type = type;
    }

    public SponsorFile(int id_file, User id_user, String url, String description, String type) {
        this.id_file = id_file;
        this.id_user = id_user;
        this.url = url;
        this.description = description;
        this.type = type;
    }

    public SponsorFile(int id_file, User id_user, String url, String description, String type, String status) {
        this.id_file = id_file;
        this.id_user = id_user;
        this.url = url;
        this.description = description;
        this.type = type;
        this.status = status;
    }

    public int getId_file() {
        return id_file;
    }

    public void setId_file(int id_file) {
        this.id_file = id_file;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        final SponsorFile other = (SponsorFile) obj;
        if (this.id_file != other.id_file) {
            return false;
        }
        if (!Objects.equals(this.id_user, other.id_user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SponsorFile{" + "id_file=" + id_file + ", id_user=" + id_user + ", url=" + url + ", description=" + description + '}';
    }

}
