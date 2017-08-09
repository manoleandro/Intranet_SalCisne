package br.com.sp.intranet.service.comercial.impl;

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
import br.com.sp.intranet.model.comercial.Configuracao;
import br.com.sp.intranet.service.comercial.ConfiguracaoService;
import br.com.sp.intranet.util.ConstantWS;
import br.com.sp.intranet.util.WsrServiceUtils;

@Service
public class ConfiguracaoServiceImpl extends WsrServiceUtils implements ConfiguracaoService{
	private static final String CONFIGURACAO = "configuracao";
	private static final String ALTERAR_CONFIGURACAO = "configuracao/update";
	
	@Override
	public Configuracao find(CsUsuario usuario) throws JPAException{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		ParameterizedTypeReference<Configuracao> typeRef = new ParameterizedTypeReference<Configuracao>() {};
		ResponseEntity<Configuracao> response = restTemplate.exchange(ConstantWS.URL + CONFIGURACAO, 
				HttpMethod.GET, 
				new HttpEntity<T>(header), 
				typeRef);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			return response.getBody();
		}else{
			return null;
		}
	}
	
	@Override
	public boolean salvar(CsUsuario usuario, Configuracao configuracao){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		HttpEntity<Configuracao> request = new HttpEntity<Configuracao>(configuracao, header);
		
		ParameterizedTypeReference<Configuracao> typeRef = new ParameterizedTypeReference<Configuracao>() {};
		ResponseEntity<Configuracao> response = restTemplate.exchange(ConstantWS.URL + ALTERAR_CONFIGURACAO, 
				HttpMethod.PUT,
				request, 
				typeRef);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			return true;
		}
		
		return false;
	}
}