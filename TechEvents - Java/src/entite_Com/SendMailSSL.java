/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author USER
 */
public class SendMailSSL {

    public static void main(String[] args) {
        //from,password,to,subject,message  
      Mailer.send("lina.sahli@esprit.tn", "bitchwhore04A", "lina.sahli@esprit.tn", "hello javatpoint", "How r u?");
        //change from, password and to  
    }
}
