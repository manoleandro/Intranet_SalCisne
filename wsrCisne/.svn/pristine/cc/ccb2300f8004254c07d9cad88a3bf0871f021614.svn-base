package wsr.model.comercial.repository;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.model.comercial.Parametro;

@Repository
public class ParametroRepository extends GenericDAOAnalista{
	
	public Parametro findByIdParametro(Long idParametro){
		Parametro parametro = null;
		parametro = (Parametro) getSession().get(Parametro.class, idParametro);
		return parametro;
	}
}