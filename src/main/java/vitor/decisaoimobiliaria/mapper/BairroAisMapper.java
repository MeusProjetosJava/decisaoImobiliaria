package vitor.decisaoimobiliaria.mapper;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BairroAisMapper {

    private static final Map<String, Integer> MAPA = Map.of(
            "Meireles", 5,
            "Aldeota", 8,
            "Centro", 1
    );

    public Integer getAisByBairro(String bairro) {
        return MAPA.get(bairro);
    }
}

