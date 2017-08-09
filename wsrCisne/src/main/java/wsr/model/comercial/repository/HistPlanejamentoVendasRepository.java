package wsr.model.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.model.comercial.HistPlanejamentoVendas;
import wsr.query.comercial.HistPlanejamentoVendasQuery;

@Repository
public class HistPlanejamentoVendasRepository extends GenericDAOAnalista{
	
	public HistPlanejamentoVendas findByMes(String mesAno, Long idCliente){
		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append("");
		
		@SuppressWarnings("unchecked")
		List<HistPlanejamentoVendas> list = getSession().createQuery(HistPlanejamentoVendasQuery.HQL_FIND_BY_MES)
				.setParameter("idCliente", idCliente)
				.setParameter("mesAno", mesAno)
				.list();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
}