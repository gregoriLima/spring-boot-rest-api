package br.com.forum.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.forum.modelo.Usuario;
import br.com.forum.repository.UsuarioRepository;

@Service 
public class AutenticacaoService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository repository;
    
     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<Usuario> usuario = repository.findByEmail(username); // se o find não encontrar nada, ele devolve um optional
	
	if (usuario.isPresent()) {
	    
	    Usuario user = usuario.get();
	    
	    return new org.springframework.security.core.userdetails.User(user.getNome(), user.getSenha(), user.getAuthorities()); // esse retorno evita o erro "Empty encoded password"
		   
	    // return usuario.get();
	} 

	throw new UsernameNotFoundException("Dados inválidos!");
	
    } 
    

}
