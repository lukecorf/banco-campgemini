package com.capgemini.banco.repositorio;

import com.capgemini.banco.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de manipulacao da JPA referente a entidade de Usuario.
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>  {
}
