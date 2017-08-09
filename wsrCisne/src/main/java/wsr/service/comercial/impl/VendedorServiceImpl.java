package wsr.service.comercial.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wsr.exception.JPAException;
import wsr.model.comercial.Cliente;
import wsr.model.comercial.Municipio;
import wsr.model.comercial.Vendedor;
import wsr.model.comercial.VendedorClienteByMunicipio;
import wsr.model.comercial.repository.ClienteRepository;
import wsr.model.comercial.repository.VendedorRepository;
import wsr.model.comercial.vo.ClientesByMunicipioByZonaVendasBean;
import wsr.service.comercial.VendedorService;
import wsr.util.DateUtils;

@Service
public class VendedorServiceImpl implements VendedorService{
	
	@Autowired
	private VendedorRepository repository;
	
	@Autowired
	private ClienteRepository repositoryCliente;
	
	@Override
	@Transactional("analistaTransactionManager")
	public List<Vendedor> findAll() throws JPAException{
		return repository.findAll();
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public Vendedor findByIdVendedor(Long idVendedor) throws JPAException{
		return repository.findByIdVendedor(idVendedor);
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public Vendedor findByZonaVendas(Long zonaVendas) throws JPAException{
		return repository.findByZonaVendas(zonaVendas);
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public VendedorClienteByMunicipio findClientesByMunicipioByZonaVendas(String mesAno, Long zonaVendas, String dia) {

		VendedorClienteByMunicipio retorno = new VendedorClienteByMunicipio();

		List<ClientesByMunicipioByZonaVendasBean> clientesByMunicipioByZonaVendasBean = repositoryCliente.findClientesByMunicipioByMes(mesAno, zonaVendas);
		
		/*List<ClientesByMunicipioByZonaVendasBean> clientesByMunicipioByZonaVendasBean = repositoryCliente.findClientesByMunicipioByDia(zonaVendas, DateUtils.criarData(dia, "dd/MM/yyyy"));*/
		
		// ClientesByMunicipio
		Map<Municipio, List<ClientesByMunicipioByZonaVendasBean>> clientesByMunicipio = clientesByMunicipioByZonaVendasBean
				.stream()
				.collect(Collectors.groupingBy(ClientesByMunicipioByZonaVendasBean::getMunicipio, Collectors.toList()));

		Iterator<Entry<Municipio, List<ClientesByMunicipioByZonaVendasBean>>> iteratorClientesByMunicipio = clientesByMunicipio
				.entrySet().iterator();
		
		if (!clientesByMunicipioByZonaVendasBean.isEmpty()) {
			// Montando retorno
			retorno.setCodZonaVendas(clientesByMunicipioByZonaVendasBean.get(0).getVendedor().getCodZonaVendas());
			retorno.setNomeVendedor(clientesByMunicipioByZonaVendasBean.get(0).getVendedor().getNomeVendedor());
			retorno.setIdVendedor(clientesByMunicipioByZonaVendasBean.get(0).getVendedor().getIdVendedor());
			retorno.setMesAno(mesAno);

			List<Municipio> municipios = new ArrayList<>();

			while (iteratorClientesByMunicipio.hasNext()) {
				Map.Entry<Municipio, List<ClientesByMunicipioByZonaVendasBean>> entryClientesByMunicipio = 
						(Map.Entry<Municipio, List<ClientesByMunicipioByZonaVendasBean>>) iteratorClientesByMunicipio.next();

				// montando municipios
				Municipio municipio = entryClientesByMunicipio.getKey();

				List<Cliente> clientes = new ArrayList<>();

				for (ClientesByMunicipioByZonaVendasBean clientesByMunicipioByZonaVendasBean2 : entryClientesByMunicipio.getValue()) {
					Cliente cliente = new Cliente(clientesByMunicipioByZonaVendasBean2.getCliente(), clientesByMunicipioByZonaVendasBean2.getDataVisita());
					clientes.add(cliente);
				}

				municipio.setClientes(clientes);
				municipios.add(municipio);
			}

			retorno.setMunicipios(municipios);
		}else{
			return null;
		}
		return retorno;
	}
	
	@Override
	@Transactional("analistaTransactionManager")
	public List<VendedorClienteByMunicipio> findListVendedorClienteByMunicipio(String mesAno) throws JPAException{
		List<VendedorClienteByMunicipio> list = new ArrayList<>();
		
		List<Vendedor> vendedores = this.findAll();
		
		for (Vendedor vendedor : vendedores) {
			VendedorClienteByMunicipio obj = new VendedorClienteByMunicipio();
			obj = this.findClientesByMunicipioByZonaVendas(mesAno, vendedor.getCodZonaVendas(), null);
			
			if(obj != null){
				list.add(obj);
			}	
		}
		return list;
	}
}