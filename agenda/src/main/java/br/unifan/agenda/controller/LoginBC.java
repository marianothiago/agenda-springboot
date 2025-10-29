package br.unifan.agenda.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unifan.agenda.util.JwtUtil;

@RestController
@RequestMapping("/login")
public class LoginBC {

    @PostMapping
    public ResponseEntity<Object> logar(@RequestBody Map<String, String> credenciais){
        try{
            if(!credenciais.containsKey("email") || !credenciais.containsKey("senha")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Faltando campos obrigatórios: email e senha");
            }
            
            String email = credenciais.get("email");
            String senha = credenciais.get("senha");
            
            if(!email.equals("admin@unifan.br")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Usuário inválido");
            }
            
            if(!senha.equals("passwd")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Senha Inválida");
            }
            
            String tokenJWT = JwtUtil.gerarToken(email);
            
            Map<String, String> body = new HashMap<>();
            body.put("tokenJWT", tokenJWT);
            body.put("email", email);
            
            return ResponseEntity.ok(body);
            
        } catch(Exception error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(error.getMessage());
        }
    }
}