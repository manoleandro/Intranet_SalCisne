package wsr.controller.comercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import wsr.exception.JPAException;
import wsr.model.comercial.Configuracao;
import wsr.service.comercial.ConfiguracaoService;

@RestController
public class ConfiguracaoController {

	@Autowired
	private ConfiguracaoService service;
	
	@RequestMapping("/configuracao")
	public ResponseEntity<Configuracao> find(){
		try {
			Configuracao configuracao = service.find();
			HttpHeaders header = new HttpHeaders();
			header.set("type", "configuracao");
			
			return new ResponseEntity<Configuracao>(configuracao, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/configuracao/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void salvar(@RequestBody Configuracao configuracao){
		try {
			service.update(configuracao);
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
}