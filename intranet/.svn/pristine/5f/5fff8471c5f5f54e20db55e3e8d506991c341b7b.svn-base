package br.com.sp.intranet.dao.caixa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Bancos;

@Repository
public class BancosDAO extends GenericDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<Bancos> findAll() throws JPAException {
		List<Bancos> list;
		list = getSession().createQuery("SELECT model FROM Bancos model order by model.id").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@Transactional("sic")
	public Bancos findById(Long id) throws JPAException {
		return (Bancos) findById(Bancos.class, id);
	}


}