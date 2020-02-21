package com.capgemini.banco.web.rest;

import com.capgemini.banco.builder.ExtratoBuilder;
import com.capgemini.banco.dominio.Extrato;
import com.capgemini.banco.servico.dto.ExtratoDTO;
import com.capgemini.banco.util.BaseIntUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ExtratoRecursoIntTest extends BaseIntUtil {

    @Autowired
    public ExtratoBuilder extratoBuilder;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private final String URL = "/api/extrato";


    @Test
    public void listarExtratoPorConta() throws Exception{

        Extrato extrato = extratoBuilder.construirESalvaExtrato();
        List<ExtratoDTO> extratoDTOS = jacksonMessageConverter.getObjectMapper().readValue(getMockMvc().perform(get(URL+"/"+extrato.getContaExtrato().getId()+"/por-conta"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), jacksonMessageConverter.getObjectMapper().getTypeFactory().constructCollectionType(List.class, ExtratoDTO.class));

        Assert.assertEquals(extratoDTOS.size(),1);
        Assert.assertEquals(extratoDTOS.get(0).getId(),extrato.getId());
        Assert.assertEquals(extratoDTOS.get(0).getContaExtrato().getId(),extrato.getContaExtrato().getId());
        Assert.assertEquals(extratoDTOS.get(0).getValor(),extrato.getValor());


    }
}
