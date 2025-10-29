package br.unifan.agenda.controller.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409 Conflict
public class ContatoJaExiste extends RuntimeException{
    public ContatoJaExiste(String email){
        super(String.format("Já existe contato com email %s", email));
    }
}
