package br.com.sp.intranet.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {

	public static void enviar(String assunto, String mensagem, String destinatario, String remetente) throws SendFailedException, Exception {
		Properties props = new Properties();

		props.put("mail.smtp.host", "200.219.212.5");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Authenticator autenticador = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("intranet@salcisne.com.br", "sal@12589");
			}
		};

		Session s = Session.getInstance(props, autenticador);
		InternetAddress from = new InternetAddress("intranet@salcisne.com.br");

		if (remetente != null) {
			from = null;
			from = new InternetAddress(remetente);
		}

		MimeMessage message = new MimeMessage(s);
		message.setFrom(from);

		InternetAddress to = new InternetAddress(destinatario);

		message.addRecipient(Message.RecipientType.TO, to);
		message.setSubject(assunto);
		message.setContent(mensagem, "text/html");
		Transport.send(message);
	}
}