package wsr.model.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.model.comercial.HistDataVisita;
import wsr.query.comercial.HistDataVisitaQuery;

@Repository
public class HistDataVisitaRepository extends GenericDAOAnalista{ 
	
	@SuppressWarnings("unchecked")
	public HistDataVisita findMaxDataVisitaMesByIdCliente(Long idCliente, String mesAno){
		List<HistDataVisita> list = getSession().createQuery(HistDataVisitaQuery.HQL_MAX_DATA_VISITA_MES_BY_CLIENTE)
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
