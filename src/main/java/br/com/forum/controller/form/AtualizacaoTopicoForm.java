package br.com.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.forum.modelo.Topico;
import br.com.forum.repository.TopicoRepository;

//Esta classe é um DTO para atualizar um tópico

public class AtualizacaoTopicoForm {

	@NotNull @NotEmpty @Length(min=5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min=10)
	private String mensagem;

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		Topico topico = topicoRepository.getOne(id);
		
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		topico.setMensagem(this.mensagem);
		
		return topico;
	}
	
	
	
}
