package Core.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import static qooco.ConstantData.EMAIL_SENDER;
import static qooco.ConstantData.PASS_EMAIL;
import static qooco.StartDriver.sessionID;
import static Core.TestMethods.XmlReader.readXML;


public class SendMail {


    public void sendMail(String Body) {
        String baseEmail =readXML("baseData","mailSend");
        String user= EMAIL_SENDER;
        String pass= PASS_EMAIL;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(baseEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(readXML("baseData","mailList")));
            message.setSubject("SESSION ID: " + sessionID);
            message.setText(Body,"UTF-8","html");
            SimpleDateFormat format = new SimpleDateFormat("HH");
            Calendar cal = Calendar.getInstance();
            Date dateTime = cal.getTime();
            format.format(dateTime);
            Transport.send(message);


        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
}