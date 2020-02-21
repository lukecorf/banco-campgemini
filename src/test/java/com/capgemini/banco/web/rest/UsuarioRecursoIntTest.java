package com.capgemini.banco.web.rest;

import com.capgemini.banco.builder.UsuarioBuilder;
import com.capgemini.banco.dominio.Usuario;
import com.capgemini.banco.servico.dto.UsuarioDTO;
import com.capgemini.banco.util.BaseIntUtil;
import com.capgemini.banco.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UsuarioRecursoIntTest extends BaseIntUtil {

    @Autowired
    public UsuarioBuilder usuarioBuilder;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private final String URL = "/api/usuario";

    @Test
    public void salvar() throws Exception {
        UsuarioDTO usuario = usuarioBuilder.construirDTO();

        Long usuariosAntes = usuarioBuilder.contaUsuarios();
        usuariosAntes++;

        UsuarioDTO result = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuario)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);
        Long usuariosDepois = usuarioBuilder.contaUsuarios();

        Assert.assertNotNull(result.getId());
        Assert.assertEquals(usuariosAntes, usuariosDepois);
    }

    @Test
    public void buscarPorId() throws Exception {
        UsuarioDTO usuario = usuarioBuilder.construirDTO();

        UsuarioDTO result = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuario)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);

        UsuarioDTO result2 = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(get(URL+"/"+result.getId()))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);


        Assert.assertEquals(result.getSexo(),result2.getSexo());
        Assert.assertEquals(result.getId(),result2.getId());
        Assert.assertEquals(result.getNome(),result2.getNome());
        Assert.assertEquals(result.getCpf(),result2.getCpf());
        Assert.assertEquals(result.getIdade(),result2.getIdade());
    }

    @Test
    public void atualizar() throws Exception {
        UsuarioDTO usuarioDTO = usuarioBuilder.construirDTO();

        UsuarioDTO result = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioDTO)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);
        Long usuariosAntes = usuarioBuilder.contaUsuarios();

        result.setIdade(29);

        result = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(put(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(result)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);

        Long usuariosDepois = usuarioBuilder.contaUsuarios();

        Usuario usuario = usuarioBuilder.buscaPorId(result.getId());

        Assert.assertEquals(usuariosAntes, usuariosDepois);
        Assert.assertEquals(usuario.getCpf(),result.getCpf());
        Assert.assertEquals(usuario.getIdade(),result.getIdade());
        Assert.assertEquals(usuario.getNome(),result.getNome());
        Assert.assertEquals(usuario.getSexo(),result.getSexo());

    }

    @Test
    public void listarTodos() throws Exception {
        UsuarioDTO usuarioDTO = usuarioBuilder.construirDTO();

        UsuarioDTO result = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(usuarioDTO)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), UsuarioDTO.class);

        List<UsuarioDTO> usuarios = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(get(URL))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), jacksonMessageConverter.getObjectMapper().getTypeFactory().constructCollectionType(List.class, UsuarioDTO.class));

        Assert.assertEquals(usuarios.size(),1);
        Assert.assertEquals(usuarios.get(0).getIdade(),result.getIdade());
        Assert.assertEquals(usuarios.get(0).getCpf(),result.getCpf());
        Assert.assertEquals(usuarios.get(0).getNome(),result.getNome());
        Assert.assertEquals(usuarios.get(0).getSexo(),result.getSexo());
        Assert.assertEquals(usuarios.get(0).getId(),result.getId());

    }

}

