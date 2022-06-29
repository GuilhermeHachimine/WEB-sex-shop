package sex_shop.sex_shop_kenji_rander.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// Qualquer um pode fazer requisições para essas URLs
				.antMatchers("/css/**", "/js/**" ).permitAll()
				// Um usuário autenticado e com o papel ADMIN pode fazer requisições para essas URLs	
				.antMatchers("/", "/index.html").hasRole("ADMIN")
				//.antMatchers("URL").hasAnyRole("ADMIN", "USUARIO")
				.and()
			// A autenticação usando formulário está habilitada 
				.formLogin()
				// Uma página de login customizada
				.loginPage("/login");
				// Define a URL para o caso de falha no login
				//.failureUrl("/login-error");
	}

}
