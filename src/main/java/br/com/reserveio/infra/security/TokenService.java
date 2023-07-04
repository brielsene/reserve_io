package br.com.reserveio.infra.security;

import br.com.reserveio.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("Reserve")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now().plusMinutes(40).toInstant(ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("SECRET"));

    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("SECRET"))
                .withIssuer("Reserve")
                .build().verify(token).getSubject();

    }
}
