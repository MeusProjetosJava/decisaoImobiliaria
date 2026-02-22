package vitor.decisaoimobiliaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "bairro_ais")
public class BairroAis {

    @Id
    @Column(name = "bairro_norm", length = 120, nullable = false)
    private String bairroNorm;

    @Column(name = "bairro_original", length = 120, nullable = false)
    private String bairroOriginal;

    @Column(name = "ais", nullable = false)
    private Integer ais;

    @Column(name = "municipio", length = 80, nullable = false)
    private String municipio;
}