package wsr.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wsr.model.comercial.RegistroVisita;
import wsr.service.comercial.RelatorioVisitaService;

@RestController
public class RelatorioVisitaController {

	@Autowired
	private RelatorioVisitaService service;
	
	
	@RequestMapping(value = "/relatorio_visita", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RegistroVisita> findRegistroVisitaBymes(@RequestParam(value="zonaVendas") Long zonaVendas, @RequestParam(value="mesAno") String mesAno){
		return service.findRegistroVisitaByMes(zonaVendas, mesAno);
	}
}
