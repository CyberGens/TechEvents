/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API.mail;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javafx.scene.image.Image;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

/**
 *
 * @author
 */
public class Mail {


 private String mailTemplate() {
        return "<!doctype html>\n" +
"<html>\n" +
"  <head>\n" +
"    <meta name='viewport' content='width=device-width'>\n" +
"    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n" +
"    <title></title>\n" +
"    <style>\n" +
"    /* -------------------------------------\n" +
"        INLINED WITH htmlemail.io/inline\n" +
"    ------------------------------------- */\n" +
"    /* -------------------------------------\n" +
"        RESPONSIVE AND MOBILE FRIENDLY STYLES\n" +
"    ------------------------------------- */\n" +
"    @media only screen and (max-width: 620px) {\n" +
"      table[class=body] h1 {\n" +
"        font-size: 28px !important;\n" +
"        margin-bottom: 10px !important;\n" +
"      }\n" +
"      table[class=body] p,\n" +
"            table[class=body] ul,\n" +
"            table[class=body] ol,\n" +
"            table[class=body] td,\n" +
"            table[class=body] span,\n" +
"            table[class=body] a {\n" +
"        font-size: 16px !important;\n" +
"      }\n" +
"      table[class=body] .wrapper,\n" +
"            table[class=body] .article {\n" +
"        padding: 10px !important;\n" +
"      }\n" +
"      table[class=body] .content {\n" +
"        padding: 0 !important;\n" +
"      }\n" +
"      table[class=body] .container {\n" +
"        padding: 0 !important;\n" +
"        width: 100% !important;\n" +
"      }\n" +
"      table[class=body] .main {\n" +
"        border-left-width: 0 !important;\n" +
"        border-radius: 0 !important;\n" +
"        border-right-width: 0 !important;\n" +
"      }\n" +
"      table[class=body] .btn table {\n" +
"        width: 100% !important;\n" +
"      }\n" +
"      table[class=body] .btn a {\n" +
"        width: 100% !important;\n" +
"      }\n" +
"      table[class=body] .img-responsive {\n" +
"        height: auto !important;\n" +
"        max-width: 100% !important;\n" +
"        width: auto !important;\n" +
"      }\n" +
"    }\n" +
"    /* -------------------------------------\n" +
"        PRESERVE THESE STYLES IN THE HEAD\n" +
"    ------------------------------------- */\n" +
"    @media all {\n" +
"      .ExternalClass {\n" +
"        width: 100%;\n" +
"      }\n" +
"      .ExternalClass,\n" +
"            .ExternalClass p,\n" +
"            .ExternalClass span,\n" +
"            .ExternalClass font,\n" +
"            .ExternalClass td,\n" +
"            .ExternalClass div {\n" +
"        line-height: 100%;\n" +
"      }\n" +
"      .apple-link a {\n" +
"        color: inherit !important;\n" +
"        font-family: inherit !important;\n" +
"        font-size: inherit !important;\n" +
"        font-weight: inherit !important;\n" +
"        line-height: inherit !important;\n" +
"        text-decoration: none !important;\n" +
"      }\n" +
"      #MessageViewBody a {\n" +
"        color: inherit;\n" +
"        text-decoration: none;\n" +
"        font-size: inherit;\n" +
"        font-family: inherit;\n" +
"        font-weight: inherit;\n" +
"        line-height: inherit;\n" +
"      }\n" +
"      .btn-primary table td:hover {\n" +
"        background-color: #34495e !important;\n" +
"      }\n" +
"      .btn-primary a:hover {\n" +
"        background-color: #34495e !important;\n" +
"        border-color: #34495e !important;\n" +
"      }\n" +
"    }\n" +
"    </style>\n" +
"  </head>\n" +
"  <body class='' style='background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;'>\n" +
"    <table border='0' cellpadding='0' cellspacing='0' class='body' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;'>\n" +
"      <tr>\n" +
"        <td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>&nbsp;</td>\n" +
"        <td class='container' style='font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;'>\n" +
"          <div class='content' style='box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;'>\n" +
"\n" +
"            <!-- START CENTERED WHITE CONTAINER -->\n" +
"            <span class='preheader' style='color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;'>Bienvenue sur notre plateforme.</span>\n" +
"            <table class='main' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;'>\n" +
"\n" +
"              <!-- START MAIN CONTENT AREA -->\n" +
"              <tr>\n" +
"                <td class='wrapper' style='font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;'>\n" +
"                  <table border='0' cellpadding='0' cellspacing='0' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;'>\n" +
"                    <tr>\n" +
"                      <td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>\n" +
"                        <p style='font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;'>Salut,</p>\n" +
"                        <p style='font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;'>Nous sommes très heureux que vous choisissiez notre plateforme pour réserver tes événements.</p>\n" +
"                        <table border='0' cellpadding='0' cellspacing='0' class='btn btn-primary' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;'>\n" +
"                          <tbody>\n" +
"                            <tr>\n" +
"                              <td align='left' style='font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;'>\n" +
"                                <table border='0' cellpadding='0' cellspacing='0' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;'>\n" +
"                                  <tbody>\n" +
"                                    <tr>\n" +
"                                      <td style='font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;'><a href='https://imgbb.com/'><img src='https://i.ibb.co/GvNMTNH/tester.jpg' alt='CyberGens' border='0'></a> </td>\n" +
"                                    </tr>\n" +
"                                  </tbody>\n" +
"                                </table>\n" +
"                              </td>\n" +
"                            </tr>\n" +
"                          </tbody>\n" +
"                        </table>\n" +
"                        <p style='font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;'>C'est votre code QR dessous dans les pièces jointes qui peut être téléchargé , tu peut le montré au personnel de l'evenement..</p>\n" +
"                        <p style='font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px;'>Bonne chance!</p>\n" +
"                      </td>\n" +
"                    </tr>\n" +
"                  </table>\n" +
"                </td>\n" +
"              </tr>\n" +
"\n" +
"            <!-- END MAIN CONTENT AREA -->\n" +
"            </table>\n" +
"\n" +
"            <!-- START FOOTER -->\n" +
"            <div class='footer' style='clear: both; Margin-top: 10px; text-align: center; width: 100%;'>\n" +
"              <table border='0' cellpadding='0' cellspacing='0' style='border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;'>\n" +
"                <tr>\n" +
"                  <td class='content-block' style='font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;'>\n" +
"                    <span class='apple-link' style='color: #999999; font-size: 12px; text-align: center;'>CyberGens</span>\n" +
"                  </td>\n" +
"                </tr>\n" +
"                <tr>\n" +
"                  <td class='content-block powered-by' style='font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;'>\n" +
"                    Powered by <a href='http://htmlemail.io' style='color: #999999; font-size: 12px; text-align: center; text-decoration: none;'>CyberGens</a>.\n" +
"                  </td>\n" +
"                </tr>\n" +
"              </table>\n" +
"            </div>\n" +
"            <!-- END FOOTER -->\n" +
"\n" +
"          <!-- END CENTERED WHITE CONTAINER -->\n" +
"          </div>\n" +
"        </td>\n" +
"        <td style='font-family: sans-serif; font-size: 14px; vertical-align: top;'>&nbsp;</td>\n" +
"      </tr>\n" +
"    </table>\n" +
"  </body>\n" +
"</html>";
 }

