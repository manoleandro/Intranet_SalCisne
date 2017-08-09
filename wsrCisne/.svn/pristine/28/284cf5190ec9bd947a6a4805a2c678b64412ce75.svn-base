package wsr.model.comercial.repository;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.exception.JPAException;
import wsr.model.comercial.Configuracao;

@Repository
public class ConfiguracaoRepository extends GenericDAOAnalista{
	
	public Configuracao find() throws JPAException {
		return (Configuracao) getSession().createQuery("SELECT model FROM Configuracao model")
				.uniqueResult();
	}
}