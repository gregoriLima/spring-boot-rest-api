package br.com.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.forum.config.security.AutenticacaoViaTokenFilter;
import br.com.forum.config.security.TokenService;
import br.com.forum.config.security.AutenticacaoService;

// configurando a segurança

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManager();
    }
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private AutenticacaoService autenticacaoService;
    
    // configurações de controle de acesso
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	
    }

    // configurações de acesso aos endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()  
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // definindo que não será utilizado session na autenticação
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class); // filtro para fazer a autenticação via jwt
    }
    
    // configurações de recursos estáticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        
    }
}
