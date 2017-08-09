package br.com.sp.intranet.service.comercial.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.comercial.Cliente;
import br.com.sp.intranet.service.comercial.ClienteService;
import br.com.sp.intranet.util.ConstantWS;
import br.com.sp.intranet.util.WsrServiceUtils;

@Service
public class ClienteServiceImpl extends WsrServiceUtils implements ClienteService{
	
	
	private static final String CLIENTES_BY_ZONA_VENDAS = "clientes_by_zona_vendas?zonaVendas={zonaVendas}";
	
	@Override
	public List<Cliente> carregarClientesByZonaVendas(Long zonaVendas, CsUsuario usuario){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("zonaVendas", zonaVendas.toString());
		
		ParameterizedTypeReference<List<Cliente>> typeRef = new ParameterizedTypeReference<List<Cliente>>() {};
		
		ResponseEntity<List<Cliente>> response = restTemplate.exchange(ConstantWS.URL + CLIENTES_BY_ZONA_VENDAS, 
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
}