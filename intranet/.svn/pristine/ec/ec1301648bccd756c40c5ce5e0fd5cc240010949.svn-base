package br.com.sp.intranet.controller.externo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.externo.Cardapio;
import br.com.sp.intranet.model.externo.Contato;
import br.com.sp.intranet.model.externo.Externo;
import br.com.sp.intranet.model.externo.ExternoCardapio;
import br.com.sp.intranet.model.externo.ExternoFotosCardapio;
import br.com.sp.intranet.model.externo.ExternoGaleria;
import br.com.sp.intranet.model.externo.FotosCardapio;
import br.com.sp.intranet.model.externo.Galeria;
import br.com.sp.intranet.service.externo.CardapioService;
import br.com.sp.intranet.service.externo.ContatoService;
import br.com.sp.intranet.service.externo.FotoCardapioService;
import br.com.sp.intranet.service.externo.GaleriaService;
import br.com.sp.intranet.util.PropertyUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@Scope("view")
public class ExternoController extends GenericController {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContatoService contatoService;
	@Autowired
	private CardapioService cardapioService;
	@Autowired
	private FotoCardapioService fotoCardapioService;
	@Autowired
	private GaleriaService galeriaService;

	private List<Contato> listContatos;
	private List<FotosCardapio> listFotosCardapio;
	private List<Galeria> listGalerias;
	private List<Cardapio> listCardapios;
	
	private Contato contatoSelecionado, contato;
	private Cardapio cardapio;
	private Galeria galeriaSelecionada, galeria;
	
	private FotosCardapio fotoCardapio;

	private boolean inclusao, inclusaoGaleria, imagemGaleriaEnviada;
	private Integer index;
	private UploadedFile file, fileGaleria;
	private String caminhoFotos;
	private File fotoGaleria;
	
