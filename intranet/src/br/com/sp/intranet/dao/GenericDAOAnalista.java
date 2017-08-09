package br.com.sp.intranet.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import br.com.sp.intranet.controller.administrador.LoginController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.GenericEntity;

public abstract class GenericDAOAnalista {
	
	@Autowired
	@Qualifier(value="sessionFactoryAnalista")
	private SessionFactory sessionFactory;
	
	@Autowired
	private LoginController login;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void persist(GenericEntity entity) throws JPAException {
		try {
			entity.setUsuarioUltAlteracao(login.getUsuario().getUsername());
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
			entity.setUsuarioUltAlteracao(login.getUsuario().getUsername());
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
			entity.setUsuarioUltAlteracao(login.getUsuario().getUsername());
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