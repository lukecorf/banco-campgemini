package com.capgemini.banco.servico;

import com.capgemini.banco.servico.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioServico {

    /**
     * Insere um novo Usuario no sistema
     *
     * @param usuarioDTO Usuario a ser inserido
     * @return O usuario inserido com seu ID
     */
    UsuarioDTO inserir(UsuarioDTO usuarioDTO);

    /**
     * Persiste um Usuario do sistema
     *
     * @param usuarioDTO Usuario a ser persistido
     * @return O usuario persistido
     */
    UsuarioDTO persistir(UsuarioDTO usuarioDTO);

    /**
     * Lista os usuarios do sistema
     *
     * @return A lista de usuarios cadastrados.
     */
    List<UsuarioDTO> listar(UsuarioDTO usuarioDTO);

    /**
     * Busca um Usuario do sistema pelo seu ID
     *
     * @param id ID do Usuario a ser consultado
     * @return O usuario consultado
     */
    UsuarioDTO buscarPorId(Long id);

}
