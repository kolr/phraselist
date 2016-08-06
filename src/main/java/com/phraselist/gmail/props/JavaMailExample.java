package com.phraselist.gmail.props;

import com.phraselist.gmail.props.EmailPropertiesResolver;
import com.phraselist.gmail.props.entities.Credentials;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

/**
 * 05.08.2016
 * Created by Rodion.
 */
public class JavaMailExample {
    private static final Logger LOG = Logger.getLogger(JavaMailExample.class);

    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;

    static Credentials credentials;


    public static void main(String args[]) throws AddressException, MessagingException {
        generateAndSendEmail();
        System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
    }

    public static void generateAndSendEmail() throws AddressException, MessagingException {
        EmailPropertiesResolver resolver = new EmailPropertiesResolver();
        try {
            credentials = resolver.getProperties();
        } catch (IOException e) {
            LOG.error(e);
        }
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.ssl.trust", credentials.getTrust());

        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        System.out.println("Mail Server Properties have been setup successfully..");

        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("example@example.com"));
        generateMailMessage.setSubject("Greetings from PhraseList. (no reply)");
        String emailBody = "Test email by PhraseList. " + "<br><br>Best Regards, <br>";
        generateMailMessage.setContent(emailBody, "text/html");
        System.out.println("Mail Session has been created successfully..");

        Transport transport = getMailSession.getTransport(credentials.getTransport());

        transport.connect(credentials.getTrust(), credentials.getEmail(), credentials.getPass());
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

}
