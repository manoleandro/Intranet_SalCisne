package br.com.sp.intranet.service.arquivo;

public class EmailArquivo {
	
	private String assunto;
	private String corpoEmail;
	private String destinatario;
	private String cc;
	private String emailOrigem="admin@salcisne.com.br";
	
	public EmailArquivo(){
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpoEmail() {
		return corpoEmail;
	}

	public void setCorpoEmail(String corpoEmail) {
		this.corpoEmail = corpoEmail;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getEmailOrigem() {
		return emailOrigem;
	}

	public void setEmailOrigem(String emailOrigem) {
		this.emailOrigem = emailOrigem;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}
}
