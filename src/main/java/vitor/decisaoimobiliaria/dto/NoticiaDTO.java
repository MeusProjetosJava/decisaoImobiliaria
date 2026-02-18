package vitor.decisaoimobiliaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class NoticiaDTO {

    private String titulo;
    private String url;
    private String imagem;
    private LocalDate data;
}

