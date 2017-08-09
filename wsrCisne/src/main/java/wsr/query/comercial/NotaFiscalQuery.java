package wsr.query.comercial;

public class NotaFiscalQuery {
	
	public final static String PRECO_MEDIO_MES = "SELECT (SUM(QUANTIDADE * VALOR_UNITARIO) - SUM(PERC_DESCONTO)) / SUM(QUANTIDADE * PESO_LIQUIDO) " +
			" FROM WSR_NOTA_FISCAL" +
			" WHERE ID_CLIENTE= :idCliente " +
			" AND TO_CHAR(DATA_EMISSAO, 'MM/yyyy') = :mesAno";
	
	public final static String QUANTIDADE_NF_SEM_CANHOTO_MES_BY_ID_CLIENTE = "SELECT SUM(IT.QUANTIDADE * D.PESO_LIQUIDO) as quantidade"+
			" FROM IT_NF_FISCAL IT, DADOS_FINANCEIROS D, NOTA_FISCAL NF" +
			" WHERE IT.ID_PRODUTO = D.ID_PRODUTO AND NF.ID_NOTA_FISCAL = IT.ID_NOTA_FISCAL" +
			" AND NF.ID_CLIENTE = :idCliente AND TO_CHAR(NF.DATA_EMISSAO,'MM/YYYY') = :mesAno"+
			" AND IT.ID_PRODUTO IN (SELECT MODEL.ID_PRODUTO FROM PROD_ESTOQUE MODEL) AND NF.DATA_CANHOTO IS NULL AND NF.ID_STATUS NOT IN(2,4)";

	
	public final static String HQL_QUANTIDADE_NF_SEM_CANHOTO_MES_BY_ID_CLIENTE = "SELECT SUM(quantidade * pesoLiquido) FROM NotaFiscal" +
			" WHERE dataCanhoto IS NULL " +
			" AND TO_CHAR(dataEmissao, 'MM/yyyy') = :mesAno" +
			" AND idCliente = :idCliente";
}