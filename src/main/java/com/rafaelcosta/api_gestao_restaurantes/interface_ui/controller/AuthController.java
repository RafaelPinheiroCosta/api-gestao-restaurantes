package com.rafaelcosta.api_gestao_restaurantes.interface_ui.controller;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.AuthLoginRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.AuthLoginResponse;
import com.rafaelcosta.api_gestao_restaurantes.infrastructure.security.JwtService;
import com.rafaelcosta.api_gestao_restaurantes.infrastructure.security.UsuarioUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public AuthLoginResponse login(@RequestBody AuthLoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.login(), request.senha())
        );

        UsuarioUserDetails principal = (UsuarioUserDetails) authentication.getPrincipal();
        String role = principal.getAuthorities().iterator().next().getAuthority();

        String token = jwtService.generateToken(
                principal.getUsername(),
                Map.of(
                        "role", role,
                        "userId", principal.getUsuarioId().toString(),
                        "perfilTipo", principal.getPerfilTipo()
                )
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
}
