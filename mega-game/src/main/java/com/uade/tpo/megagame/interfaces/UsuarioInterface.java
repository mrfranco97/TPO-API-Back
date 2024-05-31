package com.uade.tpo.megagame.interfaces;
import com.uade.tpo.megagame.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioInterface {
    public List<Usuario> findAll();

    public Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);
    
    void deleteById(Long id);
}