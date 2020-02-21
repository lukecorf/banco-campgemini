package com.capgemini.banco.dominio;

import com.capgemini.banco.dominio.enumeration.EnumSexo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * Entidade que representa o Usuario
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Size(max = 11)
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "IDADE")
    private Integer idade;

    @NotNull
    @Column(name = "SEXO")
    @Enumerated(EnumType.ORDINAL)
    private EnumSexo sexo;

}
