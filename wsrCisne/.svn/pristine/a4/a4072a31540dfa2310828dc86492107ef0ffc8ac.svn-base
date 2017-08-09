package wsr.model.comercial.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import wsr.GenericDAOAnalista;
import wsr.exception.JPAException;
import wsr.model.comercial.DespesaVisita;
import wsr.model.comercial.DespesaVisitaCliente;

@Repository
public class DespesaVisitaRepository extends GenericDAOAnalista{
	
	@SuppressWarnings("unchecked")
	public List<DespesaVisita> findDespesaVisitaByMes(String mesAno, Long zonaVendas) throws JPAException{
		List<DespesaVisita> list;
		
		StringBuffer sql = new StringBuffer("SELECT model FROM DespesaVisita model");
		sql.append(" WHERE TO_CHAR(model.data, 'MM/yyyy') = :mesAno AND model.zonaVendas = :zonaVendas");
		sql.append(" ORDER BY model.data");
		
		list = getSession().createQuery(sql.toString())
				.setParameter("mesAno", mesAno)
				.setParameter("zonaVendas", zonaVendas)
				.list();

		if (list == null || list.isEmpty())
			return null;

		return list;
	}
	
	@SuppressWarnings("unchecked")
	public DespesaVisita findByDiaAndZona(Date dia, Long zonaVendas) throws JPAException{
		List<DespesaVisita> list;
		
		StringBuffer sql = new StringBuffer("SELECT model FROM DespesaVisita model");
		sql.append(" WHERE model.data = :dia AND model.zonaVendas = :zonaVendas");
		sql.append(" ORDER BY model.data");
		
		list = getSession().createQuery(sql.toString())
				.setParameter("dia", dia)
				.setParameter("zonaVendas", zonaVendas)
				.list();

		if (list == null || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	public DespesaVisita findById(Long id) throws JPAException{
		return (DespesaVisita) findById(DespesaVisita.class, id);	
	}
	
	public List<DespesaVisitaCliente> carregarVisitaClientes(DespesaVisita despesaVisita){
		Hibernate.initialize(despesaVisita.getVisitaClientes());
		
		return despesaVisita.getVisitaClientes();
	}
}
