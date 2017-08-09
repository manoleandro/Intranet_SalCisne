package wsr.controller.comercial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import wsr.exception.JPAException;
import wsr.model.comercial.Vendedor;
import wsr.model.comercial.VendedorClienteByMunicipio;
import wsr.service.comercial.VendedorService;

@RestController
public class VendedorController {
	
	@Autowired
	private VendedorService service;
	
	@RequestMapping(value = "/vendedor")
	public ResponseEntity<Vendedor>findByIdVendedor(@RequestParam(value = "idVendedor") Long idVendedor){
		Vendedor vendedor = null;
		HttpHeaders header = new HttpHeaders();
		header.set("type", "vendedor");
		try {
			vendedor = service.findByIdVendedor(idVendedor);
			return new ResponseEntity<Vendedor>(vendedor, header, HttpStatus.OK);
			
		} catch (JPAException e) {
			e.printStackTrace();
			return new ResponseEntity<Vendedor>(vendedor, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/vendedor_by_zona_vendas")
	public ResponseEntity<Vendedor>findByZonaVendas(@RequestParam(value = "zonaVendas") Long zonaVendas){
		Vendedor vendedor = null;
		HttpHeaders header = new HttpHeaders();
		header.set("type", "vendedor_by_zona_vendas");
		try {
			vendedor = service.findByZonaVendas(zonaVendas);
			return new ResponseEntity<Vendedor>(vendedor, header, HttpStatus.OK);
			
		} catch (JPAException e) {
			e.printStackTrace();
			return new ResponseEntity<Vendedor>(vendedor, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/vendedores")
	public ResponseEntity<List<Vendedor>> findByAll(){
		HttpHeaders header = new HttpHeaders();
		header.set("type", "vendedores");
		List<Vendedor> vendedores = null;
		try {
			vendedores =service.findAll();
			return new ResponseEntity<List<Vendedor>>(vendedores, header, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Vendedor>>(vendedores, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/vendedor_clientes_by_municipio")
	public ResponseEntity<VendedorClienteByMunicipio> findClientesMunicipioByZonaVendas(@RequestParam(value = "mesAno") String mesAno, @RequestParam(value = "zonaVendas") Long zonaVendas,
			@RequestParam(value="dia") String dia){
		
		HttpHeaders header = new HttpHeaders();
		header.set("type", "vendedor_clientes_by_municipio");
		VendedorClienteByMunicipio retorno = null;
		
		try {
			retorno = service.findClientesByMunicipioByZonaVendas(mesAno, zonaVendas, dia);
			return new ResponseEntity<VendedorClienteByMunicipio>(retorno, header, HttpStatus.OK);
			
		} catch (JPAException e) {
			e.printStackTrace();
			return new ResponseEntity<VendedorClienteByMunicipio>(retorno, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping("/vendedores_clientes_by_municipio")
	public ResponseEntity<List<VendedorClienteByMunicipio>> findClientesMunicipio(@RequestParam(value = "mesAno") String mesAno){
		HttpHeaders header = new HttpHeaders();
		header.set("type", "vendedor_clientes_by_municipio");
		List<VendedorClienteByMunicipio> retorno = null;
		
		try {
			retorno = service.findListVendedorClienteByMunicipio(mesAno);
			return new ResponseEntity<List<VendedorClienteByMunicipio>>(retorno, header, HttpStatus.OK);
			
		} catch (JPAException e) {
 			e.printStackTrace();
 			return new ResponseEntity<List<VendedorClienteByMunicipio>>(retorno, header, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}