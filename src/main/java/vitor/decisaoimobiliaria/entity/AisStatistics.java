package vitor.decisaoimobiliaria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "ais_statistics", schema = "seguranca_urbana")
@Entity
public class AisStatistics {

    @Id
    private Integer ais;

    private BigDecimal cvliMediana;
    private BigDecimal armasMediana;
    private BigDecimal furtoMediana;

    private Integer periodoInicio;
    private Integer periodoFim;
}
