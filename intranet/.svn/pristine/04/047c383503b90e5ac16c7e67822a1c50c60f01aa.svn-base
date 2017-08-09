package br.com.sp.intranet.service.comercial;

import java.util.Date;
import java.util.List;

import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.RegistroVisita;

public interface RelatorioVisitaService {

	public List<RegistroVisita> carregarRelatorioVisita(Long zonaVendas, String mesAno, CsUsuario usuario) throws Exception;

	public List<RegistroVisita> filtrarByData(List<RegistroVisita> listRegistroVisita, Date data);
	
	public List<RegistroVisita> filtrar(List<RegistroVisita> listRegistroVisita, String filtro);
}