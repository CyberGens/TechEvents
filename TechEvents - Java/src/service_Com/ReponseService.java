
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_Com;


import entite.Question;
import entite.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

/**
 *
 * @author esprit
 */
public class ReponseService {

    private Statement st;
    private ResultSet rs;
    private static ReponseService instance;

    private ReponseService() {

    }

    public static ReponseService getInstance() {
        if (instance == null) {
            return new ReponseService();
        }
        return instance;
    }

    public boolean add(Reponse reponse) {

        boolean isAdded = false;
        try {

            String req = "insert into reponse (contenu,User_id,Question_id) values(?,?,?)";

            Connection cnx=DataSource.getInstance().getConnexion();
            PreparedStatement pst;
            pst = cnx.prepareStatement(req);

            pst.setString(1, reponse.getContenu());
            pst.setInt(2, reponse.getUser().getId());
            pst.setInt(3, reponse.getQuestion().getId());
            int result = pst.executeUpdate();

            if (result > 0) {
                isAdded = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReponseService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isAdded;
    }

}
