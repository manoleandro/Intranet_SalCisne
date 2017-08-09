package wsr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class GenericDAOSecurity {
	
	 @Autowired
	 @Qualifier("sessionFactorySecurity")
	 protected SessionFactory sessionFactory;
	 
	 protected Session getSession() {
		 return sessionFactory.getCurrentSession();
	 }
}