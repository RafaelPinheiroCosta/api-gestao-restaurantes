package com.rafaelcosta.api_gestao_restaurantes.interface_ui.controller;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.service.UsuarioService;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        log.info("Listando usuários page={} size={}", page, size);
        return ResponseEntity.ok(usuarioService.findAllUsuarios(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponse>> buscarUsuarioPorId(@PathVariable UUID id) {
        log.info("Buscando usuário id={}", id);
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> salvarUsuario(@RequestBody UsuarioRequest request) {
        log.info("Salvando novo usuário login={}", request.login());
        return ResponseEntity.ok(usuarioService.saveUsuario(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponse>> atualizarUsuario(
            @PathVariable UUID id,
            @RequestBody UsuarioRequest request
    ) {
        log.info("Atualizando usuário id={}", id);
        return ResponseEntity.ok(usuarioService.updateUsuario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        log.info("Deletando usuário id={}", id);
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
