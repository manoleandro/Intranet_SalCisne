package wsr.model.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.model.comercial.EstoqueClienteInf;
import wsr.query.comercial.EstoqueClienteInfQuery;

@Repository
public class EstoqueClienteInfRepository extends GenericDAOAnalista{
	
	@SuppressWarnings("unchecked")
	public EstoqueClienteInf findUltimoEstoqueInf(Long idCliente, String mesAno){
		 
		List<EstoqueClienteInf> list = getSession().createQuery(EstoqueClienteInfQuery.HQL_ULTIMO_ESTOQUE_INF_MES_BY_CLIENTE)
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