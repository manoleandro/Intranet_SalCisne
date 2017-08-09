package wsr.query.comercial;

public class RoteiroVisitaQuery {

	public static final String ROTEIRO_VISITA_SUGERIDO_BY_MES = 
		"SELECT" + 
	
		" C.COD_ZONA_VENDAS as zonaVendas," +
		" H.DATA_VISITA as dataVisita," + 
		" C.ID_CLIENTE as idCliente," + 
		" C.NOME_FANTASIA as nomeCliente," + 
		" EC.CONSUMO_PROGRESSIVO as consumoProgressivo," + 
		" PV.DIAS as diasCobertura," + 
		" HPV.QUANTIDADE_ESTOQUE as quantidadeEstoque," + 
		" PV.QUANTIDE_KG as quantidadePrevisao," + 
		" PV.PRECO_MEDIO as precoMedio," + 
		" PV.VALOR_PREVISAO as valorPrevisao" +
		
		" FROM HIST_DATA_VISITA H INNER JOIN WSR_CLIENTE C ON H.ID_CLIENTE = C.ID_CLIENTE" +
		
		" LEFT JOIN (" +
			" SELECT E.CONSUMO_PROGRESSIVO, E.DATA_FIM, E.ID_CLIENTE" +
			" FROM ESTOQUE_CLIENTE E" + 
			" INNER JOIN (" +
				" SELECT ID_CLIENTE, MAX(DATA_FIM) AS MAX_DATE" +
				" FROM ESTOQUE_CLIENTE" +
				" WHERE DATA_FIM <= TO_DATE(:ultimoDiaMes, 'dd/MM/yyyy')" +
				" GROUP BY ID_CLIENTE"+
			") M ON E.ID_CLIENTE = M.ID_CLIENTE AND E.DATA_FIM = M.MAX_DATE" +
		") EC ON EC.ID_CLIENTE = C.ID_CLIENTE" +
		
		" LEFT JOIN (" +
			" SELECT H.ID, H.MES, H.ID_CLIENTE, H.QUANTIDADE_ESTOQUE"+
			" FROM HIST_PLANEJAMENTO_VENDAS H" +
			" INNER JOIN(" +
		    	" SELECT ID_CLIENTE, MAX(ID) AS MAX_ID"+
		    	" FROM HIST_PLANEJAMENTO_VENDAS" +
		    	" WHERE MES = :mesAnoAnterior" +
		    	" GROUP BY ID_CLIENTE" +
		    " )P ON P.ID_CLIENTE = H.ID_CLIENTE AND H.ID = P.MAX_ID"+
		" ) HPV ON HPV.ID_CLIENTE = C.ID_CLIENTE AND HPV.MES = :mesAnoAnterior" +
		
		" LEFT JOIN PREVISAO_VENDAS PV ON C.ID_CLIENTE = PV.ID_CLIENTE AND TO_CHAR(PV.DATA_PREVISAO,'MM/yyyy') = :mesAno" +
		
		" WHERE TO_CHAR(H.DATA_VISITA, 'MM/yyyy') = :mesAno";
	
	public static final String ROTEIRO_VISITA_SUGERIDO_BY_ZONA_MES =
			
			ROTEIRO_VISITA_SUGERIDO_BY_MES +
			" AND C.COD_ZONA_VENDAS = :zonaVendas";
	
	public static final String ROTEIRO_VISITA_SUGERIDO_BY_CLIENTE =
			
			ROTEIRO_VISITA_SUGERIDO_BY_MES +
			" AND C.ID_CLIENTE = :idCliente";
	
	public static void main(String[] args) {
		System.out.println(ROTEIRO_VISITA_SUGERIDO_BY_MES);
	}
}