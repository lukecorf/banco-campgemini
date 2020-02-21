package com.capgemini.banco.web.rest;

import com.capgemini.banco.builder.ContaBuilder;
import com.capgemini.banco.dominio.Conta;
import com.capgemini.banco.servico.dto.ContaDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ContaRecursoIntTest extends BaseIntUtil {

    @Autowired
    public ContaBuilder contaBuilder;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private final String URL = "/api/conta";

    @Test
    public void buscarPorId() throws Exception {

        Conta conta = contaBuilder.construirESalvaConta();

        ContaDTO result = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(get(URL+"/"+conta.getProprietario().getId()+"/por-usuario"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), ContaDTO.class);

        Assert.assertEquals(conta.getSaldo(), result.getSaldo());
        Assert.assertEquals(conta.getId(), result.getId());
        Assert.assertEquals(conta.getProprietario().getId(), result.getProprietario().getId());

    }

    @Test
    public void depositar() throws Exception {

        Conta conta = contaBuilder.construirESalvaConta();
        Double valor = conta.getSaldo()+200.0;
        Double result = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(put(URL+"/"+conta.getId()+"/depositar").contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(200.0)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Double.class);

        Assert.assertEquals(valor , result);

    }

    @Test
    public void sacar() throws Exception {

        Conta conta = contaBuilder.construirESalvaConta();
        Double valor = conta.getSaldo()-50.0;
        Double result = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(put(URL+"/"+conta.getId()+"/sacar").contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(50.0)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), Double.class);

        Assert.assertEquals(valor , result);

    }


}
