package br.com.forum.config.validacao;


//Essa classe é uma DTO que represenda o erro de validação

public class ErroDeFormularioDTO {

	public ErroDeFormularioDTO(String campo, String mensagem) {
		super();
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
	private String campo; // campo que gerou a exception na validação dos dados
	private String mensagem; //mensagem de erro
	
	public String getCampo() {
		return campo;
	}
	public String getMensagem() {
		return mensagem;
	}
	
}
