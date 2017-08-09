package br.com.sp.intranet.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class GenericEmail {

	private String emailOrigem;
	private List<EmailRecipient> listaDestinatario = new ArrayList<EmailRecipient>();
	private List<EmailRecipient> listaDestinatarioCc = new ArrayList<EmailRecipient>();
	private List<EmailRecipient> listaDestinatarioCco = new ArrayList<EmailRecipient>();
	private List<File> listaArquivo = new ArrayList<File>();
	private String assunto;
	private String conteudo;

	public GenericEmail() {
	}

	public GenericEmail(String emailOrigem, String assunto, String conteudo, List<EmailRecipient> listaDestinatario,
			List<EmailRecipient> listaDestinatarioCc, List<EmailRecipient> listaDestinatarioCco) {

		this.emailOrigem = emailOrigem;
		this.assunto = assunto;
		this.conteudo = conteudo;
		this.listaDestinatario = listaDestinatario;
		this.listaDestinatarioCc = listaDestinatarioCc;
		this.listaDestinatarioCco = listaDestinatarioCco;
	}

	public GenericEmail(String emailOrigem, String assunto, String conteudo, List<EmailRecipient> listaDestinatario,
			List<EmailRecipient> listaDestinatarioCc) {

		this.emailOrigem = emailOrigem;
		this.assunto = assunto;
		this.conteudo = conteudo;
		this.listaDestinatario = listaDestinatario;
		this.listaDestinatarioCc = listaDestinatarioCc;
	}

	public GenericEmail(String emailOrigem, String assunto, String conteudo, List<EmailRecipient> listaDestinatario) {

		this.emailOrigem = emailOrigem;
		this.assunto = assunto;
		this.conteudo = conteudo;
		this.listaDestinatario = listaDestinatario;
	}

	public GenericEmail(String emailOrigem, String assunto, String conteudo, String destinatario) {

		this.emailOrigem = emailOrigem;
		this.assunto = assunto;
		this.conteudo = conteudo;
		EmailRecipient email = new EmailRecipient();
		email.setEndereco(destinatario);
		this.listaDestinatario.add(email);
	}

	public GenericEmail(String emailOrigem, String assunto, String conteudo) {

		this.emailOrigem = emailOrigem;
		this.assunto = assunto;
		this.conteudo = conteudo;
	}

	public String getEmailOrigem() {
		return emailOrigem;
	}

	public List<EmailRecipient> getListaDestinatario() {
		return listaDestinatario;
	}

	public List<EmailRecipient> getListaDestinatarioCc() {
		return listaDestinatarioCc;
	}

	public List<EmailRecipient> getListaDestinatarioCco() {
		return listaDestinatarioCco;
	}

	public String getAssunto() {
		return assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public List<File> getListaArquivo() {
		return listaArquivo;
	}

	public void adicionaDestinatario(String destinatario) {
		EmailRecipient email = new EmailRecipient();
		email.setEndereco(destinatario);
		adicionaDestinatario(email);
	}

	public void adicionaDestinatario(EmailRecipient destinatario) {
		getListaDestinatario().add(destinatario);
	}

	public void adicionaDestinatarioCopia(String destinatario) {
		EmailRecipient email = new EmailRecipient();
		email.setEndereco(destinatario);
		adicionaDestinatarioCopia(email);
	}

	public void adicionaDestinatarioCopia(EmailRecipient destinatario) {
		getListaDestinatarioCc().add(destinatario);
	}

	public void adicionaDestinatarioCopiaOculta(String destinatario) {
		EmailRecipient email = new EmailRecipient();
		email.setEndereco(destinatario);
		adicionaDestinatarioCopiaOculta(email);
	}

	public void adicionaDestinatarioCopiaOculta(EmailRecipient destinatario) {
		getListaDestinatarioCco().add(destinatario);
	}

	public void adicionaArquivo(File arquivo) {
		getListaArquivo().add(arquivo);
	}

	/**
	 * Metodo para enviar um email com base nos atributos preenchidos
	 * 
	 * @return
	 */
	public Boolean enviarEmail() {

		Boolean enviado = new Boolean(false);
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", ConstantUtils.HOST_SMTP);
			props.put("mail.smtp.port", ConstantUtils.PORTA_SMTP);
			props.put("mail.smtp.auth", "true");

			// props.put("mail.smtp.port", "465");
			// props.put("mail.smtp.socketFactory.class",
			// "javax.net.ssl.SSLSocketFactory");
			// props.put("mail.smtp.socketFactory.fallback", "false");
			Authenticator autenticador = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(ConstantUtils.USUARIO_SMTP, ConstantUtils.SENHA_SMTP);
				}
			};

			Session s = Session.getInstance(props, autenticador);

			InternetAddress from = new InternetAddress(getEmailOrigem());

			MimeMessage message = new MimeMessage(s);
			message.setFrom(from);

			List<EmailRecipient> listaDestinatario = getListaDestinatario();
			for (Iterator<EmailRecipient> iterator = listaDestinatario.iterator(); iterator.hasNext();) {
				EmailRecipient email = (EmailRecipient) iterator.next();
				if (validaEmail(email)) {
					InternetAddress to = new InternetAddress(email.getEndereco().trim(), email.getNome());
					message.addRecipient(Message.RecipientType.TO, to);
				}
			}

			List<EmailRecipient> listaDestinatarioCc = getListaDestinatarioCc();
			for (Iterator<EmailRecipient> iterator = listaDestinatarioCc.iterator(); iterator.hasNext();) {
				EmailRecipient email = (EmailRecipient) iterator.next();
				if (validaEmail(email)) {
					InternetAddress to = new InternetAddress(email.getEndereco().trim(), email.getNome());
					message.addRecipient(Message.RecipientType.CC, to);
				}
			}

			List<EmailRecipient> listaDestinatarioCco = getListaDestinatarioCco();
			for (Iterator<EmailRecipient> iterator = listaDestinatarioCco.iterator(); iterator.hasNext();) {
				EmailRecipient email = (EmailRecipient) iterator.next();
				if (validaEmail(email)) {
					InternetAddress to = new InternetAddress(email.getEndereco().trim(), email.getNome());
					message.addRecipient(Message.RecipientType.BCC, to);
				}
			}

			Multipart multiPartMail = new MimeMultipart();
			MimeBodyPart mbpText = new MimeBodyPart();
			mbpText.setContent(formatarHtml(getConteudo()), "text/html; charset=UTF-8");
			multiPartMail.addBodyPart(mbpText);

			if (getListaArquivo().size() > 0) {
				List<File> listaArquivo = getListaArquivo();
				for (Iterator<File> iterator = listaArquivo.iterator(); iterator.hasNext();) {
					File file = (File) iterator.next();
					MimeBodyPart mbpFile = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(file);
					mbpFile.setDataHandler(new DataHandler(fds));
					mbpFile.setFileName(fds.getName());
					multiPartMail.addBodyPart(mbpFile);
				}
			}
			message.setSubject(getAssunto());
			message.setContent(multiPartMail);

			Transport.send(message);
			enviado = new Boolean(true);
		} catch (Exception e) {
			System.out.println("Erro no envio do email!");
			e.printStackTrace();
		}

		return enviado;
	}

	/**
	 * Metodo para formatar a mensagem com tags html
	 * 
	 * @param message
	 * @return
	 */
	private String formatarHtml(String message) {

		message = message.replaceAll("\\n", "<br>");
		return message;
	}

	private Boolean validaEmail(EmailRecipient email) {
		if (email.getEndereco() != null && !email.getEndereco().equals("") && email.getEndereco().contains("@")) {
			if (email.getEndereco().indexOf("@") > 1 && email.getEndereco().lastIndexOf(".") != -1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
