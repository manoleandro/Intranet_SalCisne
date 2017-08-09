package br.com.sp.intranet.service.comercial.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.sp.intranet.model.comercial.Vendedor;
import br.com.sp.intranet.service.comercial.VendedorService;
import br.com.sp.intranet.util.ConstantWS;
import br.com.sp.intranet.util.DateUtils;
import br.com.sp.intranet.util.WsrServiceUtils;

@Service
public class VendedorServiceImpl extends WsrServiceUtils implements VendedorService{
	
	private final static String VENDEDORES = "vendedores";
	private final static String VENDEDOR = "vendedor?idVendedor={idVendedor}";
	private final static String VENDEDOR_BY_ZONA = "vendedor_by_zona_vendas?zonaVendas={zonaVendas}";
	
	@Override
	public List<Vendedor> carregarVendedores(CsUsuario usuario) throws Exception{
		 
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		ParameterizedTypeReference<List<Vendedor>> typeRef = new ParameterizedTypeReference<List<Vendedor>>() {};
		ResponseEntity<List<Vendedor>> response = restTemplate.exchange(ConstantWS.URL + VENDEDORES, 
				HttpMethod.GET, 
				new HttpEntity<T>(header), 
				typeRef);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			List<Vendedor> vendedores = response.getBody();
			Collections.sort(vendedores);
			return vendedores;
		}else{
			return null;
		}
	}
	
	@Override
	public Vendedor findById(CsUsuario usuario, Long idVendedor) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("idVendedor", idVendedor.toString());
		
		ParameterizedTypeReference<Vendedor> typeRef = new ParameterizedTypeReference<Vendedor>() {};
		ResponseEntity<Vendedor> response = restTemplate.exchange(ConstantWS.URL + VENDEDOR, 
				HttpMethod.GET, 
				new HttpEntity<T>(header), 
				typeRef,
				params);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			Vendedor vendedor = response.getBody();
			return vendedor;
		}else{
			return null;
		}
	}
	
	@Override
	public Vendedor findByZonaVendas(CsUsuario usuario, Long zonaVendas) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("zonaVendas", zonaVendas.toString());
		
		ParameterizedTypeReference<Vendedor> typeRef = new ParameterizedTypeReference<Vendedor>() {};
		ResponseEntity<Vendedor> response = restTemplate.exchange(ConstantWS.URL + VENDEDOR_BY_ZONA, 
				HttpMethod.GET, 
				new HttpEntity<T>(header), 
				typeRef,
				params);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			Vendedor vendedor = response.getBody();
			return vendedor;
		}else{
			return null;
		}
	}
}