package wsr.model.comercial.repository;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.query.comercial.NotaFiscalQuery;

@Repository
public class NotaFiscalRepository extends GenericDAOAnalista{

	public Double findPrecoMedioMes(Long idCliente, String data){
		Double retorno = null;
		
		retorno = (Double) getSession().createSQLQuery(NotaFiscalQuery.PRECO_MEDIO_MES)
				.setParameter("idCliente", idCliente)
				.setParameter("data", data)
				.uniqueResult();
		
		return retorno;
	}
	
	public Long findQuantidadeNfSemCanhotoMesByCliente(Long idCliente, String mesAno){
		Double retorno = null;
		
		retorno = (Double) getSession().createSQLQuery(NotaFiscalQuery.QUANTIDADE_NF_SEM_CANHOTO_MES_BY_ID_CLIENTE)
				.addScalar("quantidade", StandardBasicTypes.DOUBLE)
				.setParameter("idCliente", idCliente)
				.setParameter("mesAno", mesAno)
				.uniqueResult();
		
		if(retorno != null){
			return retorno.longValue();
		}
		return null;
	}
}