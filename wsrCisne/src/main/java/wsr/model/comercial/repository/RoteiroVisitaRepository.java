package wsr.model.comercial.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.DateType;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.model.comercial.RoteiroVisita;
import wsr.query.comercial.RoteiroVisitaQuery;

@Repository
public class RoteiroVisitaRepository extends GenericDAOAnalista{
	
	@SuppressWarnings("unchecked")
	public List<RoteiroVisita> findRoteiroVisitaSugeridoByZonaVendas(String ultimoDiaMes, String mesAno, String mesAnoAnterior, Long codZonaVendas){
		
		Query query = getSession().createSQLQuery(RoteiroVisitaQuery.ROTEIRO_VISITA_SUGERIDO_BY_ZONA_MES + " ORDER BY H.DATA_VISITA, C.NOME_FANTASIA")
				.addScalar("zonaVendas", LongType.INSTANCE)
				.addScalar("dataVisita", DateType.INSTANCE)
				.addScalar("idCliente", LongType.INSTANCE)
				.addScalar("nomeCliente" , StringType.INSTANCE)
				.addScalar("consumoProgressivo", LongType.INSTANCE)
				.addScalar("diasCobertura", LongType.INSTANCE)
				.addScalar("quantidadeEstoque", LongType.INSTANCE)
				.addScalar("quantidadePrevisao", LongType.INSTANCE)
				.addScalar("precoMedio", DoubleType.INSTANCE)
				.addScalar("valorPrevisao", DoubleType.INSTANCE)
				.setResultTransformer(new AliasToBeanResultTransformer(RoteiroVisita.class));

		query.setParameter("ultimoDiaMes", ultimoDiaMes);
		query.setParameter("mesAno", mesAno);
		query.setParameter("mesAnoAnterior", mesAnoAnterior);
		query.setParameter("zonaVendas", codZonaVendas);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public RoteiroVisita findRoteiroVisitaSugeridoByCliente(String ultimoDiaMes, String mesAno, String mesAnoAnterior, Long idCliente){
		
		Query query = getSession().createSQLQuery(RoteiroVisitaQuery.ROTEIRO_VISITA_SUGERIDO_BY_CLIENTE + " ORDER BY H.DATA_VISITA, C.NOME_FANTASIA")
				.addScalar("zonaVendas", LongType.INSTANCE)
				.addScalar("dataVisita", DateType.INSTANCE)
				.addScalar("idCliente", LongType.INSTANCE)
				.addScalar("nomeCliente", StringType.INSTANCE)
				.addScalar("consumoProgressivo", LongType.INSTANCE)
				.addScalar("diasCobertura", LongType.INSTANCE)
				.addScalar("quantidadeEstoque", LongType.INSTANCE)
				.addScalar("quantidadePrevisao", LongType.INSTANCE)
				.addScalar("precoMedio", DoubleType.INSTANCE)
				.addScalar("valorPrevisao", DoubleType.INSTANCE)
				.setResultTransformer(new AliasToBeanResultTransformer(RoteiroVisita.class));

		query.setParameter("ultimoDiaMes", ultimoDiaMes);
		query.setParameter("mesAno", mesAno);
		query.setParameter("mesAnoAnterior", mesAnoAnterior);
		query.setParameter("idCliente", idCliente);
		
		if(!query.list().isEmpty()){
			return (RoteiroVisita) query.list().get(0);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<RoteiroVisita> findRoteiroVisitaSugerido(String ultimoDiaMes, String mesAno){
		
		Query query = getSession().createSQLQuery(RoteiroVisitaQuery.ROTEIRO_VISITA_SUGERIDO_BY_MES + " ORDER BY H.DATA_VISITA")
				.addScalar("zonaVendas", LongType.INSTANCE)
				.addScalar("pk.dataVisita", DateType.INSTANCE)
				.addScalar("pk.idCliente", LongType.INSTANCE)
				.addScalar("nomeCliente" , StringType.INSTANCE)
				.addScalar("consumoProgressivo", LongType.INSTANCE)
				.addScalar("diasCobertura", LongType.INSTANCE)
				.addScalar("quantidadeEstoque", LongType.INSTANCE)
				.addScalar("quantidadePrevisao", LongType.INSTANCE)
				.addScalar("precoMedio", DoubleType.INSTANCE)
				.addScalar("valorPrevisao", DoubleType.INSTANCE)
				.setResultTransformer(new AliasToBeanResultTransformer(RoteiroVisita.class));

		query.setParameter("ultimoDiaMes", ultimoDiaMes);
		query.setParameter("mesAno", mesAno);
		
		return query.list();
	}
}