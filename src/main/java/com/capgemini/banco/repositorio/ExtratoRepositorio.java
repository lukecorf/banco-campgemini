package com.capgemini.banco.repositorio;

import com.capgemini.banco.dominio.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de manipulacao da JPA referente a entidade de Extrato.
 */
@Repository
public interface ExtratoRepositorio extends JpaRepository<Extrato, Long> {

    /**
     * Busca os Extratos de uma conta espcifica.
     *
     * @param idConta ID da conta utilizado para a filtragem.
     * @return Lista de extratos daquela conta.
     */
    @Query("SELECT extrato FROM Extrato extrato WHERE extrato.contaExtrato.id = :idConta")
    List<Extrato> buscarExtratosPorConta(@Param("idConta") Long idConta);

}
