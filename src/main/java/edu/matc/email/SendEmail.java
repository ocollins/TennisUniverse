package edu.matc.email;

import org.apache.log4j.Logger;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created by O Collins on 4/27/17.
 * This class will create email body and assign to and from email addresses
 * It will connect to the mail service and send the email
 */
public class SendEmail {
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * Send email.
     *
     * @param userName     the user name
     * @param password     the password
     * @param emailAddress the email address
     * @throws MessagingException the messaging exception
     */
    public void sendEmail(String userName, String password, String emailAddress)
            throws MessagingException{
        // Recipient's email ID needs to be mentioned.
        String to = emailAddress;
        // Sender's email ID needs to be mentioned
        String from = "tennisuniverse@gmail.com";
        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        //properties.setProperty("mail.smtp.host", host);
        properties.setProperty("smtp.gmail.com", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Reminder from Tennis Universe");

            // Now set the actual message
            message.setText("User Name: " + userName + " Password: " + password);

            // Send message
            Transport.send(message);
            logger.info("Sent message successfully....");

        }catch (MessagingException mex) {
            logger.info("Error sending email " + mex);
        }

    }


    }


