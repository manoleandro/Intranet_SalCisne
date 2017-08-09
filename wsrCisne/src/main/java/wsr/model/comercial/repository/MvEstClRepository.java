package wsr.model.comercial.repository;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.query.comercial.MvEstClQuery;

@Repository
public class MvEstClRepository extends GenericDAOAnalista{
	
	public Long findQuantidadeEntregaByDatas(Long idCliente, LocalDate dataLancto, LocalDate dataFimMes){
		Double retorno = null;
		
		retorno = (Double) getSession().createQuery(MvEstClQuery.HQL_SUM_ENTRADAS_MES_BY_DATAS)
				.setParameter("idCliente", idCliente)
				.setParameter("dataLancto", dataLancto)
				.setParameter("dataFimMes", dataFimMes)
				.uniqueResult();
		
		if(retorno != null)
			return retorno.longValue();
		
		return null;
	}
	
	public Long findQuantidadeEntregaMes(Long idCliente, String mesAno){
		Double retorno = null;
		
		retorno = (Double) getSession().createQuery(MvEstClQuery.HQL_SUM_ENTRADAS_MES_BY_CLIENTE)
				.setParameter("idCliente", idCliente)
				.setParameter("mesAno", mesAno)
				.uniqueResult();
		
		if(retorno != null)
			return retorno.longValue();
		
		return null;
	}
}