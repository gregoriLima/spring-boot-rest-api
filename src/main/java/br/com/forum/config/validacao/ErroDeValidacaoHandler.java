package br.com.forum.config.validacao;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.MethodArgumentBuilder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

// Interceptor para lidar com as exceptions

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	// para verificar a linguagem internacional da mensagem
	@Autowired
	private MessageSource messageSource;
	
	// Tratando as exceptions MethodArgumetNotValidException (tem essa validação na classe TopicosForm que pode ser lançada ao executar o bean validator)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST) // status de retorno ao cliente
	public List<ErroDeFormularioDTO> handle(MethodArgumentNotValidException exception) {

		List<ErroDeFormularioDTO> dto = new ArrayList<>();
		
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors(); // retorna o nome dos campos onde os erros foram gerados na validação

		fieldErros.forEach(e -> {
		
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale()); // formatando a mensagem com de acordo com a origem da língua do cliente
			
			ErroDeFormularioDTO erro = new ErroDeFormularioDTO(e.getField(), mensagem);
			
			dto.add(erro);
		
		});
		
		return dto;
	}
	

}


// exemplo, caso alguém não mande o campo mensagem no formulário, é retornado o seguinte erro
/*  
   {
        "campo": "mensagem",
        "mensagem": "não deve estar vazio"
    },
    */
