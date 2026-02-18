package vitor.decisaoimobiliaria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vitor.decisaoimobiliaria.dto.AnaliseSegurancaResponse;
import vitor.decisaoimobiliaria.repository.AisStatisticsRepository;

@RequiredArgsConstructor
@Service
public class AnaliseSegurancaService {
    private final AisStatisticsRepository aisStatisticsRepository;
    private final RankingService rankingService;

    public AnaliseSegurancaResponse analisarBairro(String bairro){
        return null;
    }

}
