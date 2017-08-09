package com.mdSync.util;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaEmail {
    public static void enviar(String assunto, String texto) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "200.219.212.5");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            Authenticator autenticador = new Authenticator(){

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("intranet@salcisne.com.br", "sal@12589");
                }
            };
            Session s = Session.getInstance((Properties)props, (Authenticator)autenticador);
            InternetAddress from = new InternetAddress("oper@salcisne.com.br");
            MimeMessage message = new MimeMessage(s);
            message.setFrom((Address)from);
            InternetAddress to = new InternetAddress("rafael.nakano@salcisne.com.br");
            message.addRecipient(Message.RecipientType.TO, (Address)to);
            message.setSubject(assunto);
            message.setText(texto == null? "nullPointerException": texto);
            Transport.send((Message)message);
        }
        catch (Exception e) {
            System.out.println("Erro no envio do email!");
            e.printStackTrace();
        }
    }

}

