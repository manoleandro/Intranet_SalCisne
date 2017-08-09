package br.com.sp.intranet.dao.portaria;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.HistMovPortaria;
import br.com.sp.intranet.model.portaria.Veiculo;

@Repository
public class HistoricoMovimentacaoPortariaDAO extends GenericDAO {

	@SuppressWarnings("unchecked")
	public List<HistMovPortaria> findAll() throws JPAException {
		List<HistMovPortaria> list;
		list = getSession().createQuery("SELECT model FROM HistMovPortaria model order by model.id").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<HistMovPortaria> findByData(String dataInicio, String dataFim) throws JPAException {
		List<HistMovPortaria> list;
		list = getSession().createQuery("SELECT model FROM HistMovPortaria model where model.data >= '"+dataInicio+"' and model.data <= '"+dataFim+"' order by model.data").list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}

}