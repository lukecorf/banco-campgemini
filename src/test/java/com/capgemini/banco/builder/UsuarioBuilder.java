package com.capgemini.banco.builder;

import com.capgemini.banco.dominio.Usuario;
import com.capgemini.banco.dominio.enumeration.EnumSexo;
import com.capgemini.banco.repositorio.UsuarioRepositorio;
import com.capgemini.banco.servico.dto.UsuarioDTO;
import com.capgemini.banco.servico.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioBuilder {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario construirEntidade () {
        Usuario usuario = new Usuario();
        usuario.setIdade(20);
        usuario.setCpf("10010010010");
        usuario.setNome("Nome");
        usuario.setSexo(EnumSexo.MASCULINO);
        return usuario;
    }

    public UsuarioDTO construirDTO(){
        return usuarioMapper.toDto(construirEntidade());
    }

    public Long contaUsuarios(){
        return usuarioRepositorio.count();
    }

    public Usuario buscaPorId(Long id){
        return usuarioRepositorio.getOne(id);
    }

    public Usuario constroiESalvaUsuario(){
        return usuarioRepositorio.save(construirEntidade());
    }

}
