package com.capgemini.banco.servico;

import com.capgemini.banco.dominio.Conta;
import com.capgemini.banco.dominio.enumeration.EnumOperacao;
import com.capgemini.banco.servico.dto.ExtratoDTO;

import java.util.List;

public interface ExtratoServico {

    /**
     * Busca os extratos pela conta.
     *
     * @param idConta ID da conta.
     * @return Lista de extratos.
     */
    List<ExtratoDTO> buscaExtratoPorConta(Long idConta);

    /**
     * Salva um novo extrato.
     *
     * @param conta Conta que gerou o extrato.
     * @param valor Valor do extrato
     * @param tipo Tipo de operacao( Saque ou Deposito )
     */
    void salvarExtrato(Conta conta, Double valor, EnumOperacao tipo);

}
