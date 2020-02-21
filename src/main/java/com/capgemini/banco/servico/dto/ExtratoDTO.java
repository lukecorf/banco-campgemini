package com.capgemini.banco.servico.dto;

import com.capgemini.banco.dominio.enumeration.EnumOperacao;
import lombok.*;

import java.io.Serializable;

/**
 * DTO que representa o Extrato
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExtratoDTO implements Serializable {

    private Long id;
    private ContaDTO contaExtrato;
    private Double valor;
    private EnumOperacao operacao;

}
