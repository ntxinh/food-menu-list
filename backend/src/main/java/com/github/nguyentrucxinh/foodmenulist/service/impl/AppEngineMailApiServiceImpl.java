package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.common.constants.MailType;
import com.github.nguyentrucxinh.foodmenulist.common.constants.RecipientType;
import com.github.nguyentrucxinh.foodmenulist.dto.MailDto;
import com.github.nguyentrucxinh.foodmenulist.service.AppEngineMailApiService;
import com.github.nguyentrucxinh.foodmenulist.service.GoogleCloudStorageService;
import com.github.nguyentrucxinh.foodmenulist.service.TemplateService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TemplateService templateService;

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
            msg.setSubject("sendSimpleMail");
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
    public void sendMultipartMail(byte[] content, String fileName, String contentType) {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "...";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("nguyentrucxjnh@gmail.com", "github.com/nguyentrucxinh XinhNguyen"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress("tuthithaodung@gmail.com", "Ms. Dung"));
            msg.setSubject("sendMultipartMail");
            msg.setText(msgBody);

            // [START multipart_example]
            String htmlBody = "";          // ...
            byte[] attachmentData = content;  // ...
            Multipart mp = new MimeMultipart();

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlBody, "text/html");
            mp.addBodyPart(htmlPart);

            MimeBodyPart attachment = new MimeBodyPart();
            InputStream attachmentDataStream = new ByteArrayInputStream(attachmentData);
            attachment.setFileName(fileName);
            attachment.setContent(attachmentDataStream, contentType);
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
        }
    }

    @Override
    public void sendMultipartMail(MultipartFile multipartFile) {
        try {
            sendMultipartMail(multipartFile.getBytes(), multipartFile.getOriginalFilename(), multipartFile.getContentType());
        } catch (IOException e) {
            LOGGER.info("Throw IOException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }

    @Override
    public void sendMultipartMail(Blob blob) {
        sendMultipartMail(blob.getContent(Blob.BlobSourceOption.generationMatch()), blob.getName(), blob.getContentType());
    }

    @Override
    public void sendMail(MailType mailType, MailDto mailDto) {

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("nguyentrucxjnh@gmail.com", "github.com/nguyentrucxinh XinhNguyen"));

            mailDto.getRecipientDtos().forEach(recipientDto -> {
                try {
                    Message.RecipientType recipientType;
                    if (RecipientType.TO == recipientDto.getRecipientType()) {
                        recipientType = Message.RecipientType.TO;
                    } else if (RecipientType.CC == recipientDto.getRecipientType()) {
                        recipientType = Message.RecipientType.CC;
                    } else {
                        recipientType = Message.RecipientType.BCC;
                    }
                    msg.addRecipient(recipientType,
                            new InternetAddress(recipientDto.getAddress(), recipientDto.getPersonal()));
                } catch (MessagingException e) {
                    LOGGER.info("Throw MessagingException: ");
                    LOGGER.log(Level.SEVERE, e.toString(), e);
                } catch (UnsupportedEncodingException e) {
                    LOGGER.info("Throw UnsupportedEncodingException: ");
                    LOGGER.log(Level.SEVERE, e.toString(), e);
                }
            });

            msg.setSubject(mailDto.getSubject());
            msg.setText("...");

            if (MailType.MULTIPART == mailType) {
                // [START multipart_example]
                Multipart mp = new MimeMultipart();

                String content;
                if (mailDto.isUseTemplate()) {
                    content = templateService.mergeTemplateIntoString(mailDto.getTemplateDto().getTemplateName(), mailDto.getTemplateDto().getDataModel());
                } else {
                    content = mailDto.getContent();
                }

                MimeBodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent(content, mailDto.getContentType());
                mp.addBodyPart(htmlPart);

                MimeBodyPart attachment = new MimeBodyPart();

                mailDto.getFileAttachmentDtos().forEach(fileAttachmentDto -> {
                    InputStream attachmentDataStream = new ByteArrayInputStream(fileAttachmentDto.getContent());
                    try {
                        attachment.setFileName(fileAttachmentDto.getFileName());
                        attachment.setContent(attachmentDataStream, fileAttachmentDto.getContentType());
                        mp.addBodyPart(attachment);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });

                msg.setContent(mp);
                // [END multipart_example]
            }

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
