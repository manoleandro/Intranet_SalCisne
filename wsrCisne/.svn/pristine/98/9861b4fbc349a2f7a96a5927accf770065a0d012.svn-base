package wsr.service.comercial;

import wsr.exception.JPAException;
import wsr.model.comercial.RegistroVisita;

public interface RegistroVisitaService{
	public RegistroVisita montarRegistroVisita(Long idCliente, String mesAno);
	
	public RegistroVisita findRegistroVisita(Long idCliente, String mesAno, String dia);

	public void incluir(RegistroVisita registroVisita) throws JPAException;

	public void alterar(RegistroVisita registroVisita) throws JPAException;
}