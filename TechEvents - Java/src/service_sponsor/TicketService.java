/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_sponsor;

import entities.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DataSource;

/**
 *
 * @author trabe
 */
public class TicketService {

    private final Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private String h;

    public TicketService() {
        cnx = DataSource.getInstance().getConnexion();
    }

    public void insertTicketService(Ticket T) throws SQLException {

        String req = "Insert into tickets (Id_user,ID_Event,QR_code,image_name) "
                + "values ('" + T.getId_user()
                + "','" + T.getID_Event() + "', '"
                + T.getQR_code() + "', '"
                + T.getImage_name() + "');";
        System.out.println(req);
        ste = cnx.createStatement();
        ste.executeUpdate(req);

    }

    public String findTicket(int idUser, int idEvent) throws SQLException {
        String req = "SELECT * FROM `tickets` WHERE `Id_user`=? and `ID_Event`=? ";
        pst = cnx.prepareStatement(req);
        pst.setInt(1, idUser);
        pst.setInt(2, idEvent);
        rs = pst.executeQuery();

        while (rs.next()) {
            return rs.getString("image_name");

        }
        return "";
    }
}
