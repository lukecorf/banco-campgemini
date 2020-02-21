package com.capgemini.banco.servico.impl;

import com.capgemini.banco.dominio.Conta;
import com.capgemini.banco.dominio.Usuario;
import com.capgemini.banco.dominio.enumeration.EnumOperacao;
import com.capgemini.banco.repositorio.ContaRepositorio;
import com.capgemini.banco.servico.ContaServico;
import com.capgemini.banco.servico.ExtratoServico;
import com.capgemini.banco.servico.dto.ContaDTO;
import com.capgemini.banco.servico.mapper.ContaMapper;
import com.capgemini.banco.util.MensagensUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ContaServicoImpl implements ContaServico {

    private final ContaRepositorio contaRepositorio;

    private final ContaMapper contaMapper;

    private final ExtratoServico extratoServico;

    /**
     * Busca a conta pelo ID do usuario.
     *
     * @param id ID do usuario
     * @return A conta
     */
    @Override
    public ContaDTO buscarPorIdUsuario(Long id) {

        Conta conta = contaRepositorio.buscarPorIdUsuario(id).orElseThrow(()-> new RuntimeException(MensagensUtil.ERRO_REGISTRO_NAO_ENCONTRADO));
        return contaMapper.toDto(conta);

    }

    /**
     * Deposita uma determinada quantia em uma determinada conta.
     *
     * @param id ID da conta
     * @param valor Valor depositado.
     * @return Saldo da conta.
     */
    @Override
    public Double depositar(Long id, Double valor) {

        Conta conta = contaRepositorio.findById(id).orElseThrow(()-> new RuntimeException(MensagensUtil.ERRO_REGISTRO_NAO_ENCONTRADO));
        conta.setSaldo(conta.getSaldo()+valor);
        conta = contaRepositorio.save(conta);
        extratoServico.salvarExtrato(conta,valor, EnumOperacao.DEPOSITO);
        return conta.getSaldo();

    }

    /**
     * Saca uma determinada quantia em uma determinada conta.
     *
     * @param id ID da conta
     * @param valor Valor sacado.
     * @return Saldo da conta.
     */
    @Override
    public Double sacar(Long id, Double valor) {

        Conta conta = contaRepositorio.findById(id).orElseThrow(()-> new RuntimeException(MensagensUtil.ERRO_REGISTRO_NAO_ENCONTRADO));

        if(conta.getSaldo() < valor){
            throw new RuntimeException(MensagensUtil.ERRO_SALDO_INSUFICIENTE);
        }

        conta.setSaldo(conta.getSaldo()-valor);
        conta = contaRepositorio.save(conta);
        extratoServico.salvarExtrato(conta,valor, EnumOperacao.SAQUE);
        return conta.getSaldo();

    }

    /**
     * Cria uma conta a partir de um usuario.
     *
     * @param usuario Usuario que tera a conta vinculada
     */
    @Override
    public void criarContaParaUsuario(Usuario usuario) {

        Conta conta = new Conta();
        conta.setProprietario(usuario);
        conta.setSaldo(0.0);
        contaRepositorio.save(conta);

    }
}
