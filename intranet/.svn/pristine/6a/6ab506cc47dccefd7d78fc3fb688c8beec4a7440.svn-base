package br.com.sp.intranet.service.comercial.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
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
import br.com.sp.intranet.model.comercial.RegistroVisita;
import br.com.sp.intranet.service.comercial.RelatorioVisitaService;
import br.com.sp.intranet.util.ConstantWS;

@Service
public class RelatorioVisitaServiceImpl implements RelatorioVisitaService{
	
	private final static String RELATORIO_VISITA = "relatorio_visita?zonaVendas={zonaVendas}&mesAno={mesAno}";
	
	@Override
	public List<RegistroVisita> carregarRelatorioVisita(Long zonaVendas, String mesAno, CsUsuario usuario) throws Exception{
		 
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("mesAno", mesAno);
		params.put("zonaVendas", zonaVendas.toString());
		
		ParameterizedTypeReference<List<RegistroVisita>> typeRef = new ParameterizedTypeReference<List<RegistroVisita>>() {};
		ResponseEntity<List<RegistroVisita>> response = restTemplate.exchange(ConstantWS.URL + RELATORIO_VISITA, 
				HttpMethod.GET, 
				new HttpEntity<T>(header), 
				typeRef,
				params);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			List<RegistroVisita> relatorioVisita = response.getBody();
			Collections.sort(relatorioVisita);
			return relatorioVisita;
		}else{
			return null;
		}
	}
	
	public HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	@Override
	public List<RegistroVisita> filtrarByData(List<RegistroVisita> listRegistroVisita, Date data) {
		List<RegistroVisita> retorno = new ArrayList<>();
		
		if(listRegistroVisita != null && !listRegistroVisita.isEmpty()){
			retorno = listRegistroVisita.stream().filter(r -> r.getPk().getDataVisitaReal().equals(data)).collect(Collectors.toList());
		}
		return retorno;
	}
	
	@Override
	public List<RegistroVisita> filtrar(List<RegistroVisita> listRegistroVisita, String filtro) {
		List<RegistroVisita> retorno = new ArrayList<>();
		
		if(filtro != null && !filtro.isEmpty() && listRegistroVisita != null && !listRegistroVisita.isEmpty()){
			if(!StringUtils.isNumeric(filtro) ){
				retorno = listRegistroVisita.stream().filter(r -> r.getNomeCliente().toLowerCase().contains(filtro.toLowerCase())).collect(Collectors.toList());
			
			}else{
				retorno = listRegistroVisita.stream().filter(r -> r.getPk().getIdCliente().toString().startsWith(filtro)).collect(Collectors.toList());
			}
		}
		return retorno;
	}
}