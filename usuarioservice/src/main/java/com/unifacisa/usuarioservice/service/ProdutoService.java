package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.repository.ProdutoRepository;
import com.unifacisa.usuarioservice.service.dto.ProdutoDTO;
import com.unifacisa.usuarioservice.service.mapper.ProdutoMapper;
import com.unifacisa.usuarioservice.utils.CrudUtils;
import com.unifacisa.usuarioservice.utils.exceptions.CustomServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.unifacisa.usuarioservice.utils.ConstantsUtils.API_URL_PRODUTOS;

@Service
@RequiredArgsConstructor
public class ProdutoService implements CrudUtils<Produto, ProdutoDTO> {

    private final WebClient webClient;
    private final ProdutoMapper produtoMapper;
    private final ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDTO> listar() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoMapper.INSTANTE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO salvar(Produto produto) {
        return null;
    }

    @Override
    public ProdutoDTO buscar(Long e) {
        return null;
    }

    @Override
    public ProdutoDTO editar(Produto produto) {
        return null;
    }

    private Mono<? extends Throwable> handler5xxServerError(ClientResponse clientResponse) {
        return Mono.error(new CustomServerErrorException("Erro do server"));
    }


}