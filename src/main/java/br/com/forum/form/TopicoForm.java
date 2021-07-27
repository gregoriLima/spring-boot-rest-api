package br.com.forum.form;

// ESSA É A CLASSE DTO DOS DADOS QUE VEM DO CLIENTE

//essa classe usa o Bean Validation para validar os dados que vem do cliente
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.forum.modelo.Curso;
import br.com.forum.modelo.Topico;
import br.com.forum.repository.CursoRepository;


public class TopicoForm {

	// usando o bean validator
	
	@NotNull @NotEmpty @Length(min = 5)
	 private String titulo;
	@NotNull @NotEmpty @Length(min = 10)
     private String mensagem;
	@NotNull @NotEmpty // @Size
     private String nomeCurso;
     
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	public Topico converter(CursoRepository cursoRepository) {

		Curso curso = cursoRepository.findByNome(nomeCurso);
		
		return new Topico(titulo, mensagem, curso);
	}
	
}
