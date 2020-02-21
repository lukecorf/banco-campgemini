package com.capgemini.banco.servico;

import com.capgemini.banco.dominio.Usuario;
import com.capgemini.banco.servico.dto.ContaDTO;

public interface ContaServico {

    /**
     * Busca a conta pelo ID do usuario.
     *
     * @param id ID do usuario
     * @return A conta
     */
    ContaDTO buscarPorIdUsuario(Long id);

    /**
     * Deposita uma determinada quantia em uma determinada conta.
     *
     * @param id ID da conta
     * @param valor Valor depositado.
     * @return Saldo da conta.
     */
    Double depositar(Long id, Double valor);

    /**
     * Saca uma determinada quantia em uma determinada conta.
     *
     * @param id ID da conta
     * @param valor Valor sacado.
     * @return Saldo da conta.
     */
    Double sacar(Long id, Double valor);

    /**
     * Cria uma conta a partir de um usuario.
     *
     * @param usuario Usuario que tera a conta vinculada
     */
    void criarContaParaUsuario(Usuario usuario);

}
