package wsr.query.comercial;

public class EstoqueClienteInfQuery {
	public static final String ULTIMO_ESTOQUE_INF_MES_BY_CLIENTE = "SELECT * FROM"
			+ " (SELECT * FROM ESTOQUE_CLIENTE_INF"
			+ " WHERE ID_CLIENTE = :idCliente AND TO_CHAR(DATA_INCLUSAO, 'MM/yyyy') = :mesAno"
			+ " ORDER BY DATA_INCLUSAO DESC)" + " WHERE ROWNUM = 1";

	public static final String HQL_ULTIMO_ESTOQUE_INF_MES_BY_CLIENTE = " SELECT estoque FROM EstoqueClienteInf estoque"
			+ " WHERE estoque.cliente.idCliente = :idCliente AND TO_CHAR(estoque.dataInclusao, 'MM/yyyy') = :mesAno"
			+ " ORDER BY estoque.dataInclusao DESC";
}