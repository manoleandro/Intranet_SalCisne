package br.com.sp.intranet.dao.galeria;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.evento.Evento;

@Repository
public class EventoDAO extends GenericDAO {

	public List<Evento> findAll() throws JPAException {
		try{
			StringBuffer sbSQL = new StringBuffer( "SELECT model FROM Evento model " );
			sbSQL.append( " order by model.data desc" );
			Query query = getSession().createQuery( sbSQL.toString() );
			return query.list();
		
		}catch( Exception e ){
			throw new JPAException( Evento.class, e );
		}
	}
}