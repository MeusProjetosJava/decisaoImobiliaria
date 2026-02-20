package vitor.decisaoimobiliaria.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

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
    private String explicacaoIA;
    private List<NoticiaDTO> noticias;
}

