package br.com.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.modelo.Usuario;

// interface que vai fazer a consulta ao banco de dados, buscando os dados do usuário para autenticação
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // método que faz a busca pelo email
    Optional<Usuario> findByEmail(String email);
    
}
