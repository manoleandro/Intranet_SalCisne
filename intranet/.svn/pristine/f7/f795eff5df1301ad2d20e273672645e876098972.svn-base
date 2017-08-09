package br.com.sp.intranet.dao.administrador;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsAutorizacao;

@Repository
public class AutorizacaoDAO extends GenericDAO{
	
	@SuppressWarnings("unchecked")
	public List<CsAutorizacao> findAll() throws JPAException{
		List<CsAutorizacao> list;
		
		list = getSession().createQuery("SELECT model FROM CsAutorizacao model ORDER BY model.servico.id, model.descricao").list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
}