package br.com.sp.intranet.controller.arquivos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lowagie.text.DocumentException;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.controller.administrador.LoginController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsSetor;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.upload.UploadArquivo;
import br.com.sp.intranet.model.upload.UploadPasta;
import br.com.sp.intranet.service.administrador.SetorService;
import br.com.sp.intranet.service.administrador.UsuarioService;
import br.com.sp.intranet.service.arquivo.EmailArquivo;
import br.com.sp.intranet.service.arquivo.UploadArquivoService;
import br.com.sp.intranet.service.arquivo.UploadPastaService;
import br.com.sp.intranet.util.DataTypes;
import br.com.sp.intranet.util.GenericEmail;


@Controller
@Scope("view")
public class SistemaDeArquivosController extends GenericController {
	
	private static final long serialVersionUID = 5999161685241713053L;

	@Autowired
	private UploadPastaService uploadPastaService;
	
	@Autowired
	private UploadArquivoService uploadArquivoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SetorService setorService;
	
	@Autowired
	private LoginController login;
	
	private PastaModel listaPasta;
	
	private UploadPasta [] listaPastaSelecionada;
	private UploadPasta uploadPastaSelecionada;
	private UploadPasta uploadPasta = new UploadPasta();
	
	private UploadArquivo[] arquivosSelecionados;
	private UploadArquivo uploadArquivo = new UploadArquivo();
	private UploadArquivo arquivoSelecionado;
	
	private EmailArquivo emailArquivo = new EmailArquivo();
	private EmailArquivo emailArquivoSelecionado;
	
	private List<UploadPasta> listaPastaFiltro;
	private List<UploadPasta> pasta = new ArrayList<UploadPasta>();
	private List<UploadPasta> listaPastaComp = new ArrayList<UploadPasta>();
	private List<UploadPasta> listAllPastas  = new ArrayList<UploadPasta>();
	
	private List<UploadArquivo> arquivo = new ArrayList<UploadArquivo>();
	private List<UploadArquivo> listaArquivoFiltro;
	
	
	private DualListModel<String> usuariosComp;
   	private DualListModel<String> setoresComp;
    private DualListModel<String> usuariosDestino;
	private DualListModel<String> setoresDestino;
	
	List<String> usuariosDestinoSource = new ArrayList<String>();  
    List<String> usuariosDestinoTarget = new ArrayList<String>();
    List<String> setoresDestinoSource = new ArrayList<String>();  
    List<String> setoresDestinoTarget = new ArrayList<String>();
    
    List<String> setoresCompSource = new ArrayList<String>();  
    List<String> setoresCompTarget = new ArrayList<String>();
    List<String> usuariosCompSource = new ArrayList<String>();  
    List<String> usuariosCompTarget = new ArrayList<String>();
	
	
    private Long idPastaSelecionada;
    
	private boolean isGestor = false;
	private boolean isGestorPastaPai = false;
	private boolean isInclusaoPasta;
	private boolean isInclusaoArquivo;
	private boolean isDestinatarioPara;
	
	private Boolean bloqueiaPasta = new Boolean(true);
	private Boolean bloqueiaArquivo = new Boolean(true);
	private Boolean bloqueiaArquivoSalvo = new Boolean(true);
	private Boolean bloqueiaSetor = new Boolean(true);
	
    private Date dataInicio;
	private Date dataFim;
	 
	private String corpoEmail;
	private String destinatario;
	private String cc;
	private String assunto;
	
	private CsUsuario usuario = new CsUsuario();
	
	private TreeNode root;
	private TreeNode selectedNode;

	private ArquivoModel listaArquivo;
    private CsSetor csSetor = new CsSetor();
	private StreamedContent imagemArquivo = new DefaultStreamedContent();
	

	public void init() {
	if (!FacesContext.getCurrentInstance().isPostback()) {
		
			usuario = findUsuarioLoggedByUsername(); 
			arquivoSelecionado = new UploadArquivo();
			
			try {
				setGestor(this.isGestor(usuario));
			} catch (JPAException e) {
				e.printStackTrace();
			}
			if(isGestor){
				setBloqueiaPasta(false);
				setBloqueiaArquivo(false);
			}
			
			try {
				setGestorPastaPai(this.isGestorPastaPai(usuario));
			} catch (JPAException e) {
				e.printStackTrace();
			}
			if(isGestorPastaPai){
				setBloqueiaSetor(false);
			}
			
			try {
				listAllPastas = listarTodasPastas();
			} catch (JPAException e) {
				e.printStackTrace();
			}
						
			try {
				this.montarTreeNode(listAllPastas);
			} catch (JPAException e) {
				e.printStackTrace();
			}
			this.setIdPastaSelecionada(null);
			setListAllPastas(listAllPastas);
	
			usuariosCompSource.add("");  
			usuariosComp = new DualListModel<String>(usuariosCompSource, usuariosCompTarget);
			
			setoresCompSource.add("");
			setoresComp = new DualListModel<String>(setoresCompSource, setoresCompTarget);
			
			usuariosDestinoSource.add("");  
			usuariosDestino = new DualListModel<String>(usuariosDestinoSource, usuariosDestinoTarget);
			
			setoresDestinoSource.add("");
			setoresDestino = new DualListModel<String>(setoresDestinoSource, setoresDestinoTarget);
		}
		
	}
	
	
	
	public boolean isGestor(CsUsuario usuario) throws JPAException {
		boolean isGestor = false;
		if(usuario.isGestor()){
			isGestor = true;
		}
		return isGestor;
	}
	
	public boolean isGestorPastaPai(CsUsuario usuario) throws JPAException{
		boolean isGestorPastaPai = false;
		List<UploadPasta> pasta = new ArrayList<UploadPasta>();
		pasta = this.uploadPastaService.findByColumnIsNull("superior", "0", "UploadPasta");
		for (UploadPasta uploadPasta : pasta) {
			if(uploadPasta.getCsSetor()!= null && uploadPasta.getCsSetor().getIdSetor() == usuario.getIdSetor()){
				isGestorPastaPai = true;
			}
		}
		return isGestorPastaPai;
	}
	

	
	public void prepararUsuariosComp() throws JPAException {
	
		UploadArquivo uploadArquivo = new UploadArquivo(); 
		uploadArquivo = this.uploadArquivoService.findById(arquivoSelecionado.getIdArquivo());
		
		List<CsUsuario> listaUsuario = this.usuarioService.findAll();
        usuariosCompSource = new ArrayList<String>();  
        usuariosCompTarget = new ArrayList<String>();
        
        for (CsUsuario csUsuario : listaUsuario) {
        	if(!csUsuario.getUsername().equals(usuario.getUsername())){
        		usuariosCompSource.add(csUsuario.getUsername());
        	}	
        }
        
        if(uploadArquivo != null){
        	String usuariosComp = DataTypes.parseNull(uploadArquivo.getUsuarioComp());
        	List<String> listUserTarget = this.getListTarget(usuariosComp);
        	usuariosCompSource.removeAll(listUserTarget);
        	usuariosCompTarget.addAll(listUserTarget);
        }
		
		usuariosComp = new DualListModel<String>(usuariosCompSource, usuariosCompTarget);
	}	
	
	
	