    public void envoyer(String AdresseMail, String objet ,String QrImagePath) {

        final String username = "amine.youssef@esprit.tn";
        final String password = "183JMT2297";

// Etape 1 : Création de la session
        Properties props = new Properties();

        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.protocol.ssl.trust", "*");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

//        Session session = Session.getInstance(props, null);
        try {
// Etape 2 : Création de l'objet Message

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("amine.youssef@esprit.tn"));
            System.out.println("1");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(AdresseMail));
            System.out.println("2");
            message.setSubject(objet);

            // message.setText(contenu + "\n");
         //   String filename = "C:\\Users\\trabe\\OneDrive\\Nouveau dossier\\Documents\\NetBeansProjects\\Sponsoring\\src\\gui\\images\\1572211074945.jpg";

            MimeBodyPart mbp2 = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            // creation partie principale du message
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(this.mailTemplate(),"text/html");
            multipart.addBodyPart(messageBodyPart);

            // creation et ajout de la piece jointe
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(QrImagePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(QrImagePath);
            multipart.addBodyPart(messageBodyPart);

            // ajout des éléments au mail
            message.setContent(multipart);
// Etape 3 : Envoyer le message
            System.out.println("3");
            //Transport transport =session.getTransport();
            System.out.println(message);
            Transport transport = session.getTransport();
//            transport.connect(username, password);
            transport.send(message);

            System.out.println("4");
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);

        }

    }
    
    
    
    
}
