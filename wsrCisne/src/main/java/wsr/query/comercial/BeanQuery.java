package wsr.query.comercial;

public class BeanQuery {
	
	public static final String CLIENTES_BY_MUNICIPIO_BY_ZONA_VENDAS_BY_MES 
			= "SELECT " + 
					"{vendedor.*}," +
					"{municipio.*}," +
					"{cliente.*}," +
					"registroVisita.DATA_VISITA_REAL as dataVisita" +
					
			" FROM WSR_VENDEDOR vendedor INNER JOIN WSR_MUNICIPIO municipio ON vendedor.ID_VENDEDOR = municipio.ID_VENDEDOR " +
			" INNER JOIN WSR_CLIENTE cliente ON municipio.ID_VENDEDOR = cliente.ID_VENDEDOR AND municipio.ID_MUNICIPIO = cliente.ID_MUNICIPIO "+
			" LEFT JOIN REGISTRO_VISITA registroVisita ON cliente.ID_CLIENTE = registroVisita.ID_CLIENTE" +
			" AND TO_CHAR(registroVisita.DATA_VISITA_REAL, 'MM/yyyy') = :mesAno" +
			
			" WHERE cliente.COD_ZONA_VENDAS = :zonaVendas";
	
	
	public static final String CLIENTES_BY_MUNICIPIO_BY_ZONA_VENDAS_BY_DIA 
			= "SELECT " + 
					"{vendedor.*}," +
					"{municipio.*}," +
					"{cliente.*}," +
					"registroVisita.DATA_VISITA_REAL as dataVisita" +
			
			" FROM WSR_VENDEDOR vendedor INNER JOIN WSR_MUNICIPIO municipio ON vendedor.ID_VENDEDOR = municipio.ID_VENDEDOR " +
			" INNER JOIN WSR_CLIENTE cliente ON municipio.ID_VENDEDOR = cliente.ID_VENDEDOR AND municipio.ID_MUNICIPIO = cliente.ID_MUNICIPIO "+
			" LEFT OUTER JOIN REGISTRO_VISITA registroVisita ON cliente.ID_CLIENTE = registroVisita.ID_CLIENTE" + 
			" AND registroVisita.DATA_VISITA_SPV = :dia" +
			" INNER JOIN HIST_DATA_VISITA histDataVisita ON histDataVisita.ID_CLIENTE = cliente.ID_CLIENTE" +
			" AND TO_DATE(TO_CHAR(histDataVisita.DATA_VISITA, 'dd/MM/yyyy'), 'dd/MM/yyyy') = :dia" +
	
			" WHERE cliente.COD_ZONA_VENDAS = :zonaVendas";
	
	public static void main(String[] args) {
		System.out.println(CLIENTES_BY_MUNICIPIO_BY_ZONA_VENDAS_BY_MES);
	}
}