package br.com.sp.intranet.dao.galeria;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.evento.Evento;
import br.com.sp.intranet.model.evento.Foto;

@Repository
public class FotoDAO extends GenericDAO {

	@Transactional("sic")
	public Foto findById(Long id) throws JPAException {
		return (Foto) findById(Foto.class, id);
	}

	@Transactional("sic")
	public List<Foto> buscarPorEvento(Evento evento) throws JPAException {
		StringBuffer str = new StringBuffer("from Foto model where model.evento.idEvento = :idEvento");
		str.append(" order by model.ordem");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("idEvento", evento.getIdEvento());
		return query.list();
	}
	
	@Transactional("sic")
	public List<Foto> buscarPrincipal() throws JPAException {
		StringBuffer str = new StringBuffer("from Foto model where model.exibe = 1");
		str.append(" order by model.ordem");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}
}
