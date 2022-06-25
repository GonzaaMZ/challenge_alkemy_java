package com.challengealkemy.challengealkemy.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.challengealkemy.challengealkemy.models.Usuario;
import com.challengealkemy.challengealkemy.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Set<GrantedAuthority> authorities = new HashSet<>();

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese email" + email));

        return new User(usuario.getEmail(), usuario.getPassword(), getAuthorities());
    }

    public void User() {
        authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}