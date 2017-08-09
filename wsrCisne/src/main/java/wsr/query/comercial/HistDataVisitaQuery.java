package wsr.query.comercial;

public class HistDataVisitaQuery {
	
	public final static String MAX_DATA_VISITA_MES_BY_CLIENTE = "SELECT * FROM (SELECT * FROM HIST_DATA_VISITA" +
			" WHERE ID_CLIENTE = :idCliente AND TO_CHAR(DATA_VISITA, 'MM/yyyy') = :mesAno" +
			" ORDER BY DATA_VISITA DESC)" +
			" WHERE ROWNUM = 1" ;
	
	public final static String HQL_MAX_DATA_VISITA_MES_BY_CLIENTE = "SELECT hist FROM HistDataVisita hist" +
			" WHERE hist.idCliente = :idCliente AND TO_CHAR(hist.dataVisita, 'MM/yyyy') = :mesAno" +
			" ORDER BY hist.dataVisita DESC";
}