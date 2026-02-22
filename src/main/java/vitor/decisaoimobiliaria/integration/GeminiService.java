package vitor.decisaoimobiliaria.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vitor.decisaoimobiliaria.config.GeminiConfig;
import vitor.decisaoimobiliaria.dto.AnaliseSegurancaResponse;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final RestTemplate restTemplate;
    private final GeminiConfig config;

    public String gerarExplicacao(AnaliseSegurancaResponse analise) {

        String prompt = montarPrompt(analise);

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        String urlComKey = config.getUrl() + "?key=" + config.getApiKey();

        ResponseEntity<Map> response = restTemplate.exchange(
                urlComKey,
                HttpMethod.POST,
                entity,
                Map.class
        );

        return extrairTexto(response.getBody());
    }

    private String montarPrompt(AnaliseSegurancaResponse a) {

        return """
                Você é um analista urbano especializado em segurança pública.

                Gere uma explicação clara, objetiva e profissional com base nos dados abaixo.

                Bairro: %s
                AIS: %d
                Índice de Segurança: %s
                Classificação: %s
                Posição Geral: %d
                Ranking CVLI: %d
                Ranking Furto: %d

                Explique o que esses números significam para alguém avaliando compra de imóvel.
                Linguagem técnica acessível.
                """
                .formatted(
                        a.getBairro(),
                        a.getAis(),
                        a.getIndiceSeguranca(),
                        a.getClassificacaoGeral(),
                        a.getPosicaoGeral(),
                        a.getCvliRank(),
                        a.getFurtoRank()
                );
    }

    private String extrairTexto(Map<String, Object> body) {

        List<Map<String, Object>> candidates =
                (List<Map<String, Object>>) body.get("candidates");

        Map<String, Object> content = candidates.get(0);

        Map<String, Object> partsWrapper =
                (Map<String, Object>) content.get("content");

        List<Map<String, Object>> parts =
                (List<Map<String, Object>>) partsWrapper.get("parts");

        return (String) parts.get(0).get("text");
    }
}