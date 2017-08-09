package wsr.controller.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import wsr.model.TypeRest;
import wsr.model.security.CsUsuario;
import wsr.service.security.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping("/usuarios")
	@ResponseBody
	public ResponseEntity<List<CsUsuario>> findUsuariosApp(){
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<List<CsUsuario>>(service.findUsuariosApp(), header, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuario")
	public ResponseEntity<TypeRest>findByUsername(@RequestParam(value = "username") String username){
		TypeRest type = new TypeRest("usuario", service.findByUsername(username));
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<TypeRest>(type, header, HttpStatus.OK);
	}
}