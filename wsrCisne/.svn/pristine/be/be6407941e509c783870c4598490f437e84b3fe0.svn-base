package wsr.query.comercial;

public class PrevisaoVendasQuery {
	
	public static final String MAX_MES_BY_CLIENTE = "SELECT * FROM " +
								" (SELECT ID_PREVISAO_VENDAS, ID_CLIENTE, DATA_PREVISAO, VALOR_PREVISAO, QUANTIDE_KG, DIAS, PRECO_MEDIO, ESTOQUE_FINAL" + 
								" FROM PREVISAO_VENDAS" + 
								" WHERE ID_CLIENTE = :idCliente"+ 
								" AND DATA_PREVISAO <= :data"+
								" ORDER BY DATA_PREVISAO DESC)"+
								" WHERE ROWNUM = 1";
	
	public static final String HQL_MAX_MES_BY_CLIENTE = "SELECT model" + 
			" FROM PrevisaoVendas model" + 
			" WHERE model.idCliente = :idCliente"+ 
			" AND TO_CHAR(model.dataPrevisao,'MM/yyyy') = :mesAno"+
			" ORDER BY model.dataPrevisao DESC";
	
	
	public static final String HQL_MAX_PRECO_MEDIO = "SELECT MAX(model.precoMedio)" + 
			" FROM PrevisaoVendas model" + 
			" WHERE model.idCliente = :idCliente"+ 
			" ORDER BY model.dataPrevisao DESC";

}
