package vitor.decisaoimobiliaria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vitor.decisaoimobiliaria.dto.AnaliseSegurancaResponse;
import vitor.decisaoimobiliaria.integration.GeminiService;
import vitor.decisaoimobiliaria.service.AnaliseSegurancaService;

@RestController
@RequestMapping("/analise")
@RequiredArgsConstructor
public class AnaliseIaController {

    private final AnaliseSegurancaService analiseSegurancaService;
    private final GeminiService geminiService;

    @GetMapping("/interpretar")
    public ResponseEntity<String> interpretar(@RequestParam String bairro) {

        AnaliseSegurancaResponse analise =
                analiseSegurancaService.analisarBairro(bairro);

        String texto = geminiService.gerarExplicacao(analise);

        return ResponseEntity.ok(texto);
    }
}
