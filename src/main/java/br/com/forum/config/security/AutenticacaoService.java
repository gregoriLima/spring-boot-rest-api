package br.com.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.forum.modelo.Usuario;
import br.com.forum.repository.UsuarioRepository;

// essa classe é gerenciada pelo spring, por isso é colocado o @Service
@Service // quem chama essa service é o Spring. O próprio controller do spring chama essa service...
public class AutenticacaoService implements UserDetailsService{ // esta interface diz que esta service é a service que tem a lógica de autenticação 
    
    @Autowired
    private UsuarioRepository repository;
    
    // no formulário de login, ao se digitar o nome de usuário e a senha, ao clicar no botão de login
    // o spring chama esse método. Esse método recebe como parâmetro o email colocado como nome de usuário na tela de login
   // A senha, é consultada no banco de dados pelo spring através do email. A senha o spring faz em memória, o spring faz a checagem da senha pela memória
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<Usuario> usuario = repository.findByEmail(username); // se o find não encontrar nada, ele devolve um optional
	
	// se for encontrado um usuário...
	if (usuario.isPresent()) {
	    return usuario.get();
	} 
	
	
	    //  jogando uma exception dizendo para o Spring dizendo que o usuário não existe no banco
	    throw new UsernameNotFoundException("Dados inválidos!");
	
    } 
    

}
