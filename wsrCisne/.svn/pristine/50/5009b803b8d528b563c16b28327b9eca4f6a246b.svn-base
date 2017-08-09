package wsr.query.comercial;

public class PedidoVendaQuery {
	
	/*public final static String MAX_ID_FILIAL_BY_DATA_EMISSAO = "SELECT CASE WHEN ID_FILIAL = 41 THEN 'CB' ELSE 'SP' END AS FILIAL" +
									" FROM (SELECT ID_FILIAL FROM WSR_PEDIDO_VENDA" +
									" WHERE ID_CLIENTE = :idCliente" +
									" AND TO_CHAR(DATA_EMISSAO, 'MM/yyyy') = :mesAno"+
									" ORDER BY DATA_EMISSAO DESC)" +
									" WHERE ROWNUM=1 ";*/
	
	public final static String MAX_ID_FILIAL_BY_DATA_EMISSAO = "SELECT CASE WHEN ID_FILIAL = 41 THEN 'CB' ELSE 'SP' END AS FILIAL" +
									" FROM (SELECT ID_FILIAL FROM WSR_PEDIDO_VENDA" +
									" WHERE ID_CLIENTE = :idCliente" +
									" AND DATA_EMISSAO <= :ultimoDiaMes"+
									" ORDER BY DATA_EMISSAO DESC)" +
									" WHERE ROWNUM=1 ";
	
	public final static String QUANTIDADE_PEDIDO_PENDENTE_MES_BY_CLIENTE = "SELECT SUM(IPV.QUANTIDADE_PEDIDA * D.PESO_LIQUIDO) as quantidade " +
									" FROM PEDIDO_VENDAS PV, ITEM_PEDIDO_VENDA IPV, DADOS_FINANCEIROS D, PROD_ESTOQUE PE" +
									" WHERE PV.ID_PEDIDO_VENDAS = IPV.ID_PEDIDO_VENDAS" +
									" AND IPV.ID_PRODUTO = D.ID_PRODUTO" +
									" AND PE.ID_PRODUTO = D.ID_PRODUTO" +
									" AND PV.ID_STATUS = 1" +
									" AND PV.ID_CLIENTE = :idCliente" +
									" AND TO_CHAR(PV.DATA_EMISSAO, 'MM/yyyy') = :mesAno";
	
	public final static String MAX_DATA_EMISSAO = "SELECT MAX(pv.dataEmissao) FROM PedidoVenda pv WHERE pv.idCliente = :idCliente";
}