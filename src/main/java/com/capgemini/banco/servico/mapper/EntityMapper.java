package com.capgemini.banco.servico.mapper;

import java.util.List;

/**
 * Interface generica do mapper de dto para entity.
 *
 * @param <D> - Parametro DTO .
 * @param <E> - Parametro Entidade .
 */
public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);
}
