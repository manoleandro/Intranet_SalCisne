package wsr.query.comercial;

public class MvEstClQuery {
	public static final String SUM_ENTRADAS_MES_BY_CLIENTE = "SELECT SUM(QUANTIDADE) FROM MV_EST_CL WHERE ID_CLIENTE = :idCliente"
			+ " AND TO_CHAR(DATA_LANCTO, 'MM/yyyy') = :mesAno";
	
	public static final String HQL_SUM_ENTRADAS_MES_BY_DATAS = "SELECT SUM(quantidade) as quantidade FROM MvEstCl WHERE cliente.idCliente = :idCliente"
			+ " AND dataLancto >= :dataLancto AND dataLancto <= :dataFimMes";
	
	public static final String HQL_SUM_ENTRADAS_MES_BY_CLIENTE = "SELECT SUM(quantidade) as quantidade FROM MvEstCl WHERE cliente.idCliente = :idCliente"
			+ " AND TO_CHAR(dataLancto, 'MM/yyyy') = :mesAno";
}