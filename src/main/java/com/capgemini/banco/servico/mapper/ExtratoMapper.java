package com.capgemini.banco.servico.mapper;

import com.capgemini.banco.dominio.Extrato;
import com.capgemini.banco.servico.dto.ExtratoDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper de conversao de DTO/Entidade do Extrato.
 *
 * Responsavel por mapear e converter  um objeto ao outro.
 */
@Component
@Mapper(componentModel = "spring", uses = {ContaMapper.class})
public interface ExtratoMapper extends EntityMapper<ExtratoDTO, Extrato> {
}
