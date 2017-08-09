package wsr;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import wsr.exception.JPAException;
import wsr.model.GenericEntity;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenericDAOAnalista {
	
	@Autowired
	@Qualifier("analistaSessionFactory")
    protected SessionFactory analistaSessionFactory;
 
	protected Session getSession() {
        return analistaSessionFactory.getCurrentSession();
    }
	
	public void persist(GenericEntity entity) throws JPAException {
		try {
			entity.setDataUltAlteracao(new Date());
			getSession().persist(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JPAException(e.getMessage());
		}
	}

	public boolean delete(GenericEntity entity) throws JPAException {
		boolean retorno = true;
		try {
			getSession().delete(entity);
		} catch (Exception e) {
			retorno = false;
			e.printStackTrace();
			throw new JPAException(e.getMessage());
		}
		return retorno;
	}

	public boolean save(GenericEntity entity) throws JPAException {
		boolean retorno = true;
		try {
			entity.setDataUltAlteracao(new Date());
			getSession().saveOrUpdate(entity);
		} catch (Exception e) {
			retorno = false;
			e.printStackTrace();
			throw new JPAException(e.getMessage());
		}
		return retorno;
	}	

	public boolean update(GenericEntity entity) throws JPAException {
		boolean retorno = true;
		try {
			entity.setDataUltAlteracao(new Date());
			getSession().merge(entity);
		} catch (Exception e) {
			retorno = false;
			e.printStackTrace();
			throw new JPAException(e.getMessage());
		}
		
		return retorno;
	}
	
	public void flush() throws JPAException{
		getSession().flush();
	}
	
	public void clear() throws JPAException{
		getSession().clear();
	}

	public Object findById(Class genericClass, Long id) throws JPAException {
		try {
			return getSession().get(genericClass, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JPAException(e.getMessage());
		}
	}
	
	public Object findById(Class genericClass, String id) throws JPAException {
		try {
			return getSession().get(genericClass, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JPAException(e.getMessage());
		}
	}
}