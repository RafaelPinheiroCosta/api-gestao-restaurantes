package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.AuthLoginRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.AuthLoginResponse;
import com.rafaelcosta.api_gestao_restaurantes.infrastructure.security.JwtService;
import com.rafaelcosta.api_gestao_restaurantes.infrastructure.security.UsuarioUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthLoginResponse login(AuthLoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.login(), request.senha())
        );

        UsuarioUserDetails principal = (UsuarioUserDetails) authentication.getPrincipal();

        String role = principal.getAuthorities().iterator().next().getAuthority();

        String token = jwtService.generateToken(
                principal.getUsername(),
                buildClaims(principal, role)
        );

        return new AuthLoginResponse(
                token,
                "Bearer",
                principal.getUsuarioId(),
                principal.getUsername(),
                role,
                principal.getPerfilTipo()
        );
    }


    private Map<String, Object> buildClaims(UsuarioUserDetails principal, String role) {
        return Map.of(
                "role", role,
                "userId", principal.getUsuarioId().toString(),
                "perfilTipo", principal.getPerfilTipo()
        );
    }
}
