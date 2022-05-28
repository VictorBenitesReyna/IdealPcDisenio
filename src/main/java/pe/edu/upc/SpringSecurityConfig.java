package pe.edu.upc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.serviceimplements.JpaUserDetailsService;
import pe.edu.upc.spring.auth.handler.LoginSucessHandler;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private LoginSucessHandler sucessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests().antMatchers("/race/**", "/css/**", "/js/**", "/img/**","/usuario/createAccount","/usuario/saveAccount").permitAll().anyRequest()
			.authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll().and()
			.exceptionHandling().accessDeniedPage("/error");

			/*http.authorizeRequests().antMatchers("/race/**").access("hasRole('ROLE_ADMIN')").antMatchers("/dueno/**")
					.access("hasRole('ROLE_ADMIN')").antMatchers("/pet/**")
					.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").antMatchers("/welcome/**")
					.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").and().formLogin()
					.successHandler(sucessHandler).loginPage("/login").loginProcessingUrl("/login")
					.defaultSuccessUrl("/welcome/bienvenido").permitAll().and().logout().logoutSuccessUrl("/login")
					.permitAll().and().exceptionHandling().accessDeniedPage("/error_403");*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {

		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}
}
