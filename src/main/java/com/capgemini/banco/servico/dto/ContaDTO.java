package com.capgemini.banco.servico.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO que representa a Conta
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContaDTO implements Serializable {

    private Long id;
    private Double saldo;
    private UsuarioDTO proprietario;

}
