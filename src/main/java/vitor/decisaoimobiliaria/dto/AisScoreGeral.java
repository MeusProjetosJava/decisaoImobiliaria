package vitor.decisaoimobiliaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AisScoreGeral {
    private Integer ais;
    private BigDecimal score;
}
