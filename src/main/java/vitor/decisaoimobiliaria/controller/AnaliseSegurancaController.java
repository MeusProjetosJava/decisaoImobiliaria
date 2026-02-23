package vitor.decisaoimobiliaria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vitor.decisaoimobiliaria.dto.AnaliseSegurancaResponse;
import vitor.decisaoimobiliaria.service.AnaliseSegurancaService;

@RestController
@RequestMapping("/analise")
@RequiredArgsConstructor
public class AnaliseSegurancaController {

    private final AnaliseSegurancaService analiseSegurancaService;

    @GetMapping
    public ResponseEntity<AnaliseSegurancaResponse> analisar(
            @RequestParam String bairro
    ) {
        return ResponseEntity.ok(analiseSegurancaService.analisarBairro(bairro));
    }
}

