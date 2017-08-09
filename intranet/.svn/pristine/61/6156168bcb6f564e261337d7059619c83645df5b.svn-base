package br.com.sp.intranet.dao.comercial;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAOAnalista;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.comercial.DespesaVisita;

@Repository
@SuppressWarnings("unchecked")
public class DespesaVisitaDAO extends GenericDAOAnalista{
	
	@SuppressWarnings("unchecked")
	public List<DespesaVisita> findDespesaVisitaByMes(String mesAno, Long zonaVendas) throws JPAException{
		List<DespesaVisita> list;
		
		StringBuffer sql = new StringBuffer("SELECT model FROM DespesaVisita model");
		sql.append(" WHERE TO_CHAR(model.data, 'MM/yyyy') = :mesAno AND model.zonaVendas = :zonaVendas");
		sql.append(" ORDER BY model.data");
		
		list = getSession().createQuery(sql.toString())
				.setParameter("mesAno", mesAno)
				.setParameter("zonaVendas", zonaVendas)
				.list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
	
	public DespesaVisita findByDiaAndZona(Date dia, Long zonaVendas) throws JPAException{
		List<DespesaVisita> list;
		
		StringBuffer sql = new StringBuffer("SELECT model FROM DespesaVisita model");
		sql.append(" WHERE model.data = :dia AND model.zonaVendas = :zonaVendas");
		sql.append(" ORDER BY model.data");
		
		list = getSession().createQuery(sql.toString())
				.setParameter("dia", dia)
				.setParameter("zonaVendas", zonaVendas)
				.list();

		if (list == null || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
}
