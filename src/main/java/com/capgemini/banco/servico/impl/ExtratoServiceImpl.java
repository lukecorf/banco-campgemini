package com.capgemini.banco.servico.impl;

import com.capgemini.banco.dominio.Conta;
import com.capgemini.banco.dominio.Extrato;
import com.capgemini.banco.dominio.enumeration.EnumOperacao;
import com.capgemini.banco.repositorio.ExtratoRepositorio;
import com.capgemini.banco.servico.ExtratoServico;
import com.capgemini.banco.servico.dto.ExtratoDTO;
import com.capgemini.banco.servico.mapper.ExtratoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExtratoServiceImpl implements ExtratoServico {

    private final ExtratoRepositorio extratoRepositorio;

    private final ExtratoMapper extratoMapper;

    /**
     * Busca os extratos pela conta.
     *
     * @param idConta ID da conta.
     * @return Lista de extratos.
     */
    @Override
    public List<ExtratoDTO> buscaExtratoPorConta(Long idConta) {

        List<Extrato> extratos = extratoRepositorio.buscarExtratosPorConta(idConta);
        return extratoMapper.toDto(extratos);

    }

    /**
     * Salva um novo extrato.
     *
     * @param conta Conta que gerou o extrato.
     * @param valor Valor do extrato
     * @param tipo Tipo de operacao( Saque ou Deposito )
     */
    @Override
    public void salvarExtrato(Conta conta, Double valor, EnumOperacao tipo) {

        Extrato extrato = new Extrato();
        extrato.setContaExtrato(conta);
        extrato.setOperacao(tipo);
        extrato.setValor(valor);
        extratoRepositorio.save(extrato);

    }

}