	public List<String> getListTarget(String campoComp){
		List<String> listTarget = new ArrayList<String>();
		String s[] = null;
		s = campoComp.split(";");
		for (int x=0; x < s.length; x++) {
			listTarget.add(s[x]);
		}
		return listTarget;
	}
	
	public void prepararAlteracao() throws JPAException {
		if(uploadPastaSelecionada.getCsSetor() != null){
			csSetor = new CsSetor();
			csSetor.setIdSetor(uploadPastaSelecionada.getCsSetor().getIdSetor());
			csSetor.setDescricao(uploadPastaSelecionada.getCsSetor().getDescricao());
			uploadPastaSelecionada.setCsSetor(csSetor);
		} else {
			uploadPastaSelecionada.setCsSetor(new CsSetor());
		}
		setUploadPasta(uploadPastaSelecionada);
		setInclusaoPasta(false);
	}
	
	public void prepararAlteracaoArquivo() throws JPAException{
		UploadArquivo uploadArquivo = new UploadArquivo();
		uploadArquivo = this.uploadArquivoService.findById(arquivoSelecionado.getIdArquivo());
		setUploadArquivo(uploadArquivo);
		this.isInclusaoArquivo = false;
		this.bloqueiaArquivoSalvo = false;
	}
	
	public List<String> getListTarget(String campoComp, List<CsSetor> listSetor){
		List<String> listTarget = new ArrayList<String>();
		
		String s[] = null;
		s = campoComp.split(";");
		
		for (int x=0; x < s.length; x++) {
			for (CsSetor csSetor : listSetor) {
				if(csSetor.getIdSetor().toString().equals(s[x])){
					listTarget.add(csSetor.getDescricao());
				}
			}
		}
		return listTarget;
	}
	
