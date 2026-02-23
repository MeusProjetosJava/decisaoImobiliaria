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
            return null;
        }

        return bairroAisRepository.findById(key).map(BairroAis::getAis).orElse(null);

    }
}

