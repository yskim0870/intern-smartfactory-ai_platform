package kr.smartfactory.platform.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import open.commons.spring.web.config.SpringfoxSwaggerWebSecurityCofigurer;

import kr.smartfactory.platform.web.security.UserLoginAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends SpringfoxSwaggerWebSecurityCofigurer {

	private UserLoginAuthenticationProvider provider;

	/**
	 * Desc : Constructor of WebSecurityConfig.java class
	 */
	@Autowired
	public WebSecurityConfig(UserLoginAuthenticationProvider provider) {
		this.provider = provider;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests()//
				.antMatchers("/").permitAll()//
				.antMatchers("/**").authenticated()//
				.and()//
				.formLogin()//
				.loginPage("/").loginProcessingUrl("/j_security_check").defaultSuccessUrl("/")//
				.successHandler(new SimpleUrlAuthenticationSuccessHandler())//
				.failureHandler(new SimpleUrlAuthenticationFailureHandler())//
				.usernameParameter("username")//
				.passwordParameter("password")//
				.and()//
				.logout().logoutUrl("/j_spring_security_logout")//
				.logoutSuccessUrl("/")//
				.and().sessionManagement().sessionFixation().newSession().invalidSessionUrl("/").maximumSessions(1)//
				.maxSessionsPreventsLogin(false);

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 자원에 대한 접근 허용
		web.ignoring().antMatchers("/static/**") //
				.antMatchers("/errors/**")//
				.antMatchers("/favicon.ico") //
		;
	}
}
