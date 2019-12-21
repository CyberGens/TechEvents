package mail;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brahim
 */
public class SendMailSSL {
     public static void main(String[] args) {    
     //from,password,to,subject,message  
     Mailer.send("from@gmail.com","xxxxx","to@gmail.com","hello javatpoint","How r u?");  
     //change from, password and to  
 }   
    
}
