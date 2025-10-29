package br.unifan.agenda.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.unifan.agenda.controller.exceptions.TokenAusente;
import br.unifan.agenda.controller.exceptions.TokenInvalido;
import br.unifan.agenda.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ValidacaoToken {

    @Around("@annotation(br.unifan.agenda.security.Autorizar)")
    public Object validarToken(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new TokenAusente();
        }

        HttpServletRequest request = attributes.getRequest();
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.trim().isEmpty()) {
            throw new TokenAusente();
        }

        String token = authorizationHeader.trim();
        if (token.isEmpty()) {
            throw new TokenAusente();
        }

        try {
            JwtUtil.validarTokenJWT(token);
        } catch (Exception e) {
            throw new TokenInvalido();
        }
        Object resultado = joinPoint.proceed();
        return resultado;
    }
}