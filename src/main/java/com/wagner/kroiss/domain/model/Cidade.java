package com.wagner.kroiss.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;



@Entity
@NoArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;

    public Cidade(Long id, String nome, Estado estado) {        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cidade cidade)) return false;
        return Objects.equals(getId(), cidade.getId()) && Objects.equals(getNome(), cidade.getNome()) && Objects.equals(getEstado(), cidade.getEstado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getEstado());
    }
}
