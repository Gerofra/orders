package com.orders.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.orders.config.CustomAccessDeniedHandler;
import com.orders.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Lazy
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEnconder = new BCryptPasswordEncoder();
		return bCryptPasswordEnconder;
	}
	
	
    @Bean
    AccessDeniedHandler accessDeniedHandler() {
    	return new CustomAccessDeniedHandler();
    }
    
    

    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
    	auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
    }
    
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		
    	http.authorizeRequests()
    		.antMatchers("/", "/**", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll()
    		.antMatchers("/portal/").permitAll().anyRequest().authenticated()
    			.and().formLogin()
    				.loginPage("/").permitAll()
    					.loginProcessingUrl("/logincheck")
    					.failureUrl("/login?error=error").permitAll()
    					.usernameParameter("mail")
    					.passwordParameter("password")
    					.defaultSuccessUrl("/portal")
    				.and()
    					.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
    				.and()
    					.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    					.logoutSuccessUrl("/?logout").permitAll()
    					.deleteCookies("JSESSIONID")
    				.and()
    					.rememberMe().tokenValiditySeconds(3600000).key("secret").rememberMeParameter("checkRememberMe");
    
    }    
}