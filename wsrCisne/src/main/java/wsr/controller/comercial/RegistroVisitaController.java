package wsr.controller.comercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import wsr.exception.JPAException;
import wsr.model.comercial.RegistroVisita;
import wsr.service.comercial.RegistroVisitaService;

@RestController
public class RegistroVisitaController {
	
	@Autowired
	private RegistroVisitaService service;
	
	@RequestMapping(value = "/registro_visita_atual", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RegistroVisita findRegistroVisitaAtualByCliente(@RequestParam(value="idCliente") Long idCliente, @RequestParam(value="mesAno") String mesAno){
		return service.montarRegistroVisita(idCliente, mesAno);
	}
	
	@RequestMapping(value = "/registro_visita", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RegistroVisita findRegistroVisitaByCliente(@RequestParam(value="idCliente") Long idCliente, @RequestParam(value="mesAno") String mesAno, 
			@RequestParam(value = "dataVisita") String dataVisita){
		return service.findRegistroVisita(idCliente, mesAno, dataVisita);
	}
	
	@RequestMapping(value = "/registro_visita/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void incluir(@RequestBody RegistroVisita registroVisita){
		try {
			service.incluir(registroVisita);
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/registro_visita/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void alterar(@RequestBody RegistroVisita registroVisita){
		try {
			service.alterar(registroVisita);
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
}