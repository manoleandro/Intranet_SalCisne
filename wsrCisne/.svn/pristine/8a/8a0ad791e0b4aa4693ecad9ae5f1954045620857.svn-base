package wsr.query.comercial;

public class PrazoReposicaoQuery {
	
	public final static String SOMA_DIAS_BY_ORIGEM_DESTINO = "SELECT DIAS + TOTAL_DIAS_ENTREGA " + 
			" FROM WSR_PRAZO_REPOSICAO" +
			" WHERE ORIGEM = :origem "+
			" AND DESTINO = :destino " ;
	
	
	public final static String HQL_SOMA_DIAS_BY_ORIGEM_DESTINO = "SELECT model.dias + model.totalDiasEntrega " + 
			" FROM PrazoReposicao model" +
			" WHERE model.origem = :origem "+
			" AND model.destino = :destino " ;
}