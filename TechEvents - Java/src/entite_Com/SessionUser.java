/*/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite_Com;

import entities.User;
import java.net.Authenticator;
import java.util.Properties;


/**
 *
 * @author esprit
 */
public class SessionUser {

    private static User connectedUser;
  
    public SessionUser() {
    }

    public static User getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(User connectedUser) {
        SessionUser.connectedUser = connectedUser;
    }
    
    public static void logOut() {
        SessionUser.connectedUser = null;
    }
    
    
}