package com.capgemini.banco.web.rest;

import com.capgemini.banco.servico.ContaServico;
import com.capgemini.banco.servico.dto.ContaDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conta")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ContaRecurso {

    private final ContaServico contaServico;

    /**
     * GET /api/conta/{idUsuario}/por-usuario : Busca a conta pelo ID do usuario.
     *
     * @param idUsuario ID do usuario
     * @return A conta
     */
    @GetMapping("/{idUsuario}/por-usuario")
    public ResponseEntity<ContaDTO> buscarPorIdUsuario(@PathVariable Long idUsuario) {

        ContaDTO conta = contaServico.buscarPorIdUsuario(idUsuario);
        return ResponseEntity.ok(conta);

    }

    /**
     * PUT /api/conta/id}/depositar : Deposita uma determinada quantia em uma determinada conta.
     *
     * @param id ID da conta
     * @param valor Valor depositado.
     * @return Saldo da conta.
     */
    @PutMapping("/{id}/depositar")
    public ResponseEntity<Double> depositar(@PathVariable Long id, @RequestBody Double valor) {

        Double resultado = contaServico.depositar(id,valor);
        return ResponseEntity.ok(resultado);

    }

    /**
     * PUT /api/conta/id}/sacar : Saca uma determinada quantia em uma determinada conta.
     *
     * @param id ID da conta
     * @param valor Valor sacado.
     * @return Saldo da conta.
     */
    @PutMapping("/{id}/sacar")
    public ResponseEntity<Double> sacar(@PathVariable Long id, @RequestBody Double valor) {

        Double resultado = contaServico.sacar(id,valor);
        return ResponseEntity.ok(resultado);

    }


}
