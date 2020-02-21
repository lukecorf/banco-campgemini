package com.capgemini.banco.web.rest;

import com.capgemini.banco.servico.UsuarioServico;
import com.capgemini.banco.servico.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioRecurso {

    private final UsuarioServico usuarioServico;

    /**
     * POST /api/usuario : Insere um novo Usuario no sistema
     *
     * @param usuarioDTO Usuario a ser inserido
     * @return O usuario inserido com seu ID
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> inserirUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO resultado = usuarioServico.inserir(usuarioDTO);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    /**
     * PUT /api/usuario : Atualiza um Usuario do sistema
     *
     * @param usuarioDTO Usuario a ser atualizado
     * @return O usuario atualizado
     */
    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO resultado = usuarioServico.persistir(usuarioDTO);
        return ResponseEntity.ok(resultado);
    }

    /**
     * GET /api/usuario : Lista os usuarios do sistema
     *
     * @return A lista de usuarios cadastrados.
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> resultado = usuarioServico.listar(new UsuarioDTO());
        return ResponseEntity.ok(resultado);
    }

    /**
     * PUT /api/usuario/{id} : Busca um Usuario do sistema pelo seu ID
     *
     * @param id ID do Usuario a ser consultado
     * @return O usuario consultado
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable Long id) {
        UsuarioDTO resultado = usuarioServico.buscarPorId(id);
        return ResponseEntity.ok(resultado);
    }


}
