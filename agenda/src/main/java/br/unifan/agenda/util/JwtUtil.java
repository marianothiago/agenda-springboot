package br.unifan.agenda.util;

import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtUtil {
    
    private static final String SECRET_KEY = "minha-chave-secreta-muito-segura-para-jwt-validation-12345678";
    
    public static String gerarToken(String usuario, int expiraEmMinutos) {
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + (expiraEmMinutos * 60 * 1000));
        
        SecretKeySpec key = new SecretKeySpec(
            SECRET_KEY.getBytes(), 
            "HmacSHA256"
        );
        
        return Jwts.builder()
                .subject(usuario)
                .issuedAt(agora)
                .expiration(expiracao)
                .signWith(key)
                .compact();
    }
    
    public static String gerarToken(String usuario) {
        return gerarToken(usuario, 60);
    }

    public static void validarTokenJWT(String token) throws JwtException {
        try {
            SecretKeySpec key = new SecretKeySpec(
                SECRET_KEY.getBytes(), 
                "HmacSHA256"
            );
            
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            System.out.println("Token válido para usuário: " + claims.getSubject());
            
        } catch (JwtException e) {
            throw e;
        }
    }
}