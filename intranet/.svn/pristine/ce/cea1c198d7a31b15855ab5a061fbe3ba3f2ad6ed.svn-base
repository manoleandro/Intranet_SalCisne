package br.com.sp.intranet.controller.administrador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.SendFailedException;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.controller.bean.cs.FaleConosco;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.evento.Foto;
import br.com.sp.intranet.model.externo.Cardapio;
import br.com.sp.intranet.model.externo.Contato;
import br.com.sp.intranet.model.externo.Externo;
import br.com.sp.intranet.model.externo.ExternoCardapio;
import br.com.sp.intranet.model.externo.ExternoGaleria;
import br.com.sp.intranet.model.externo.Galeria;
import br.com.sp.intranet.service.administrador.UsuarioService;
import br.com.sp.intranet.service.evento.FotoService;
import br.com.sp.intranet.util.DateUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

import org.json.JSONObject;
import org.json.XML;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class LoginController extends GenericController{
	
	private static final String URL_LOGIN = "/login.jsf?faces-redirect=true";
	private static final String URL_INICIO = "/inicio.jsf?faces-redirect=true";

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private FotoService implFo;
	private FaleConosco faleConosco;

	private Date dataCorrente = new Date(System.currentTimeMillis());

	private Date date = null;
	private List<Galeria> galerias;
	private List<Cardapio> cardapios;
	private List<Contato> aniverssarios, contatos;
	private Cardapio cardapioDia;
	private String strFiltro = "";
	private String galDesc;
	private String conteudoXML, conteudoXMLCardapio;
	private DateUtils dateUtils = DateUtils.getInstance();
	private List<Foto> fotos = new ArrayList<Foto>();

	private String tab1, tab2, tab3, tab4, tab5;
	private CsUsuario usuario;
	
	private String email;

	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			try {
				contatos = converteJsonParaJava().getContatos();
				cardapios = new ArrayList<Cardapio>();
				cardapios = converteJsonCardapioParaJava().getCardapios();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public LoginController() {

		usuario = new CsUsuario();
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();

			if (authentication instanceof Authentication) {
				usuario.setUsername(((User) authentication.getPrincipal()).getUsername());
			}
		}
	}

	public CsUsuario getUsuario() {
		try {
			usuario = new CsUsuario();

			SecurityContext context = SecurityContextHolder.getContext();
			if (context instanceof SecurityContext) {
				Authentication authentication = context.getAuthentication();

				if (authentication instanceof Authentication) {
					if (!authentication.getPrincipal().toString().equals("anonymousUser")) {
						usuario.setUsername(((User) authentication.getPrincipal()).getUsername());
						authentication.getAuthorities();
						usuarioService.atualizarUltimoAcesso(usuario.getUsername());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	public void enviarEmailRequisicaoAcesso() {
		try {
			usuarioService.enviarEmailRequisicao(email);
			createMessage(FacesMessage.SEVERITY_INFO, "email.sucesso");
			email = null;
		} catch (SendFailedException se) {
			se.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "email.erro.dominio");
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "email.erro");
		}
	}
	
	public void prepararEsqueciSenha(){
		usuario = null;
	}
	
	public String abrirPaginaInicio(){
		return URL_INICIO;
	}
	
	public String abrirPaginaLogin(){
		return URL_LOGIN;
	}

	public void filt(String desc) throws JPAException {
		setGalDesc(desc);
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public List<Galeria> montarGaleria() {

		try {
			converteJsonGaleriasParaJava();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			galerias = new ArrayList<Galeria>();
			for (Galeria g : converteJsonGaleriasParaJava().getGalerias()) {
				if (g.getExibe().equals("1")) {
					galerias.add(g);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return galerias;
	}

	public List<Cardapio> montarCardapio() throws IOException {

		int dia = dateUtils.retornaDiaDaSemana(new Date(System.currentTimeMillis()));

		try {

			for (Cardapio c : cardapios) {
				if (c.getDiaNumero().equals(String.valueOf(dia))) {
					c.setPratoDoDia(true);
					cardapioDia = c;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cardapios;
	}

	public List<Contato> montarAniversariantes() throws IOException {
		this.converteJsonParaJava();

		Map<String, Contato> unsortMap = new HashMap<String, Contato>();
		int count = 0;

		for (Contato it : this.converteJsonParaJava().getContatos()) {
			if (!it.getDate().equals(null) && it.getDate() != "" && it.getDate().length() > 0) {
				if (DateUtils.trataData(new Date(System.currentTimeMillis())).substring(3, 5)
						.equals(it.getDate().substring(3, 5))) {
					if (DateUtils.trataData(new Date(System.currentTimeMillis())).substring(0, 5)
							.equals(it.getDate().substring(0, 5))) {
						it.setMarcacao("color:blue;text-decoration: blink;");
						it.setAviso("background-color:BCE9FA;");
					}
					unsortMap.put(it.getDate() + count, it);
					count++;
				}

			}
		}

		aniverssarios = new ArrayList<Contato>();
		Map<String, Contato> treeMap = new TreeMap<String, Contato>(unsortMap);
		for (Map.Entry entry : treeMap.entrySet()) {
			aniverssarios.add((Contato) entry.getValue());
		}

		return aniverssarios;

	}

	public ExternoCardapio converteJsonCardapioParaJava() throws FileNotFoundException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/json/cardapio.json");
		Reader reader = new FileReader(caminho);
		ExternoCardapio externoCardapio = gson.fromJson(reader, ExternoCardapio.class);

		Collections.sort(externoCardapio.getCardapios());

		return externoCardapio;
	}

	public ExternoGaleria converteJsonGaleriasParaJava() throws FileNotFoundException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/json/galeria.json");
		Reader reader = new FileReader(caminho);
		ExternoGaleria externoGaleria = gson.fromJson(reader, ExternoGaleria.class);
		Collections.sort(externoGaleria.getGalerias());
		return externoGaleria;
	}

	public Externo converteJsonParaJava() throws FileNotFoundException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/json/contatos.json");
		Reader reader = new FileReader(caminho);
		Externo externo = gson.fromJson(reader, Externo.class);

		Collections.sort(externo.getContatos());

		return externo;
	}

	public void convertParaJson() {
		JSONObject xmlJSONObj = XML.toJSONObject(conteudoXML);
		String jsonPrettyPrintString = xmlJSONObj.toString(3);

		Writer output = null;
		File file = new File("C:/Users/jde.proj3/Desktop/contatos.json");

		try {
			System.out.println("Inicianco arquivo");
			output = new BufferedWriter(new FileWriter(file));
			output.write(xmlJSONObj.toString(1));
			output.close();
			System.out.println("Arquivo Finalizado!!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		/* System.out.println(jsonPrettyPrintString); */
	}

	public String getStrFiltro() {
		return strFiltro;
	}

	public void setStrFiltro(String strFiltro) {
		this.strFiltro = strFiltro;
	}

	public void filtrador() throws JPAException, IOException {
		this.montarContatos(strFiltro);
	}

	public List<Contato> montarContatos(String filtro) throws IOException {

		/*
		 * this.lerArquivoXML(); this.converteJsonParaJava();
		 */
		contatos = new ArrayList<Contato>();
		contatos = this.converteJsonParaJava().getContatos();

		if (getStrFiltro().equals("")) {
			contatos = this.converteJsonParaJava().getContatos();

		} else {
			List<Contato> contatosFilter = new ArrayList<>();
			contatosFilter = contatos.stream().filter(c -> c.getNome().toLowerCase().contains(filtro.toLowerCase()))
					.sorted().collect(Collectors.toList());

			contatos = contatosFilter;
		}

		return contatos;

	}

	public Date getDate() {
		date = new Date();
		return date;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		System.out.println("setStartDate:" + startDate);
		this.startDate = startDate;
	}

	private Date startDate;

	public void handleDateSelect(SelectEvent event) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date date = (Date) event.getObject();
		String s = format.format(date);
		System.out.println("::::::" + s);
	}

	public void gerarNovaSenha() {
		try {
			usuario = usuarioService.findById(usuario.getUsername());

			if (usuario != null) {
				usuarioService.updatePassword(usuario);
				createMessage(FacesMessage.SEVERITY_INFO, "senha.gerada.sucesso");
			} else {
				createMessage(FacesMessage.SEVERITY_ERROR, "usuario.nao.cadastrado");
			}

		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "senha.gerada.erro");
		}
	}

	public Date getDataCorrente() {
		return dataCorrente;
	}

	public void setDataCorrente(Date dataCorrente) {
		this.dataCorrente = dataCorrente;
	}

	public List<Foto> getFotos() throws JPAException {
		fotos = implFo.buscarPrincipal();
		return fotos;
	}

	public String getGalDesc() {
		return galDesc;
	}

	public void setGalDesc(String galDesc) {
		this.galDesc = galDesc;
	}

	public FaleConosco getFaleConosco() {
		return faleConosco;
	}

	public void setFaleConosco(FaleConosco faleConosco) {
		this.faleConosco = faleConosco;
	}

	public void onTabChange(TabChangeEvent event) {
		this.setTab1("faleconosco.xhtml");
	}

	public String getTab1() {
		return tab1;
	}

	public void setTab1(String tab1) {
		this.tab1 = tab1;
	}

	public String getTab2() {
		return tab2;
	}

	public void setTab2(String tab2) {
		this.tab2 = tab2;
	}

	public String getTab3() {
		return tab3;
	}

	public void setTab3(String tab3) {
		this.tab3 = tab3;
	}

	public String getTab4() {
		return tab4;
	}

	public void setTab4(String tab4) {
		this.tab4 = tab4;
	}

	public String getTab5() {
		return tab5;
	}

	public void setTab5(String tab5) {
		this.tab5 = tab5;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public FotoService getImplFo() {
		return implFo;
	}

	public void setImplFo(FotoService implFo) {
		this.implFo = implFo;
	}

	public List<Cardapio> getCardapios() {
		return cardapios;
	}

	public void setCardapios(List<Cardapio> cardapios) {
		this.cardapios = cardapios;
	}

	public List<Contato> getAniverssarios() {
		return aniverssarios;
	}

	public void setAniverssarios(List<Contato> aniverssarios) {
		this.aniverssarios = aniverssarios;
	}

	public Cardapio getCardapioDia() {
		return cardapioDia;
	}

	public void setCardapioDia(Cardapio cardapioDia) {
		this.cardapioDia = cardapioDia;
	}

	public String getConteudoXML() {
		return conteudoXML;
	}

	public void setConteudoXML(String conteudoXML) {
		this.conteudoXML = conteudoXML;
	}

	public String getConteudoXMLCardapio() {
		return conteudoXMLCardapio;
	}

	public void setConteudoXMLCardapio(String conteudoXMLCardapio) {
		this.conteudoXMLCardapio = conteudoXMLCardapio;
	}

	public DateUtils getDateUtils() {
		return dateUtils;
	}

	public void setDateUtils(DateUtils dateUtils) {
		this.dateUtils = dateUtils;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public void setUsuario(CsUsuario usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}