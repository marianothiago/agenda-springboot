package br.unifan.agenda.controller.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) // 401 Unauthorized
public class TokenInvalido extends RuntimeException{
    public TokenInvalido(){
        super(String.format("Token de acesso inválido"));
    }
}
