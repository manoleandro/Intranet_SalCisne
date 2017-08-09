package br.com.sp.intranet.dao.caixa;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.SaldoConta;

@Repository
public class SaldoContaDAO extends GenericDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<SaldoConta> findAll() throws JPAException {
		List<SaldoConta> list;
		list = getSession().createQuery("SELECT model FROM Contas model order by model.descricao").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@Transactional("sic")
	public SaldoConta findById(Long id) throws JPAException {
		return (SaldoConta) findById(SaldoConta.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<SaldoConta> findByProperty(String propertyName, Object value) throws JPAException {
		
		StringBuffer sbSQL = new StringBuffer( "from Contas model" );
		sbSQL.append( " where "+ propertyName + "= :propertyValue" );
		sbSQL.append( " order by model.descricao" );
		
		List<SaldoConta> list;
		list = getSession().createQuery(sbSQL.toString()).setParameter( "propertyValue", value ).list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<SaldoConta> findByBancosCompSaldo(Long idBanco, String compSaldo) throws JPAException {
		
		StringBuffer sql = new StringBuffer();
		if(compSaldo.equalsIgnoreCase("T")) {
			compSaldo ="'1','0'";
		}
		sql.append("SELECT model FROM Contas model WHERE model.banco.id = :idBanco AND model.compSaldo IN("+compSaldo+") order by model.descricao");

		List<SaldoConta> list;
		list = getSession().createQuery(sql.toString()).setParameter("idBanco", idBanco).list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Double findUltimoSaldo(Long idConta, String dtLancamento) throws JPAException{
		try{

			StringBuffer str = new StringBuffer();
			str.append("SELECT model.saldo FROM SaldoConta model");
			str.append(" WHERE model.pk.idConta = :idConta");
			str.append(" AND model.pk.dia < to_date(:dtLancamento,'dd/MM/yyyy')"); //Oracle Command
			str.append(" ORDER BY model.pk.dia DESC LIMIT 1");
			
			return (Double) getSession().createQuery(str.toString()).setParameter("idConta", idConta).setParameter("dtLancamento", dtLancamento).uniqueResult();
		}catch( NoResultException e ){
			return null;
		}
	}
	
	
	
	public Double findSaldo(Long idConta, Date dataLancamento, boolean isSaldoDia) throws JPAException{
		try{
			
			StringBuffer str = new StringBuffer();
			str.append("SELECT SUM(model.valor)");
			str.append(" FROM Lancamentos model");
			str.append(" WHERE model.contas.idConta = :idConta"); 
			
			if(!isSaldoDia)
				str.append(" AND model.dtLancamento < :dataLancamento");
			else
				str.append(" AND model.dtLancamento <= :dataLancamento");
			System.out.println("findSaldo :" + str);
			return (Double) getSession().createQuery(str.toString()).setParameter("idConta", idConta).setParameter("dataLancamento", dataLancamento).uniqueResult();
		}catch( NoResultException e ){
			return null;
		}
	}
	
	
	public Double findSaldoInicial (Long idConta) throws JPAException{
		try{

			StringBuffer str = new StringBuffer();
			str.append("SELECT model.saldo");
			str.append(" FROM SaldoInicialConta model");
			str.append(" WHERE model.conta.idConta = :idConta"); 

			return (Double) getSession().createQuery(str.toString()).setParameter("idConta", idConta).uniqueResult();
		}catch( NoResultException e ){
			return null;
		}
	}
	
	
	public void atualizaDiferencaSaldoPosterior(String dataLancamento, Double diferencaSaldo, Long idConta) throws JPAException{
		try{
			
			StringBuffer str = new StringBuffer();
			str.append("UPDATE SaldoConta model SET saldo = saldo + :diferencaSaldo");
			/*str.append(" WHERE date_format(model.pk.dia, '%Y-%m-%d') > :dataLancamento");//MySQL Command*/
			str.append(" WHERE to_date(model.pk.dia, 'dd/MM/yyyy') > :dataLancamento");//Oracle Command
			str.append(" AND model.pk.idConta = :idConta");
			
			Query query = getSession().createQuery(str.toString());
			query.setParameter("diferencaSaldo", diferencaSaldo);
			query.setParameter("dataLancamento", dataLancamento);
			query.setParameter("idConta", idConta);
			query.executeUpdate();
			
			
		}catch(Exception e){
			throw new JPAException(SaldoConta.class, e);
		}
	}
	

}