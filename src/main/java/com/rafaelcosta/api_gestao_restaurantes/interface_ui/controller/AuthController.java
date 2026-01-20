package com.rafaelcosta.api_gestao_restaurantes.interface_ui.controller;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.AuthLoginRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.AuthLoginResponse;
import com.rafaelcosta.api_gestao_restaurantes.application.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Login",
            description = "Autentica usuário e retorna um JWT para usar no Swagger (Authorize) e nas rotas protegidas."
    )
    @SecurityRequirements
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = AuthLoginResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                                      "tokenType": "Bearer",
                                      "usuarioId": "d290f1ee-6c54-4b01-90e6-d701748f0851",
                                      "login": "rafael",
                                      "role": "ROLE_USER",
                                      "perfilTipo": "CLIENTE"
                                    }
                                    """))),
            @ApiResponse(responseCode = "400", description = "Erro de validação (campos obrigatórios)",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "type": "about:blank",
                                      "title": "Erro de validação",
                                      "status": 400,
                                      "detail": "Um ou mais campos são inválidos",
                                      "instance": "/api/v1/auth/login",
                                      "errors": {
                                        "login": "must not be null",
                                        "senha": "must not be null"
                                      }
                                    }
                                    """))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "type": "about:blank",
                                      "title": "Credenciais inválidas",
                                      "status": 401,
                                      "detail": "Bad credentials",
                                      "instance": "/api/v1/auth/login"
                                    }
                                    """)))
    })
    @PostMapping("/login")
    public AuthLoginResponse login(@RequestBody @Valid AuthLoginRequest request) {
        log.debug("Login request: {}", request);
        return authService.login(request);
    }
}
