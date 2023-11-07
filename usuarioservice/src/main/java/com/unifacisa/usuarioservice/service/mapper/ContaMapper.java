package com.unifacisa.usuarioservice.service.mapper;

import com.unifacisa.usuarioservice.domain.Conta;
import com.unifacisa.usuarioservice.service.dto.ContaDTO;
import com.unifacisa.usuarioservice.utils.mapper.EntityMapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContaMapper extends EntityMapperUtils<ContaDTO, Conta> {

    ContaMapper INSTANTE = Mappers.getMapper(ContaMapper.class);

    @Override
    ContaDTO toDto(Conta entity);

    @Override
    Conta toEntity(ContaDTO dto);

}

