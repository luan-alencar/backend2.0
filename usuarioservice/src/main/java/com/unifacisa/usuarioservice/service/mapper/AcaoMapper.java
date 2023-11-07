package com.unifacisa.usuarioservice.service.mapper;

import com.unifacisa.usuarioservice.domain.Acao;
import com.unifacisa.usuarioservice.service.dto.AcaoDTO;
import com.unifacisa.usuarioservice.utils.mapper.EntityMapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AcaoMapper extends EntityMapperUtils<AcaoDTO, Acao> {

    AcaoMapper INSTANTE = Mappers.getMapper(AcaoMapper.class);

    @Override
    AcaoDTO toDto(Acao entity);

    @Override
    Acao toEntity(AcaoDTO dto);

}

