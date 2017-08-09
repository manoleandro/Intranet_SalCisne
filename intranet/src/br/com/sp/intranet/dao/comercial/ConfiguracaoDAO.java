package br.com.sp.intranet.dao.comercial;

import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAOAnalista;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.comercial.Configuracao;

@Repository
public class ConfiguracaoDAO extends GenericDAOAnalista{
	
	@SuppressWarnings("unchecked")
	public Configuracao find() throws JPAException {
		return (Configuracao) getSession().createQuery("SELECT model FROM Configuracao model")
				.uniqueResult();
	}
}