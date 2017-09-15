package cz.campanus.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author jan.hadas@i.cz
 */
public class StartTLSMail {
  private static final String FROM = "hadas.jan@gmail.com";
  private static final String HOST = "smtp.gmail.com";
  private static final String LOGIN = "hadas.jan@gmail.com";
  private static final String PASSWORD = "hadswd12";
  private static final int PORT = 587;

  public static void sendEmail(String body, String subject, String recipient) throws MessagingException {

    Properties mailProps = new Properties();
    mailProps.put("mail.smtp.host", HOST);
    mailProps.put("mail.smtp.port", PORT);
    mailProps.put("mail.smtp.auth", true);
    mailProps.put("mail.smtp.socketFactory.port", PORT);
    mailProps.put("mail.transport.protocol", "smtp");
    mailProps.put("mail.smtp.socketFactory.fallback", "false");
    mailProps.put("mail.smtp.starttls.enable", "true");

    Session mailSession = Session.getDefaultInstance(mailProps, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(LOGIN, PASSWORD);
      }
    });

    MimeMessage message = new MimeMessage(mailSession);
    message.setFrom(new InternetAddress(FROM));
    String[] emails = { recipient };
    InternetAddress dests[] = new InternetAddress[emails.length];
    for (int i = 0; i < emails.length; i++) {
      dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
    }
    message.setRecipients(Message.RecipientType.TO, dests);
    message.setSubject(subject, "UTF-8");
    Multipart mp = new MimeMultipart();
    MimeBodyPart mbp = new MimeBodyPart();
    mbp.setContent(body, "text/html;charset=utf-8");
    mp.addBodyPart(mbp);
    message.setContent(mp);
    message.setSentDate(new java.util.Date());

    Transport.send(message);
  }
}
