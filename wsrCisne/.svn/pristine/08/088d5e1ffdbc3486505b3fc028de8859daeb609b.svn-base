package wsr.model.comercial.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wsr.GenericDAOAnalista;
import wsr.model.comercial.VendedorClienteByMunicipio;

@Repository
@Transactional("analistaTransactionManager")
public class VendedorClienteByMunicipioRespository extends GenericDAOAnalista{ 
	public VendedorClienteByMunicipio findByIdVendedor(Long idVendedor){
		VendedorClienteByMunicipio vendedorClienteByMunicipio = getSession().get(VendedorClienteByMunicipio.class, idVendedor); 
		
		return vendedorClienteByMunicipio;
	}
}
