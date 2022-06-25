package com.challengealkemy.challengealkemy.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.challengealkemy.challengealkemy.dto.LoginDTO;
import com.challengealkemy.challengealkemy.dto.RegisterDTO;
import com.challengealkemy.challengealkemy.models.Usuario;
import com.challengealkemy.challengealkemy.repository.UsuarioRepository;
import com.challengealkemy.challengealkemy.security.JwtAuthResponseDTO;
import com.challengealkemy.challengealkemy.security.JwtTokenProvider;
import com.challengealkemy.challengealkemy.utils.EmailSend;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSend emailSend;

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody RegisterDTO registerDTO){
        if(usuarioRepository.existsByEmail(registerDTO.getEmail())){
            return new ResponseEntity<>("El email ya existe", HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(registerDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        try {
            emailSend.sendEmail(usuario.getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        usuarioRepository.save(usuario);



        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generarToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponseDTO(token));
    }
}
