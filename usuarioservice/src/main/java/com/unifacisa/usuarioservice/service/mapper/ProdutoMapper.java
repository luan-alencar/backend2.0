package com.unifacisa.usuarioservice.service.mapper;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.service.dto.ProdutoDTO;
import com.unifacisa.usuarioservice.utils.mapper.EntityMapperUtils;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper extends EntityMapperUtils<ProdutoDTO, Produto> {

    @Override
    Produto toEntity(ProdutoDTO dto);

    @Override
    ProdutoDTO toDto(Produto entity);

}

