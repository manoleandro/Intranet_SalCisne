package br.com.sp.intranet.dao.comercial;

import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAOAnalista;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.comercial.RegistroVisita;
import br.com.sp.intranet.model.comercial.RegistroVisitaPK;

@Repository
public class RegistroVisitaDAO extends GenericDAOAnalista{
	
	public RegistroVisita findById(RegistroVisitaPK pk) throws JPAException{
		
		RegistroVisita rv = (RegistroVisita) getSession().createQuery("SELECT model FROM RegistroVisita model WHERE model.pk = :pk")
				.setParameter("pk", pk)
				.uniqueResult();
		
		return rv;
	}
}
