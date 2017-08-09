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
import br.com.sp.intranet.model.comercial.Cliente;
import br.com.sp.intranet.model.comercial.Municipio;
import br.com.sp.intranet.model.comercial.RegistroVisita;
import br.com.sp.intranet.model.comercial.VendedorClienteByMunicipio;
import br.com.sp.intranet.service.comercial.RegistroVisitaService;
import br.com.sp.intranet.util.ConstantWS;
import br.com.sp.intranet.util.DateUtils;
import br.com.sp.intranet.util.WsrServiceUtils;

@Service
public class RegistroVisitaServiceImpl extends WsrServiceUtils implements RegistroVisitaService {
	
	private final static String CLIENTES_BY_MUNICIPIO = "vendedor_clientes_by_municipio?mesAno={mesAno}&zonaVendas={zonaVendas}&dia={dia}";
	private final static String DETALHE_REGISTRO_VISITA = "registro_visita?idCliente={idCliente}&mesAno={mesAno}&dataVisita={dataVisita}";
	private final static String INCLUSAO_REGISTRO_VISITA = "registro_visita/add";
	
	@Override
	@SuppressWarnings("unchecked")
	public VendedorClienteByMunicipio carregarVendedorClienteByMunicipio(String mesAno, Long zonaVendas, CsUsuario usuario, Date data) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("mesAno", mesAno);
		params.put("zonaVendas", zonaVendas.toString());
		params.put("dia", DateUtils.obtemData(data, "dd/MM/yyyy"));
		
		ParameterizedTypeReference<VendedorClienteByMunicipio> typeRef = new ParameterizedTypeReference<VendedorClienteByMunicipio>() {};
		ResponseEntity<VendedorClienteByMunicipio> response = restTemplate.exchange(ConstantWS.URL + CLIENTES_BY_MUNICIPIO, 
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
	public boolean incluir(RegistroVisita vo, CsUsuario usuario) throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		HttpEntity<RegistroVisita> request = new HttpEntity<RegistroVisita>(vo, header);
		
		ParameterizedTypeReference<RegistroVisita> typeRef = new ParameterizedTypeReference<RegistroVisita>() {};
		ResponseEntity<RegistroVisita> response = restTemplate.exchange(ConstantWS.URL + INCLUSAO_REGISTRO_VISITA, 
				HttpMethod.POST,
				request, 
				typeRef);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean alterar(RegistroVisita vo, CsUsuario usuario) throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		HttpEntity<RegistroVisita> request = new HttpEntity<RegistroVisita>(vo, header);
		
		ParameterizedTypeReference<RegistroVisita> typeRef = new ParameterizedTypeReference<RegistroVisita>() {};
		ResponseEntity<RegistroVisita> response = restTemplate.exchange(ConstantWS.URL + INCLUSAO_REGISTRO_VISITA, 
				HttpMethod.PUT,
				request, 
				typeRef);
		
		if(response.getStatusCode() ==  HttpStatus.OK){
			return true;
		}
		
		return false;
	}
	
	@Override
	public VendedorClienteByMunicipio filtrarByData(VendedorClienteByMunicipio vendedorClienteByMunicipio, Date data) throws Exception {
		List<Municipio> municipios = new ArrayList<>();
		if (vendedorClienteByMunicipio != null) {
			municipios = vendedorClienteByMunicipio.getMunicipios().stream()
					.filter(m -> m.getClientes().stream().anyMatch(c -> c.getDataVisita() != null && c.getDataVisita().equals(data)))
					.collect(Collectors.toList());

			for (Municipio municipio : municipios) {
				List<Cliente> clientes = municipio.getClientes().stream()
						.filter(c -> c.getDataVisita() != null && c.getDataVisita().equals(data))
						.collect(Collectors.toList());

				municipio.setClientes(clientes);
			}
			vendedorClienteByMunicipio.setMunicipios(municipios);
		}
		return vendedorClienteByMunicipio;
	}
	
	@Override
	public VendedorClienteByMunicipio filtarCliente(String filtro, VendedorClienteByMunicipio vendedorClienteByMunicipio) throws Exception{
		List<Municipio> municipios = new ArrayList<>();
		
		if(filtro != null && !filtro.isEmpty()){
			if(StringUtils.isNumeric(filtro) ){
				municipios = vendedorClienteByMunicipio.getMunicipios().stream()
						.filter(m -> m.getClientes().stream().anyMatch(c -> c.getIdCliente().toString().startsWith(filtro)))
						.collect(Collectors.toList());
				
				for (Municipio municipio : municipios) {
					List<Cliente> clientes = municipio.getClientes().stream()
							.filter(c -> c.getIdCliente().toString().startsWith(filtro))
							.collect(Collectors.toList());
					
					municipio.setClientes(clientes);
				}

			}else{
				municipios = vendedorClienteByMunicipio.getMunicipios().stream()
						.filter(mu -> mu.getClientes().stream().anyMatch(ce -> ce.getNomeFantasia().toUpperCase().contains(filtro.toUpperCase())))
						.collect(Collectors.toList());
				
				for (Municipio municipio : municipios) {
					List<Cliente> clientes = municipio.getClientes().stream()
							.filter(c -> c.getNomeFantasia().toUpperCase().contains(filtro.toUpperCase()))
							.collect(Collectors.toList());
					
					municipio.setClientes(clientes);
				}
					
			}
			vendedorClienteByMunicipio.setMunicipios(municipios);
		}
		
		return vendedorClienteByMunicipio;
	}
	
	@Override
	public RegistroVisita carregarDetalheRegistroVisita(String cliente, String mesAno, CsUsuario usuario, Date dia){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(usuario.getUsername(), usuario.getPassword());
		
		Map<String, String> params = new HashMap<>();
		params.put("idCliente", cliente);
		params.put("mesAno", mesAno);
		params.put("dataVisita", DateUtils.obtemData(dia, "dd/MM/yyyy"));
		
		ParameterizedTypeReference<RegistroVisita> typeRef = new ParameterizedTypeReference<RegistroVisita>() {};
		ResponseEntity<RegistroVisita> response = restTemplate.exchange(ConstantWS.URL + DETALHE_REGISTRO_VISITA, 
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