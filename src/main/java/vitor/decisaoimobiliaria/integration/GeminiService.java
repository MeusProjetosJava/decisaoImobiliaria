package vitor.decisaoimobiliaria.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        String dadosJson = converterParaJson(a);

        return """
                Você é um assistente especializado em análise estatística urbana e segurança pública.
                
                Você receberá um JSON contendo indicadores consolidados de uma Área Integrada de Segurança (AIS) de Fortaleza.
                
                IMPORTANTE:
                - Os dados representam TODA a AIS.
                - O bairro informado é apenas um dos bairros que compõem essa AIS.
                - Todas as análises devem deixar explícito que os dados são da AIS como um todo, e não exclusivamente do bairro isolado.
                
                Sua tarefa NÃO é gerar texto livre.
                Sua tarefa é gerar um JSON estruturado seguindo EXATAMENTE o formato abaixo:
                
                {
                  "visaoGeral": "string",
                  "crimesViolentos": "string",
                  "crimesPatrimoniais": "string",
                  "circulacaoArmas": "string",
                  "conclusaoImobiliaria": "string"
                }
                
                ----------------------------------------
                REGRAS GERAIS OBRIGATÓRIAS
                ----------------------------------------
                
                1. Interpretar exclusivamente os dados recebidos.
                2. Não inventar informações.
                3. Não omitir valores numéricos relevantes.
                4. Não utilizar linguagem sensacionalista.
                5. Não mencionar política ou gestão pública.
                6. Linguagem técnica, objetiva e acessível.
                7. Sempre mencionar o período analisado.
                8. Sempre utilizar o campo "totalAis" para contextualizar posições no ranking.
                9. Sempre estruturar posições como:
                   "3ª posição entre 10 AIS avaliadas na cidade".
                10. Sempre deixar explícito que os dados representam a AIS como um todo.
                
                ----------------------------------------
                INTERPRETAÇÃO DO ÍNDICE (0–100)
                ----------------------------------------
                
                O campo "indiceSeguranca" é calculado com base na posição comparativa da AIS no ranking geral.
                
                Sempre explicar da seguinte forma:
                
                "Índice de X em escala de 0 a 100, onde 100 representa a AIS com melhor desempenho relativo comparativo e 0 a de pior desempenho comparativo."
                
                Classificação textual obrigatória:
                80–100 → Muito seguro
                60–79 → Seguro
                40–59 → Intermediário
                20–39 → Arriscado
                0–19 → Muito arriscado
                
                ----------------------------------------
                INTERPRETAÇÃO DOS RANKINGS
                ----------------------------------------
                
                - Menor posição no ranking = melhor desempenho comparativo.
                - Sempre mencionar posição + totalAis.
                - Nunca mencionar apenas a posição isoladamente.
                
                ----------------------------------------
                INTERPRETAÇÃO DAS MEDIANAS
                ----------------------------------------
                
                Os valores presentes em "valoresEstatisticos" representam medianas anuais aproximadas no período analisado.
                
                Sempre escrever no formato:
                
                "aproximadamente X ocorrências anuais no período analisado (2009–2025)."
                
                ----------------------------------------
                REGRAS POR CAMPO
                ----------------------------------------
                
                VISÃO GERAL:
                - Informar:
                  - Nome da AIS
                  - Período analisado
                  - Índice 0–100 com explicação da escala
                  - Classificação geral
                  - Posição geral entre totalAis
                - Listar TODOS os bairros que compõem a AIS utilizando o campo bairroOriginal.
                - Deixar claro que os dados representam a AIS como um todo.
                
                CRIMES VIOLENTOS:
                - Informar:
                  - Posição (ex: 3ª entre 10 AIS)
                  - Valor da mediana anual aproximada (cvliMediana)
                - Explicar que menor posição indica melhor desempenho comparativo.
                
                CRIMES PATRIMONIAIS:
                - Informar:
                  - Posição
                  - Valor da mediana anual aproximada (furtoMediana)
                - Explicar lógica do ranking.
                
                CIRCULAÇÃO DE ARMAS:
                - Informar:
                  - Posição
                  - Valor da mediana anual aproximada (armasMediana)
                - Explicar que é indicador complementar.
                - Explicar que quantidade de apreensões não define isoladamente o cenário de segurança.
                
                CONCLUSÃO IMOBILIÁRIA:
                - Sintetizar os indicadores.
                - Reforçar que os dados são da AIS.
                - Linguagem analítica, não promocional.
                
                ----------------------------------------
                
                JSON DE ENTRADA:
                %s
                
                Retorne APENAS o JSON estruturado.
                """.formatted(dadosJson);
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


    private String converterParaJson(AnaliseSegurancaResponse a) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(a);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter DTO para JSON", e);
        }
    }
}