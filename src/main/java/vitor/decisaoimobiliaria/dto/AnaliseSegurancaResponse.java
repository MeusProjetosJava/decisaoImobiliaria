package vitor.decisaoimobiliaria.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import vitor.decisaoimobiliaria.entity.AisStatistics;
import vitor.decisaoimobiliaria.entity.BairroAis;

import java.math.BigDecimal;
import java.util.List;
@JsonPropertyOrder({
        "bairro",
        "bairroAis",
        "ais",
        "totalAis",
        "indiceSeguranca",
        "classificacaoGeral",
        "posicaoGeral",
        "aisScoreGeral",
        "cvliRank",
        "furtoRank",
        "armasIndicadorComplementar",
        "valoresEstatisticos"
})
@Getter
@Builder
public class AnaliseSegurancaResponse {

    private String bairro;
    private Integer ais;
    private Integer totalAis;
    private Integer cvliRank;
    private Integer furtoRank;
    private BigDecimal aisScoreGeral;
    private Integer posicaoGeral;
    private BigDecimal indiceSeguranca;
    private ArmasIndicadorDTO armasIndicadorComplementar;
    private String classificacaoGeral;
    private List<BairroAis> bairroAis;
    private List<AisStatistics> valoresEstatisticos;

}

