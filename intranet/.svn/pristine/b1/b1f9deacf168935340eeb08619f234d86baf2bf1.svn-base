package br.com.sp.intranet.dao.administrador;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.app.CsAutorizacaoApp;
import br.com.sp.intranet.model.administrador.app.CsServicoApp;

@Repository
public class GrupoDAO extends GenericDAO {

	@SuppressWarnings("unchecked")
	public List<CsGrupo> findAll() throws JPAException{
		List<CsGrupo> list;
		
		list = getSession().createQuery("SELECT model FROM CsGrupo model ORDER BY model.descricao").list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
	
	public List<CsServico> carregarServicos(CsGrupo grupo) throws JPAException {
		CsGrupo grupoDb = (CsGrupo) findById(CsGrupo.class, grupo.getId());
		Hibernate.initialize(grupoDb.getServicos());

		return grupoDb.getServicos();
	}
	
	public List<CsAutorizacao> carregarAutorizacoes(CsGrupo grupo) throws JPAException{
		CsGrupo grupoDb = (CsGrupo) findById(CsGrupo.class, grupo.getId());
		Hibernate.initialize(grupoDb.getAutorizacoes());

		return grupoDb.getAutorizacoes();
	}
	
	public List<CsServicoApp> carregarServicosApp(CsGrupo grupo) throws JPAException {
		CsGrupo grupoDb = (CsGrupo) findById(CsGrupo.class, grupo.getId());
		Hibernate.initialize(grupoDb.getServicosApp());

		return grupoDb.getServicosApp();
	}
	
	public List<CsAutorizacaoApp> carregarAutorizacoesApp(CsGrupo grupo) throws JPAException{
		CsGrupo grupoDb = (CsGrupo) findById(CsGrupo.class, grupo.getId());
		Hibernate.initialize(grupoDb.getAutorizacoesApp());

		return grupoDb.getAutorizacoesApp();
	}
}