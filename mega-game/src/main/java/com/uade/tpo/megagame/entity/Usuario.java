package com.uade.tpo.megagame.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Usuario implements UserDetails {
    public Usuario(){
    }

    public Usuario(String nombre){
        this.nombre=nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String nombre;
    
    @Column
    private String telefono;
    
    @Column
    private String mail;
    
    @Column
    private String login;
    
    @Column
    private String pass;

    @Column
    private Boolean flag_estado;

    @JsonIgnoreProperties("usuario")
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Venta> compras;

    @Enumerated
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Aquí puedes mapear los roles a autoridades
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // O ajusta la lógica según tus necesidades
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // O ajusta la lógica según tus necesidades
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // O ajusta la lógica según tus necesidades
    }

    @Override
    public boolean isEnabled() {
        return this.flag_estado; // O ajusta la lógica según tus necesidades
    }
}
