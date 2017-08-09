package wsr.service.comercial;

import wsr.exception.JPAException;
import wsr.model.comercial.Configuracao;

public interface ConfiguracaoService {

	public Configuracao find() throws JPAException;

	public void update(Configuracao configuracao) throws JPAException;

}
