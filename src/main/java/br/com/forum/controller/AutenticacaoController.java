package br.com.forum.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
    
    
    // lógica de autenticação
    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form){ // seguindo o padrão DTO
	
	
	System.out.println(form.getEmail());
	System.out.println(form.getSenha());
	
	return ResponseEntity.ok().build();
	
    }

}