	private Properties propriedades = PropertyUtils.getInstance("config");

	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			limparTela();
		}
	}
	
	public void limparTela() {
		listarContatos();
		listarGalerias();
		cardapio = new Cardapio();
		galeria = new Galeria();
		contatoSelecionado = new Contato();
		galeriaSelecionada = new Galeria();
		fotoCardapio = new FotosCardapio();
		caminhoFotos = propriedades.getProperty("caminho.arquivo.galeria");
		listarCardapios();
		listarFotosCardapio();
	}
	
	public void uploadFotoCardapio(FileUploadEvent e) throws IOException {

		UploadedFile uploadedPhoto = e.getFile();
		String filePath = propriedades.getProperty("caminho.arquivo.galeria"); //"c:/arquivo/";
		byte[] bytes = null;

		if (null != uploadedPhoto) {
			System.out.println("Enviando foto...");
			bytes = uploadedPhoto.getContents();
			String filename = uploadedPhoto.getFileName();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
			stream.write(bytes);
			stream.close();
			fotoCardapio.setLabelFoto(filename);
			fotoCardapio.setNomeFoto(filename);
			salvarFotoCardapio();
		}
		createMessage(FacesMessage.SEVERITY_INFO, "inclusao.foto");
	}
	
	
	public void uploadFotoGaleria(FileUploadEvent e) throws IOException {

		UploadedFile uploadedPhoto = e.getFile();
		String filePath = propriedades.getProperty("caminho.arquivo.galeria"); //"c:/arquivo/galeria/";
		byte[] bytes = null;

		if (null != uploadedPhoto) {
			System.out.println("Enviando foto...");
			bytes = uploadedPhoto.getContents();
			String filename = uploadedPhoto.getFileName();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + filename)));
			stream.write(bytes);
			stream.close();
			galeria.setIdFoto(filename);
			this.imagemGaleriaEnviada = true;
		}
		createMessage(FacesMessage.SEVERITY_INFO, "inclusao.foto");
	}

	public void salvarFotoCardapio() {
		try {
			fotoCardapioService.update(fotoCardapio, converteJsonParaJavaFotosCardapio());
			listarFotosCardapio();
			cardapio.setNomeFoto(fotoCardapio.getNomeFoto());
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.update("menuForm");
		} catch (JPAException e) {
			e.printStackTrace();
		}
	}

	public void listarCardapios() {
		listCardapios = converteJsonCardapioParaJava().getCardapios();
		Collections.sort(listContatos);
	}

	public void listarContatos() {
		listContatos = this.converteJsonParaJava().getContatos();
		Collections.sort(listContatos);
	}
	
	public void listarGalerias() {
		listGalerias = this.converteJsonGaleriasParaJava().getGalerias();
		Collections.sort(listGalerias);
	}
	
	public void listarFotosCardapio() {
		listFotosCardapio = this.converteJsonParaJavaFotosCardapio().getFotos();
		Collections.sort(listFotosCardapio);
	}

	public void prepararIncluir() {
		contato = new Contato();
		inclusao = true;
		abrirDialog();
	}

	public void prepararIncluirGaleria() {
		galeria = new Galeria();
		inclusaoGaleria = true;
		this.imagemGaleriaEnviada = false;
		abrirDialogGaleria();
	}
	
	public void prepararAlterarGaleria() {
		if (galeriaSelecionada != null) {
			galeria = galeriaSelecionada;
			galeriaSelecionada = null;
			inclusaoGaleria = false;
			imagemGaleriaEnviada = false;
			abrirDialogGaleria();
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}

	
	public void prepararAlterar() {
		if (contatoSelecionado != null) {
			contato = contatoSelecionado;
			contatoSelecionado = null;
			inclusao = false;
			abrirDialog();
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}

	public void prepararAlterarCardapio(Cardapio pCardapio) {
		cardapio = new Cardapio();
		cardapio = pCardapio;
		abrirDialogCardapio();
	}

	public ExternoCardapio converteJsonCardapioParaJava() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/json/cardapio.json");
		Reader reader = null;
		ExternoCardapio externoCardapio = new ExternoCardapio();
		try {
			reader = new FileReader(caminho);
			externoCardapio = gson.fromJson(reader, ExternoCardapio.class);
			reader.close();
			Collections.sort(externoCardapio.getCardapios());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return externoCardapio;
	}

	public Externo converteJsonParaJava() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/json/contatos.json");
		Reader reader = null;
		Externo externo = new Externo();
		try {
			reader = new FileReader(caminho);
			externo = gson.fromJson(reader, Externo.class);
			reader.close();
			Collections.sort(externo.getContatos());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return externo;
	}

	public ExternoFotosCardapio converteJsonParaJavaFotosCardapio() {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/json/fotoscardapio.json");
		Reader reader = null;
		ExternoFotosCardapio externo = new ExternoFotosCardapio();
		try {
			reader = new FileReader(caminho);
			externo = gson.fromJson(reader, ExternoFotosCardapio.class);
			reader.close();
			Collections.sort(externo.getFotos());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return externo;
	}

	public void createMessage(Severity severity, String messageKeyPropertie) {
		FacesMessage msg = new FacesMessage(severity, ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle()).getString(messageKeyPropertie), null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void abrirDialog() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialog-contato').show();");
	}
	
	public void abrirDialogGaleria() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialog-galeria').show();");
	}

	public void abrirDialogCardapio() {
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("PF('dialog-cardapio').show();");
	}

	public void salvar() throws JPAException {
		if (verificaCamposPreenchidos()) {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		} else {

			if (inclusao) {
				contatoService.save(contato, converteJsonParaJava());
				createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			} else {
				contatoService.update(contato, converteJsonParaJava());
				createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			}

			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dialog-contato').hide();");
			rc.execute("PF('tableMenu').clearFilters();");
			rc.execute("location.reload();");
		}
		listarContatos();
	}
	
	
	 public ExternoGaleria converteJsonGaleriasParaJava()  {

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String caminho = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/json/galeria.json");
			Reader reader = null;
			ExternoGaleria externoGaleria = new ExternoGaleria();
			try {
				reader = new FileReader(caminho);
				externoGaleria = gson.fromJson(reader, ExternoGaleria.class);
				reader.close();
				Collections.sort(externoGaleria.getGalerias());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return externoGaleria;
		}

	
	public void salvarGaleria() throws JPAException, FileNotFoundException {
		if (verificaCamposPreenchidosGaleria()) {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		} else {

			if (inclusaoGaleria) {
				//String idEvento = UUID.randomUUID().toString();
				//System.out.println("idEvento " + idEvento);
				//galeria.setIdEvento(idEvento);
				galeriaService.save(galeria, converteJsonGaleriasParaJava());
				createMessage(FacesMessage.SEVERITY_INFO, "incluir.sucesso");
			} else {
				galeriaService.update(galeria, converteJsonGaleriasParaJava());
				createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			}

			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dialog-galeria').hide();");
			rc.execute("PF('tableMenu').clearFilters();");
			rc.execute("location.reload();");
		}
		listarGalerias();
	}

	public void salvarCardapio() throws JPAException, FileNotFoundException {
		if (verificaCamposPreenchidosCardapio()) {
			createMessage(FacesMessage.SEVERITY_WARN, "incluir.alerta");
		} else {

			if (cardapio.getNomeFoto() == "novafoto") {
				if (fotoCardapio.getNomeFoto() != null && fotoCardapio.getNomeFoto() != "") {
					cardapio.setNomeFoto(fotoCardapio.getNomeFoto());
				} else {
					cardapio.setNomeFoto("semimagem.jpg");
				}
			}

			cardapioService.update(cardapio, converteJsonCardapioParaJava());
			createMessage(FacesMessage.SEVERITY_INFO, "alterar.sucesso");
			RequestContext rc = RequestContext.getCurrentInstance();
			rc.execute("PF('dialog-cardapio').hide();");
			rc.execute("location.reload();");
		}
		listarCardapios();
	}

	public void remover() {
		if (contatoSelecionado != null) {
			try {
				contatoService.delete(contatoSelecionado, converteJsonParaJava());
				createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
				listarContatos();
				contatoSelecionado = new Contato();
			} catch (JPAException e) {
				createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
				e.printStackTrace();
			}
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}
	
	
	public void removerGaleria() {
		if (galeriaSelecionada != null) {
			try {
				galeriaService.delete(galeriaSelecionada, converteJsonGaleriasParaJava());
				createMessage(FacesMessage.SEVERITY_INFO, "excluir.sucesso");
				listarGalerias();
				galeriaSelecionada = new Galeria();
			} catch (JPAException e) {
				createMessage(FacesMessage.SEVERITY_ERROR, "excluir.erro");
				e.printStackTrace();
			}
		} else {
			createMessage(FacesMessage.SEVERITY_ERROR, "registro.nao.selecionado");
		}
	}

	public boolean verificaCamposPreenchidos() {
		boolean camposVazios = false;

		if (this.contato.getNome() == null || this.contato.getNome().length() == 0) {
			camposVazios = true;
		}

		if (this.contato.getCidade() == null || this.contato.getCidade().length() == 0) {
			camposVazios = true;
		}

		if (this.contato.getSetor() == null || this.contato.getSetor().length() == 0) {
			camposVazios = true;
		}

		if (this.contato.getEmail() == null || this.contato.getEmail().length() == 0) {
			camposVazios = true;
		}

		if (this.contato.getRamal() == null || this.contato.getRamal().length() == 0) {
			camposVazios = true;
		}

		if (this.contato.getTelefone() == null || this.contato.getTelefone().length() == 0) {
			camposVazios = true;
		}

		if (this.contato.getDate() == null || this.contato.getDate().length() == 0) {
			camposVazios = true;
		}
		return camposVazios;
	}
	
	
	public boolean verificaCamposPreenchidosGaleria() {
		boolean camposVazios = false;

		if (this.galeria.getTitulo() == null || this.galeria.getTitulo().length() == 0) {
			camposVazios = true;
		}

		if (this.galeria.getData() == null || this.galeria.getData().length() == 0) {
			camposVazios = true;
		}

		if (this.galeria.getDescricao() == null || this.galeria.getDescricao().length() == 0) {
			camposVazios = true;
		}
		
		if (this.galeria.getExibe() == null || this.galeria.getExibe().length() == 0) {
			camposVazios = true;
		}
		
		if (this.galeria.getIdFoto() == null || this.galeria.getIdFoto().length() == 0) {
			camposVazios = true;
		}
		return camposVazios;
	}

	public boolean verificaCamposPreenchidosCardapio() {
		boolean camposCardapioVazios = false;

		if (this.cardapio.getTitulo() == null || this.cardapio.getTitulo().length() == 0) {
			camposCardapioVazios = true;
		}

		if (this.cardapio.getAcompanhamento() == null || this.cardapio.getAcompanhamento().length() == 0) {
			camposCardapioVazios = true;
		}

		if (this.cardapio.getGuarnicao() == null || this.cardapio.getGuarnicao().length() == 0) {
			camposCardapioVazios = true;
		}

		if (this.cardapio.getSalada() == null || this.cardapio.getSalada().length() == 0) {
			camposCardapioVazios = true;
		}

		if (this.cardapio.getSobremesa() == null || this.cardapio.getSobremesa().length() == 0) {
			camposCardapioVazios = true;
		}

		if (this.cardapio.getNomeFoto() == null || this.cardapio.getNomeFoto().length() == 0) {
			camposCardapioVazios = true;
		}
		return camposCardapioVazios;
	}
	
	
	
	
	public List<Contato> getListContatos() {
		return listContatos;
	}

	public void setListContatos(List<Contato> listContatos) {
		this.listContatos = listContatos;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public boolean isInclusao() {
		return inclusao;
	}

	public void setInclusao(boolean inclusao) {
		this.inclusao = inclusao;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public ContatoService getContatoService() {
		return contatoService;
	}

	public void setContatoService(ContatoService contatoService) {
		this.contatoService = contatoService;
	}

	public List<Cardapio> getListCardapios() {
		return listCardapios;
	}

	public void setListCardapios(List<Cardapio> listCardapios) {
		this.listCardapios = listCardapios;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public List<FotosCardapio> getListFotosCardapio() {
		return listFotosCardapio;
	}

	public void setListFotosCardapio(List<FotosCardapio> listFotosCardapio) {
		this.listFotosCardapio = listFotosCardapio;
	}

	public CardapioService getCardapioService() {
		return cardapioService;
	}

	public void setCardapioService(CardapioService cardapioService) {
		this.cardapioService = cardapioService;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public FotoCardapioService getFotoCardapioService() {
		return fotoCardapioService;
	}

	public void setFotoCardapioService(FotoCardapioService fotoCardapioService) {
		this.fotoCardapioService = fotoCardapioService;
	}

	public GaleriaService getGaleriaService() {
		return galeriaService;
	}

	public void setGaleriaService(GaleriaService galeriaService) {
		this.galeriaService = galeriaService;
	}

	public List<Galeria> getListGalerias() {
		return listGalerias;
	}

	public void setListGalerias(List<Galeria> listGalerias) {
		this.listGalerias = listGalerias;
	}

	public FotosCardapio getFotoCardapio() {
		return fotoCardapio;
	}

	public void setFotoCardapio(FotosCardapio fotoCardapio) {
		this.fotoCardapio = fotoCardapio;
	}

	public Galeria getGaleriaSelecionada() {
		return galeriaSelecionada;
	}

	public void setGaleriaSelecionada(Galeria galeriaSelecionada) {
		this.galeriaSelecionada = galeriaSelecionada;
	}

	public Galeria getGaleria() {
		return galeria;
	}

	public void setGaleria(Galeria galeria) {
		this.galeria = galeria;
	}

	public boolean isInclusaoGaleria() {
		return inclusaoGaleria;
	}

	public void setInclusaoGaleria(boolean inclusaoGaleria) {
		this.inclusaoGaleria = inclusaoGaleria;
	}

	public UploadedFile getFileGaleria() {
		return fileGaleria;
	}

	public void setFileGaleria(UploadedFile fileGaleria) {
		this.fileGaleria = fileGaleria;
	}

	public boolean isImagemGaleriaEnviada() {
		return imagemGaleriaEnviada;
	}

	public void setImagemGaleriaEnviada(boolean imagemGaleriaEnviada) {
		this.imagemGaleriaEnviada = imagemGaleriaEnviada;
	}

	public String getCaminhoFotos() {
		return caminhoFotos;
	}

	public void setCaminhoFotos(String caminhoFotos) {
		this.caminhoFotos = caminhoFotos;
	}

	public File getFotoGaleria() {
		return fotoGaleria;
	}

	public void setFotoGaleria(File fotoGaleria) {
		this.fotoGaleria = fotoGaleria;
	}


}