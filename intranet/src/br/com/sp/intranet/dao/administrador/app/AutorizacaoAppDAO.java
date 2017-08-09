package br.com.sp.intranet.dao.administrador.app;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.app.CsAutorizacaoApp;

@Repository
public class AutorizacaoAppDAO extends GenericDAO{
	
	@SuppressWarnings("unchecked")
	public List<CsAutorizacaoApp> findAll() throws JPAException{
		List<CsAutorizacaoApp> list;
		
		list = getSession().createQuery("SELECT model FROM CsAutorizacaoApp model ORDER BY model.descricao").list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
}