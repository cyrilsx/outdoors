package org.nexu.outdoors.web.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MailService {

    public static final String FROM = "from";

    @Autowired
    @Qualifier("mailConfiguration")
    private Properties mailProperties;

    private ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public void sendConfirmationMail(final String to, final String key) {

        threadPool.submit(new Runnable() {
            @Override
            public void run() {

                // Sender's email ID needs to be mentioned
                String from = mailProperties.getProperty(FROM);

                // Get the default Session object.
                Session session = Session.getDefaultInstance(mailProperties);

                try{
                    // Create a default MimeMessage object.
                    MimeMessage message = new MimeMessage(session);

                    // Set From: header field of the header.
                    message.setFrom(new InternetAddress(from));

                    // Set To: header field of the header.
                    message.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(to));

                    // Set Subject: header field
                    message.setSubject("Welcome to Outdoors!");

                    // Create the message part
                    BodyPart messageBodyPart = new MimeBodyPart();

                    // Fill the message add link
                    messageBodyPart.setText("Hello, activation key " + key);

                    // Create a multipar message
                    Multipart multipart = new MimeMultipart();

                    // Set text message part
                    multipart.addBodyPart(messageBodyPart);

                    // Part two is attachment
                    messageBodyPart = new MimeBodyPart();
                    //String filename = "file.txt";
                    //DataSource source = new FileDataSource(filename);
                    //messageBodyPart.setDataHandler(new DataHandler(source));
                    //messageBodyPart.setFileName(filename);
                    multipart.addBodyPart(messageBodyPart);

                    // Send the complete message parts
                    message.setContent(multipart);

                    // Send message
                    Transport.send(message);
                }catch (MessagingException mex) {
                    mex.printStackTrace();
                }
            }
        });

    }
}
