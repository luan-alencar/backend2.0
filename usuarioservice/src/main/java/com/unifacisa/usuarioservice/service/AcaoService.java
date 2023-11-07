package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Acao;
import com.unifacisa.usuarioservice.repository.AcaoRepository;
import com.unifacisa.usuarioservice.service.dto.AcaoDTO;
import com.unifacisa.usuarioservice.service.mapper.AcaoMapper;
import com.unifacisa.usuarioservice.utils.CrudUtils;
import com.unifacisa.usuarioservice.utils.exceptions.CustomServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcaoService implements CrudUtils<Acao, AcaoDTO> {

    private final WebClient webClient;
    private final AcaoRepository acaoRepository;

    @Override
    public List<AcaoDTO> listar() {
        return acaoRepository.findAll()
                .stream()
                .map(AcaoMapper.INSTANTE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AcaoDTO salvar(Acao acao) {
        Acao novoAcao = acaoRepository.save(acao);
        return AcaoMapper.INSTANTE.toDto(novoAcao);
    }

    @Override
    public AcaoDTO buscar(Long id) {
        return AcaoMapper.INSTANTE.toDto(acaoRepository.getById(id));
    }

    @Override
    public AcaoDTO editar(Acao acao) {
        if (acaoRepository.existsById(acao.getId())) {
            acao.setId(acao.getId());
            return this.salvar(acao);
        } else {
            throw new CustomServerErrorException("Ação não encontrado para o ID: " + acao.getId());
        }
    }

    @Override
    public void deletar(Long id) {
        this.acaoRepository.deleteById(id);
    }

    private Mono<? extends Throwable> handler5xxServerError(ClientResponse clientResponse) {
        return Mono.error(new CustomServerErrorException("Erro do server"));
    }

}