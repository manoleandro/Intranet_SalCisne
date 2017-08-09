package wsr.model.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.model.comercial.PrevisaoVendas;
import wsr.query.comercial.PrevisaoVendasQuery;

@Repository
public class PrevisaoVendasRepository extends GenericDAOAnalista{
	
	@SuppressWarnings("unchecked")
	public PrevisaoVendas findMaxPrevisaoMesByCliente(Long idCliente, String mesAno){
		List<PrevisaoVendas> list = null;
		
		list = getSession().createQuery(PrevisaoVendasQuery.HQL_MAX_MES_BY_CLIENTE)
				.setParameter("idCliente", idCliente)
				.setParameter("mesAno", mesAno)
				.list();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Double findMaxPrecoMedio(Long idCliente){
		List<Double> list = null;
		
		list = getSession().createQuery(PrevisaoVendasQuery.HQL_MAX_PRECO_MEDIO)
				.setParameter("idCliente", idCliente)
				.list();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}

}