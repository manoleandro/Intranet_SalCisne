package wsr.service.comercial;

import java.util.List;

import wsr.exception.JPAException;
import wsr.model.comercial.Vendedor;
import wsr.model.comercial.VendedorClienteByMunicipio;

public interface VendedorService {

	public List<Vendedor> findAll() throws JPAException;

	public Vendedor findByIdVendedor(Long idVendedor) throws JPAException;

	public VendedorClienteByMunicipio findClientesByMunicipioByZonaVendas(String mesAno, Long zonaVendas, String dia) throws JPAException;

	public List<VendedorClienteByMunicipio> findListVendedorClienteByMunicipio(String mesAno) throws JPAException;

	public Vendedor findByZonaVendas(Long zonaVendas) throws JPAException;
}