package br.com.sp.intranet.service.comercial;

import java.util.Date;

import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.RegistroVisita;
import br.com.sp.intranet.model.comercial.VendedorClienteByMunicipio;

public interface RegistroVisitaService {
	
	public VendedorClienteByMunicipio carregarVendedorClienteByMunicipio(String mesAno, Long zonaVendas, CsUsuario usuario, Date data) throws Exception;
	
	public RegistroVisita carregarDetalheRegistroVisita(String idCliente, String mesAno, CsUsuario usuario, Date dia);

	public boolean alterar(RegistroVisita vo, CsUsuario usuario) throws Exception;

	public boolean incluir(RegistroVisita vo, CsUsuario usuario) throws Exception;

	public VendedorClienteByMunicipio filtarCliente(String filtro, VendedorClienteByMunicipio vendedorClienteByMunicipio)throws Exception;

	public VendedorClienteByMunicipio filtrarByData(VendedorClienteByMunicipio vendedorClienteByMunicipio, Date data) throws Exception;
}