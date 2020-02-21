package com.capgemini.banco.servico.impl;

import com.capgemini.banco.dominio.Usuario;
import com.capgemini.banco.exception.CustomException;
import com.capgemini.banco.repositorio.UsuarioRepositorio;
import com.capgemini.banco.servico.ContaServico;
import com.capgemini.banco.servico.UsuarioServico;
import com.capgemini.banco.servico.dto.UsuarioDTO;
import com.capgemini.banco.servico.mapper.UsuarioMapper;
import com.capgemini.banco.util.MensagensUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * Implementacao das funcoes listadas na Interface de servicos do Usuario.
 */
@Service
@Transactional
@AllArgsConstructor
public class UsuarioServicoImpl implements UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;

    private final UsuarioMapper usuarioMapper;

    private final ContaServico contaServico;

    /**
     * POST /api/usuario : Insere um novo Usuario no sistema. O novo usuario nao pode possuir ID, caso contrario sera disparado uma exception
     *
     * @param usuarioDTO Usuario a ser inserido
     * @return O usuario inserido com seu ID
     */
    @Override
    public UsuarioDTO inserir(UsuarioDTO usuarioDTO) {

        if(Objects.nonNull(usuarioDTO.getId())){
            throw new CustomException(MensagensUtil.ERRO_ENTIDADE_COM_ID);
        }

        UsuarioDTO resultado = persistir(usuarioDTO);
        contaServico.criarContaParaUsuario(usuarioMapper.toEntity(resultado));
        return resultado;

    }

    /**
     *  Persiste um Usuario do sistema
     *
     * @param usuarioDTO Usuario a ser persistido
     * @return O usuario persistido
     */
    @Override
    public UsuarioDTO persistir(UsuarioDTO usuarioDTO) {

        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);

    }

    /**
     * Lista os usuarios do sistema
     *
     * @return A lista de usuarios cadastrados.
     */
    @Override
    public List<UsuarioDTO> listar(UsuarioDTO usuarioDTO) {
        return usuarioMapper.toDto(usuarioRepositorio.findAll());
    }

    /**
     * Busca um Usuario do sistema pelo seu ID
     *
     * @param id ID do Usuario a ser consultado
     * @return O usuario consultado
     */
    public UsuarioDTO buscarPorId(Long id){
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(()->new CustomException(MensagensUtil.ERRO_REGISTRO_NAO_ENCONTRADO));
        return usuarioMapper.toDto(usuario);
    }
}
