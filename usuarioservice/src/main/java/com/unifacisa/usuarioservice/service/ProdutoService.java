package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Produto;
import com.unifacisa.usuarioservice.repository.ProdutoRepository;
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

import java.util.List;

import static com.unifacisa.usuarioservice.utils.ConstantsUtils.API_URL_PRODUTOS;

@Service
@RequiredArgsConstructor
public class ProdutoService implements CrudUtils<Produto> {

    private final WebClient webClient;

    private final ProdutoRepository produtoRepository;

    @Override
    public List<Produto> listar() {
        Flux<Produto> produtosExternoFlux = webClient.get()
                .uri(API_URL_PRODUTOS)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, this::handler5xxServerError)
                .bodyToFlux(Produto.class);

        List<Produto> todosProdutos = produtosExternoFlux.collectList().block();
        return todosProdutos;
    }

    private Mono<? extends Throwable> handler5xxServerError(ClientResponse clientResponse) {
        return Mono.error(new CustomServerErrorException("Erro do server"));
    }

    @Override
    public Produto salvar(Produto produto) {
        Produto novoProduto = this.produtoRepository.saveAndFlush(produto);
        return webClient.post()
                .uri(API_URL_PRODUTOS)
                .body(Mono.just(novoProduto), Produto.class)
                .retrieve()
                .bodyToMono(Produto.class)
                .block();
    }

    @Override
    public Produto buscar(Long e) {
        return null;
    }

    @Override
    public Produto editar(Produto produto) {
        return null;
    }
}