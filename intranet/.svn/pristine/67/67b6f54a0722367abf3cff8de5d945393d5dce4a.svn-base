package br.com.sp.intranet.dao.portaria;

import java.util.List;
import org.springframework.stereotype.Repository;

import br.com.sp.intranet.dao.GenericDAO;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.Veiculo;

@Repository
public class VeiculosPortariaDAO extends GenericDAO {

	@SuppressWarnings("unchecked")
	public List<Veiculo> findAll() throws JPAException {
		List<Veiculo> list;
		list = getSession().createQuery("SELECT model FROM Veiculo model order by model.idVeiculo").list();
		if (list == null || list.isEmpty())
			return null;
		return list;
	}
}