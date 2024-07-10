package com.uade.tpo.megagame.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.megagame.auth.AuthenticationRequest;
import com.uade.tpo.megagame.auth.AuthenticationResponse;
import com.uade.tpo.megagame.auth.RegisterRequest;
import com.uade.tpo.megagame.config.JwtService;
import com.uade.tpo.megagame.entity.Usuario;
import com.uade.tpo.megagame.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UsuarioRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var user = Usuario.builder()
                                .nombre(request.getNombre())
                                .telefono(request.getTelefono())
                                .mail(request.getMail())
                                .login(request.getLogin())
                                .pass(passwordEncoder.encode(request.getPass()))
                                .flag_estado(true) 
                                .role(request.getRole())
                                .build();
                                try {
                                        repository.save(user);
                                    } catch (DataIntegrityViolationException e) {
                                        throw new BadCredentialsException("El correo electrónico ya está registrado");
                                    }
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .mail(user.getMail())
                                .login(user.getUsername())
                                .nombre(user.getNombre())
                                .rol(user.getRole())
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));

                var user = repository.findBymail(request.getEmail())
                                .orElseThrow();
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .mail(user.getMail())
                                .login(user.getLogin())
                                .nombre(user.getNombre())
                                .rol(user.getRole())
                                .build();
        }
}
