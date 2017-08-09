package br.com.sp.intranet.dao.portaria;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.MovPortaria;

@Repository
public class PortariaDAO extends GenericDAO {

	@SuppressWarnings("unchecked")
	public List<MovPortaria> findAll() throws JPAException {
		List<MovPortaria> list;
		list = getSession().createQuery("SELECT model FROM MovPortaria model order by model.idMov").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MovPortaria> findByStatus(int status) throws JPAException {
		List<MovPortaria> list;
		list = getSession().createQuery("SELECT model FROM MovPortaria model WHERE model.status = :status AND model.transportadoraPortaria.idTransportadora is not null AND model.veiculo.idVeiculo is not null order by model.idMov")
										.setParameter("status", status).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<MovPortaria> findConsultaMovPortaria(String dataInicio, String dataFim) throws JPAException {
		List<MovPortaria> list;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT model FROM MovPortaria model");
		sql.append(" WHERE TO_DATE(TO_CHAR(model.data,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"','dd/MM/yyyy') AND TO_DATE('"+dataFim+"','dd/MM/yyyy')");
		sql.append(" AND model.status = 4");
		sql.append(" ORDER BY model.data");		
		list = getSession().createQuery(sql.toString()).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<MovPortaria> getListMediaPortaria(String dataInicio, String dataFim) throws JPAException {
		List<MovPortaria> list;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT model "); 
		sql.append(" FROM MovPortaria model");
		sql.append(" WHERE TO_DATE(TO_CHAR(model.horaInicioCarregamento, 'dd/MM/yyyy'), 'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"', 'dd/MM/yyyy') AND TO_DATE('"+dataFim+"', 'dd/MM/yyyy') ");
		sql.append(" AND model.status = 4");
		sql.append(" ORDER BY model.data");
		System.out.println("sql " + sql);
		list = getSession().createQuery(sql.toString()).list();	
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map> getQtdPesoTipoCargaTurno(String dataInicio, String dataFim, String horarioInicio, String horarioFim, String tipoCarga) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		sql1.append("SELECT NEW MAP(contratacao AS CONTRATACAO, SUM(peso2 - peso) AS PESO) FROM MovPortaria");
		sql1.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento, 'HH24:MI:SS'), 'HH24:MI:SS') BETWEEN TO_DATE('"+horarioInicio+"','HH24:MI:SS')");
		sql1.append(" AND TO_DATE('"+horarioFim+"','HH24:MI:SS')");
		sql1.append(" AND TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"', 'dd/MM/yyyy') AND TO_DATE('"+dataFim+"', 'dd/MM/yyyy')");
		sql1.append(" and status = 4");
		sql1.append(" AND tipoCarga = '"+tipoCarga+"'");
		sql1.append(" GROUP BY contratacao");	
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map> getQuantidadeVeiculosTurnoDetalhado(String dataInicio, String dataFim, String horarioInicio, String horarioFim, String tipoCarga) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		sql1.append("SELECT NEW MAP( contratacao as CONTRATACAO, COUNT(*) AS QTD) FROM MovPortaria");
		sql1.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento, 'HH24:MI:SS'), 'HH24:MI:SS') BETWEEN TO_DATE('"+horarioInicio+"','HH24:MI:SS')");
		sql1.append(" AND TO_DATE('"+horarioFim+"','HH24:MI:SS')");
		sql1.append(" AND TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"', 'dd/MM/yyyy') AND TO_DATE('"+dataFim+"', 'dd/MM/yyyy')");
		sql1.append(" AND status = 4");
		sql1.append(" AND tipoCarga = '"+tipoCarga+"'");
		sql1.append(" GROUP BY contratacao");	
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map> getQtdTotalContracao(String dataInicio, String dataFim, String contratacao) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("SELECT NEW MAP (COUNT(*) AS QTD, TO_CHAR(horaInicioCarregamento, 'MM/yyyy') as MESANO) FROM MovPortaria");
		sql1.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"', 'dd/MM/yyyy') AND TO_DATE('"+dataFim+"', 'dd/MM/yyyy')");
		
		if(contratacao.equalsIgnoreCase("CIF"))
			sql1.append(" AND (contratacao = '"+contratacao+"' OR contratacao = 'DEP')");
		else
			sql1.append(" AND (contratacao = '"+contratacao+"')");
		sql1.append(" AND status = 4");
		sql1.append(" GROUP BY TO_CHAR(horaInicioCarregamento,'MM/yyyy')");
		sql1.append(" ORDER BY MESANO");
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Map> getQtdTotalTipoCarga(String dataInicio, String dataFim, String tipoCarga) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("SELECT NEW MAP (COUNT(*) AS QTD, TO_CHAR(horaInicioCarregamento, 'MM/yyyy') as MESANO) FROM MovPortaria");
		sql1.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"', 'dd/MM/yyyy') AND TO_DATE('"+dataFim+"', 'dd/MM/yyyy')");
		sql1.append(" AND tipoCarga = '"+tipoCarga+"'");
		sql1.append(" AND status = 4");
		sql1.append(" GROUP BY TO_CHAR(horaInicioCarregamento,'MM/yyyy')");
		sql1.append(" ORDER BY MESANO");
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Map> getQtdPesoContratacao(String dataInicio, String dataFim, String contratacao) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("SELECT NEW MAP (SUM(peso2 - peso) as PESO, TO_CHAR(horaInicioCarregamento, 'MM/yyyy') as MESANO) FROM MovPortaria");
		sql1.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN '"+dataInicio+"' AND '"+dataFim+"'");
		sql1.append(" AND contratacao = '"+contratacao+"'");
		sql1.append(" AND tipoCarga IN ('PALET', 'MANUAL')");
		sql1.append(" GROUP BY TO_CHAR(horaInicioCarregamento,'MM/yyyy')");
		sql1.append(" ORDER BY MESANO");
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map> getQtdPesoTipoCarga(String dataInicio, String dataFim, String tipoCarga) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("SELECT NEW MAP (SUM(peso2 - peso) as PESO, TO_CHAR(horaInicioCarregamento, 'MM/yyyy') as MESANO) FROM MovPortaria");
		sql1.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN '"+dataInicio+"' AND '"+dataFim+"'");
		sql1.append(" AND tipoCarga = '"+tipoCarga+"'");
		sql1.append(" AND contratacao IN ('CIF', 'FOB')");
		sql1.append(" GROUP BY TO_CHAR(horaInicioCarregamento,'MM/yyyy')");
		sql1.append(" ORDER BY MESANO");
		System.out.println("getQtdPesoTipoCarga " + sql1);
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map> getQtdDiasMesCarregamento(String dataInicio, String dataFim) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("SELECT NEW MAP (TO_CHAR(horaInicioCarregamento, 'dd/MM/yyyy') as MESANO) FROM MovPortaria");
		sql1.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"', 'dd/MM/yyyy') AND TO_DATE('"+dataFim+"', 'dd/MM/yyyy')");
		sql1.append(" GROUP BY TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy')");
		sql1.append(" ORDER BY MESANO");
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map> getQtdFaixaHorarioCarregamento(String dataInicio, String dataFim, String horarioInicio, String horarioFim) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("SELECT NEW MAP (COUNT(*) AS QTD, TO_CHAR(horaInicioCarregamento, 'MM/yyyy') as MESANO) FROM MovPortaria");
		sql1.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento, 'HH24:MI:SS'), 'HH24:MI:SS') BETWEEN TO_DATE('"+horarioInicio+"','HH24:MI:SS')"); 
		sql1.append(" AND TO_DATE('"+horarioFim+"','HH24:MI:SS')");
		sql1.append(" AND TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"', 'dd/MM/yyyy') AND TO_DATE('"+dataFim+"', 'dd/MM/yyyy')");
		sql1.append(" AND status = 4");
		sql1.append(" GROUP BY TO_CHAR(horaInicioCarregamento,'MM/yyyy')");
		sql1.append(" ORDER BY MESANO");
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map> getTemposCarregamento(String dataInicio, String dataFim, String tipoCarga, String contratacao, String horaInicio, String horaFim) throws JPAException {
		List<Map> list;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT tempoCarregamento AS TEMPOS FROM MovPortaria"); 
		sql.append(" WHERE TO_DATE(TO_CHAR(horaInicioCarregamento,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN TO_DATE('"+dataInicio+"','dd/MM/yyyy') AND TO_DATE('"+dataFim+"','dd/MM/yyyy')");
		sql.append(" AND tipoCarga = '"+tipoCarga+"'");
		sql.append(" AND contratacao = '"+contratacao+"'");
		sql.append(" AND TO_DATE(TO_CHAR(horaInicioCarregamento, 'HH24:MI:SS'), 'HH24:MI:SS') BETWEEN TO_DATE('"+horaInicio+"','HH24:MI:SS')");
		sql.append(" AND TO_DATE('"+horaFim+"','HH24:MI:SS')");
		
		System.out.println("SQL getTemposCarregamento: " + sql);
		list = getSession().createQuery(sql.toString()).list();	
		return list;
	}
	
	
	

	@SuppressWarnings("unchecked")
	public List<Map> getQtdFaixaHorario(String dataInicio, String dataFim, String horarioInicio, String horarioFim) throws JPAException {
		List<Map> list;
		
		StringBuffer sql1 = new StringBuffer();
		
		sql1.append("SELECT NEW MAP (COUNT(*) AS QTD, to_char(data, 'MM/yyyy') as MESANO) FROM MovPortaria");
		sql1.append(" WHERE to_char(data,'HH24:MI:SS') BETWEEN to_char(to_date('"+horarioInicio+"','HH24:MI:SS'),'HH24:MI:SS')"); 
		sql1.append(" AND to_char(to_date('"+horarioFim+"','HH24:MI:SS'),'HH24:MI:SS')");
		sql1.append(" AND to_date(to_char(data,'dd/MM/yyyy'),'dd/MM/yyyy') BETWEEN to_date('"+dataInicio+"', 'dd/MM/yyyy') AND to_date('"+dataFim+"', 'dd/MM/yyyy')");
		sql1.append(" GROUP BY to_char(data,'MM/yyyy')");
		System.out.println("sql1 " + sql1);
		list = getSession().createQuery(sql1.toString()).list();	
		return list;
	}

	
	
	

}