package vitor.decisaoimobiliaria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitor.decisaoimobiliaria.dto.AnaliseSegurancaResponse;
import vitor.decisaoimobiliaria.service.AnaliseSegurancaService;

@RestController
@RequestMapping("/analise")
@RequiredArgsConstructor
public class AnaliseSegurancaController {

    private final AnaliseSegurancaService service;

    @GetMapping
    public ResponseEntity<AnaliseSegurancaResponse> analisar(
            @RequestParam String bairro
    ) {
        return ResponseEntity.ok(service.analisarBairro(bairro));
    }
}

