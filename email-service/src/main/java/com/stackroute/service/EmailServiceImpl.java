package com.stackroute.service;

import com.stackroute.entity.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class EmailServiceImpl implements EmailService{
    @Value("${spring.mail.id}")
    private String dataSourceEmail;
    @Value("${spring.mail.password}")
    private String dataSourcePass;

    @Override
    public void sendEmail(Email emailBody) {
        try {
            String to = emailBody.getPlayerEmail();
            String from  = dataSourceEmail;
            String subject = "SportsArena: Booking Confirmation " + emailBody.getBookingID();
            String message = ""
                    + "<div style='background-color: #00203FFF;width: 50rem;margin-top:60px;margin-left: 100px;margin:15rm;height: 55rem;border-radius: 1rem;'>"
                    + "<h1 style='color: white; position: relative;margin: 0;left: 17rem;top: 3rem;padding-top: 10px;text-align: center;'>Sports Arena</h1>"
                    + "<div style='background-color: #ADEFD1FF; text-align: center;margin-left:120px; margin-top:30px;width: 50%;padding: 5rem;position: relative;left: 20%;top: 30% ;border-radius: 1rem;'>"
                    + "<h2 style='margin: 0;font-size: 15px;text-align: center;'>Your Slot Booking Details</h2>"
                    + "<br> <br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>Booking Id: " + emailBody.getBookingID() + "</h4><br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>Slot Id: " + emailBody.getSlotID() + "</h4><br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>Slot Timimg: " + emailBody.getSlotStartTime() + " to " + emailBody.getSlotEndTime() + "</h4><br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>Booking Status: " + emailBody.getBookingID() + "</h4><br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>Ground Name: " + emailBody.getGroundName() + "</h4><br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>Address: " + emailBody.getAddress() + "</h4><br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>City: " + emailBody.getCity() + "</h4><br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>Date of Booking: " + emailBody.getSlotDate() + "</h4><br>"
                    + "<h4 style='color: grey; text-align: left;display: inline-block;'>Contact Email: " + emailBody.getOwnerEmail() + "</h4>"
                    + "</div>"
                    + "</div>";

            sendEmailConfiguration(message, subject, to, from);
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendEmailConfiguration(String message, String subject, String to, String from) throws MessagingException {

        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(dataSourceEmail, dataSourcePass);
            }
        });
        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipients(Message.RecipientType.TO, to);
            mimeMessage.setSubject(subject);
            mimeMessage.setContent(message, "text/html");
            Transport.send(mimeMessage);

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
