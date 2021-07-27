package br.com.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.forum.modelo.Topico;

// seguindo o padrão Repository:

//JpaRepository <Tipo da entidade, tipo do atributo 'id'>
public interface TopicoRepository extends JpaRepository<Topico, Long>{

    // findBy + nome do atributo
	List<Topico> findByTitulo(String nome);

	// findBy + nome da entidade relacionada + nome do atributo
	List<Topico> findByCurso_Nome(String nome);

	// Consulta personalizada
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso")String nomeCurso);

}
