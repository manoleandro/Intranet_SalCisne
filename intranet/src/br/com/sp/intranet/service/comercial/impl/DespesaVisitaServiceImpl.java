package br.com.sp.intranet.service.comercial.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.DespesaVisita;
import br.com.sp.intranet.service.comercial.DespesaVisitaService;
import br.com.sp.intranet.util.ConstantWS;
import br.com.sp.intranet.util.DateUtils;
import br.com.sp.intranet.util.WsrServiceUtils;

@Service
public class DespesaVisitaServiceImpl extends WsrServiceUtils implements DespesaVisitaService{
	
	private static final String DESPESA_VISITA_BY_MES = "despesa_visita_by_mes?mesAno={mesAno}&zonaVendas={zonaVendas}";
	private static final String DESPESA_VISITA_BY_DIA_AND_ZONA = "despesa_visita_by_mes?dia={dia}&zonaVendas={zonaVendas}";
	private static final String DESPESA_VISITA_BY_ID = "despesa_visita_by_id?id={id}";
	private static final String ALTERACAO_DESPESA_VISITA = "despesa_visita/update";
	
	@Override
	public List<DespesaVisita> findDespesaVisitaByMes(CsUsuario usuario, String mesAno, Long zonaVendas) throws JPAException{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("mesAno", mesAno);
		params.put("zonaVendas", zonaVendas.toString());
		
		ParameterizedTypeReference<List<DespesaVisita>> typeRef = new ParameterizedTypeReference<List<DespesaVisita>>() {};
		ResponseEntity<List<DespesaVisita>> response = restTemplate.exchange(ConstantWS.URL + DESPESA_VISITA_BY_MES, 
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
	public DespesaVisita findByDiaAndZona(CsUsuario usuario, Date dia, Long zonaVendas) throws JPAException{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("dia", DateUtils.obtemData(dia, "dd/MM/yyyy"));
		params.put("zonaVendas", zonaVendas.toString());
		
		ParameterizedTypeReference<DespesaVisita> typeRef = new ParameterizedTypeReference<DespesaVisita>() {};
		ResponseEntity<DespesaVisita> response = restTemplate.exchange(ConstantWS.URL + DESPESA_VISITA_BY_DIA_AND_ZONA, 
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
	public DespesaVisita findById(CsUsuario usuario, Long id) throws JPAException{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		
		ParameterizedTypeReference<DespesaVisita> typeRef = new ParameterizedTypeReference<DespesaVisita>() {};
		ResponseEntity<DespesaVisita> response = restTemplate.exchange(ConstantWS.URL + DESPESA_VISITA_BY_ID, 
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
	public boolean salvar(CsUsuario usuario, DespesaVisita despesaVisita){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		HttpEntity<DespesaVisita> request = new HttpEntity<DespesaVisita>(despesaVisita, header);
		
		ParameterizedTypeReference<DespesaVisita> typeRef = new ParameterizedTypeReference<DespesaVisita>() {};
		ResponseEntity<DespesaVisita> response = restTemplate.exchange(ConstantWS.URL + ALTERACAO_DESPESA_VISITA, 
				HttpMethod.PUT,
				request, 
				typeRef);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<DespesaVisita> filtrar(List<DespesaVisita> despesas, String filtro){
		List<DespesaVisita> retorno = new ArrayList<>();
		
		if(filtro != null && !filtro.isEmpty() && despesas != null && !despesas.isEmpty()){
			if(filtro.contains("/")){
				retorno = despesas.stream().filter(d -> DateUtils.obtemData(d.getData(), "dd/MM/yyyy").startsWith(filtro)).collect(Collectors.toList());
			
			}else{
				retorno = despesas.stream().filter(d -> d.getDeslocamento().toString().toLowerCase().contains(filtro.toLowerCase())).collect(Collectors.toList());
			}
		}
		return retorno;
	}
	
	@Override
	public List<DespesaVisita> filtrarByData(List<DespesaVisita> listRegistroVisita, Date data) {
		List<DespesaVisita> retorno = new ArrayList<>();
		
		if(listRegistroVisita != null && !listRegistroVisita.isEmpty()){
			retorno = listRegistroVisita.stream().filter(d -> d.getData().equals(data)).collect(Collectors.toList());
		}
		return retorno;
	}
}