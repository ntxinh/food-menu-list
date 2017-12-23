package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.service.AppEngineMailApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// [START simple_includes]
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// [END simple_includes]

// [START multipart_includes]
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
// [END multipart_includes]

@Service
public class AppEngineMailApiServiceImpl implements AppEngineMailApiService {

    private static final Logger LOGGER = Logger.getLogger(AppEngineMailApiServiceImpl.class.getName());

    @Override
    public void sendSimpleMail() {
        // [START simple_example]
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("nguyentrucxjnh@gmail.com", "github.com/nguyentrucxinh XinhNguyen"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("tuthithaodung@gmail.com", "Ms. Dung"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText("This is a test");
            Transport.send(msg);
        } catch (AddressException e) {
            LOGGER.info("Throw AddressException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (MessagingException e) {
            LOGGER.info("Throw MessagingException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.info("Throw UnsupportedEncodingException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        // [END simple_example]
    }

    @Override
    public void sendMultipartMail(MultipartFile multipartFile) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "...";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("nguyentrucxjnh@gmail.com", "github.com/nguyentrucxinh XinhNguyen"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("tuthithaodung@gmail.com", "Ms. Dung"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText(msgBody);

            // [START multipart_example]
            String htmlBody = "";          // ...
            byte[] attachmentData = multipartFile.getBytes();  // ...
            Multipart mp = new MimeMultipart();

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html");
            mp.addBodyPart(htmlPart);

            MimeBodyPart attachment = new MimeBodyPart();
            InputStream attachmentDataStream = new ByteArrayInputStream(attachmentData);
            attachment.setFileName("manual.pdf"); //multipartFile.getOriginalFilename()
            attachment.setContent(attachmentDataStream, "application/pdf"); //multipartFile.getContentType()
            mp.addBodyPart(attachment);

            msg.setContent(mp);
            // [END multipart_example]

            Transport.send(msg);

        } catch (AddressException e) {
            LOGGER.info("Throw AddressException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (MessagingException e) {
            LOGGER.info("Throw MessagingException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.info("Throw UnsupportedEncodingException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (IOException e) {
            LOGGER.info("Throw IOException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }
}
