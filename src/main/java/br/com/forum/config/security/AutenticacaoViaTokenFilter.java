package br.com.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Filter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.forum.config.security.TokenService;

// filtro utilizado para pegar o token e autorizar o cliente
// este filtro é registrado na classe SecurityConfigurations, filtros não são registrados no spring via annotations
// ele é registrado com o método .add().addFilterBefore()
// é necessário colocar no addFilterBefore() pois o spring já tem um filtro próprio de autenticação para a statefull autenticação
// então este filtro deve vir antes
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter { // filtro do Spring que é chamado uma vez só a cada requisição

    private TokenService tokenService;
    
    public AutenticacaoViaTokenFilter(TokenService tokenService) {

        this.tokenService = tokenService;
    }
    
    // neste método é pegado o token no cabeçalho, verificar se está ok e autenticar no spring
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException { // é necessário autenticar novamente o usuário com o token, por que  a autenticação é stateless, não guarda nenhuma informação do cliente
		
		
	// recuperando o token do cabeçalho
	String token = recuperarToken(request);
	
	System.out.println(token);
	
	filterChain.doFilter(request, response); // seguindo o fluxo da requisição
    }

private String recuperarToken(HttpServletRequest request) {
   
     String token = request.getHeader("Authorization");
     
     // verificando se o cabeçalho está correto:
     if(token == null || token.isEmpty() || !token.startsWith("Bearer "))
	 return null;
     
     return token.split(" ")[1]; // devolvendo só o token
}

}
