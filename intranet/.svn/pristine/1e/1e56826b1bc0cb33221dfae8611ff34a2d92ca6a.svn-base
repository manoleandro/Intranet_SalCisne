package br.com.sp.intranet.dao.caixa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.TpConta;

@Repository
public class TpContaDAO extends GenericDAO {


	@SuppressWarnings("unchecked")
	public List<TpConta> findAll() throws JPAException {
		List<TpConta> list;
		list = getSession().createQuery("SELECT model FROM TpConta model order by model.id").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@Transactional("sic")
	public TpConta findById(Long id) throws JPAException {
		return (TpConta) findById(TpConta.class, id);
	}
}