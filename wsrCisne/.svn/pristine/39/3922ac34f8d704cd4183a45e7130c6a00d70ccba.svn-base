package wsr.configuration;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import wsr.model.security.CsGrupo;
import wsr.model.security.CsServicoApp;
import wsr.model.security.CsUsuario;
import wsr.query.security.CsUsuarioQuery;
import wsr.service.security.ServicoAppService;
import wsr.service.security.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService serviceUsuario;

	@Autowired
	private ServicoAppService serviceServicoApp;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			/*montarPermissoesByUsuario(auth);*/
			auth.jdbcAuthentication()
			.usersByUsernameQuery(CsUsuarioQuery.USUARIO)
			.authoritiesByUsernameQuery(CsUsuarioQuery.USUARIO_AUTHORITIES)
			.dataSource(dataSource);
			/*.passwordEncoder(bCryptPasswordEncoder);*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void configure(HttpSecurity http) {
		try {
			this.montarConfiguracaoPermissoes(http);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void montarConfiguracaoPermissoes(HttpSecurity http) throws Exception {
		List<CsServicoApp> servicosApp = serviceServicoApp.findAll();

		for (CsServicoApp csServicoApp : servicosApp) {

			switch (csServicoApp.getTipoPermissao()) {
			case CONSULTA:
				http.httpBasic().and().authorizeRequests()
						.antMatchers(HttpMethod.GET, csServicoApp.getMapeamento())
						.hasAuthority(csServicoApp.getRegra()).and().csrf().disable();
				break;

			case ALTERACAO:
				http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.PUT, csServicoApp.getMapeamento())
						.hasAuthority(csServicoApp.getRegra()).and().csrf().disable();;
				break;

			case INCLUSAO:
				http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.POST, csServicoApp.getMapeamento())
						.hasAuthority(csServicoApp.getRegra()).and().csrf().disable();;
				break;

			case EXCLUSAO:
				http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.DELETE, csServicoApp.getMapeamento())
						.hasAuthority(csServicoApp.getRegra()).and().csrf().disable();;
				break;
			default:
				break;
			}
		}
	}

	public void montarPermissoesByUsuario(AuthenticationManagerBuilder auth) throws Exception {
		List<CsUsuario> usuarios = serviceUsuario.findUsuariosApp();

		for (CsUsuario csUsuario : usuarios) {
			auth.inMemoryAuthentication().withUser(csUsuario.getUsername()).password(csUsuario.getPassword())
					.roles(this.montarRoles(csUsuario));
		}
	}

	public String[] montarRoles(CsUsuario usuario) {
		String[] roles = null;

		for (CsGrupo grupo : usuario.getGrupos()) {
			roles = grupo.getServicosApp().stream().map(CsServicoApp::getRegra).toArray(String[]::new);
		}
		return roles;
	}
}