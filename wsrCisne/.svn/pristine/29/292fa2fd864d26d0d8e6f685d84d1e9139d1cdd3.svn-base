package wsr.model.comercial.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.DateType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.exception.JPAException;
import wsr.model.comercial.Cliente;
import wsr.model.comercial.Municipio;
import wsr.model.comercial.Vendedor;
import wsr.model.comercial.vo.ClientesByMunicipioByZonaVendasBean;
import wsr.query.comercial.BeanQuery;

@Repository
@SuppressWarnings("unchecked")
public class ClienteRepository extends GenericDAOAnalista{
	
	public Cliente findByIdCliente(Long idCliente){
		return (Cliente) getSession().get(Cliente.class, idCliente);
	}
	
	
	public List<ClientesByMunicipioByZonaVendasBean> findClientesByMunicipioByMes(String mesAno, Long codZonaVendas){
		
		Query query = getSession().createSQLQuery(BeanQuery.CLIENTES_BY_MUNICIPIO_BY_ZONA_VENDAS_BY_MES)
				.addEntity("vendedor", Vendedor.class)
				.addEntity("municipio", Municipio.class)
				.addEntity("cliente", Cliente.class)
				.addScalar("dataVisita", DateType.INSTANCE)
				.setResultTransformer(new AliasToBeanResultTransformer(ClientesByMunicipioByZonaVendasBean.class));

		query.setParameter("mesAno", mesAno);
		query.setParameter("zonaVendas", codZonaVendas);
		
		return query.list();
	}
	
	public List<ClientesByMunicipioByZonaVendasBean> findClientesByMunicipioByDia(Long codZonaVendas, Date dia){
		
		Query query = getSession().createSQLQuery(BeanQuery.CLIENTES_BY_MUNICIPIO_BY_ZONA_VENDAS_BY_DIA)
				.addEntity("vendedor", Vendedor.class)
				.addEntity("municipio", Municipio.class)
				.addEntity("cliente", Cliente.class)
				.addScalar("dataVisita", DateType.INSTANCE)
				.setResultTransformer(new AliasToBeanResultTransformer(ClientesByMunicipioByZonaVendasBean.class));

		query.setParameter("zonaVendas", codZonaVendas);
		query.setParameter("dia", dia);
		
		return query.list();
	}
	
	public List<Cliente> findAll() throws JPAException{
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes = getSession().createQuery("from Cliente").list();
		
		return clientes;
	}
	
	
	public List<Long> findAllIdCliente() throws JPAException{
		List<Long> clientes = null;
		clientes = getSession().createQuery("SELECT idCliente from Cliente").list();
		
		return clientes;
	}
	
	public List<Cliente> findByZonaVendas(Long zonaVendas) throws JPAException{
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		clientes = getSession().createQuery("SELECT model FROM Cliente model WHERE model.codZonaVendas = :zonaVendas")
				.setParameter("zonaVendas", zonaVendas)
				.list();
		
		return clientes;
	}
}