package vitor.decisaoimobiliaria.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vitor.decisaoimobiliaria.entity.BairroAis;
import vitor.decisaoimobiliaria.repository.BairroAisRepository;
import vitor.decisaoimobiliaria.util.TextNormalizer;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class BairroAisMapper {

    private final BairroAisRepository bairroAisRepository;

    public Integer getAisByBairro(String bairro) {
        String key = TextNormalizer.normalizeKey(bairro);

        if (key == null) {
            throw new NullPointerException("Bairro Inválido");
        }

        BairroAis bairroAis = bairroAisRepository.findById(key).
                orElseThrow(() -> new IllegalArgumentException("Bairro não mapeado " + bairro));
        return bairroAis.getAis();

    }
}

