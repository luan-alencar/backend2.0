package com.unifacisa.usuarioservice.utils.mapper;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.service.ProdutoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProdutoMapper extends EntityMapperUtils<Produto, ProdutoDTO> {

    @Override
    Produto toEntity(ProdutoDTO dto);

    @Override
    ProdutoDTO toDTO(Produto entity);

    @Override
    List<ProdutoDTO> toDTOList(List<Produto> entityList);

    @Override
    List<Produto> toEntityList(List<ProdutoDTO> dtoList);
}
