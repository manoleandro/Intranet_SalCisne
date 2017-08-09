package br.com.sp.intranet.dao.caixa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.HistoricoPadrao;

@Repository
public class HistoricoPadraoDAO extends GenericDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<HistoricoPadrao> findAll() throws JPAException {
		List<HistoricoPadrao> list;
		list = getSession().createQuery("SELECT model FROM HistoricoPadrao model order by model.id").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@Transactional("sic")
	public HistoricoPadrao findById(Long id) throws JPAException {
		return (HistoricoPadrao) findById(HistoricoPadrao.class, id);
	}

}