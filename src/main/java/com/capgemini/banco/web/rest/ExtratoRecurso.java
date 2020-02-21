package com.capgemini.banco.web.rest;

import com.capgemini.banco.servico.ExtratoServico;
import com.capgemini.banco.servico.dto.ExtratoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extrato")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ExtratoRecurso {

    private final ExtratoServico extratoServico;

    /**
     * GET /api/extrato/{idConta}/por-conta : Busca a lista de extratos de uma determinada conta.
     *
     * @param idConta ID da conta
     * @return Lista com os extratos.
     */
    @GetMapping("/{idConta}/por-conta")
    public ResponseEntity<List<ExtratoDTO>> buscarExtratoPorConta(@PathVariable Long idConta ) {

        List<ExtratoDTO> resultado = extratoServico.buscaExtratoPorConta(idConta);
        return ResponseEntity.ok(resultado);

    }

}
