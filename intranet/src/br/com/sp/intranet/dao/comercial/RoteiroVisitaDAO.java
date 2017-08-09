package br.com.sp.intranet.dao.comercial;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAOAnalista;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.comercial.RoteiroVisita;

@Repository
public class RoteiroVisitaDAO extends GenericDAOAnalista{
	
	public BigDecimal findRoteiroVisitaByMesAno(String mesAno){
		
		StringBuffer sql = new StringBuffer("SELECT ID FROM ROTEIRO_VISITA WHERE TO_CHAR(DATA_VISITA, 'MM/yyyy') = :mesAno AND ROWNUM=1");
		
		return (BigDecimal) getSession().createSQLQuery(sql.toString())
				.setParameter("mesAno", mesAno)
				.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<RoteiroVisita> findRoteiroVisitaEfetivoByMesAno(String mesAno, Long zonaVendas) throws JPAException{
		List<RoteiroVisita> list;
		
		StringBuffer sql = new StringBuffer("SELECT model FROM RoteiroVisita model");
		sql.append(" WHERE TO_CHAR(model.dataVisita, 'MM/yyyy') = :mesAno AND model.zonaVendas = :zonaVendas");
		sql.append(" ORDER BY model.dataVisita");
		
		list = getSession().createQuery(sql.toString())
				.setParameter("mesAno", mesAno)
				.setParameter("zonaVendas", zonaVendas)
				.list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
}