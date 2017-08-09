package br.com.sp.intranet.controller.arquivos;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.sp.intranet.controller.GenericController;
import br.com.sp.intranet.controller.administrador.LoginController;
import br.com.sp.intranet.exception.JPAException;
import br.com.sp.intranet.model.administrador.CsUsuario;
import br.com.sp.intranet.model.upload.UploadPasta;
import br.com.sp.intranet.service.administrador.UsuarioService;
import br.com.sp.intranet.service.arquivo.UploadPastaService;

@FacesValidator("custom.sistemaArquivosValidator")
public class SistemaArquivosValidator implements Validator  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UploadPasta uploadPasta = new UploadPasta();
	
	@Autowired
	private LoginController login;
	
	@Autowired
	private UploadPastaService uploadPastaService;
	
	@Autowired
	private UsuarioService serviceUsuario;

	//private UploadPastaServiceImpl implPasta = UploadPastaServiceImpl.getInstance();
	
	List<UploadPasta> listaPasta = new ArrayList<UploadPasta>();
	List<UploadPasta> listaTodasPasta = new ArrayList<UploadPasta>();
	
	//private UsuarioFacade usuarioFacade = new UsuarioFacade();
	private CsUsuario usuario = new CsUsuario();
	
	boolean isPastaPermitida = false;
	
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
	
	public List<UploadPasta> getHierarquiaPastaSetor(CsUsuario usuario) throws JPAException {
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
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException{
		
		
		try {
			
			//FacesContext context       = FacesContext.getCurrentInstance();  
	    	//HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			//HttpSession httpSession    = request.getSession(true);
			//usuario = usuarioFacade.getUsuario(httpSession.getAttribute("usuario").toString());
			usuario = serviceUsuario.findById(login.getUsuario().getUsername());
			if(!this.isGestorPastaPai(usuario)){
				if(value==null){
					throw new ValidatorException (new FacesMessage(FacesMessage.SEVERITY_ERROR, "O campo Superior deve ser preenchido!",""));
				
				}else{
					
					uploadPasta = this.uploadPastaService.findById(new Long(value.toString()));
					if(uploadPasta != null){
						listaTodasPasta = this.uploadPastaService.listaTodos();
						listaPasta = this.getHierarquiaPastaSetor(usuario);
					
					
						for (UploadPasta uploadPastaPermisUser : listaPasta) {
							if(uploadPasta.getIdPasta().equals(uploadPastaPermisUser.getIdPasta())){
								isPastaPermitida = true;
							}
						}
						
						if(!isPastaPermitida){
							throw new ValidatorException (new FacesMessage(FacesMessage.SEVERITY_ERROR, "A pasta não pode ser incluída! O código não faz parte da sua estrutura!",""));
						}
					}	
				}
			}
			
		} catch (JPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}