	public void prepararSetoresComp() throws JPAException{
		
		List<CsSetor> listaSetor = this.setorService.findAll();
		setoresCompSource = new ArrayList<String>();  
        setoresCompTarget = new ArrayList<String>();
        
        UploadArquivo uploadArquivo = new UploadArquivo(); 
		uploadArquivo = this.uploadArquivoService.findById(arquivoSelecionado.getIdArquivo());
        
        for (CsSetor csSetor : listaSetor) {
        	if(!csSetor.getIdSetor().equals(usuario.getIdSetor()) && !csSetor.getIdSetor().equals(new Long("3"))){
        		setoresCompSource.add(csSetor.getDescricao());
        	}
		}
        
        if(uploadArquivo != null){
        	String setoresComp = DataTypes.parseNull(uploadArquivo.getSetorComp());
        	List<String> listSetorTarget = this.getListTarget(setoresComp, listaSetor);
        	setoresCompSource.removeAll(listSetorTarget);
        	setoresCompTarget.addAll(listSetorTarget);
        }
        
        setoresComp = new DualListModel<String>(setoresCompSource, setoresCompTarget);
	}
	
	
	public void salvarUsuariosComp() {
		try {
			List<String> listUsuariosCompSelecionados = usuariosComp.getTarget();
			String usuariosComp = this.getStringTarget(listUsuariosCompSelecionados);
			uploadArquivo = this.uploadArquivoService.findById(uploadArquivo.getIdArquivo());
			uploadArquivo.setUsuarioComp(usuariosComp);
			this.uploadArquivoService.alterarArquivo(uploadArquivo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário compartilhado foi incluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public String getStringTarget(List<String> listTarget){
		String stringTarget = "";
		if(listTarget.size() == 1){
			stringTarget = listTarget.get(0);
		
		}else{
			for (int x = 0; x < listTarget.size(); x++) {
				if(x == listTarget.size()-1){
					stringTarget = stringTarget + listTarget.get(x);
				}else{
					stringTarget = stringTarget + listTarget.get(x)+";";
				}	
			}
		}
		return stringTarget;
	}
	
	public String getStringTargetEmailSetor(List<String> listTarget) throws JPAException{
		CsSetor setor= new CsSetor();
		String stringTarget = "";
		
		if(listTarget.size() == 1){
			setor = this.setorService.findByDescricao(listTarget.get(0));
			stringTarget = setor.getEmail();
		
		}else{
			for (int x = 0; x < listTarget.size(); x++) {
				setor = this.setorService.findByDescricao(listTarget.get(x));
				if(x == listTarget.size()-1){
					stringTarget = stringTarget + setor.getEmail();
				}else{
					stringTarget = stringTarget + setor.getEmail()+";";
				}	
			}
		}
		return stringTarget;
	}
	
	
	
	public void salvarInformacoes() throws JPAException {
    	if(arquivoSelecionado.getArquivo() != null){
    		UploadPasta uploadPasta = this.uploadPastaService.findById(uploadPastaSelecionada.getIdPasta());
    		arquivoSelecionado.setUploadPasta(uploadPasta);
    		arquivoSelecionado.setDtInclusao(new Date());
    		
    		if(isInclusaoArquivo){
    			this.uploadArquivoService.salvarArquivo(arquivoSelecionado);
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo incluído com Sucesso!", ""));
    		}else{
    			this.uploadArquivoService.alterarArquivo(arquivoSelecionado);
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo alterado com Sucesso!", ""));
    		}	
    		arquivo = this.uploadArquivoService.findByProperty("model.uploadPasta.idPasta", uploadPasta.getIdPasta());
    		ArquivoModel listaArquivo = new ArquivoModel(arquivo);
			setListaArquivo(listaArquivo);
			setListaArquivoFiltro(null);
			
    		setBloqueiaArquivoSalvo(false);
    		setArquivoSelecionado(uploadArquivo);
    		
    	}else{
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não há arquivo a ser gerado", ""));
    		
    	}
	}
    
    public void salvarArquivo(FileUploadEvent file) throws JPAException{
		if(file != null){
			try {
				if(isInclusaoArquivo){
					imagemArquivo = new DefaultStreamedContent(new ByteArrayInputStream(file.getFile().getContents()));
					uploadArquivo = new UploadArquivo();
					uploadArquivo.setNomeArquivo(file.getFile().getFileName());
					uploadArquivo.setTipoArquivo(file.getFile().getContentType());
					uploadArquivo.setArquivo(file.getFile().getContents());
				}else{
					uploadArquivo = this.uploadArquivoService.findById(arquivoSelecionado.getIdArquivo());
					uploadArquivo.setNomeArquivo(file.getFile().getFileName());
					uploadArquivo.setTipoArquivo(file.getFile().getContentType());
					uploadArquivo.setArquivo(file.getFile().getContents());
				}	

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Arquivo não foi carregado", ""));
		}	
		
	}
    
    public void atualizaPastaSuperior(UploadPasta pasta) throws JPAException{
    	uploadPasta.setSuperior(pasta.getIdPasta());
    }

	
	public void excluirArquivo(UploadArquivo arq) throws JPAException {
    	UploadArquivo arquivoAlvo = this.uploadArquivoService.findById(arq.getIdArquivo());
    	this.uploadArquivoService.excluirArquivo(arquivoAlvo);
    	arquivo = this.uploadArquivoService.findByProperty("model.uploadPasta.idPasta", this.getIdPastaSelecionada());
    	ArquivoModel listaArquivo = new ArquivoModel(arquivo);
		setListaArquivo(listaArquivo);
		setListaArquivoFiltro(null);
		
    	FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo excluído com Sucesso!", ""));
    	
    }
	
	
	public void visualizarPDFDb(UploadArquivo uploadArquivo) throws JPAException, IOException, DocumentException {
		System.out.println("abriu aquiii PDF");
		uploadArquivo = this.uploadArquivoService.findById(uploadArquivo.getIdArquivo());
		imagemArquivo = new DefaultStreamedContent(new ByteArrayInputStream(uploadArquivo.getArquivo()), uploadArquivo.getTipoArquivo().toString(), uploadArquivo.getNomeArquivo().toString());
	}
	
	
	
	public Long getIdPastaTreeNode(String idPasta) {
		
		String s1[] = null;  
        for(int i = 0; i <= idPasta.length(); i++){  
            s1 = idPasta.split(" -"); 
        }
        
        return new Long(s1[0]);
	}

	public List<Long> listarArquivoComp() throws JPAException{
		List<Long> listaArquivoComp = this.uploadArquivoService.findByLike("model.usuarioComp", "model.setorComp", usuario.getUsername().toString(), usuario.getIdSetor().toString());
		return listaArquivoComp;
	}
	
	public void redirecionaPaginaArquivos()  {
		try {
			System.out.println("entrou redireciona");
			FacesContext.getCurrentInstance().getExternalContext().redirect("arquivos.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onSelectPasta(NodeSelectEvent event) {
		List<Long> idArquivoComp = new ArrayList<Long>();
		try {
			
			arquivo = new ArrayList<UploadArquivo>();
			setListaArquivo(null);
			Long idPasta = null;
			//idPasta = implPasta.getIdPastaTreeNode(event.getTreeNode().toString());
			idPasta = this.getIdPastaTreeNode(event.getTreeNode().toString());
			
			this.uploadPastaService.findPastaBySuperior(idPasta);
			
			
			System.out.println("ID_PASTA: " + idPasta);
			this.idPastaSelecionada = idPasta;
			this.setUploadPastaSelecionada(this.uploadPastaService.findById(idPasta));
			
			if(isGestorPastaPai){
				listaPastaComp = new ArrayList<UploadPasta>();
			
			}else{
				/*listaArquivoComp = listarArquivoComp();*/
				idArquivoComp = listarArquivoComp();
				listaPastaComp = listarPastaComp(usuario);
			}	
			
			if(listaPastaComp.contains(uploadPastaSelecionada)){
				
				//List<UploadPasta> list = new ArrayList<UploadPasta>();
				//if(list.size() == 0) {
					//this.redirecionaPaginaArquivos();
				//}
				
				/*for (UploadArquivo uploadArquivo : listaArquivoComp) {
					if(uploadArquivo.getUploadPasta().getIdPasta().equals(idPasta)){
						arquivo.add(uploadArquivo);
					}	
				}*/
				List<UploadArquivo> arqTemp = this.findByPropertyObject("model.uploadPasta.idPasta", uploadPastaSelecionada.getIdPasta());
				for (UploadArquivo uploadArquivo : arqTemp) {
					if(idArquivoComp.contains(uploadArquivo.getIdArquivo())){
						arquivo.add(uploadArquivo);
					}
				}
				setBloqueiaArquivo(true);
			}else{
				arquivo = this.findByPropertyObject("model.uploadPasta.idPasta", uploadPastaSelecionada.getIdPasta());
				if(isGestor){
					setBloqueiaArquivo(false);
				}else{
					setBloqueiaArquivo(true);
				}	
			}
			ArquivoModel listaArquivo = new ArquivoModel(arquivo);
			setListaArquivo(listaArquivo);
			setListaArquivoFiltro(null);
			
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.update("itens:btnIncludArq");
			rc.update("dadosArquivo");	
			rc.update("tabelaArquivo");
			rc.update("dadosArquivo:tabelaArquivo");		

		} catch (JPAException e) {
			e.printStackTrace();
		}
	}
	
	public List<UploadArquivo> findByPropertyObject(String propertyName, Object value) throws JPAException{
		List<UploadArquivo> list = new ArrayList<UploadArquivo>();
		for (Object[] col: this.uploadArquivoService.findByPropertyObject(propertyName, value)) {
			UploadArquivo uploadArquivo = new UploadArquivo();
			uploadArquivo.setIdArquivo((Long)col[0]);
			uploadArquivo.setDescricao((String)col[1]);
			uploadArquivo.setUploadPasta((UploadPasta)col[2]);
			uploadArquivo.setDtInclusao((Date)col[3]);
			uploadArquivo.setNomeArquivo((String)col[4]);
			uploadArquivo.setTipoArquivo((String)col[4]);
			
			list.add(uploadArquivo);
		}
		 return list;
		
	}
	
	
public boolean isArquivoReferenciado(UploadPasta uploadPasta) throws JPAException{
		
		List<UploadArquivo> uploadArquivo;
		uploadArquivo = this.uploadArquivoService.findByProperty("uploadPasta", uploadPasta);
		
		if(uploadArquivo.isEmpty()){
			return false;
		}else{
			return true;
		}	
	}

	public void montaTreeNode()throws JPAException{
		listAllPastas = listarTodasPastas();
		montarTreeNode(listAllPastas);
	}
	
	public void excluirPasta(UploadPasta uploadPasta){
		try {
			if(this.isArquivoReferenciado(uploadPasta)){
				FacesMessage msg = new FacesMessage( FacesMessage.SEVERITY_ERROR, "A Pasta não pode ser excluída! Existem arquivos referenciados.", "" );
		        FacesContext.getCurrentInstance().addMessage(null, msg);
			
			} else {
				UploadPasta uploadPastaAlvo;
				uploadPastaAlvo = this.uploadPastaService.findById(uploadPasta.getIdPasta());
				this.uploadPastaService.excluirPasta(uploadPastaAlvo);
				montaTreeNode();
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "A Pasta foi excluída com Sucesso!" , "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		
		} catch (JPAException e) {
			e.printStackTrace();
		}	
	}
	
	public void prepararAssistenteSuperior() throws JPAException{
		pasta = new ArrayList<UploadPasta>();
		pasta = this.uploadPastaService.listaTodos();
		setPasta(pasta);
	}
	
	
	public void prepararInclusaoArquivo() throws Exception{
		/*ExportarUploadArquivo asda = new ExportarUploadArquivo();
		asda.exportar();*/
		System.out.println("idPastaSelecionada noew "  + idPastaSelecionada);
		if(this.idPastaSelecionada == null){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não é possível incluir Arquivos! Nenhuma pasta foi selecionada!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			
		} else{
			uploadArquivo = new UploadArquivo();
			setBloqueiaArquivoSalvo(true);
			setInclusaoArquivo(true);
		}
	}
	
	
	public List<UploadPasta> listarTodasPastas() throws JPAException{
		return this.uploadPastaService.listaTodos();
	}
	
	public void prepararPastas() throws JPAException {
		
		List<UploadPasta> listaPastas = new ArrayList<UploadPasta>();
		if(!isGestorPastaPai){
			listaPastas = this.getHierarquiaPastaSetor(usuario);
		} else {
			listaPastas = listarTodasPastas();
		}	
		PastaModel lista = new PastaModel(listaPastas);
		
		setListaPasta(lista);
		setListaPastaFiltro(null);
		setListaPastaSelecionada(null);
	}
	
	public List<UploadPasta> getHierarquiaPastaSetor(CsUsuario usuario) throws JPAException{
		List<UploadPasta> listHierarquiaPastaSetor = new ArrayList<UploadPasta>();
		
		/*for (UploadPasta uploadPastasSetor : pastas) {
			if(uploadPastasSetor.getCsSetor() != null && uploadPastasSetor.getCsSetor().getIdSetor().equals(usuario.getIdSetor()) && uploadPastasSetor.getSuperior() == null ){
				listHierarquiaPastaSetor.addAll(pastas);
				break;
			}
			if(uploadPastasSetor.getCsSetor() != null && uploadPastasSetor.getCsSetor().getIdSetor().equals(usuario.getIdSetor())){
				listHierarquiaPastaSetor.add(uploadPastasSetor);
				
				for (UploadPasta uploadPastasAbaixoSetor : pastas) {
					if(DataTypes.parseNull(uploadPastasAbaixoSetor.getSuperior()).equals(DataTypes.parseNull(uploadPastasSetor.getIdPasta()))){
						listHierarquiaPastaSetor.add(uploadPastasAbaixoSetor);
						
						for (UploadPasta uploadPastasAbaixoSetor2 : pastas) {
							if(DataTypes.parseNull(uploadPastasAbaixoSetor2.getSuperior()).equals(DataTypes.parseNull(uploadPastasAbaixoSetor.getIdPasta()))){
								listHierarquiaPastaSetor.add(uploadPastasAbaixoSetor2);
							}
						}
					}
				}
			}	
		}*/
		listHierarquiaPastaSetor = this.uploadPastaService.findByProperty("model.csSetor.idSetor", usuario.getIdSetor());
		return listHierarquiaPastaSetor;
	}
	
	public void montarTreeNode(List<UploadPasta> listAllPastas) throws JPAException{
		
		root = this.montaTreeNode(listAllPastas, usuario);
	}
	
	public List<UploadPasta> listarPastaComp (CsUsuario usuario) throws JPAException{
		List<UploadPasta> listaPastaComp = this.listarPastaComp2(usuario);
		return listaPastaComp;
	}
	
	public TreeNode montaTreeNode(List<UploadPasta> lista, CsUsuario usuario) throws JPAException {
		TreeNode root = new DefaultTreeNode("root", null);
		TreeNode nodePai;
		TreeNode nodeFilho;
		TreeNode nodeFilho2;
		TreeNode nodeFilho3;
		TreeNode nodeFilho4;
		Long idPastaRaiz = null;
		
		List<UploadPasta> listaPastaComp = listarPastaComp(usuario);
		for (UploadPasta uploadPastaPai : lista) {
			boolean isUsuarioPastaPai  = false;
			// Pastas Topo
			if (DataTypes.isNull(uploadPastaPai.getSuperior()) || uploadPastaPai.getSuperior() == 0) {
				idPastaRaiz = uploadPastaPai.getIdPasta();
				//Controlando permissao de setor
				if(uploadPastaPai.getCsSetor() != null && usuario.getIdSetor().equals(uploadPastaPai.getCsSetor().getIdSetor())){
					isUsuarioPastaPai = true;
				}	
				nodePai = new DefaultTreeNode(uploadPastaPai.getIdPasta()+ " - " +uploadPastaPai.getDescricao(), root);
				//Pastas Filho 1
				for (UploadPasta uploadPastaFilho : lista) {
					if (DataTypes.parseNull(uploadPastaFilho.getSuperior()).equals(DataTypes.parseNull(uploadPastaPai.getIdPasta()))) {
						
						//Controlando permissao de setor caso o Usuário que esteja acessando nÃ£o seja do setor da pasta pai
						if(!isUsuarioPastaPai){
							if(uploadPastaFilho.getCsSetor() != null && usuario.getIdSetor().equals(uploadPastaFilho.getCsSetor().getIdSetor())){
								nodeFilho = new DefaultTreeNode(uploadPastaFilho.getIdPasta() + " - " + uploadPastaFilho.getDescricao(), nodePai);
								
								//Pastas Filho 2
								for (UploadPasta uploadPastaFilho2 : lista) {
									if (DataTypes.parseNull(uploadPastaFilho2.getSuperior()).equals(DataTypes.parseNull(uploadPastaFilho.getIdPasta()))) {
										nodeFilho2 = new DefaultTreeNode(uploadPastaFilho2.getIdPasta() + " - " + uploadPastaFilho2.getDescricao(),nodeFilho);
										
										//Pastas Filho 3
										for (UploadPasta uploadPastaFilho3 : lista) {
											if(DataTypes.parseNull(uploadPastaFilho3.getSuperior()).equals(DataTypes.parseNull(uploadPastaFilho2.getIdPasta()))){
												nodeFilho3 = new DefaultTreeNode(uploadPastaFilho3.getIdPasta() + " - " + uploadPastaFilho3.getDescricao(), nodeFilho2);
												
												for (UploadPasta uploadPastaFilho4 : lista) {
													if(DataTypes.parseNull(uploadPastaFilho4.getSuperior()).equals(DataTypes.parseNull(uploadPastaFilho3.getIdPasta()))){
														nodeFilho4 = new DefaultTreeNode(uploadPastaFilho4.getIdPasta() + " - " + uploadPastaFilho4.getDescricao(), nodeFilho3);
													}	
												}
											}
										}
									}
								}
							
							}else{
								//Caso a pasta de segundo nivel nÃ£o tenha permissao de setor, entÃ£o verificar se hÃ¡ permissao de arquivo complementar
								if(listaPastaComp.contains(uploadPastaFilho)){
									for (UploadPasta uploadPastaComp : listaPastaComp) {
										if(DataTypes.parseNull(uploadPastaComp.getSuperior()).equals(idPastaRaiz)){
											nodeFilho = new DefaultTreeNode(uploadPastaComp.getIdPasta() + " - " + uploadPastaComp.getDescricao(), nodePai);
											
											for (UploadPasta uploadPastaComp2 : listaPastaComp) {
												if(DataTypes.parseNull(uploadPastaComp2.getSuperior()).equals(DataTypes.parseNull(uploadPastaComp.getIdPasta()))){
													nodeFilho2 = new DefaultTreeNode(uploadPastaComp2.getIdPasta() + " - " + uploadPastaComp2.getDescricao(), nodeFilho);
													
													for (UploadPasta uploadPastaComp3 : listaPastaComp) {
														if(DataTypes.parseNull(uploadPastaComp3.getSuperior()).equals(DataTypes.parseNull(uploadPastaComp2.getIdPasta()))){
															nodeFilho3 = new DefaultTreeNode(uploadPastaComp3.getIdPasta() + " - " + uploadPastaComp3.getDescricao(), nodeFilho2);
															
															for (UploadPasta uploadPastaComp4 : listaPastaComp) {
																if(DataTypes.parseNull(uploadPastaComp4.getSuperior()).equals(DataTypes.parseNull(uploadPastaComp3.getIdPasta()))){
																	nodeFilho4 = new DefaultTreeNode(uploadPastaComp4.getIdPasta() + " - " + uploadPastaComp4.getDescricao(), nodeFilho3);
																}	
															}
														}
													}
												}	
											}
										}	
									}
								}	
							}
						} else {
							//se o usuario que esta acessando for da pasta topo, entÃ£o nÃ£o Ã© necessÃ¡rio validar o setor para as pastas abaixo.
							nodeFilho = new DefaultTreeNode(uploadPastaFilho.getIdPasta() + " - " +uploadPastaFilho.getDescricao(), nodePai);
							// Pastas Filho 2
							for (UploadPasta uploadPastaFilho2 : lista) {
								if (DataTypes.parseNull(uploadPastaFilho2.getSuperior()).equals(DataTypes.parseNull(uploadPastaFilho.getIdPasta()))) {
									nodeFilho2 = new DefaultTreeNode(uploadPastaFilho2.getIdPasta() + " - " + uploadPastaFilho2.getDescricao(),nodeFilho);
									//Pastas Fiho 3
									for (UploadPasta uploadPasta3 : lista) {
										if(DataTypes.parseNull(uploadPasta3.getSuperior()).equals(DataTypes.parseNull(uploadPastaFilho2.getIdPasta()))){
											nodeFilho3 = new DefaultTreeNode(uploadPasta3.getIdPasta() + " - " + uploadPasta3.getDescricao(), nodeFilho2);
											
											for (UploadPasta uploadPasta4 : lista) {
												if(DataTypes.parseNull(uploadPasta4.getSuperior()).equals(DataTypes.parseNull(uploadPasta3.getIdPasta()))){
													nodeFilho4 = new DefaultTreeNode(uploadPasta4.getIdPasta() + " - " + uploadPasta4.getDescricao(), nodeFilho3);
												}
											}	
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return root;
	}
	
	public List<Long> findByLikeIdPasta(String propertyName, String propertyName2, String value, String value2) throws JPAException{
		return this.uploadArquivoService.findByLikeIdPasta(propertyName, propertyName2, value, value2);
	}
	
	public List<UploadPasta> listarPastaComp2(CsUsuario usuario) throws JPAException {
		UploadPasta pasta = new UploadPasta();
		List<UploadPasta> listaPastaComp = new ArrayList<UploadPasta>();
		
		/*List<UploadArquivo> listaArquivoComp = new ArrayList<UploadArquivo>();
		listaArquivoComp = implArq.findByLike("model.usuarioComp", "model.setorComp", usuario.getUsuario().toString(), usuario.getIdSetor().toString());*/
		List<Long> listaIdPastaComp = new ArrayList<Long>();
		listaIdPastaComp = this.uploadArquivoService.findByLikeIdPasta("model.usuarioComp", "model.setorComp", usuario.getUsername().toString(), usuario.getIdSetor().toString());
		
		for (Long long1 : listaIdPastaComp) {
			pasta = this.uploadPastaService.findById(long1);
			
			if (!listaPastaComp.contains(pasta)) {
				if (pasta.getSuperior() != null) {
					listaPastaComp.add(pasta);
					pasta = this.uploadPastaService.findById(pasta.getSuperior());
					if (!listaPastaComp.contains(pasta)) {
						if (pasta.getSuperior() != null) {
							listaPastaComp.add(pasta);
							pasta = this.uploadPastaService.findById(pasta.getSuperior());
							if(!listaPastaComp.contains(pasta)){
								if(pasta.getSuperior() != null){
									listaPastaComp.add(pasta);
									pasta = this.uploadPastaService.findById(pasta.getSuperior());
									listaPastaComp.add(pasta);
								}else{
									listaPastaComp.add(pasta);
								}		
							}
							
						} else {
							listaPastaComp.add(pasta);
						}
					}
				} else {
					listaPastaComp.add(pasta);
				}
			}
			/*listaPastaComp.add(pasta);*/
		}
		return listaPastaComp;
	}
	
	public void salvarSetoresComp(){
		try {
			List<CsSetor> listaSetor = this.setorService.findAll();
			List<String> listSetoresCompSelecionados = setoresComp.getTarget();
			String setoresComp = this.getStringTarget(listSetoresCompSelecionados, listaSetor);
			uploadArquivo = this.uploadArquivoService.findById(uploadArquivo.getIdArquivo());
			uploadArquivo.setSetorComp(setoresComp);
			this.uploadArquivoService.alterarArquivo(uploadArquivo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Setor compartilhado foi incluído com Sucesso!" , "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		
		} catch (JPAException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void prepararInclusao() throws JPAException{
		uploadPasta = new UploadPasta();
		uploadPasta.setCsSetor(new CsSetor());
		setUploadPasta(uploadPasta);
		isInclusaoPasta = true;
	}
	
	public String getStringTarget(List<String> listTarget, List<CsSetor> listSetor){
		String stringTarget = "";
		for (int x = 0; x < listTarget.size(); x++) {
			for (CsSetor csSetor : listSetor) {
				if(csSetor.getDescricao().equalsIgnoreCase(listTarget.get(x))){
					stringTarget = stringTarget + csSetor.getIdSetor().toString()+";";
				}
			}
		}
		return stringTarget;
	}
	
	public void selecionarArquivosMail() {
		emailArquivo.setCorpoEmail(this.montaEmail(arquivosSelecionados));
		//System.out.println("emailArquivo " + emailArquivo);
		setEmailArquivo(emailArquivo);
	}
	
	public void onRowSelect(SelectEvent event) {
	    System.out.println("row selected, " + event.getObject());
	    System.out.println("arquivosSelecionados " + arquivosSelecionados) ;
	}
	
	
	public String montaEmail(UploadArquivo[] arquivosSelecionados){
		StringBuffer email = new StringBuffer();
		email.append("Prezados(as) Colaboradores(as),");
		email.append("<br>");
		email.append("<br>");
		email.append("<br>");
		email.append("Os Arquivos abaixo foram incluídos/alterados na intranet para visualização. Gestores, favor verificar a abrangência dos documentos, caso implique em sua área implementar e garantir o cumprimento do procedimento, entregando o Registro de Treinamento ao RH até o dia");
		email.append("<br>");
		email.append("<br>");
		email.append("<ul>");
		
		for (UploadArquivo uploadArquivo: arquivosSelecionados) {
			email.append("<li>");
			email.append("<b>"+uploadArquivo.getDescricao() +"</b>");
			email.append("</li>");
		}
		email.append("</ul>");
		email.append("<br>");
		email.append("<br>");
		email.append("Atenciosamente, Equipe SGI");
		email.append("<br>");
		email.append("<a href=http://www.salcisne.info>www.salcisne.info</a>");
		
		return email.toString();
	}
	
	public void prepararDestinatarioPara() throws JPAException{
		setDestinatarioPara(true);
		prepararDestinatario();
	}
	
	public void prepararDestinatarioCC() throws JPAException{
		setDestinatarioPara(false);
		prepararDestinatario();
	}
	
	public void prepararDestinatario() throws JPAException {

		List<CsSetor> listaSetor = this.setorService.findAll();
		setoresDestinoSource = new ArrayList<String>();
		setoresDestinoTarget = new ArrayList<String>();
		
		for (CsSetor csSetor : listaSetor) {
        	setoresDestinoSource.add(csSetor.getDescricao().toString());
		}
		setoresDestino = new DualListModel<String>(setoresDestinoSource, setoresDestinoTarget);
		
		List<CsUsuario> listaUsuario = this.usuarioService.findAll();
		usuariosDestinoSource = new ArrayList<String>();
		usuariosDestinoTarget = new ArrayList<String>();
		
		for (CsUsuario csUsuario : listaUsuario) {
        	usuariosDestinoSource.add(csUsuario.getUsername());
		}
		usuariosDestino = new DualListModel<String>(usuariosDestinoSource,usuariosDestinoTarget);
	}
	
	public void prepararMail() throws JPAException{
		Date hoje = new Date();
		List<UploadPasta>listaPastasSetor = this.uploadPastaService.findByProperty("model.csSetor.idSetor", usuario.getIdSetor());
		setDataInicio(hoje);
		setDataFim(hoje);
		setListaArquivo( this.getModelListArquivo(isGestorPastaPai, usuario, hoje, hoje, listaPastasSetor) );
	}
	
	public ArquivoModel getModelListArquivo(boolean isGestorPastaPai, CsUsuario usuario, Date dataInicio, Date dataFim, List<UploadPasta> listaPastasSetor) throws JPAException{
		List<UploadArquivo> listaArquivos = new ArrayList<UploadArquivo>();
		StringBuffer pastasWhere = new StringBuffer();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","BR"));
		String dataInicioStr = dateFormat.format(dataInicio);
		String dataFimStr = dateFormat.format(dataFim);
		
		if(isGestorPastaPai){
			listaArquivos = this.uploadArquivoService.findByDateIN("model.dtInclusao", dataInicioStr, dataFimStr, null, null);
		
		}else{
			Iterator it = listaPastasSetor.iterator();
			int i = 0;
			
			while(it.hasNext()){
				UploadPasta uploadPasta = (UploadPasta) it.next();
				
				if(i == 0){
					pastasWhere.append("(");
				}
				pastasWhere.append(uploadPasta.getIdPasta().toString());
				if(it.hasNext()){
					if(i == 100) {
						i=-1;
						pastasWhere.append(") ");
						pastasWhere.append("OR model.uploadPasta.idPasta IN ");
					}else{
						pastasWhere.append(",");
					}	
				}else{
					pastasWhere.append(")");
				}
				i += 1;
			}
			
			listaArquivos = this.uploadArquivoService.findByDateIN("model.dtInclusao", dataInicioStr, dataFimStr, "model.uploadPasta.idPasta", pastasWhere.toString());
			
		}	
		
		ArquivoModel lista = new ArquivoModel( listaArquivos );
		
		return lista;
	}
	
	public void selecionarDestinatarioUsuario() throws JPAException{
		List<String> listUsuariosDestinoSelecionados = usuariosDestino.getTarget();
		if(isDestinatarioPara){
			emailArquivo.setDestinatario(DataTypes.isNull(emailArquivo.getDestinatario()) ? this.getStringTargetEmailUsuario(listUsuariosDestinoSelecionados) : 
			emailArquivo.getDestinatario() + ";" + this.getStringTargetEmailUsuario(listUsuariosDestinoSelecionados));
		}else{
			emailArquivo.setCc(DataTypes.isNull(emailArquivo.getCc()) ? this.getStringTargetEmailUsuario(listUsuariosDestinoSelecionados) : 
			emailArquivo.getCc() + ";" + this.getStringTargetEmailUsuario(listUsuariosDestinoSelecionados));
		}	
			
		setEmailArquivo(emailArquivo);
	}
	
	public String getStringTargetEmailUsuario(List<String> listTarget) throws JPAException{
		//UsuarioFacade usuarioFacade = new UsuarioFacade();
		CsUsuario usuario = new CsUsuario();
		String stringTarget = "";
		
		if(listTarget.size() == 1){
			usuario = this.usuarioService.findById(listTarget.get(0));
			stringTarget = usuario.getEmail();
		
		}else{
			for (int x = 0; x < listTarget.size(); x++) {
				usuario = this.usuarioService.findById(listTarget.get(x));
				if(x == listTarget.size()-1){
					stringTarget = stringTarget + usuario.getEmail();
				}else{
					stringTarget = stringTarget + usuario.getEmail()+";";
				}	
			}
		}
		return stringTarget;
	}
	
	public void selecionarDestinatarioSetor() throws JPAException{
		List<String> listSetoresDestinoSelecionados = setoresDestino.getTarget();
		/*if(isDestinatarioPara){
			emailArquivo.setDestinatario(DataTypes.isNull(emailArquivo.getDestinatario()) ? implArq.getStringTargetEmailSetor(listSetoresDestinoSelecionados) : 
			emailArquivo.getDestinatario() + ";" + implArq.getStringTargetEmailSetor(listSetoresDestinoSelecionados));
		}else{
			emailArquivo.setCc(DataTypes.isNull(emailArquivo.getCc()) ? implArq.getStringTargetEmailSetor(listSetoresDestinoSelecionados) : 
			emailArquivo.getCc() + ";" + implArq.getStringTargetEmailSetor(listSetoresDestinoSelecionados));
		}*/
		
		if(isDestinatarioPara){
			for (int x = 0; x < listSetoresDestinoSelecionados.size(); x++) {
				CsSetor setor = this.setorService.findByDescricao(listSetoresDestinoSelecionados.get(x));
				List<CsUsuario> usuarios = new ArrayList<CsUsuario>();
				usuarios = this.usuarioService.findByProperty("model.idSetor", setor.getIdSetor());
				
				for (CsUsuario csUsuario : usuarios) {
					if(!DataTypes.isNull(csUsuario.getEmail())){
						emailArquivo.setDestinatario(DataTypes.isNull(emailArquivo.getDestinatario()) ? csUsuario.getEmail() : 
							emailArquivo.getDestinatario() + ";" + csUsuario.getEmail());
					}	
				}
			}
		}else{
			for (int x = 0; x < listSetoresDestinoSelecionados.size(); x++) {
				CsSetor setor = this.setorService.findByDescricao(listSetoresDestinoSelecionados.get(x));
				List<CsUsuario> usuarios = new ArrayList<CsUsuario>();
				usuarios = this.usuarioService.findByProperty("model.idSetor", setor.getIdSetor());
				
				for (CsUsuario csUsuario : usuarios) {
					emailArquivo.setCc(DataTypes.isNull(emailArquivo.getCc()) ? csUsuario.getEmail() : 
					emailArquivo.getCc() + ";" + csUsuario.getEmail());
				}
			}
			
		}
		setEmailArquivo(emailArquivo);
	}
	
	
	public void enviarEmail(){
		try {
			GenericEmail email = new GenericEmail(emailArquivo.getEmailOrigem(), emailArquivoSelecionado.getAssunto(), emailArquivoSelecionado.getCorpoEmail());
			List<String> listDestinatarioTarget = this.getListTarget(emailArquivoSelecionado.getDestinatario());
			
			for (int x = 0; x < listDestinatarioTarget.size(); x++) {
				email.adicionaDestinatario(listDestinatarioTarget.get(x));
			}
			
			List<String> listDestinatarioCC = this.getListTarget(emailArquivoSelecionado.getCc());
			
			for (int x = 0; x < listDestinatarioCC.size(); x++) {
				email.adicionaDestinatarioCopia(listDestinatarioCC.get(x));
			}

			if(email.enviarEmail()){
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso!" , "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no envio, verifique o endereço de E-mail... " , "");
				FacesContext.getCurrentInstance().addMessage(null, msg);
				
			}	
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void pesquisarArquivosData() throws JPAException{
		List<UploadPasta>listaPastasSetor = this.uploadPastaService.findByProperty("model.csSetor.idSetor", usuario.getIdSetor());
		setListaArquivo(this.getModelListArquivo(isGestorPastaPai, usuario, dataInicio, dataFim, listaPastasSetor));
		setListaArquivoFiltro(null);
		setArquivosSelecionados(null);
	}
	

	public UploadPastaService getUploadPastaService() {
		return uploadPastaService;
	}

	public void setUploadPastaService(UploadPastaService uploadPastaService) {
		this.uploadPastaService = uploadPastaService;
	}

	public PastaModel getListaPasta() {
		return listaPasta;
	}

	public void setListaPasta(PastaModel listaPasta) {
		this.listaPasta = listaPasta;
	}

	public boolean isGestorPastaPai() {
		return isGestorPastaPai;
	}

	public void setGestorPastaPai(boolean isGestorPastaPai) {
		this.isGestorPastaPai = isGestorPastaPai;
	}

	public CsUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(CsUsuario usuario) {
		this.usuario = usuario;
	}

	public UploadPasta[] getListaPastaSelecionada() {
		return listaPastaSelecionada;
	}

	public void setListaPastaSelecionada(UploadPasta[] listaPastaSelecionada) {
		this.listaPastaSelecionada = listaPastaSelecionada;
	}

	public List<UploadPasta> getListaPastaFiltro() {
		return listaPastaFiltro;
	}

	public void setListaPastaFiltro(List<UploadPasta> listaPastaFiltro) {
		this.listaPastaFiltro = listaPastaFiltro;
	}

	public UploadArquivoService getUploadArquivoService() {
		return uploadArquivoService;
	}

	public void setUploadArquivoService(UploadArquivoService uploadArquivoService) {
		this.uploadArquivoService = uploadArquivoService;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}


	public Long getIdPastaSelecionada() {
		return idPastaSelecionada;
	}


	public void setIdPastaSelecionada(Long idPastaSelecionada) {
		this.idPastaSelecionada = idPastaSelecionada;
	}


	public UploadArquivo getUploadArquivo() {
		return uploadArquivo;
	}


	public void setUploadArquivo(UploadArquivo uploadArquivo) {
		this.uploadArquivo = uploadArquivo;
	}


	public Boolean getBloqueiaArquivoSalvo() {
		return bloqueiaArquivoSalvo;
	}


	public void setBloqueiaArquivoSalvo(Boolean bloqueiaArquivoSalvo) {
		this.bloqueiaArquivoSalvo = bloqueiaArquivoSalvo;
	}


	public boolean isInclusaoPasta() {
		return isInclusaoPasta;
	}


	public void setInclusaoPasta(boolean isInclusaoPasta) {
		this.isInclusaoPasta = isInclusaoPasta;
	}


	public boolean isInclusaoArquivo() {
		return isInclusaoArquivo;
	}


	public void setInclusaoArquivo(boolean isInclusaoArquivo) {
		this.isInclusaoArquivo = isInclusaoArquivo;
	}


	public boolean isDestinatarioPara() {
		return isDestinatarioPara;
	}


	public void setDestinatarioPara(boolean isDestinatarioPara) {
		this.isDestinatarioPara = isDestinatarioPara;
	}


	public ArquivoModel getListaArquivo() {
		return listaArquivo;
	}


	public void setListaArquivo(ArquivoModel listaArquivo) {
		this.listaArquivo = listaArquivo;
	}


	public List<UploadArquivo> getArquivo() {
		return arquivo;
	}


	public void setArquivo(List<UploadArquivo> arquivo) {
		this.arquivo = arquivo;
	}


	public UploadPasta getUploadPastaSelecionada() {
		return uploadPastaSelecionada;
	}


	public void setUploadPastaSelecionada(UploadPasta uploadPastaSelecionada) {
		this.uploadPastaSelecionada = uploadPastaSelecionada;
	}

	public List<UploadPasta> getListaPastaComp() {
		return listaPastaComp;
	}

	public void setListaPastaComp(List<UploadPasta> listaPastaComp) {
		this.listaPastaComp = listaPastaComp;
	}

	public List<UploadPasta> getListAllPastas() {
		return listAllPastas;
	}

	public void setListAllPastas(List<UploadPasta> listAllPastas) {
		this.listAllPastas = listAllPastas;
	}

	public Boolean getBloqueiaPasta() {
		return bloqueiaPasta;
	}

	public void setBloqueiaPasta(Boolean bloqueiaPasta) {
		this.bloqueiaPasta = bloqueiaPasta;
	}

	public Boolean getBloqueiaArquivo() {
		return bloqueiaArquivo;
	}

	public void setBloqueiaArquivo(Boolean bloqueiaArquivo) {
		this.bloqueiaArquivo = bloqueiaArquivo;
	}

	public Boolean getBloqueiaSetor() {
		return bloqueiaSetor;
	}

	public void setBloqueiaSetor(Boolean bloqueiaSetor) {
		this.bloqueiaSetor = bloqueiaSetor;
	}

	public boolean isGestor() {
		return isGestor;
	}

	public void setGestor(boolean isGestor) {
		this.isGestor = isGestor;
	}

	public List<UploadArquivo> getListaArquivoFiltro() {
		return listaArquivoFiltro;
	}

	public void setListaArquivoFiltro(List<UploadArquivo> listaArquivoFiltro) {
		this.listaArquivoFiltro = listaArquivoFiltro;
	}

	public UploadArquivo getArquivoSelecionado() {
		return arquivoSelecionado;
	}

	public void setArquivoSelecionado(UploadArquivo arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public StreamedContent getImagemArquivo() {
		return imagemArquivo;
	}

	public void setImagemArquivo(StreamedContent imagemArquivo) {
		this.imagemArquivo = imagemArquivo;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public SetorService getSetorService() {
		return setorService;
	}

	public void setSetorService(SetorService setorService) {
		this.setorService = setorService;
	}

	public UploadPasta getUploadPasta() {
		return uploadPasta;
	}

	public void setUploadPasta(UploadPasta uploadPasta) {
		this.uploadPasta = uploadPasta;
	}

	public List<String> getSetoresCompSource() {
		return setoresCompSource;
	}

	public void setSetoresCompSource(List<String> setoresCompSource) {
		this.setoresCompSource = setoresCompSource;
	}

	public List<String> getSetoresCompTarget() {
		return setoresCompTarget;
	}

	public void setSetoresCompTarget(List<String> setoresCompTarget) {
		this.setoresCompTarget = setoresCompTarget;
	}

	public List<String> getUsuariosCompSource() {
		return usuariosCompSource;
	}

	public void setUsuariosCompSource(List<String> usuariosCompSource) {
		this.usuariosCompSource = usuariosCompSource;
	}

	public List<String> getUsuariosCompTarget() {
		return usuariosCompTarget;
	}

	public void setUsuariosCompTarget(List<String> usuariosCompTarget) {
		this.usuariosCompTarget = usuariosCompTarget;
	}

	public DualListModel<String> getUsuariosComp() {
		return usuariosComp;
	}

	public void setUsuariosComp(DualListModel<String> usuariosComp) {
		this.usuariosComp = usuariosComp;
	}

	public DualListModel<String> getSetoresComp() {
		return setoresComp;
	}

	public void setSetoresComp(DualListModel<String> setoresComp) {
		this.setoresComp = setoresComp;
	}

	public List<UploadPasta> getPasta() {
		return pasta;
	}

	public void setPasta(List<UploadPasta> pasta) {
		this.pasta = pasta;
	}


	public LoginController getLogin() {
		return login;
	}


	public void setLogin(LoginController login) {
		this.login = login;
	}


	public List<String> getUsuariosDestinoSource() {
		return usuariosDestinoSource;
	}


	public void setUsuariosDestinoSource(List<String> usuariosDestinoSource) {
		this.usuariosDestinoSource = usuariosDestinoSource;
	}


	public List<String> getUsuariosDestinoTarget() {
		return usuariosDestinoTarget;
	}


	public void setUsuariosDestinoTarget(List<String> usuariosDestinoTarget) {
		this.usuariosDestinoTarget = usuariosDestinoTarget;
	}


	public List<String> getSetoresDestinoSource() {
		return setoresDestinoSource;
	}


	public void setSetoresDestinoSource(List<String> setoresDestinoSource) {
		this.setoresDestinoSource = setoresDestinoSource;
	}


	public List<String> getSetoresDestinoTarget() {
		return setoresDestinoTarget;
	}


	public void setSetoresDestinoTarget(List<String> setoresDestinoTarget) {
		this.setoresDestinoTarget = setoresDestinoTarget;
	}


	public DualListModel<String> getUsuariosDestino() {
		return usuariosDestino;
	}


	public void setUsuariosDestino(DualListModel<String> usuariosDestino) {
		this.usuariosDestino = usuariosDestino;
	}


	public DualListModel<String> getSetoresDestino() {
		return setoresDestino;
	}


	public void setSetoresDestino(DualListModel<String> setoresDestino) {
		this.setoresDestino = setoresDestino;
	}


	public CsSetor getCsSetor() {
		return csSetor;
	}


	public void setCsSetor(CsSetor csSetor) {
		this.csSetor = csSetor;
	}



	public EmailArquivo getEmailArquivo() {
		return emailArquivo;
	}



	public void setEmailArquivo(EmailArquivo emailArquivo) {
		this.emailArquivo = emailArquivo;
	}



	public EmailArquivo getEmailArquivoSelecionado() {
		return emailArquivoSelecionado;
	}



	public void setEmailArquivoSelecionado(EmailArquivo emailArquivoSelecionado) {
		this.emailArquivoSelecionado = emailArquivoSelecionado;
	}



	public Date getDataInicio() {
		return dataInicio;
	}



	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}



	public Date getDataFim() {
		return dataFim;
	}



	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}



	public String getCorpoEmail() {
		return corpoEmail;
	}



	public void setCorpoEmail(String corpoEmail) {
		this.corpoEmail = corpoEmail;
	}



	public String getDestinatario() {
		return destinatario;
	}



	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}



	public String getCc() {
		return cc;
	}



	public void setCc(String cc) {
		this.cc = cc;
	}



	public String getAssunto() {
		return assunto;
	}



	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}



	public UploadArquivo[] getArquivosSelecionados() {
		return arquivosSelecionados;
	}



	public void setArquivosSelecionados(UploadArquivo[] arquivosSelecionados) {
		this.arquivosSelecionados = arquivosSelecionados;
	}
	

}
