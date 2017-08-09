
package br.com.sp.intranet.service.evento.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.galeria.FotoDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.evento.Evento;
import br.com.sp.intranet.model.evento.Foto;
import br.com.sp.intranet.service.evento.FotoService;

@Service
public class FotoServiceImpl implements FotoService {

	@Autowired
	private FotoDAO dao;

	@Override
	@Transactional
	public Foto findById(Long id) throws JPAException {
		return dao.findById(id);
	}
	
	@Override
	@Transactional
	public List<Foto> buscarPorEvento(Evento evento) throws JPAException {
		return dao.buscarPorEvento(evento);
	}
	
	@Override
	@Transactional
	public List<Foto> buscarPrincipal() throws JPAException {
		return dao.buscarPrincipal();
	}
	
	@Override
	@Transactional
	public void salva(Foto foto) throws JPAException {
		dao.save(foto);
	}
	
	@Override
	@Transactional
	public void altera(Foto foto) throws JPAException {
		dao.update(foto);
	}
}
