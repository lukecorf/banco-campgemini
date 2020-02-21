package com.capgemini.banco.dominio;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entidade que representa a conta
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "CONTA")
public class Conta implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
    @SequenceGenerator(name = "SEQ_CONTA", sequenceName = "SEQ_CONTA", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "SALDO")
    private Double saldo;

    @NotNull
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario proprietario;

}
