package br.com.sp.intranet.service.comercial.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.RoteiroVisita;
import br.com.sp.intranet.service.comercial.RoteiroVisitaService;
import br.com.sp.intranet.util.ConstantWS;
import br.com.sp.intranet.util.WsrServiceUtils;

@Service
public class RoteiroVisitaServiceImpl extends WsrServiceUtils implements RoteiroVisitaService{
	private final static String ROTEIRO_VISITA_SUGERIDO_BY_ZONA_VENDAS = "roteiro_visita_sugerido_by_zona_vendas?mesAno={mesAno}&zonaVendas={zonaVendas}";
	private final static String ROTEIRO_VISITA_SUGERIDO_BY_CLIENTE = "roteiro_visita_sugerido_by_cliente?mesAno={mesAno}&idCliente={idCliente}";
	
	@Override
	public List<RoteiroVisita> carregarRoteiroVisitaSugeridoByZonaVendas(String mesAno, Long zonaVendas, CsUsuario usuario){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("mesAno", mesAno);
		params.put("zonaVendas", zonaVendas.toString());
		
		ParameterizedTypeReference<List<RoteiroVisita>> typeRef = new ParameterizedTypeReference<List<RoteiroVisita>>() {};
		
		ResponseEntity<List<RoteiroVisita>> response = restTemplate.exchange(ConstantWS.URL + ROTEIRO_VISITA_SUGERIDO_BY_ZONA_VENDAS, 
				HttpMethod.GET, 
				new HttpEntity<T>(header), 
				typeRef,
				params);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			return response.getBody();
		}else{
			return null;
		}
	}
	
	@Override
	public RoteiroVisita carregarRoteiroVisitaSugeridoByCliente(String mesAno, Long idCliente, CsUsuario usuario){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("mesAno", mesAno);
		params.put("idCliente", idCliente.toString());
		
		ParameterizedTypeReference<RoteiroVisita> typeRef = new ParameterizedTypeReference<RoteiroVisita>() {};
		
		ResponseEntity<RoteiroVisita> response = restTemplate.exchange(ConstantWS.URL + ROTEIRO_VISITA_SUGERIDO_BY_CLIENTE, 
				HttpMethod.GET, 
				new HttpEntity<T>(header), 
				typeRef,
				params);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			return response.getBody();
		}else{
			return null;
		}
	}
	
	@Override
	public List<RoteiroVisita> filtrarByData(List<RoteiroVisita> listRoteiroVisita, Date data) {
		List<RoteiroVisita> retorno = new ArrayList<>();
		
		if(listRoteiroVisita != null && !listRoteiroVisita.isEmpty()){
			retorno = listRoteiroVisita.stream().filter(r -> r.getDataVisita().equals(data)).collect(Collectors.toList());
		}
		return retorno;
	}
	
	@Override
	public List<RoteiroVisita> filtrar(List<RoteiroVisita> listRoteiroVisita, String filtro) {
		List<RoteiroVisita> retorno = new ArrayList<>();
		
		if(filtro != null && !filtro.isEmpty() && listRoteiroVisita != null && !listRoteiroVisita.isEmpty()){
			if(!StringUtils.isNumeric(filtro) ){
				retorno = listRoteiroVisita.stream().filter(r -> r.getNomeCliente().toLowerCase().contains(filtro.toLowerCase())).collect(Collectors.toList());
			
			}else{
				retorno = listRoteiroVisita.stream().filter(r -> r.getIdCliente().toString().startsWith(filtro)).collect(Collectors.toList());
			}
		}
		return retorno;
	}
}