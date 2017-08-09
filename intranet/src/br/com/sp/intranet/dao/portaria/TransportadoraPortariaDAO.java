package br.com.sp.intranet.dao.portaria;

import java.util.List;
import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.TransportadoraPortaria;



@Repository
public class TransportadoraPortariaDAO extends GenericDAO {

	@SuppressWarnings("unchecked")
	public List<TransportadoraPortaria> findAll() throws JPAException {
		List<TransportadoraPortaria> list;
		list = getSession().createQuery("SELECT model FROM TransportadoraPortaria model order by model.idTransportadora").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
}