package wsr.service.comercial;

import java.util.Date;
import java.util.List;

import wsr.exception.JPAException;
import wsr.model.comercial.DespesaVisita;
import wsr.model.comercial.RegistroVisita;

public interface DespesaVisitaService {

	public List<DespesaVisita> findDespesaVisitaByMes(String mesAno, Long zonaVendas) throws JPAException;

	public DespesaVisita findByDiaAndZona(Date dia, Long zonaVendas) throws JPAException;

	public void salvarDespesa(RegistroVisita registroVisita) throws JPAException;

	public void salvar(DespesaVisita despesaVisita) throws JPAException;

	public DespesaVisita findById(Long idDespesa) throws JPAException;
}
