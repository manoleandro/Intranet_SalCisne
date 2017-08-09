package wsr.model.comercial.repository;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.query.comercial.PedidoVendaQuery;
import wsr.util.DataTypes;

@Repository
public class PedidoVendasRepository extends GenericDAOAnalista{
	
	public String findFilialPedidoVendaMaxMes(Long idCliente, Date ultimoDiaMes){
		String retorno = null;
		
		retorno = (String) getSession().createSQLQuery(PedidoVendaQuery.MAX_ID_FILIAL_BY_DATA_EMISSAO)
				.setParameter("idCliente", idCliente)
				.setParameter("ultimoDiaMes", ultimoDiaMes)
				.uniqueResult();
		
		return retorno;
	}
	
	public Double findQuantidadePedidoPendentesMesByCliente(Long idCliente, String mesAno){
		Double retorno = null;
		
		retorno = (Double) getSession().createSQLQuery(PedidoVendaQuery.QUANTIDADE_PEDIDO_PENDENTE_MES_BY_CLIENTE)
				.addScalar("quantidade", StandardBasicTypes.DOUBLE)
				.setParameter("idCliente", idCliente)
				.setParameter("mesAno", mesAno)
				.uniqueResult();
		
		return DataTypes.parseNull(retorno);
	}
	
	public LocalDate findUltimaDataPedido(Long idCliente){
		LocalDate retorno = null;
		
		retorno = (LocalDate) getSession().createQuery(PedidoVendaQuery.MAX_DATA_EMISSAO)
				.setParameter("idCliente", idCliente)
				.uniqueResult();
		
		return retorno;
	}
}