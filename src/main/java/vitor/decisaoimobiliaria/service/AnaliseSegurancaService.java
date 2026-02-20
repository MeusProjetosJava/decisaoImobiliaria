package vitor.decisaoimobiliaria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vitor.decisaoimobiliaria.dto.AisRankingResultado;
import vitor.decisaoimobiliaria.dto.AnaliseSegurancaResponse;
import vitor.decisaoimobiliaria.dto.ArmasIndicadorDTO;
import vitor.decisaoimobiliaria.entity.AisStatistics;
import vitor.decisaoimobiliaria.mapper.BairroAisMapper;
import vitor.decisaoimobiliaria.repository.AisStatisticsRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnaliseSegurancaService {
    private final AisStatisticsRepository aisStatisticsRepository;
    private final BairroAisMapper bairroAisMapper;
    private final RankingService rankingService;

    public AnaliseSegurancaResponse analisarBairro(String bairro) {
        Integer ais = bairroAisMapper.getAisByBairro(bairro);

        List<AisStatistics> todasAis = aisStatisticsRepository.findAll();

        int cvliRank = rankingService.calcularRankingCvli(ais,todasAis);


        int furtosRank = rankingService.calcularRankingFurtos(ais,todasAis);

        int armasRank = rankingService.calcularRankingArmas(ais,todasAis);

        ArmasIndicadorDTO armasIndicadorDTO = new ArmasIndicadorDTO(
                armasRank,"Indicador complementar de circulação de armamento");

        AisRankingResultado rankingResultado = rankingService.calcularRankingGeral(ais,todasAis);

        return AnaliseSegurancaResponse.builder().bairro(bairro).ais(ais).
                cvliRank(cvliRank).furtoRank(furtosRank).
                armasIndicadorComplementar(armasIndicadorDTO).
                aisScoreGeral(rankingResultado.getScore()).posicaoGeral(rankingResultado.getPosicao()).
                classificacaoGeral(rankingResultado.getClassificacao()).indiceSeguranca(rankingResultado.getIndiceSeguranca())
                .build();
    }

}
