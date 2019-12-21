/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service_sponsor;
import entities.Ticket;
 import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import java.util.UUID;
  


/**
 *
 * @author Admin
 */
public class QrService {
    
       private Connection cnx;
       private Statement ste;
       private PreparedStatement pst;
      private ResultSet rs;

       private String ImagePath = "src\\gui_sponsor\\images\\";
    
    /**
     * @param args the command line arguments
     */
    public   void QrCodeGenerate(String token ,String imageName) throws FileNotFoundException, IOException {
        
           Path path = Paths.get(ImagePath);
            if (Files.notExists(path)) {
               new File(ImagePath).mkdir();

            }
        
        ByteArrayOutputStream stream = QRCode.from(token).to(ImageType.JPG).stream();
 
        FileOutputStream fileStream = new FileOutputStream(new File( ImagePath + imageName + ".jpg" ));
        fileStream.write (stream.toByteArray());
        fileStream.flush ();
        fileStream.close ();

    
}
     
    public String uuidRandom() {
      UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        return randomUUIDString ;
    }
    
       public void saveQr(Ticket T) throws SQLException {
     
       TicketService TS = new TicketService();
       TS.insertTicketService(T);

    }
       
       
}
