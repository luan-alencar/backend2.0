package com.unifacisa.usuarioservice.service.mapper;

import com.unifacisa.usuarioservice.domain.Usuario;
import com.unifacisa.usuarioservice.service.dto.UsuarioDTO;
import com.unifacisa.usuarioservice.utils.mapper.EntityMapperUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapperUtils<UsuarioDTO, Usuario> {

    UsuarioMapper INSTANTE = Mappers.getMapper(UsuarioMapper.class);

    @Override
    @Mapping(target = "conta", source = "conta.id")
    UsuarioDTO toDto(Usuario entity);

    @Override
    @Mapping(target = "conta.id", source = "conta")
    Usuario toEntity(UsuarioDTO dto);
}
