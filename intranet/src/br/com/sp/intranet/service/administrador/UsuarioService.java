package br.com.sp.intranet.service.administrador;

import java.util.List;

import javax.mail.SendFailedException;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.exception.PasswordNotMachedException;
import br.com.sp.intranet.model.GenericEntity;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.CsUsuario;

public interface UsuarioService {

	public void updatePassword(CsUsuario usuario) throws SendFailedException, Exception;

	public void delete(GenericEntity entity) throws JPAException;

	public void update(GenericEntity entity) throws JPAException;

	public void save(GenericEntity entity) throws JPAException;

	public List<CsUsuario> findAll() throws JPAException;

	public CsUsuario findById(String usuario) throws JPAException;
	
	public CsUsuario tratarPassword(CsUsuario usuario);
	
	public List<CsGrupo> carregarGrupos(CsUsuario usuario) throws JPAException;
	
	public void updateAndChangePassword(CsUsuario usuario, String senhaAtual, String novaSenha) throws PasswordNotMachedException, JPAException;
	
	public List<CsServico> carregarServicos(CsUsuario usuario) throws JPAException;
	
	public List<CsAutorizacao> carregarAutorizacoes(CsUsuario usuario) throws JPAException;
	
	public List<CsUsuario> findByProperty (String propertyName, Object value) throws JPAException;

	public void enviarEmailRequisicao(String email) throws SendFailedException, Exception;

	public void atualizarUltimoAcesso(String username) throws JPAException;
}