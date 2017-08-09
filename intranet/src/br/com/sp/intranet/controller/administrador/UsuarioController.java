package br.com.sp.intranet.controller.administrador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.exception.PasswordNotMachedException;
import br.com.sp.intranet.model.administrador.CsAutorizacao;
import br.com.sp.intranet.model.administrador.CsGrupo;
import br.com.sp.intranet.model.administrador.CsServico;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.administrador.vo.rh.Colaborador;
import br.com.sp.intranet.model.comercial.Vendedor;
import br.com.sp.intranet.service.administrador.AutorizacaoService;
import br.com.sp.intranet.service.administrador.GrupoService;
import br.com.sp.intranet.service.administrador.ServicoService;
import br.com.sp.intranet.service.administrador.UsuarioService;
import br.com.sp.intranet.service.administrador.colaborador.ColaboradorService;
import br.com.sp.intranet.service.comercial.VendedorService;

@SuppressWarnings("serial")
@Controller
@Scope("view")
public class UsuarioController extends GenericController {

	@Autowired
	private UsuarioService service;

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@Autowired
	private AutorizacaoService autorizacaoService;
	
	@Autowired
	private VendedorService serviceVendedor;

	private List<CsUsuario> usuarios;
	
	private List<Colaborador> listaColaborador;

	private CsUsuario usuario;
	private CsUsuario usuarioSelecionado;
	private CsUsuario usuarioLogin;

	private String senhaAtual;
	private String novaSenha;

	private DualListModel<CsGrupo> grupos;
	private List<CsGrupo> gruposSource;
	private List<CsGrupo> gruposTarget;

	private List<CsServico> servicos;
	private List<CsServico> servicosSelecionados;

	private List<CsAutorizacao> autorizacoes;
	private List<CsAutorizacao> autorizacoesSelecionadas;
	
	private List<Vendedor> vendedores;
	private List<Vendedor> vendedoresFiltro;
	private Vendedor vendedorSelecionado;

	private boolean inclusao;

