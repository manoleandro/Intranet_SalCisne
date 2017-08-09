package wsr.model.comercial.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.model.comercial.EstoqueCliente;
import wsr.query.comercial.EstoqueClienteQuery;

@Repository
public class EstoqueClienteRepository extends GenericDAOAnalista{
	
	public EstoqueCliente findMaxEstoqueClienteByMes(Long idCliente, String mesAno){
		
		@SuppressWarnings("unchecked")
		List<EstoqueCliente> list = getSession().createQuery(EstoqueClienteQuery.HQL_CONSUMO_PROGRESSIVO_MES_BY_CLIENTE)
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
	public EstoqueCliente findEstoqueClienteAnterior(Long idCliente, LocalDate data){
		List<EstoqueCliente> list = getSession().createQuery(EstoqueClienteQuery.HQL_ESTOQUE_CLIENTE_ANTERIOR_BY_CLIENTE)
				.setParameter("idCliente", idCliente)
				.setParameter("data", data)
				.list();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}	
	}
	
	
	@SuppressWarnings("unchecked")
	public Long findUltimoConsumoProgressivo(Long idCliente, LocalDate data){
		List<Long> list = getSession().createQuery(EstoqueClienteQuery.HQL_ESTOQUE_ULTIMO_CONSUMO_PROGRESSIVO)
				.setParameter("idCliente", idCliente)
				.setParameter("data", data)
				.list();
		
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}	
	}
}