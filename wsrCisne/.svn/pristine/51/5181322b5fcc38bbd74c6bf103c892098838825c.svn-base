package wsr.model.comercial.repository;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.query.comercial.PrazoReposicaoQuery;

@Repository
public class PrazoReposicaoRepository extends GenericDAOAnalista{

	public Long findTotalDiasPrazoReposicao(String origem, String destino){
		Long retorno = null;
		
		retorno = (Long) getSession().createQuery(PrazoReposicaoQuery.HQL_SOMA_DIAS_BY_ORIGEM_DESTINO)
				.setParameter("origem", origem)
				.setParameter("destino", destino)
				.uniqueResult();
		
		return retorno;
	}
}