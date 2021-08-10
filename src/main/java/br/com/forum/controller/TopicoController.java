package br.com.forum.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.controller.form.AtualizacaoTopicoForm;
import br.com.forum.dto.TopicoDTO;
import br.com.forum.form.TopicoForm;
import br.com.forum.modelo.Curso;
import br.com.forum.modelo.Topico;
import br.com.forum.repository.CursoRepository;
import br.com.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Listando todos tópicos
    @Transactional // garantindo que este método vai rodar dentro de uma transação
    @GetMapping
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso, 
	    @PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) { // parâmetros de url para paginação e ordenacao

	if (nomeCurso == null) {
	    Page<Topico> topicos = topicoRepository.findAll(paginacao); // uma das sobrecargas do findAll recebe um Pageable
	    return TopicoDTO.converter(topicos);
	} else {
	    Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
	    return TopicoDTO.converter(topicos);
	}
    }

    // Criando um novo tópico
    @Transactional
    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {

	Topico topico = form.converter(cursoRepository); // convertendo a DTO para a clases Topico

	topicoRepository.save(topico);

	URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

	return ResponseEntity.created(uri).body(new TopicoDTO(topico));

    }

    // Consultando um tópico pelo id
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> detalhar(@PathVariable Long id) {

	Optional<Topico> topico = topicoRepository.findById(id); // FindById() devolve ou null ou um obj

	if (topico.isPresent()) {
	    return ResponseEntity.ok(new TopicoDTO(topico.get()));
	}

	return ResponseEntity.notFound().build();
    }

    // Atualizando um tópico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id,
	    @RequestBody @Valid AtualizacaoTopicoForm formparaAtualizar) {

	Topico topico = formparaAtualizar.atualizar(id, topicoRepository);

	return ResponseEntity.ok(new TopicoDTO(topico));
    }

    // Removendo um tópico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {

	topicoRepository.deleteById(id);

	return ResponseEntity.ok().build();
    }

}
