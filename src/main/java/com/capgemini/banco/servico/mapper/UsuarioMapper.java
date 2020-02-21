package com.capgemini.banco.servico.mapper;

import com.capgemini.banco.dominio.Usuario;
import com.capgemini.banco.servico.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper de conversao de DTO/Entidade do usuario.
 *
 * Responsavel por mapear e converter  um objeto ao outro.
 */
@Component
@Mapper(componentModel = "spring", uses = {})
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario> {
}
