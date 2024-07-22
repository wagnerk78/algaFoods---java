package com.wagner.kroiss.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends  RuntimeException {

    public EntidadeEmUsoException(String menssagem) {
        super(menssagem);
    }
}
