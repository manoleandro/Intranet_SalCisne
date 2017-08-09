
package br.com.sp.intranet.service.evento;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.evento.Evento;
import br.com.sp.intranet.model.evento.Foto;

public interface FotoService {

	Foto findById(Long id) throws JPAException;

	List<Foto> buscarPorEvento(Evento evento) throws JPAException;

	List<Foto> buscarPrincipal() throws JPAException;

	void salva(Foto foto) throws JPAException;

	void altera(Foto foto) throws JPAException;
		
}