package com.rafaelcosta.api_gestao_restaurantes.interface_ui.controller;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioChangePasswordRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioUpdateRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioResponse;
import com.rafaelcosta.api_gestao_restaurantes.application.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static com.rafaelcosta.api_gestao_restaurantes.infrastructure.config.OpenApiConfig.SECURITY_SCHEME_NAME;

@RestController
@RequestMapping("/api/v1/usuarios")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações de CRUD de usuários")
@SecurityRequirement(name = SECURITY_SCHEME_NAME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Listar usuários", description = "Lista usuários com paginação via query params page/size.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Não autenticado (token ausente/inválido)",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado (sem permissão)",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Listando usuários page={} size={}", page, size);
        return ResponseEntity.ok(usuarioService.findAllUsuarios(page, size));
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário pelo UUID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "UUID inválido (type mismatch)",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "title": "Tipo de parâmetro inválido",
                                      "status": 400,
                                      "detail": "O parâmetro 'id' deve ser do tipo 'UUID'. Valor recebido: '123'",
                                      "instance": "/api/v1/usuarios/123"
                                    }
                                    """))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable UUID id) {
        log.info("Buscando usuário id={}", id);
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar usuário", description = "Cria um novo usuário e retorna 201 com header Location.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Login/email duplicado",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "title": "Email já cadastrado",
                                      "status": 409,
                                      "detail": "Já existe um usuário com este email.",
                                      "instance": "/api/v1/usuarios"
                                    }
                                    """))),
            @ApiResponse(responseCode = "401", description = "Não autenticado",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> salvarUsuario(@Valid @RequestBody UsuarioRequest request) {
        log.info("Salvando novo usuário login={}", request.login());

        UsuarioResponse response = usuarioService.saveUsuario(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/usuarios/" + response.id())
                .body(response);
    }

    @Operation(summary = "Atualizar perfil do usuário",
            description = "Atualiza nome/email/login/perfilTipo/endereço. Não altera senha.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Login/email duplicado",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarPerfil(
            @PathVariable UUID id,
            @Valid @RequestBody UsuarioUpdateRequest request
    ) {
        UsuarioResponse atualizado = usuarioService.updateUsuarioPerfil(id, request);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Trocar senha",
            description = "Troca a senha do usuário, validando senha atual. Retorna 204 no sucesso.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class))),
            @ApiResponse(responseCode = "422", description = "Senha atual inválida",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "title": "Senha atual inválida",
                                      "status": 422,
                                      "detail": "A senha atual informada não confere.",
                                      "instance": "/api/v1/usuarios/{id}/senha"
                                    }
                                    """)))
    })
    @PutMapping("/{id}/senha")
    public ResponseEntity<Void> trocarSenha(
            @PathVariable UUID id,
            @Valid @RequestBody UsuarioChangePasswordRequest request
    ) {
        usuarioService.changePassword(id, request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuários por nome",
            description = "Busca por nome com paginação via query params page/size.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
    })
    @GetMapping("/busca")
    public ResponseEntity<List<UsuarioResponse>> buscarPorNome(
            @RequestParam String nome,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(usuarioService.findByNome(nome, page, size));
    }

    @Operation(summary = "Excluir usuário",
            description = "Exclui usuário por ID. Retorna 204 no sucesso.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = org.springframework.http.ProblemDetail.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        log.info("Deletando usuário id={}", id);
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
