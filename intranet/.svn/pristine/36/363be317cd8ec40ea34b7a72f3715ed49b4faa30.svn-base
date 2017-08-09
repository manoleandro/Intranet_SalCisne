
package br.com.sp.intranet.service.evento.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.galeria.EventoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.evento.Evento;
import br.com.sp.intranet.service.evento.EventoService;

@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoDAO dao;
	
	@Override
	@Transactional
	public List<Evento> listarTodos() throws JPAException {
		return dao.findAll();
	}
	
	@Override
	@Transactional
	public void salva(Evento evento) throws JPAException {
		dao.save(evento);
	}
	
	@Override
	@Transactional
	public void excluir(Evento evento) throws JPAException {
		dao.delete(evento);
	}

	@Override
	@Transactional
	public Evento findById(Evento evento) throws JPAException {
		return (Evento) dao.findById(Evento.class, evento.getIdEvento());
	}
}
