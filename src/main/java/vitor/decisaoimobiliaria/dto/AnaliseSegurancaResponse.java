package vitor.decisaoimobiliaria.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
@JsonPropertyOrder({
        "bairro",
        "ais",
        "indiceSeguranca",
        "classificacaoGeral",
        "posicaoGeral",
        "aisScoreGeral",
        "cvliRank",
        "furtoRank",
        "armasIndicadorComplementar"
})
@Getter
@Builder
public class AnaliseSegurancaResponse {

    private String bairro;
    private Integer ais;
    private Integer cvliRank;
    private Integer furtoRank;
    private BigDecimal aisScoreGeral;
    private Integer posicaoGeral;
    private BigDecimal indiceSeguranca;
    private ArmasIndicadorDTO armasIndicadorComplementar;
    private String classificacaoGeral;

}

