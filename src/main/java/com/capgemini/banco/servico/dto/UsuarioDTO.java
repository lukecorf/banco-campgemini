package com.capgemini.banco.servico.dto;

import com.capgemini.banco.dominio.enumeration.EnumSexo;
import lombok.*;

import java.io.Serializable;

/**
 * DTO que representa o Usuario
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioDTO implements Serializable {

    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;
    private EnumSexo sexo;

}
