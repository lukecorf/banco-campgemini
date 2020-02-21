package com.capgemini.banco.builder;

import com.capgemini.banco.dominio.Extrato;
import com.capgemini.banco.dominio.enumeration.EnumOperacao;
import com.capgemini.banco.repositorio.ExtratoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ExtratoBuilder {

    @Autowired
    private ExtratoRepositorio extratoRepositorio;

    @Autowired
    private ContaBuilder contaBuilder;

    public Extrato construirEntidade () {
        Extrato extrato = new Extrato();
        extrato.setValor(100.0);
        extrato.setOperacao(EnumOperacao.DEPOSITO);
        extrato.setContaExtrato(contaBuilder.construirESalvaConta());
        return extrato;
    }

    public Extrato construirESalvaExtrato() {
        return extratoRepositorio.save(construirEntidade());
    }
}
