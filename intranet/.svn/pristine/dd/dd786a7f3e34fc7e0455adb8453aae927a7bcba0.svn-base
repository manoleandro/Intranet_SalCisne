package br.com.sp.intranet.dao.administrador.app;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.app.CsServicoApp;

@Repository
public class ServicoAppDAO extends GenericDAO{
	
	@SuppressWarnings("unchecked")
	public List<CsServicoApp> findAll() throws JPAException{
		List<CsServicoApp> list;
		
		list = getSession().createQuery("SELECT model FROM CsServicoApp model ORDER BY model.descricao").list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
}