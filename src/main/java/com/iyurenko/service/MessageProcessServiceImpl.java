package com.iyurenko.service;

import com.iyurenko.CamelApplication;
import com.iyurenko.dao.domain.Thing;
import com.iyurenko.dao.repository.ThingRepository;
import com.sun.mail.smtp.SMTPTransport;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by iyurenko on 22.11.16.
 */
@Component("messageProcessService")
public class MessageProcessServiceImpl implements MessageProcessService {

    @Autowired
    private ThingRepository repository;

    @Autowired
    private CamelContext camelContext;

    private static final Logger log = LoggerFactory.getLogger(CamelApplication.class);

    public void sendDeleteMessage(Thing thing) {
        sendMessageToEmail("The thing was deleted", thing.toString());
    }

    public void sendAddMessage(Thing thing) {
        sendMessageToEmail("The thing was created", thing.toString());
    }

    @Override
    public String log() {

        StringBuilder stringBuilder = new StringBuilder("Customers found with findAll():");

        for (Thing customer : repository.findAll()) {
            stringBuilder.append(customer.toString());
        }

        stringBuilder.append("-------------------------------");

        Thing customer = repository.findOne(1L);
        stringBuilder.append("Thing found with findOne(1L):");
        stringBuilder.append(customer.toString());

        String logMessage = stringBuilder.toString();
        log.info(logMessage);

        return logMessage;
    }

    private void sendMessageToEmail(String subject, String content) {

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";


        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            String username = "tetsetsets@gmail.com";
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("urencko.igor@gmail.com"));
            message.setSubject(subject);
            message.setText(content);

            SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
            t.connect("smtp.gmail.com", username, "tetsetsetsetset231213123");
            t.sendMessage(message, message.getAllRecipients());
            t.close();

            log.info("Sent message successfully....");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
