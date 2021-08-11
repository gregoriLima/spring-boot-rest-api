package br.com.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// configurando a segurança

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    // configurações de controle de acesso
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    // configurações de acesso aos endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http.authorizeRequests()
		// neste método se diz quais urls devem ser filtradas e qual será o acesso, se será permitida ou bloqueada
		// .antMatchers("/topicos").permitAll();// neste caso, para o endpoit /topicos, é liberado todos os método e nada é bloqueado neste endpoint
		.antMatchers(HttpMethod.GET, "/topicos").permitAll() // permitindo tudo no endpoit /topicos para o método GET
		.antMatchers(HttpMethod.GET, "/topicos/*").permitAll(); // esse serão os endpoints públicos, pois não terão controle de acesso para o método GET

    }
    
    // configurações de recursos estáticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        
    }
}
