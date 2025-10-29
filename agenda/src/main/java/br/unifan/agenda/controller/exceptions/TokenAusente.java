package br.unifan.agenda.controller.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenAusente extends RuntimeException{
    public TokenAusente(){
        super(String.format("Token de acesso não fornecido"));
    }
}
