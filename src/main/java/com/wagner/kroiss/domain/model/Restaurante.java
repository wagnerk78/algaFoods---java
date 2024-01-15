package com.wagner.kroiss.domain.model;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_rest", nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal taxa_frete;

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

    public BigDecimal getTaxa_frete() {
        return taxa_frete;
    }

    public void setTaxa_frete(BigDecimal taxa_frete) {
        this.taxa_frete = taxa_frete;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(taxa_frete, that.taxa_frete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, taxa_frete);
    }

    @ManyToOne
//    @JoinColumn(name = "cozinhaId", nullable = false)
    private Cozinha cozinha;

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public Restaurante(Long id, String nome, BigDecimal taxa_frete, Cozinha cozinha) {
        this.id = id;
        this.nome = nome;
        this.taxa_frete = taxa_frete;
        this.cozinha = cozinha;
    }
}
