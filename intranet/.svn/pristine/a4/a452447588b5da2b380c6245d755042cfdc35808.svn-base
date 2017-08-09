package br.com.sp.intranet.service.administrador.impl;

import java.util.List;
import java.util.Random;

import javax.mail.SendFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.administrador.UsuarioDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.exception.PasswordNotMachedException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.service.administrador.UsuarioService;
import br.com.sp.intranet.util.DataTypes;
import br.com.sp.intranet.util.Email;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private static final String ASSUNTO_ESQUECI_SENHA = "INTRANET - Recuperação de senha";
	private static final String ASSUNTO_REQUISICAO_ACESSO = "INTRANET 2.0 - Requisição de acesso";
	private static final String EMAIL_REQUISICAO_ACESSO = "rafael.nakano@salcisne.com.br";
	
	@Autowired
	private UsuarioDAO dao;
	
	@Override
	@Transactional("sic")
	public void updatePassword(CsUsuario usuario) throws SendFailedException, Exception {
		String senha = geraSenhaAleatoria(4);
		usuario.setPassword(DataTypes.encode(senha));

		update(usuario);

		enviaEmailSenha(senha, usuario);
	}
	
	@Override
	@Transactional("sic")
	public void updateAndChangePassword(CsUsuario usuario, String senhaAtual, String novaSenha) throws PasswordNotMachedException, JPAException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		CsUsuario userDB = (CsUsuario) dao.findById(CsUsuario.class, usuario.getUsername());

		if (passwordEncoder.matches(senhaAtual, userDB.getPassword())) {
			userDB.setPassword(passwordEncoder.encode(novaSenha));
			update(userDB);
		} else {
			throw new PasswordNotMachedException();
		}
	}

	public void enviaEmailSenha(String senha, CsUsuario usuario) throws SendFailedException, Exception {
		Email.enviar(ASSUNTO_ESQUECI_SENHA, montaMsgSenha(usuario.getNome(), senha), usuario.getEmail(), null);
	}
	
	private String montaMsgSenha(String nome, String senha) {
		String msgConfirmacao = getInicio() + getCabecalho(nome) + "<p>Sua NOVA senha é <strong>" + senha
				+ "</strong>.</p>" + "<p>Após entrar no sistema, troque esta senha por uma de sua preferência.</p>"
				+ "<p><strong>" + "SALCISNE - Recuperação de Senha<br/>" + "</strong></p>" + getFim();
		return msgConfirmacao;
	}

	private String getCabecalho(String nome) {
		return "<p><br/><strong>" + "Prezado(a) Sr(a) " + nome.toUpperCase() + "</strong></p><p>"
				+ "Este e-mail é automático. Favor não Responder." + "</p>";
	}

	private String getInicio() {
		return "<body style='font-family:Verdana;font-size:13px;'>";
	}

	private String getFim() {
		return "</body>";
	}
	
	@Override
	public void enviarEmailRequisicao(String email) throws SendFailedException, Exception {
		Email.enviar(ASSUNTO_REQUISICAO_ACESSO, montaMensagemEmailRequisicao(email), EMAIL_REQUISICAO_ACESSO, email);

	}

	public String montaMensagemEmailRequisicao(String email) {
		StringBuffer retorno = new StringBuffer();

		retorno.append(getInicio());
		retorno.append("O usuário do email abaixo solicita acesso para o sistema INTRANET 2.0, verifique com seu superior!");
		retorno.append("<br/>");
		retorno.append("<br/>");
		retorno.append("Email: " + email);
		retorno.append("<br/>");
		retorno.append("<br/>");
		retorno.append("Atenciosamente");
		retorno.append(getFim());

		return retorno.toString();
	}

	private String geraSenhaAleatoria(int nDigitos) {

		String retorno = "";
		Random r = new Random();

		char[] simbolos = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWYZ".toCharArray();

		StringBuffer sb = new StringBuffer(nDigitos);
		for (int i = 0; i < nDigitos; ++i) {
			sb.append(simbolos[r.nextInt(simbolos.length)]);
		}
		retorno = sb.toString();

		return retorno;
	}
	
	@Override
	@Transactional("sic")
	public CsUsuario tratarPassword(CsUsuario usuario){
		if(usuario != null && usuario.getSenha() != null && !usuario.getSenha().isEmpty())
			usuario.setPassword(DataTypes.encode(usuario.getSenha()));
		
		return usuario;
	}
	
	@Override
	@Transactional("sic")
	public void delete(GenericEntity entity) throws JPAException {
		dao.delete(entity);
	}

	@Override
	@Transactional("sic")
	public void update(GenericEntity entity) throws JPAException {
		dao.update(entity);
	}

	@Override
	@Transactional("sic")
	public void save(GenericEntity entity) throws JPAException {
		dao.save(entity);
	}

	@Override
	@Transactional("sic")
	public List<CsUsuario> findAll() throws JPAException {
		return dao.findAll();
	}
	
	@Override
	@Transactional("sic")
	public CsUsuario findById(String usuario) throws JPAException{
		return (CsUsuario) dao.findById(CsUsuario.class, usuario);
	}
	
	@Override
	@Transactional("sic")
	public List<CsGrupo> carregarGrupos(CsUsuario usuario) throws JPAException{
		return dao.carregarGrupos(usuario);
	}
	
	@Override
	@Transactional("sic")
	public List<CsServico> carregarServicos(CsUsuario usuario) throws JPAException{
		return dao.carregarServicos(usuario);
	}
	
	@Override
	@Transactional("sic")
	public List<CsAutorizacao> carregarAutorizacoes(CsUsuario usuario) throws JPAException{
		return dao.carregarAutorizacoes(usuario);
	}

	@Override
	@Transactional("sic")
	public List<CsUsuario> findByProperty(String propertyName, Object value) throws JPAException {
		return dao.findByProperty(propertyName, value);
	}
	
	@Override
	@Transactional("sic")
	public void atualizarUltimoAcesso(String username) throws JPAException{
		dao.atualizarUltimoAcesso(username);
	}
}