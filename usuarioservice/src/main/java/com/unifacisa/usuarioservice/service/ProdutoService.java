package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.repository.ProdutoRepository;
import com.unifacisa.usuarioservice.service.dto.ProdutoDTO;
import com.unifacisa.usuarioservice.service.mapper.ProdutoMapper;
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
public class ProdutoService implements CrudUtils<Produto, ProdutoDTO> {

    private final WebClient webClient;
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
        return ProdutoMapper.INSTANTE.toDto(produtoRepository.save(produto));
    }

    @Override
    public ProdutoDTO buscar(Long e) {
        return ProdutoMapper.INSTANTE.toDto(produtoRepository.getById(e));
    }

    @Override
    public ProdutoDTO editar(Produto produto) {
        Produto novoProduto = ProdutoMapper.INSTANTE.toEntity(this.buscar(produto.getId()));
        return this.salvar(novoProduto);
    }

    private Mono<? extends Throwable> handler5xxServerError(ClientResponse clientResponse) {
        return Mono.error(new CustomServerErrorException("Erro do server"));
    }

}