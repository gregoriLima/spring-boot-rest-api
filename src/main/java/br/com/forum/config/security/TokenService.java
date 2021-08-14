package br.com.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}") //from the configuration server's application.properties file
    private String expiration;

        
    @Value("${forum.jwt.secret}")
    private String secret;
    
    public String gerarToken(Authentication authenticate) {
	
	// Usuario usuarioLogado = (Usuario) authenticate.getPrincipal(); // recuperando o usuário logado
	
	Date hoje = new Date();
	
	Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
	
	return Jwts.builder() 
		.setIssuer("API do Fórum.") 
		.setSubject(authenticate.getName() + Math.random())
		.setIssuedAt(hoje)
		.setExpiration(dataExpiracao)
		.signWith(SignatureAlgorithm.HS256, secret)
		.compact();
    }

    
    
}
