package vitor.decisaoimobiliaria.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AnaliseSegurancaResponse {

    private String bairro;
    private Integer ais;

    private Integer cvliRank;
    private Integer armasRank;
    private Integer furtoRank;

    private String classificacaoGeral;

    private String explicacaoIA;

    private List<NoticiaDTO> noticias;
}

