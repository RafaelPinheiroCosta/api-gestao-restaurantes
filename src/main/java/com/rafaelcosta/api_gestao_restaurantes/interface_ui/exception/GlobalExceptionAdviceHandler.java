package com.rafaelcosta.api_gestao_restaurantes.interface_ui.exception;


import com.rafaelcosta.api_gestao_restaurantes.domain.exception.EmailJaCadastradoException;
import com.rafaelcosta.api_gestao_restaurantes.domain.exception.LoginJaCadastradoException;
import com.rafaelcosta.api_gestao_restaurantes.domain.exception.SenhaAtualInvalidaException;
import com.rafaelcosta.api_gestao_restaurantes.domain.exception.UsuarioNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.rafaelcosta.api_gestao_restaurantes.interface_ui.exception.ProblemDetailsUtils.buildProblem;


@RestControllerAdvice
public class GlobalExceptionAdviceHandler {

//    @ExceptionHandler(Exception.class)
//    public ProblemDetail handleGenericException(Exception ex, HttpServletRequest request) {
//        return buildProblem(
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                "Erro do própio servidor",
//                ex.getMessage(),
//                request.getRequestURI()
//        );
//    }

    //Pega erros de digitação ou semelhantes
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ProblemDetail problem = buildProblem(
                HttpStatus.BAD_REQUEST,
                "Erro de validação",
                "Um ou mais campos são inválidos",
                request.getRequestURI()
        );

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        problem.setProperty("errors", errors);
        return problem;
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Tipo de parâmetro inválido");
        problem.setDetail(String.format(
                "O parâmetro '%s' deve ser do tipo '%s'. Valor recebido: '%s'",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconhecido",
                ex.getValue()
        ));
        problem.setInstance(URI.create(request.getRequestURI()));
        return problem;
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ProblemDetail handleConversionFailed(ConversionFailedException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Falha de conversão de parâmetro");
        problem.setDetail("Um parâmetro não pôde ser convertido para o tipo esperado.");
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setProperty("error", ex.getMessage());
        return problem;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Erro de validação nos parâmetros");
        problem.setDetail("Um ou mais parâmetros são inválidos");
        problem.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> errors = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String campo = violation.getPropertyPath().toString();
            String mensagem = violation.getMessage();
            errors.put(campo, mensagem);
        });
        problem.setProperty("errors", errors);
        return problem;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.UNAUTHORIZED,
                "Não autenticado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.FORBIDDEN,
                "Acesso negado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.UNAUTHORIZED,
                "Credenciais inválidas",
                ex.getMessage(),
                request.getRequestURI()
        );
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.METHOD_NOT_ALLOWED,
                "Método não permitido",
                String.format("O método %s não é suportado para esta rota. Métodos suportados: %s",
                        ex.getMethod(),
                        String.join(", ", ex.getSupportedMethods() != null ? ex.getSupportedMethods() : new String[]{})),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ProblemDetail handleEmailDuplicado(EmailJaCadastradoException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.CONFLICT,
                "Email já cadastrado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(LoginJaCadastradoException.class)
    public ProblemDetail handleLoginDuplicado(LoginJaCadastradoException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.CONFLICT,
                "Login já cadastrado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ProblemDetail handleNotFound(UsuarioNaoEncontradoException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(SenhaAtualInvalidaException.class)
    public ProblemDetail handleSenhaAtualInvalida(SenhaAtualInvalidaException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Senha atual inválida",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    // fallback para UNIQUE/constraint do banco:
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.CONFLICT,
                "Violação de integridade",
                "Dados duplicados ou inválidos para as regras do banco.",
                request.getRequestURI()
        );
    }

}