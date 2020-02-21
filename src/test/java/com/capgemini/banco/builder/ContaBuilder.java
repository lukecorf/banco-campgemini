package com.capgemini.banco.builder;

import com.capgemini.banco.dominio.Conta;
import com.capgemini.banco.repositorio.ContaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContaBuilder {

    @Autowired
    private ContaRepositorio contaRepositorio;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    public Conta construirEntidade () {
        Conta conta = new Conta();
        conta.setSaldo(100.0);
        conta.setProprietario(usuarioBuilder.constroiESalvaUsuario());
        return conta;
    }

    public Conta construirESalvaConta () {
        return contaRepositorio.save(construirEntidade());
    }
}
