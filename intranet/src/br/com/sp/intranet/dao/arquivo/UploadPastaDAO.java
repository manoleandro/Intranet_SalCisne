package br.com.sp.intranet.dao.arquivo;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.upload.UploadPasta;

@Repository
public class UploadPastaDAO extends GenericDAO {


	@SuppressWarnings("unchecked")
	public List<UploadPasta> findByColumnIsNull(String propertyName, String valueIsNull, String tableName) throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer( "from " + tableName + " model" );
		sbSQL.append( " where COALESCE (model."+ propertyName + ", "+ valueIsNull + ") =" + valueIsNull);
		System.out.println("sbSQL: " + sbSQL);
		List<UploadPasta> list;
		list = getSession().createQuery(sbSQL.toString()).list();	
		
		
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UploadPasta> listarTodasPastas() throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer("SELECT model from UploadPasta model order by model.idPasta");
		List<UploadPasta> list;
		list = getSession().createQuery(sbSQL.toString()).list();	
	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<UploadPasta> findPastaBySuperior(Long pastaSuperior) throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer( "from UploadPasta model" );
		sbSQL.append( " where model.superior "+  "= '"+pastaSuperior+"'" );
		System.out.println("query findPastaBySuperior " + sbSQL);
		List<UploadPasta> list;
		list = getSession().createQuery(sbSQL.toString()).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<UploadPasta> findByProperty(String propertyName, Object value) throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer( "from UploadPasta model" );
		sbSQL.append( " where "+ propertyName + "= :propertyValue" );
		sbSQL.append( " order by model.idPasta" );

		List<UploadPasta> list;
		list = getSession().createQuery(sbSQL.toString()).setParameter("propertyValue", value).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	

	@Transactional("sic")
	public UploadPasta findById(Long id) throws JPAException {
		return (UploadPasta) findById(UploadPasta.class, id);
	}
	
	
	

}