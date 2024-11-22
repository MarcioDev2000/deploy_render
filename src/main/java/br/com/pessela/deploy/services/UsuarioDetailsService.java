package br.com.pessela.deploy.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pessela.deploy.models.Usuario;
import br.com.pessela.deploy.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioDetailsService implements UserDetailsService { 

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return org.springframework.security.core.userdetails.User
                    .withUsername(usuario.getEmail())
                    .password(usuario.getSenha())
                    .authorities(usuario.getRole().getAuthority())
                    .accountLocked(false) 
                    .disabled(false) 
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
    }
}