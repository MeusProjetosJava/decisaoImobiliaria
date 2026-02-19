package vitor.decisaoimobiliaria.service;

import org.springframework.stereotype.Service;
import vitor.decisaoimobiliaria.dto.AisRankingResultado;
import vitor.decisaoimobiliaria.entity.AisStatistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

@Service
public class RankingService {

    private static final BigDecimal PESO_CVLI = new BigDecimal("0.6");
    private static final BigDecimal PESO_FURTO = new BigDecimal("0.4");

    public int calcularRanking(Integer aisAlvo,
                               List<AisStatistics> todasAis,
                               Comparator<AisStatistics> comparator) {

        List<AisStatistics> ordenado =
                todasAis.stream()
                        .sorted(comparator)
                        .toList();

        for (int i = 0; i < ordenado.size(); i++) {
            if (ordenado.get(i).getAis().equals(aisAlvo)) {
                return i + 1;
            }
        }

        throw new RuntimeException("AIS não encontrado: " + aisAlvo);
    }

    public int calcularRankingCvli(Integer aisAlvo, List<AisStatistics> todasAis) {
        return calcularRanking(
                aisAlvo,
                todasAis,
                Comparator.comparing(AisStatistics::getCvliMediana)
        );
    }

    public int calcularRankingArmas(Integer aisAlvo, List<AisStatistics> todasAis) {
        return calcularRanking(
                aisAlvo,
                todasAis,
                Comparator.comparing(AisStatistics::getArmasMediana)
        );
    }

    public int calcularRankingFurtos(Integer aisAlvo, List<AisStatistics> todasAis) {
        return calcularRanking(
                aisAlvo,
                todasAis,
                Comparator.comparing(AisStatistics::getFurtoMediana)
        );
    }

    public AisRankingResultado calcularRankingGeral(
            Integer aisAlvo,
            List<AisStatistics> todasAis
    ) {

        List<AisScoreGeral> listaScores =
                todasAis.stream()
                        .map(ais -> {

                            int cvliRank = calcularRankingCvli(ais.getAis(), todasAis);
                            int furtoRank = calcularRankingFurtos(ais.getAis(), todasAis);

                            BigDecimal score =
                                    BigDecimal.valueOf(cvliRank)
                                            .multiply(PESO_CVLI)
                                            .add(
                                                    BigDecimal.valueOf(furtoRank)
                                                            .multiply(PESO_FURTO)
                                            )
                                            .setScale(2, RoundingMode.HALF_UP);

                            return new AisScoreGeral(ais.getAis(), score);
                        })
                        .sorted(Comparator.comparing(AisScoreGeral::getScore))
                        .toList();

        for (int i = 0; i < listaScores.size(); i++) {

            if (listaScores.get(i).getAis().equals(aisAlvo)) {

                int posicao = i + 1;

                String classificacao =
                        definirClassificacao(posicao, listaScores.size());

                return new AisRankingResultado(
                        listaScores.get(i).getScore(),
                        posicao,
                        classificacao
                );
            }
        }

        throw new RuntimeException("AIS não encontrado: " + aisAlvo);
    }


    private String definirClassificacao(int posicao, int totalAis){

        BigDecimal percentual = BigDecimal.valueOf(posicao).divide(BigDecimal.valueOf(totalAis)
                , 4, RoundingMode.HALF_UP);

        if (percentual.compareTo(new BigDecimal("0.33")) <=0 ){
            return "BAIXO_RISCO";
        } else if (percentual.compareTo(new BigDecimal("0.66")) <= 0) {
            return "MEDIO_RISCO";
        } else{
            return "ALTO_RISCO";
        }
    }
}
