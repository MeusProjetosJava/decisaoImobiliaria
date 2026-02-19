package vitor.decisaoimobiliaria.service;

import org.springframework.stereotype.Service;
import vitor.decisaoimobiliaria.entity.AisStatistics;

import java.util.Comparator;
import java.util.List;

@Service
public class RankingService {
        public int calcularRankingCvli(Integer aisAlvo,List<AisStatistics> todasAis) {

            List<AisStatistics> ordenadoCvli = todasAis.stream()
                    .sorted(Comparator.comparing(AisStatistics::getCvliMediana)).toList();

            for (int i = 0; i < ordenadoCvli.size(); i++) {
                if (ordenadoCvli.get(i).getAis().equals(aisAlvo)){
                    return i + 1;
                }
        }

            throw new RuntimeException("AIS não encontrado" + aisAlvo);
    }

    public int calcularRankingArmas(Integer aisAlvo,List<AisStatistics> todasAis) {

            List<AisStatistics> ordenadoArmas = todasAis.stream()
                    .sorted(Comparator.comparing(AisStatistics::getArmasMediana)).toList();

            for (int i = 0; i < ordenadoArmas.size(); i++) {
                if (ordenadoArmas.get(i).getAis().equals(aisAlvo)){
                    return i + 1;
                }
            }
        throw new RuntimeException("AIS não encontrado" + aisAlvo);
    }


    public int calcularRankingFurtos(Integer aisAlvo,List<AisStatistics> todasAis) {
            List<AisStatistics> ordenadoFurtos = todasAis.stream()
                    .sorted(Comparator.comparing(AisStatistics::getFurtoMediana)).toList();

            for (int i = 0; i < ordenadoFurtos.size(); i++) {
                if (ordenadoFurtos.get(i).getAis().equals(aisAlvo)){
                    return i + 1;
                }
            }

        throw new RuntimeException("AIS não encontrado" + aisAlvo);
    }
}
