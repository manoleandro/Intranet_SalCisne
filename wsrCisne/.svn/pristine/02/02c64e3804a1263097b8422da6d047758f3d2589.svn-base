package wsr.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wsr.model.comercial.Cliente;
import wsr.service.comercial.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping("/clientes")
	public ResponseEntity<List<Long>> findAllIdCliente(){
		try {
			List<Long> clientes = service.findAllIdCliente();
			HttpHeaders header = new HttpHeaders();
			header.set("type", "clientes");
			
			return new ResponseEntity<List<Long>>(clientes, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping("/clientes_by_zona_vendas")
	public ResponseEntity<List<Cliente>> findByZonaVendas(@RequestParam(value = "zonaVendas") Long zonaVendas){
		try {
			List<Cliente> clientes = service.findByZonaVendas(zonaVendas);
			HttpHeaders header = new HttpHeaders();
			header.set("type", "clientes");
			
			return new ResponseEntity<List<Cliente>>(clientes, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}