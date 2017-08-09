package br.com.sp.intranet.dao.caixa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.caixa.Lancamentos;
import br.com.sp.intranet.model.caixa.SaldoConta;


@Repository
public class LancamentoDAO extends GenericDAO {
	
	
	@SuppressWarnings("unchecked")
	public List<Lancamentos> findAll() throws JPAException {
		List<Lancamentos> list;
		list = getSession().createQuery("SELECT model FROM Contas model order by model.descricao").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@Transactional("sic")
	public Lancamentos findById(Long id) throws JPAException {
		return (Lancamentos) findById(Lancamentos.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Lancamentos> findLancamentosPosteriores(String dataLancamento, Long idConta) throws JPAException {
		
		StringBuffer str = new StringBuffer();
		str.append("SELECT model FROM Lancamentos model");
		/*str.append(" WHERE date_format(model.dtLancamento, '%Y-%m-%d') > :dataLancamento");//MySQL Command*/
		str.append(" WHERE model.dtLancamento > to_date(:dataLancamento, 'dd/MM/yyyy')");
		str.append(" AND model.contas.idConta = :idConta");
		
		List<Lancamentos> list;
		list = getSession().createQuery(str.toString()).setParameter("dataLancamento", dataLancamento).setParameter("idConta", idConta).list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Lancamentos> findFiltroGeral(Lancamentos lancamentos, String compSaldo) throws JPAException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR"));
		String strDataInicio = dateFormat.format(lancamentos.getDataInicio());
		String strDataFim = dateFormat.format(lancamentos.getDataFim());
		
		if(compSaldo.equalsIgnoreCase("T"))
			compSaldo ="'1','0'";
		
		StringBuffer str = new StringBuffer();
		str.append("SELECT model FROM Lancamentos model WHERE ");
		str.append("model.dtLancamento between to_date('"+strDataInicio+"','dd/MM/yyyy') and to_date('"+strDataFim+"','dd/MM/yyyy') "); //Oracle Command
		/*str.append("date_format(model.dtLancamento, '%Y-%m-%d') between :strDataInicio and :strDataFim");//MySql Command
*/			if(lancamentos.getContas() != null && lancamentos.getContas().getIdConta()  != null && lancamentos.getContas().getIdConta()  != 0)
			str.append(" AND model.contas.idConta =" + lancamentos.getContas().getIdConta());
		if(lancamentos.getContas()!= null && lancamentos.getContas().getTpConta().getId() != null && lancamentos.getContas().getTpConta().getId() !=0)
			str.append(" AND model.contas.tpConta.id = "+ lancamentos.getContas().getTpConta().getId());
		if(lancamentos.getContas()!= null && lancamentos.getContas().getBanco().getId() != null && lancamentos.getContas().getBanco().getId() !=0)
			str.append(" AND model.contas.banco.id = " + lancamentos.getContas().getBanco().getId());
		if(compSaldo != null)
			str.append(" AND model.contas.compSaldo IN(" +compSaldo+")");
		
		str.append(" ORDER BY model.idLancamento desc");
		System.out.println("query findFiltroGeral" + str);
		List<Lancamentos> list;
		list = getSession().createQuery(str.toString()).list();
		
		if (list == null || list.isEmpty()) {
			return list = new ArrayList<Lancamentos>();
		} else {
			return list;
		}

	}
	
	
	
	@SuppressWarnings("unchecked")
	public List somatorioValorPorTipoConta(String dataInicio, String dataFim, String compSaldo, Long idConta) throws JPAException {
		
		StringBuffer str = new StringBuffer();
		str.append("SELECT NEW MAP (SUM(model.valor) as total, model.contas.tpConta.id as tpConta) FROM Lancamentos model ");
		str.append(" WHERE model.dtLancamento between to_date(:dataInicio,'dd/MM/yyyy') and to_date(:dataFim,'dd/MM/yyyy') "); //Oracle Command
		/*str.append("WHERE date_format(model.dtLancamento, '%Y-%m-%d') <= :dataFim");//MySql Command*/
		str.append(" AND model.contas.compSaldo IN ("+compSaldo+")" );
		if(idConta != null && idConta !=0)
			str.append("  AND model.contas.idConta = "+idConta);
		str.append(" GROUP BY (model.contas.tpConta.id)");
		
		List list;
		list = getSession().createQuery(str.toString()).setParameter("dataFim", dataFim).list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	public void atualizaDiferencaSaldoPosterior(String dataLancamento, Double diferencaSaldo, Long idConta) throws JPAException{
		try{

			StringBuffer str = new StringBuffer();
			str.append("UPDATE SaldoConta model SET saldo = saldo + :diferencaSaldo");
			/*str.append(" WHERE date_format(model.pk.dia, '%Y-%m-%d') > :dataLancamento");//MySQL Command*/
			str.append(" WHERE model.pk.dia > to_date(:dataLancamento, 'dd/MM/yyyy')");//Oracle Command
			str.append(" AND model.pk.idConta = :idConta");
			
			Query query = getSession().createQuery(str.toString());
			query.setParameter("diferencaSaldo", diferencaSaldo);
			query.setParameter("dataLancamento", dataLancamento);
			query.setParameter("idConta", idConta);
			query.executeUpdate();
			

		}catch(Exception e){
			getSession().beginTransaction().rollback();
			throw new JPAException(SaldoConta.class, e);
			
		}
	}
	
	
	public Double somatorioValor(String dataInicio, String dataFim) throws JPAException{
		try{
			
			StringBuffer str = new StringBuffer();
			str.append("SELECT SUM(model.valor) FROM Lancamentos model WHERE ");
			str.append("model.dtLancamento between to_date(:dataInicio,'dd/MM/yyyy') and to_date(:dataFim,'dd/MM/yyyy') "); //Oracle Command
			/*str.append("date_format(model.dtLancamento, '%Y-%m-%d') between :dataInicio and :dataFim");//MySql Command*/
			str.append(" ORDER BY model.idLancamento desc");

			return (Double) getSession().createQuery(str.toString()).setParameter("dataInicio", dataInicio).setParameter("dataFim", dataFim).uniqueResult();
		
		}catch( Exception e ){
			throw new JPAException( Lancamentos.class, e );
		}
	}
	
	@Transactional("sic")
	public void atualizaFechamentoConta(String dataFim) throws JPAException {
		try {
			StringBuffer str = new StringBuffer( "UPDATE Lancamentos model SET contaFechada = 1" );
			str.append(" WHERE " );
			str.append("dtLancamento < to_date(:dataFim,'dd/MM/yyyy') "); //Oracle Command
			/*str.append("date_format(model.dtLancamento, '%Y-%m-%d') < :dataFim");//MySql Command*/
			str.append(" AND model.contaFechada IS NULL");
			
			Query query = getSession().createQuery(str.toString());
			query.setParameter("dataFim", dataFim);
			query.executeUpdate();
		} 
		catch( Exception e ){
			throw new JPAException( Lancamentos.class, e );
		}
	}
	
	
	
	
	
}