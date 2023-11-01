package com.unifacisa.usuarioservice.service.mapper;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.service.dto.ProdutoDTO;
import com.unifacisa.usuarioservice.utils.mapper.EntityMapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdutoMapper extends EntityMapperUtils<ProdutoDTO, Produto> {

    ProdutoMapper INSTANTE = Mappers.getMapper(ProdutoMapper.class);

    @Override
    ProdutoDTO toDto(Produto entity);

    @Override
    Produto toEntity(ProdutoDTO dto);

}

