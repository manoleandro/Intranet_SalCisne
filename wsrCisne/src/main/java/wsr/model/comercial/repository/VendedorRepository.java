package wsr.model.comercial.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.exception.JPAException;
import wsr.model.comercial.Vendedor;

@Repository
public class VendedorRepository extends GenericDAOAnalista{
	
	public Vendedor findByIdVendedor(Long idVendedor) throws JPAException{
		Vendedor vendedor = null;
		
		vendedor = (Vendedor) getSession().get(Vendedor.class, idVendedor);
		
		return vendedor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Vendedor> findAll() throws JPAException{
		List<Vendedor> vendedores = null;
		vendedores = getSession().createQuery("from Vendedor").list();
		
		return vendedores;
	}
	
	@SuppressWarnings("unchecked")
	public Vendedor findByZonaVendas(Long zonaVendas) throws JPAException{
		Vendedor vendedor = null;
		
		List<Vendedor> list = getSession().createQuery("SELECT model FROM Vendedor model WHERE model.codZonaVendas = :zonaVendas")
			.setParameter("zonaVendas", zonaVendas)
			.list();
		
		if(list !=null && !list.isEmpty()){
			vendedor = list.get(0);
		}
		
		return vendedor;
	}
}