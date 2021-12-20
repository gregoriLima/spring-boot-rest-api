package br.com.forum.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.forum.modelo.Curso;

@DataJpaTest // para testes de repositories
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE) 
@ActiveProfiles("test")
public class CursoRepositoryTestSemH2 {
   

    @Autowired
    private CursoRepository repository;
    
    @Autowired
    private TestEntityManager em;
    
    @Test
    public void deveriaCarregarUmCursoAoBuscarPeloNome() {
	
	// persistindo um dado no banco de dados
	Curso html5 = new Curso();
	html5.setNome("HTML5");
	html5.setCategoria("PROGRAMACAO");
	em.persist(html5);
	
	String nomeCurso = "HTML5";
	Curso curso = repository.findByNome(nomeCurso);
	
	assertNotNull(curso);
	assertEquals(nomeCurso, curso.getNome());

    }

    @Test
    public void naoDeveriaCarregarUmCursoQueNaoExiste() {
	String nomeCurso = "HTML7";
	Curso curso = repository.findByNome(nomeCurso);
	
	assertNull(curso);

    }
    
}
