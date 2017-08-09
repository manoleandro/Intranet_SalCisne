package com.mdSync.service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.formula.functions.T;
import org.lightcouch.CouchDbClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import com.mdSync.model.vo.DocsRest;
import com.mdSync.util.Constant;

public class Service {
	
	private static final String REV ="_rev";
	private static final String ID ="_id";
	private static final String OBJECT = "object";
	
	public HttpHeaders createHeaders(String username, String password) {
    	final String auth = username + ":" + password;
    	return new HttpHeaders() {
			{	
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	public ResponseEntity<DocsRest> exchangeRest(String url){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(Constant.USUARIO_REST, Constant.SENHA_REST);
		
		ParameterizedTypeReference<DocsRest> typeRef = new ParameterizedTypeReference<DocsRest>() {};
		
		ResponseEntity<DocsRest> response = restTemplate.exchange(url, 
				HttpMethod.GET,
				new HttpEntity<T>(header), 
				typeRef);
		
		return response;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Object> exchangeRest(String url,  ParameterizedTypeReference typeRef){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(Constant.USUARIO_REST, Constant.SENHA_REST);
		
		@SuppressWarnings("unchecked")
		ResponseEntity<List<Object>> response = restTemplate.exchange(url, 
				HttpMethod.GET,
				new HttpEntity<T>(header), 
				typeRef);
		
		if(response.getStatusCode() ==  HttpStatus.OK)
			return response.getBody();
		else
			return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Object exchangeRestObject(String url,  ParameterizedTypeReference typeRef, Map<String, String> params){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(Constant.USUARIO_REST, Constant.SENHA_REST);
		
		@SuppressWarnings("unchecked")
		ResponseEntity<Object> response = restTemplate.exchange(url, 
				HttpMethod.GET,
				new HttpEntity<T>(header), 
				typeRef,
				params);
		
		if(response.getStatusCode() ==  HttpStatus.OK)
			return response.getBody();
		else
			return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Object> exchangeRest(String url,  ParameterizedTypeReference typeRef, Map<String, String> params){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders header = createHeaders(Constant.USUARIO_REST, Constant.SENHA_REST);
		
		@SuppressWarnings("unchecked")
		ResponseEntity<List<Object>> response = restTemplate.exchange(url, 
				HttpMethod.GET,
				new HttpEntity<T>(header), 
				typeRef,
				params);
		
		if(response.getStatusCode() ==  HttpStatus.OK)
			return response.getBody();
		else
			return null;
	}
	
	public boolean syncCouch(CouchDbClient dbClient, Object object, String id){
		Map<String, Object> map = createMap(id, object);

		dbClient.setGsonBuilder(new GsonBuilder().serializeNulls());// para criar todos os atributos do json, mesmo sem valores iniciais
		
		if(dbClient.contains(id)){
			JsonObject json = dbClient.find(JsonObject.class, id);
			map.put(REV, json.get(REV));
			dbClient.update(map);
			
		}else{
			dbClient.save(map);
		}
		
		return false;
	}
	
	public CouchDbClient createCouchDbClient(String dbName){
		CouchDbClient dbClient = new CouchDbClient(dbName, true, Constant.PROTOCOL, Constant.COUCH_HOST, Constant.COUCH_PORT, Constant.COUCH_USER, Constant.COUCH_PASSWORD);
		
		return dbClient;
	}
	
	public void shutdownCouchDbClient(CouchDbClient dbClient){
		dbClient.shutdown();
	}
	
	public Map<String, Object> createMap(String id, Object object){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ID, id);
		map.put(OBJECT, object);
		
		return map;
	}	
}