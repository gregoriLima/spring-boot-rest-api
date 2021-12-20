package br.com.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.dto.TokenDTO;
import br.com.forum.config.security.TokenService;
import br.com.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
@Profile(value = {"prod","test"})  // só carrega esta classe em ambiente de produção e teste
public class AutenticacaoController {
    
    @Autowired
    private AuthenticationManager authManager;
	
    @Autowired
    private TokenService tokenService;
    
    // lógica de autenticação
    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){ // seguindo o padrão DTO
	
	UsernamePasswordAuthenticationToken dadosLogin = form.converter();
//	System.out.println(form.getEmail());
//	System.out.println(form.getSenha());
	
	try {
	    Authentication authenticate = authManager.authenticate(dadosLogin); // aqui vai os dados de login, este método recebe um objeto específico tipo UsernamePasswordAuthenticationToken
	    String token = tokenService.gerarToken(authenticate); System.out.println("\ntoken gerado");
	   
	    System.out.println("Token gerado:");
	    System.out.println(token);
	   
	   return ResponseEntity.ok(new TokenDTO(token, "Bearer"));// Retornando o token para o usuário e o tipo de autenticação para as próximas requisições
	
	} 
	catch (Exception e) {
	    e.printStackTrace();
	   return ResponseEntity.badRequest().build(); // retorna erro 400
	}
	
    }

}
