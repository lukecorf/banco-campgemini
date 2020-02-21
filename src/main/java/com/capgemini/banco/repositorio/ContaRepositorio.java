package com.capgemini.banco.repositorio;

import com.capgemini.banco.dominio.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio de manipulacao da JPA referente a entidade de Conta.
 */
@Repository
public interface ContaRepositorio extends JpaRepository<Conta, Long> {

    /**
     * Busca a conta pelo ID do usuario.
     *
     * @param idUsuario ID do usuario.
     * @return A conta.
     */
    @Query("SELECT conta FROM Conta conta WHERE conta.proprietario.id = :idUsuario")
    Optional<Conta> buscarPorIdUsuario(@Param("idUsuario") Long idUsuario);

}
