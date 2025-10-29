package br.unifan.agenda.controller.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Conflict
public class ContatoNotFound extends RuntimeException{
    public ContatoNotFound(String email){
        super(String.format("Contato com email %s não encontrado", email));
    }
}
