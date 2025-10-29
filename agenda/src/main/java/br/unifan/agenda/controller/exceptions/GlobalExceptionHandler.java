package br.unifan.agenda.controller.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    public Map<String, Object> montarResposta(String mensagem, String nomeClasse) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("message", mensagem);
        body.put("error", nomeClasse);
        return body;
    }

    @ExceptionHandler({TokenAusente.class, TokenInvalido.class})
    public ResponseEntity<Map<String, Object>> handleTokenExceptions(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(montarResposta("Erro de autenticação: " + ex.getMessage(), ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(ContatoNotFound.class)
    public ResponseEntity<Map<String, Object>> handleContatoNotFound(ContatoNotFound ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(montarResposta("Contato não encontrado: " + ex.getMessage(), ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(ContatoJaExiste.class)
    public ResponseEntity<Map<String, Object>> handleContatoJaExiste(ContatoJaExiste ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(montarResposta("Contato já existe: " + ex.getMessage(), ex.getClass().getSimpleName()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(montarResposta("Erro interno no servidor: " + ex.getMessage(), ex.getClass().getSimpleName()));
    }
}
