package wsr.controller.comercial;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import wsr.exception.JPAException;
import wsr.model.comercial.DespesaVisita;
import wsr.service.comercial.DespesaVisitaService;

@RestController
public class DespesaVisitaController {
	
	@Autowired
	private DespesaVisitaService service;
	
	@RequestMapping("/despesa_visita_by_mes")
	public ResponseEntity<List<DespesaVisita>> findDespesaVisitaByMes(@RequestParam(value="mesAno") String mesAno, @RequestParam(value="zonaVendas") Long zonaVendas){
		try {
			List<DespesaVisita> list = service.findDespesaVisitaByMes(mesAno, zonaVendas);
			HttpHeaders header = new HttpHeaders();
			header.set("type", "despesa_visita_by_mes");
			
			return new ResponseEntity<List<DespesaVisita>>(list, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/despesa_visita_by_dia_and_zona")
	public ResponseEntity<DespesaVisita> findDespesaVisitaByDiaAndZona(@RequestParam(value="dia") Date dia, @RequestParam(value="zonaVendas") Long zonaVendas){
		try {
			DespesaVisita despesaVisita = service.findByDiaAndZona(dia, zonaVendas);
			HttpHeaders header = new HttpHeaders();
			header.set("type", "despesa_visita_by_dia_and_zona");
			
			return new ResponseEntity<DespesaVisita>(despesaVisita, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/despesa_visita_by_id")
	public ResponseEntity<DespesaVisita> findDespesaVisitaById(@RequestParam(value="id") Long id){
		try {
			DespesaVisita despesaVisita = service.findById(id);
			HttpHeaders header = new HttpHeaders();
			header.set("type", "despesa_visita_by_id");
			
			return new ResponseEntity<DespesaVisita>(despesaVisita, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/despesa_visita/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public void alterar(@RequestBody DespesaVisita despesaVisita){
		try {
			service.salvar(despesaVisita);
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
}