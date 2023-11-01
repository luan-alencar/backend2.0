package com.unifacisa.usuarioservice.utils.mapper;

public interface EntityMapperUtils<D, E> {

    D toDto(E entity);

    E toEntity(D dto);

}
