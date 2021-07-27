package br.com.forum.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	//** Para que o spring fique escutando as modificações do código igual o nodemon,
	// utiliza-se o módulo DevTools
	
	
	@RequestMapping("/")
    @ResponseBody
	public String hello() {
		return "tente ./topicos";
	}
	
	
	
}
