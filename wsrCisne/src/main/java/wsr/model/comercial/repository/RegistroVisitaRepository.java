package wsr.model.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.exception.JPAException;
import wsr.model.comercial.RegistroVisita;
import wsr.model.comercial.RegistroVisitaPK;

@Repository
public class RegistroVisitaRepository extends GenericDAOAnalista{
	
	public RegistroVisita findById(RegistroVisitaPK pk) throws JPAException{
		
		RegistroVisita rv = (RegistroVisita) getSession().createQuery("SELECT model FROM RegistroVisita model WHERE model.pk = :pk")
				.setParameter("pk", pk)
				.uniqueResult();
		
		return rv;
	}
	
	@SuppressWarnings("unchecked")
	public List<RegistroVisita> findRegistroVisitaByMes(Long zonaVendas, String mesAno) throws JPAException{
		StringBuffer str = new StringBuffer();
		str.append("SELECT model FROM RegistroVisita model WHERE model.zona = :zonaVendas ");
		str.append(" AND TO_CHAR(model.pk.dataVisitaReal, 'MM/yyyy') = :mesAno");
		
		List<RegistroVisita> list = getSession().createQuery(str.toString())
				.setParameter("zonaVendas", zonaVendas)
				.setParameter("mesAno", mesAno)
				.list();
		
		return list;
	}
}