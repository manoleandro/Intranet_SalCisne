package br.com.sp.intranet.service.portaria;

import java.util.List;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.portaria.Veiculo;

public interface VeiculosTransportadoraService {
	
	void delete(Veiculo veiculo) throws JPAException;
	void save(Veiculo veiculo) throws JPAException;
	void update(Veiculo veiculo) throws JPAException;
	List<Veiculo> findAll() throws JPAException;
	Veiculo findById(Veiculo veiculo) throws JPAException;

}
