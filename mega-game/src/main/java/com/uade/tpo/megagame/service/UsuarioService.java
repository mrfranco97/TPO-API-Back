package com.uade.tpo.megagame.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.megagame.entity.Usuario;
import com.uade.tpo.megagame.interfaces.UsuarioInterface;
import com.uade.tpo.megagame.repository.UsuarioRepository;

@Service
public class UsuarioService implements UsuarioInterface{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }
}