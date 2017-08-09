package br.com.sp.intranet.dao.administrador;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsServico;

@Repository
public class ServicoDAO extends GenericDAO {
	
	@SuppressWarnings("unchecked")
	public List<CsServico> findAll() throws JPAException{
		List<CsServico> list;
		
		list = getSession().createQuery("SELECT model FROM CsServico model ORDER BY model.descricao, model.contexto").list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
}