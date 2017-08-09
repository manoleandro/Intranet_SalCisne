package br.com.sp.intranet.service.comercial;

import java.util.Date;
import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.RoteiroVisita;

public interface RoteiroVisitaService {

	public List<RoteiroVisita> carregarRoteiroVisitaSugeridoByZonaVendas(String mesAno, Long zonaVendas, CsUsuario usuario) throws JPAException;
	
	public RoteiroVisita carregarRoteiroVisitaSugeridoByCliente(String mesAno, Long idCliente, CsUsuario usuario);

	public List<RoteiroVisita> filtrar(List<RoteiroVisita> listRoteiroVisita, String filtro);

	public List<RoteiroVisita> filtrarByData(List<RoteiroVisita> listRoteiroVisita, Date data);
}