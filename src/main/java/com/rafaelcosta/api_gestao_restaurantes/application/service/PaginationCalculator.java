package com.rafaelcosta.api_gestao_restaurantes.application.service;

import org.springframework.stereotype.Component;

@Component
public class PaginationCalculator {

    public int offset(int page, int size) {
        int safePage = Math.max(page, 1);
        int safeSize = Math.max(size, 1);
        return (safePage - 1) * safeSize;
    }

    public int size(int size) {
        return Math.max(size, 1);
    }
}
