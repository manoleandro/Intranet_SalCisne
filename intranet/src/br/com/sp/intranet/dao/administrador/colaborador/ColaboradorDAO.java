package br.com.sp.intranet.dao.administrador.colaborador;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.vo.rh.Colaborador;
import br.com.sp.intranet.model.administrador.vo.rh.Ferias;
import br.com.sp.intranet.model.administrador.vo.rh.HistoricoAfastamento;
import br.com.sp.intranet.model.administrador.vo.rh.HistoricoSalario;



@Repository
public class ColaboradorDAO extends GenericDAO {


	@SuppressWarnings("unchecked")
	public List<Colaborador> findAll() throws JPAException {
		List<Colaborador> list;
		list = getSession().createQuery("SELECT model FROM Colaborador model order by model.nome asc").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Colaborador> findColaboradorAtivo() throws JPAException {
		List<Colaborador> list;
		list = getSession().createQuery("SELECT model FROM Colaborador model where model.demissao IS NULL order by model.nome asc").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@Transactional("sic")
	public Colaborador findById(Long id) throws JPAException {
		return (Colaborador) findById(Colaborador.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Colaborador> findByColaborador() throws JPAException {
		List<Colaborador> list;

		StringBuffer sbSQL = new StringBuffer("SELECT model, cargo, dept, diretoria, setor, secao ");
		sbSQL.append(" FROM Colaborador model ");
		sbSQL.append(" LEFT OUTER JOIN model.cargo cargo");
		sbSQL.append(" LEFT OUTER JOIN model.departamento dept");
		sbSQL.append(" LEFT OUTER JOIN model.diretoria diretoria");
		sbSQL.append(" LEFT OUTER JOIN model.setor setor");
		sbSQL.append(" LEFT OUTER JOIN model.secao secao");
		sbSQL.append(" WHERE model.id IS NOT NULL");
		sbSQL.append(" order by model.nome ");
		
		list = getSession().createQuery(sbSQL.toString()).list();
													
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list;
		}

	}

	public int countTotal(Map<String, String> params, int inicia, int maxPorPagina) throws JPAException {
		String nome = params.get("colaborador.nome");
		String matricula = params.get("colaborador.matricula");
		String uf = params.get("colaborador.uf");
		String ativo = params.get("colaborador.demissao");
		String cargo = params.get("cargo.descricao");
		String depart = params.get("dept.descricao");

		StringBuffer sbSQL = new StringBuffer("SELECT model");
		sbSQL.append(" FROM Colaborador model");
		sbSQL.append(" LEFT OUTER JOIN model.cargo cargo");
		sbSQL.append(" LEFT OUTER JOIN model.departamento dept");
		sbSQL.append(" LEFT OUTER JOIN model.diretoria diretoria");
		sbSQL.append(" LEFT OUTER JOIN model.setor setor");
		sbSQL.append(" LEFT OUTER JOIN model.secao secao");
		sbSQL.append(" WHERE model.id IS NOT NULL");

		if (nome != null || matricula != null || uf != null || cargo != null || depart != null) {
			if (nome != null)
				sbSQL.append(" AND UPPER (model.nome) LIKE UPPER (% +'"+nome+"'+ %)");
			if (matricula != null)
				sbSQL.append(" AND model.matricula = '"+Long.parseLong(matricula.toString())+"'");
			if (uf != null)
				sbSQL.append(" AND model.filial.id = '"+Long.parseLong(uf)+"'");
			if (cargo != null)
				sbSQL.append(" AND UPPER (cargo.descricao) LIKE UPPER (% +'"+cargo+"'+ %)");
			if (depart != null)
				sbSQL.append(" AND UPPER (dept.descricao) LIKE UPPER (% +'"+depart+"'+ %)");
		}
		
		//if (uf != null) {
				//query.setParameter("uf", Long.parseLong(uf.toString()));
				//}

		if (ativo == null) {
			sbSQL.append(" AND model.demissao IS NULL");

		} else if (ativo.equalsIgnoreCase("D")) {
			sbSQL.append(" AND model.demissao IS NOT NULL");
		}
		sbSQL.append(" order by model.nome ");

		return  (int) getSession().createQuery(sbSQL.toString()).list().size();

	}
	
	@SuppressWarnings("unchecked")
	public List<HistoricoAfastamento> findByHistAfastamento(Long idColaborador) throws JPAException {
		try {
			List<HistoricoAfastamento> list;
			StringBuffer sbSQL = new StringBuffer("SELECT model FROM HistoricoAfastamento model ");
			sbSQL.append(" WHERE model.colaborador.id = :idColaborador ");
			list = getSession().createQuery(sbSQL.toString()).setParameter("idColaborador", idColaborador).list();
			return list;

		} catch (Exception e) {
			throw new JPAException(Ferias.class, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ferias> findByHistFerias(Long idColaborador) throws JPAException {
			List<Ferias> list;
			StringBuffer sbSQL = new StringBuffer("SELECT model FROM Ferias model ");
			sbSQL.append(" WHERE model.colaborador.id = :idColaborador ");
			sbSQL.append(" order by model.inicio desc");
			
			list = getSession().createQuery(sbSQL.toString()).setParameter("idColaborador", idColaborador).list();
			return list;	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<HistoricoSalario> findByHistSalario(Long idColaborador) throws JPAException {
		
			List<HistoricoSalario> list;
			StringBuffer sbSQL = new StringBuffer("SELECT model FROM HistoricoSalario model ");
			sbSQL.append(" WHERE model.colaborador.id = :idColaborador ");
			sbSQL.append(" order by model.idHistSalario");
			
			list = getSession().createQuery(sbSQL.toString()).setParameter("idColaborador", idColaborador).list();
			return list;

	}

	
}