	@PostConstruct
	public void init() {
		try {
			usuarioLogin = findUsuarioLoggedByUsername();
			usuarios = service.findAll();
			usuario = null;
			usuarioSelecionado = null;
			listaColaborador = new ArrayList<>();
			iniciarGrupos();
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public void selecionarVendedor(){
		if(vendedorSelecionado != null){
			usuario.setZonaVendas(vendedorSelecionado.getCodZonaVendas());
		}	
	}
	
	public void prepararVendedores(){
		vendedorSelecionado = null;
		try {
			vendedores = serviceVendedor.carregarVendedores(usuarioLogin);
			abrirDialog();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogAssistenteVendedores').show(); PF('tableVendedores').clearFilters()");
	}
	
	public void listarColaboradores() {
		try {
			System.out.println("listarColaboradores()");
			this.listaColaborador = this.colaboradorService.findColaboradorAtivo();
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarConsumoProgressivo(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost/consumoProgressivo/index.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void alterarSenha() {
		try {
			service.updateAndChangePassword(getUsuarioSelecionado(), senhaAtual, novaSenha);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.senha.sucesso");
			usuarioSelecionado = null;
		} catch (PasswordNotMachedException p) {
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.senha.diferente");

		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.senha.erro");
		}
	}

	public void salvarGrupo() {
		usuarioSelecionado.setGrupos(grupos.getTarget());
		alterar(usuarioSelecionado);
	}

	public void salvarServico() {
		usuarioSelecionado.setServicos(servicosSelecionados);
		alterar(usuarioSelecionado);
	}
	
	public void salvarAutorizacao(){
		usuarioSelecionado.setAutorizacoes(autorizacoesSelecionadas);
		alterar(usuarioSelecionado);
	}
	
	public void prepararAutorizacaoUsuario(){
		try{
			if(usuarioSelecionado != null){
				if(autorizacoes == null || autorizacoes.isEmpty()){
					autorizacoes = autorizacaoService.findAll();
				}
				
				autorizacoesSelecionadas = service.carregarAutorizacoes(usuarioSelecionado);
			}
			usuario = usuarioSelecionado;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void prepararServicoUsuario() {
		try {
			if (usuarioSelecionado != null) {
				if (servicos == null || servicos.isEmpty()) {
					servicos = servicoService.findAll();
				}

				servicosSelecionados = service.carregarServicos(usuarioSelecionado);
			}
			usuario = usuarioSelecionado;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepararGrupoUsuario() {
		try {
			if (usuarioSelecionado != null) {

				gruposSource = grupoService.findAll();

				gruposTarget = service.carregarGrupos(usuarioSelecionado);

				gruposSource.removeAll(gruposTarget);
			}
			grupos = new DualListModel<CsGrupo>(gruposSource, gruposTarget);

			usuario = usuarioSelecionado;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void iniciarGrupos() {
		gruposSource = new ArrayList<CsGrupo>();
		gruposTarget = new ArrayList<CsGrupo>();

		grupos = new DualListModel<CsGrupo>(gruposSource, gruposTarget);
	}

	public void abrirDialogUsuario() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialogUsuario').show();");
	}

	public void prepararIncluir() {
		iniciarGrupos();
		listarColaboradores();
		usuario = new CsUsuario();
		inclusao = true;
		abrirDialogUsuario();
	}

	public void prepararAlterar() {
		iniciarGrupos();
		listarColaboradores();
		if (usuarioSelecionado != null) {
			usuario = usuarioSelecionado;
			inclusao = false;
			abrirDialogUsuario();
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}

	public void salvar() {
		CsUsuario usuario = service.tratarPassword(usuarioSelecionado);

		if (inclusao)
			incluir(usuario);
		else
			alterar(usuario);
		init();
	}

	public void alterar(CsUsuario usuario) {
		try {
			service.update(usuario);
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "alterar.erro");
		}
	}

	public void incluir(CsUsuario usuario) {
		try {
			service.save(usuario);
			createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			init();
		} catch (Exception e) {
			e.printStackTrace();
			createMessage(FacesMessage.SEVERITY_ERROR, "incluir.erro");
		}
	}

	public void excluir() {
		if (usuarioSelecionado != null) {
			try {
				service.delete(usuarioSelecionado);
				createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
				init();
			} catch (JPAException e) {
				createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
				e.printStackTrace();
			}
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public List<CsUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<CsUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public CsUsuario getUsuario() {
		if(usuario == null) {
			usuario = new CsUsuario();
		}
		return usuario;
	}

	public void setUsuario(CsUsuario usuario) {
		this.usuario = usuario;
	}

	public CsUsuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(CsUsuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	public DualListModel<CsGrupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(DualListModel<CsGrupo> grupos) {
		this.grupos = grupos;
	}

	public List<CsGrupo> getGruposSource() {
		return gruposSource;
	}

	public void setGruposSource(List<CsGrupo> gruposSource) {
		this.gruposSource = gruposSource;
	}

	public List<CsGrupo> getGruposTarget() {
		return gruposTarget;
	}

	public void setGruposTarget(List<CsGrupo> gruposTarget) {
		this.gruposTarget = gruposTarget;
	}

	public List<CsServico> getServicos() {
		return servicos;
	}

	public void setServicos(List<CsServico> servicos) {
		this.servicos = servicos;
	}

	public List<CsServico> getServicosSelecionados() {
		return servicosSelecionados;
	}

	public void setServicosSelecionados(List<CsServico> servicosSelecionados) {
		this.servicosSelecionados = servicosSelecionados;
	}

	public List<CsAutorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<CsAutorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public List<CsAutorizacao> getAutorizacoesSelecionadas() {
		return autorizacoesSelecionadas;
	}

	public void setAutorizacoesSelecionadas(List<CsAutorizacao> autorizacoesSelecionadas) {
		this.autorizacoesSelecionadas = autorizacoesSelecionadas;
	}

	public List<Colaborador> getListaColaborador() {
		return listaColaborador;
	}

	public void setListaColaborador(List<Colaborador> listaColaborador) {
		this.listaColaborador = listaColaborador;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public List<Vendedor> getVendedoresFiltro() {
		return vendedoresFiltro;
	}

	public void setVendedoresFiltro(List<Vendedor> vendedoresFiltro) {
		this.vendedoresFiltro = vendedoresFiltro;
	}

	public Vendedor getVendedorSelecionado() {
		return vendedorSelecionado;
	}

	public void setVendedorSelecionado(Vendedor vendedorSelecionado) {
		this.vendedorSelecionado = vendedorSelecionado;
	}

	public CsUsuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(CsUsuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
}