package wsr.service.comercial.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import wsr.model.comercial.vo.EstoqueFimDoMes;
import wsr.service.comercial.CalculoSpvService;
import wsr.util.DataTypes;

@Service
public class CalculoSpvServiceImpl implements CalculoSpvService{

	@Override
	public EstoqueFimDoMes calcularEstoqueFimDoMes(Long quantidadeEstoque, Long quantidadeEntrada, Long quantidadeNfSemDataCanhoto, Long consumoProgressivo, Long dias,
			Long quantidadePedidoPendente, LocalDate dataLancamento){
		
		EstoqueFimDoMes estoqueFimDoMes = new EstoqueFimDoMes();
		
		Long estoqueCalculado = DataTypes.parseNull(quantidadeEstoque) + DataTypes.parseNull(quantidadeEntrada) + DataTypes.parseNull(quantidadeNfSemDataCanhoto) 
		- (DataTypes.parseNull(consumoProgressivo) * DataTypes.parseNull(dias)) + DataTypes.parseNull(quantidadePedidoPendente);
				
		estoqueFimDoMes.setQuantidadeEstoque(estoqueCalculado);
		
		estoqueFimDoMes.setDataLancamento(dataLancamento);
		estoqueFimDoMes.setConsumoProgressivo(consumoProgressivo);
		estoqueFimDoMes.setQuantidadeEntrada(quantidadeEntrada);
		
		if(estoqueFimDoMes.getQuantidadeEstoque() < 0){
			estoqueFimDoMes.setQuantidadeEstoque(new Long(0));
		}
		return estoqueFimDoMes;
	}
}