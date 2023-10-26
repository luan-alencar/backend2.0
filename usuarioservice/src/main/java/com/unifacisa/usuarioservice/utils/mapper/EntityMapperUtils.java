package com.unifacisa.usuarioservice.utils.mapper;

import java.util.List;

public interface EntityMapperUtils<E, D> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<D> toDTOList(List<E> entityList);

    List<E> toEntityList(List<D> dtoList);
}
