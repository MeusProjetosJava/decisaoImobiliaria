package vitor.decisaoimobiliaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
public class AisRankingResultado {
    private BigDecimal score;
    private Integer posicao;
    private String classificacao;
    private BigDecimal indiceSeguranca;
}
