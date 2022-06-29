package sex_shop.sex_shop_kenji_rander.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// Qualquer um pode fazer requisições para essas URLs
				.antMatchers("/css/**", "/js/**" ).permitAll()
				// Um usuário autenticado e com o papel ADMIN pode fazer requisições para essas URLs	
//				.antMatchers("/", "/index.html").hasRole("ADMIN")
				.antMatchers("/", "/index.html").hasAnyRole("ADMIN", "USUARIO")
				.and()
			// A autenticação usando formulário está habilitada 
				.formLogin()
				// Uma página de login customizada
				.loginPage("/login")
				// Define a URL para o caso de falha no login
				//.failureUrl("/login-error");
				.and()
				// Para fazer logout (já é configurado automaticamente para a URL /logout)
				// Só está habilitado aqui para mudarmos a URL de sucesso do logout
				.logout()
					// Define a URL para o caso do usuário fazer logout. O padrão é /login
					.logoutSuccessUrl("/");
	}
	
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//				.withUser("kenji").password("{noop}12345").roles("ADMIN")
//				.and()
//				.withUser("rander").password("{noop}12345").roles("USUARIO");
//				//.and()grosbilda
//				//.withUser("outro").password("12345").roles("ADMIN", "USUARIO");
//	}
	

	
	//Autenticacao JDBC
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select usuario, senha, ativo "
					+ "from usuario "
					+ "where usuario = ?")
			.authoritiesByUsernameQuery("SELECT tab.usuario, papel.nome from" + 
				"(SELECT usuario.usuario, usuario.codigo from usuario where usuario = ? ) as tab " + 
				" inner join usuario_papel on codigo_usuario = tab.codigo \n" + 
				" inner join papel on codigo_papel = papel.codigo;");
	}

}
