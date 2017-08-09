package wsr.query.comercial;

public class EstoqueClienteQuery {
	
	public final static String CONSUMO_PROGRESSIVO_MES_BY_CLIENTE = "SELECT * FROM" +
			" (SELECT * FROM ESTOQUE_CLIENTE WHERE ID_CLIENTE = :idCliente AND TO_CHAR(DATA_LANCTO, 'MM/yyyy') = :mesAno" +
			" ORDER BY DATA_LANCTO DESC) " +
			" WHERE ROWNUM = 1";
	
	public final static String HQL_CONSUMO_PROGRESSIVO_MES_BY_CLIENTE = " SELECT estoque FROM EstoqueCliente estoque " +
			" WHERE estoque.cliente.idCliente = :idCliente AND TO_CHAR(estoque.dataLancto, 'MM/yyyy') = :mesAno" +
			" ORDER BY estoque.dataLancto DESC";
	
	public final static String ESTOQUE_CLIENTE_ANTERIOR_BY_CLIENTE = "SELECT * FROM" +
			" (SELECT * FROM ESTOQUE_CLIENTE WHERE ID_CLIENTE = :idCliente AND DATA_LANCTO <= :data" +
			" ORDER BY DATA_LANCTO DESC) " +
			" WHERE ROWNUM = 1";
	
	public final static String HQL_ESTOQUE_CLIENTE_ANTERIOR_BY_CLIENTE = " SELECT estoque FROM EstoqueCliente estoque " +
			" WHERE estoque.cliente.idCliente = :idCliente AND estoque.dataLancto <= :data" +
			" ORDER BY estoque.dataLancto DESC";
	
	public final static String HQL_ESTOQUE_ULTIMO_CONSUMO_PROGRESSIVO = " SELECT consumoProgressivo FROM EstoqueCliente estoque " +
			" WHERE estoque.cliente.idCliente = :idCliente AND estoque.dataLancto <= :data" +
			" ORDER BY estoque.dataLancto DESC";
}