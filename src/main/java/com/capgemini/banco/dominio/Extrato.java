package com.capgemini.banco.dominio;

import com.capgemini.banco.dominio.enumeration.EnumOperacao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entidade que representa o Extrato
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "EXTRATO")
public class Extrato implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EXTRATO")
    @SequenceGenerator(name = "SEQ_EXTRATO", sequenceName = "SEQ_EXTRATO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @JoinColumn(name = "CONTA_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Conta contaExtrato;

    @NotNull
    @Column(name = "VALOR")
    private Double valor;

    @NotNull
    @Column(name = "TIPO_OPERACAO")
    @Enumerated(EnumType.ORDINAL)
    private EnumOperacao operacao;

}
