/**
 * 
 */
package br.com.sp.intranet.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eduardo
 *
 */
public class EmailsRecipient {

	private List<EmailRecipient> destinatario = new ArrayList<EmailRecipient>();
	private List<EmailRecipient> destinatarioCopia = new ArrayList<EmailRecipient>();
	private List<EmailRecipient> destinatarioCopiaOculta = new ArrayList<EmailRecipient>();

	public List<EmailRecipient> getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(List<EmailRecipient> destinatario) {
		this.destinatario = destinatario;
	}

	public void setDestinatario(EmailRecipient destinatario) {
		this.destinatario.add(destinatario);
	}

	public List<EmailRecipient> getDestinatarioCopia() {
		return destinatarioCopia;
	}

	public void setDestinatarioCopia(List<EmailRecipient> destinatarioCopia) {
		this.destinatarioCopia = destinatarioCopia;
	}

	public void setDestinatarioCopia(EmailRecipient destinatarioCopia) {
		this.destinatarioCopia.add(destinatarioCopia);
	}

	public List<EmailRecipient> getDestinatarioCopiaOculta() {
		return destinatarioCopiaOculta;
	}

	public void setDestinatarioCopiaOculta(List<EmailRecipient> destinatarioCopiaOculta) {
		this.destinatarioCopiaOculta = destinatarioCopiaOculta;
	}

	public void setDestinatarioCopiaOculta(EmailRecipient destinatarioCopiaOculta) {
		this.destinatarioCopiaOculta.add(destinatarioCopiaOculta);
	}

}
