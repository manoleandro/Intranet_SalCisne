package br.com.sp.intranet.service.comercial;

import java.util.List;

import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.Cliente;

public interface ClienteService {

	public List<Cliente> carregarClientesByZonaVendas(Long zonaVendas, CsUsuario usuario);

}