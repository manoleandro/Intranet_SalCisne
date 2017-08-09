
package br.com.sp.intranet.service.evento;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.evento.Evento;

public interface EventoService {

	Evento findById(Evento evento) throws JPAException;

	void excluir(Evento evento) throws JPAException;

	void salva(Evento evento) throws JPAException;

	List<Evento> listarTodos() throws JPAException;	
}
