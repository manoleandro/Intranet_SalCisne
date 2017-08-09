package br.com.sp.intranet.dao.administrador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.CsUsuario;

@Repository
@SuppressWarnings("unchecked")
public class UsuarioDAO extends GenericDAO {

	public List<CsUsuario> findAll() throws JPAException {
		List<CsUsuario> list;

		list = getSession().createQuery("SELECT model FROM CsUsuario model ORDER BY model.enable DESC, model.username")
				.list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}

	public String findSenhaByUsuario(String userName) throws JPAException {
		String senha = null;
		try {

			senha = (String) getSession()
					.createQuery("SELECT model.password FROM CsUsuario model WHERE model.username = :username")
					.setParameter("username", userName).uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw new JPAException(e.getMessage());
		}

		return senha;
	}

	public List<CsGrupo> carregarGrupos(CsUsuario usuario) throws JPAException {
		CsUsuario usuarioDB = (CsUsuario) findById(CsUsuario.class, usuario.getUsername());
		Hibernate.initialize(usuarioDB.getGrupos());

		return usuarioDB.getGrupos();
	}

	public List<CsServico> carregarServicos(CsUsuario usuario) throws JPAException {
		CsUsuario usuarioDB = (CsUsuario) findById(CsUsuario.class, usuario.getUsername());
		Hibernate.initialize(usuarioDB.getServicos());

		return usuarioDB.getServicos();
	}

	public List<CsAutorizacao> carregarAutorizacoes(CsUsuario usuario) throws JPAException {
		CsUsuario usuarioDB = (CsUsuario) findById(CsUsuario.class, usuario.getUsername());
		Hibernate.initialize(usuarioDB.getAutorizacoes());

		return usuarioDB.getAutorizacoes();
	}

	public List<CsUsuario> findByProperty(String propertyName, Object value) throws JPAException {

		StringBuffer sbSQL = new StringBuffer("from CsUsuario model");
		sbSQL.append(" where " + propertyName + "= '" + value + "'");
		sbSQL.append(" order by model.usuario");

		List<CsUsuario> list;
		list = getSession().createQuery(sbSQL.toString()).list();

		if (list == null || list.isEmpty()) {
			return null;
		}

		return list;
	}
	
	public void atualizarUltimoAcesso(String username) throws JPAException{
		getSession().createQuery("UPDATE CsUsuario SET dataUltimoAcesso = :dataAtual WHERE username = :username ")
		.setParameter("dataAtual", LocalDateTime.now())
		.setParameter("username", username)
		.executeUpdate();
	}
}