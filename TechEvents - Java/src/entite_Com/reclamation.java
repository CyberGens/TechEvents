/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite_Com;

 
import java.util.Date;
import java.util.Objects;
/**
 *
 * @author Zliaa
 */
public class reclamation {

    private int id_Reclamation ; 
    private String objet ;
    private String description ;
    private boolean etat;
    private int user ;

    public reclamation(int id_Reclamation, String objet, String description, boolean etat, int user) {
        this.id_Reclamation = id_Reclamation;
        this.objet = objet;
        this.description = description;
        this.etat = etat;
        this.user = user;
    }

    public reclamation(String objet, String description, boolean etat, int user) {
        this.objet = objet;
        this.description = description;
        this.etat = etat;
        this.user = user;
    }
    
    

    public int getId_Reclamation() {
        return id_Reclamation;
    }

    public String getObjet() {
        return objet;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEtat() {
        return etat;
    }

    public int getUser() {
        return user;
    }

    public void setId_Reclamation(int id_Reclamation) {
        this.id_Reclamation = id_Reclamation;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
    
    

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_Reclamation;
        hash = 29 * hash + Objects.hashCode(this.objet);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + (this.etat ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.user);
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
        final reclamation other = (reclamation) obj;
        if (this.id_Reclamation != other.id_Reclamation) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        if (!Objects.equals(this.objet, other.objet)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    public reclamation() {
    }

    @Override
    public String toString() {
        return "reclamation{" + "id_Reclamation=" + id_Reclamation + ", objet=" + objet + ", description=" + description + ", etat=" + etat + ", user=" + user + '}';
    }
    
}
     

    
