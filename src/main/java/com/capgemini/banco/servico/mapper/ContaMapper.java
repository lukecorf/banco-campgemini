package com.capgemini.banco.servico.mapper;

import com.capgemini.banco.dominio.Conta;
import com.capgemini.banco.servico.dto.ContaDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper de conversao de DTO/Entidade do conta.
 *
 * Responsavel por mapear e converter  um objeto ao outro.
 */
@Component
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface ContaMapper extends EntityMapper<ContaDTO, Conta> {
}
