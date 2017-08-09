package com.mdSync.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.mdSync.service.comercial.RegistroVisitaServiceImpl;
import com.mdSync.service.comercial.VendedorClientesByMunicipioServiceImpl;
import com.mdSync.service.comercial.VendedoresServiceImpl;
import com.mdSync.service.security.UsuarioServiceImpl;

public class Sync {
	private UsuarioServiceImpl implUsuario = UsuarioServiceImpl.getInstance();
	private VendedoresServiceImpl implVendedor = VendedoresServiceImpl.getInstance();
	private VendedorClientesByMunicipioServiceImpl implVendedorClientesByMunicipio = VendedorClientesByMunicipioServiceImpl.getInstance();
	private RegistroVisitaServiceImpl implRegistroVisita = RegistroVisitaServiceImpl.getInstance();
	
	public boolean iniciar() throws Exception {
		
		System.out.println("Inicio Sync usuarios...");
		implUsuario.sincronizarUsuarios();
		System.out.println("Fim");
		
		System.out.println("");
		
		System.out.println("Inicio Sync vendedores...");
		implVendedor.sincronizarVendedores();
		System.out.println("Fim");
		
		/*System.out.println("");
		
		System.out.println("Inicio Sync vendedor_clientes_by_municipio...");
		for (int i = 0; i <= 2; i++) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
			LocalDate hoje = LocalDate.now();
			String mesAno = hoje.plusMonths(-i).format(formatter);
			
			System.out.println("Sincronizando mes..." + mesAno);
			implVendedorClientesByMunicipio.sincronizarVendedorClientesByMunicipio(mesAno);
		}
		System.out.println("Fim");*/
		
		System.out.println("");
		
		System.out.println("Inicio Sync registro_visita_atual..." + new Date(System.currentTimeMillis()));
		implRegistroVisita.sincronizarRegistrosVisita();
		System.out.println("Fim " + new Date(System.currentTimeMillis()));
		
		return true;
	}
}