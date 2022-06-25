package com.challengealkemy.challengealkemy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challengealkemy.challengealkemy.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Optional<Usuario> findByEmail(String email);

    public Boolean existsByEmail(String email);


}
