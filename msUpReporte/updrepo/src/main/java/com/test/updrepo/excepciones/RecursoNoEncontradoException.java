package com.test.updrepo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends Exception {
    private static final long serialVersionUID = 1L;

    public RecursoNoEncontradoException(String message){
        super(message);
    }
}