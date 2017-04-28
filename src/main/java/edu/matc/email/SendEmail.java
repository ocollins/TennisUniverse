package edu.matc.email;

import org.apache.log4j.Logger;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import com.sun.mail.smtp.*;

/**
 * Created by O Collins on 4/27/17.
 * This class will create email body and assign to and from email addresses
 * It will connect to the mail service and send the email
 */
public class SendEmail {
    private Logger logger = Logger.getLogger(this.getClass());

    public boolean sendEmail() throws MessagingException{
        // Recipient's email ID needs to be mentioned.
        String to = "olenacollins1333@gmail.com";
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
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("Testing email");

            // Send message
            Transport.send(message);
            logger.info("Sent message successfully....");

        }catch (MessagingException mex) {
            logger.info("Error sending email " + mex);
        }
        return true;
    }


    }


