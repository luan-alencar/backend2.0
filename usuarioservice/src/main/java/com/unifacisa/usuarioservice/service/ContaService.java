package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Conta;
import com.unifacisa.usuarioservice.repository.ContaRepository;
import com.unifacisa.usuarioservice.service.dto.ContaDTO;
import com.unifacisa.usuarioservice.service.mapper.ContaMapper;
import com.unifacisa.usuarioservice.utils.CrudUtils;
import com.unifacisa.usuarioservice.utils.exceptions.CustomServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaService implements CrudUtils<Conta, ContaDTO> {

    private final ContaRepository contaRepository;

    @Override
    public List<ContaDTO> listar() {
        return contaRepository.findAll()
                .stream()
                .map(com.unifacisa.usuarioservice.service.mapper.ContaMapper.INSTANTE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContaDTO salvar(Conta conta) {
        Conta novoConta = contaRepository.save(conta);
        return com.unifacisa.usuarioservice.service.mapper.ContaMapper.INSTANTE.toDto(novoConta);
    }

    @Override
    public ContaDTO buscar(Long id) {
        return ContaMapper.INSTANTE.toDto(contaRepository.getById(id));
    }

    @Override
    public void deletar(Long id) {
        this.contaRepository.deleteById(id);
    }

}