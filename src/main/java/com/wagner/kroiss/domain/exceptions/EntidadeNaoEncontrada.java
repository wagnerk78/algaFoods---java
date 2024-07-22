package com.wagner.kroiss.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")
public class EntidadeNaoEncontrada extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontrada(String menssagem) {
        super(menssagem);
    }
}

