package br.com.sp.intranet.dao.administrador;

import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.CsSetor;
import br.com.sp.intranet.model.administrador.CsUsuario;

@Repository
@SuppressWarnings("unchecked")
public class SetorDAO extends GenericDAO {

	public List<CsSetor> findAll() throws JPAException {
		List<CsSetor> list;

		list = getSession().createQuery("SELECT model FROM CsSetor model ORDER BY model.descricao ASC").list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}

	public CsSetor findByDescricao(String descricao) throws JPAException {
		CsSetor setor = new CsSetor();
		setor = (CsSetor) getSession().createQuery(
				"SELECT model from CsSetor model WHERE model.descricao = '" + descricao + "' ORDER BY model.codSetor")
				.uniqueResult();

		if (setor == null)
			return null;
		return setor;
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
}