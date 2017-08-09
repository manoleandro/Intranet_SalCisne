package br.com.sp.intranet.dao.caixa;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Contas;

@Repository
public class ContasDAO extends GenericDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<Contas> findAll() throws JPAException {
		List<Contas> list;
		list = getSession().createQuery("SELECT model FROM Contas model order by model.descricao").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@Transactional("sic")
	public Contas findById(Long id) throws JPAException {
		return (Contas) findById(Contas.class, id);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Contas> findByProperty(String propertyName, Object value) throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer( "from Contas model" );
		sbSQL.append( " where "+ propertyName + "= :propertyValue" );
		sbSQL.append( " order by model.descricao" );
		
		List<Contas> list;
		list = getSession().createQuery(sbSQL.toString()).setParameter( "propertyValue", value ).list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Contas> findByBancosCompSaldo(Long idBanco, String compSaldo) throws JPAException {
		
		StringBuffer sql = new StringBuffer();
		if(compSaldo.equalsIgnoreCase("T")) {
			compSaldo ="'1','0'";
		}
		sql.append("SELECT model FROM Contas model WHERE model.banco.id = :idBanco AND model.compSaldo IN("+compSaldo+") order by model.descricao");

		List<Contas> list;
		list = getSession().createQuery(sql.toString()).setParameter("idBanco", idBanco).list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	

}