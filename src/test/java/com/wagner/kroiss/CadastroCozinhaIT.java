package com.wagner.kroiss;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import com.wagner.kroiss.domain.exception.CozinhaNaoEncontradaException;
import com.wagner.kroiss.domain.exception.EntidadeEmUsoException;
import com.wagner.kroiss.domain.model.Cozinha;
import com.wagner.kroiss.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CadastroCozinhaIT {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    @Test
    public void testarCadastroCozinhaComSucesso() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação
        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    public void testarCadastroCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        ConstraintViolationException erroEsperado =
                Assertions.assertThrows(ConstraintViolationException.class, () ->
                        cadastroCozinha.salvar(novaCozinha));

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {

        EntidadeEmUsoException erroEsperado =
                Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
                    cadastroCozinha.excluir(1L);
                });

        assertThat(erroEsperado).isNotNull();

    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {


        CozinhaNaoEncontradaException erroEsperado =
                Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
                    cadastroCozinha.excluir(100L);
                });

        assertThat(erroEsperado).isNotNull();

    }

}