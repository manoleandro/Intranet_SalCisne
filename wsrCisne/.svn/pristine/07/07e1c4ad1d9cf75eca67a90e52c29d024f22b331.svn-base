package wsr.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wsr.model.comercial.RoteiroVisita;
import wsr.service.comercial.RoteiroVisitaService;

@RestController
public class RoteiroVisitaController {
	
	@Autowired
	private RoteiroVisitaService service;
	
	@RequestMapping(value = "/roteiro_visita_sugerido_by_zona_vendas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoteiroVisita>> findRoteiroSugeridoByZonaVendas(@RequestParam(value = "mesAno") String mesAno, @RequestParam(value = "zonaVendas") Long zonaVendas){
		try {
			List<RoteiroVisita> roteiroSugerido = service.findRoteiroVisitaSugeridoByZonaVendas(mesAno, zonaVendas);
			HttpHeaders header = new HttpHeaders();
			header.set("type", "roteiro_sugerido_by_zona_vendas");
			
			return new ResponseEntity<List<RoteiroVisita>>(roteiroSugerido, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/roteiro_visita_sugerido_by_cliente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RoteiroVisita> findRoteiroSugerido(@RequestParam(value = "mesAno") String mesAno, @RequestParam(value = "idCliente") Long idCliente){
		try {
			RoteiroVisita roteiroSugerido = service.findRoteiroVisitaSugeridoByCliente(mesAno, idCliente);
			HttpHeaders header = new HttpHeaders();
			header.set("type", "roteiro_sugerido_by_cliente");
			
			return new ResponseEntity<RoteiroVisita>(roteiroSugerido, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}