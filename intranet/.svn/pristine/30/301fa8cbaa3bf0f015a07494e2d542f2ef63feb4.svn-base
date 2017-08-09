package br.com.sp.intranet.service.comercial;

import java.util.Date;
import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.DespesaVisita;

public interface DespesaVisitaService {

	public DespesaVisita findByDiaAndZona(CsUsuario usuario, Date dia, Long zonaVendas) throws JPAException;

	public List<DespesaVisita> filtrar(List<DespesaVisita> despesas, String filtro);

	public List<DespesaVisita> filtrarByData(List<DespesaVisita> listRegistroVisita, Date data);

	public List<DespesaVisita> findDespesaVisitaByMes(CsUsuario usuario, String mesAno, Long zonaVendas) throws JPAException;

	public boolean salvar(CsUsuario usuario, DespesaVisita despesaVisita);

	public DespesaVisita findById(CsUsuario usuario, Long id) throws JPAException;
}